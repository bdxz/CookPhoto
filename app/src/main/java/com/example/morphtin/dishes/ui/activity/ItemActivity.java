package com.example.morphtin.dishes.ui.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.api.presenter.ICookItemPresenter;
import com.example.morphtin.dishes.api.presenter.impl.CookItemPresenterImpl;
import com.example.morphtin.dishes.api.view.ICookItemView;

import java.util.ArrayList;
import java.util.List;

public class ItemActivity extends AppCompatActivity implements ICookItemView{
    private static final int ADD_COOK_ITEM = 1;

    private RecyclerView ItemRecyclerView;
    private ItemAdapter adapter;
    private ArrayList<String> cookList= new ArrayList<String>();

    private ICookItemPresenter presenter;

    // adding the toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    // handling tool bar event
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:// 点击返回图标事件
                this.finish();
            case R.id.check_ok:
                upload();
            default:
                return super.onOptionsItemSelected(item);
        }
    } //onOptionsItemsSelected

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        getSupportActionBar().setTitle("已选食材");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        Intent intent = getIntent();
//        cookList = intent.getParcelableExtra(EXTRA_MESSAGE);

        ItemRecyclerView = (RecyclerView) findViewById(R.id.item_recycler_view);
        ItemRecyclerView.setLayoutManager (new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        InitCookList();

        adapter = new ItemAdapter(cookList);
        ItemRecyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(ItemRecyclerView); //set swipe to recylcerview

        presenter = new CookItemPresenterImpl(this);
    }



    public void InitCookList(){

    }

    public void upload(){
        presenter.uploadCookItem(cookList);
    }

    private class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private String ItemName;
        private TextView mTv;
        private ImageView imageView;

        public ItemHolder(View itemView) {
            super(itemView);
            // super(inflater.inflate(R.layout.item_item,,false));
            itemView.setOnClickListener(this);

            mTv = itemView.findViewById(R.id.cook_item_title);
            imageView = itemView.findViewById(R.id.cook_item_photo);
        }

        public void bind(String name){
            ItemName = name;
            mTv.setText(name);
            imageView.setImageResource(R.drawable.vegetable);

        }


        @Override
        public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ItemActivity.this);//MainActivity.this); //alert for confirm
                builder.setMessage("++++++"); //set message
                builder.show();

        }

    }

    public void addCookItem(View view) {
        Intent intent = new Intent(getApplicationContext(),ChooseMaterialActivity.class);
        startActivityForResult(intent, ADD_COOK_ITEM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == ADD_COOK_ITEM){
            ArrayList<String> list = data.getStringArrayListExtra("ADDCOOKLIST");
            cookList.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

        private ArrayList<String> mData;

        public ItemAdapter(ArrayList<String> names) {
            mData = names;
        }

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cook_item, parent, false);
            return new ItemHolder(v);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            String name = mData.get(position);
            holder.bind(name);
        }


        @Override
        public int getItemCount() {
            return mData == null ? 0 : mData.size();
        }


    }




    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }
        @Override
        public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition(); //get position which is swipe
            if (direction == ItemTouchHelper.LEFT) { //if swipe left
                AlertDialog.Builder builder = new AlertDialog.Builder(ItemActivity.this);//MainActivity.this); //alert for confirm
                builder.setMessage("确认删除?"); //set message
                builder.setPositiveButton("是的", new DialogInterface.OnClickListener() { //when click on DELETE
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.notifyItemRemoved(position); //item removed from recylcerview
                        adapter.mData.remove(position);
                        return;
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() { //not removing items if cancel is done
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.notifyItemRemoved(position + 1); //notifies the RecyclerView Adapter that data in adapter has been removed at a particular position.
                        adapter.notifyItemRangeChanged(position, adapter.getItemCount()); //notifies the RecyclerView Adapter that positions of element in adapter has been changed from position(removed element index to end of list), please update it.
                        return;
                    }
                }).show(); //show alert dialog
            }
        }
    };


}
