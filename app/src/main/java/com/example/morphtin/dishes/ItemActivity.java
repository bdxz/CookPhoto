package com.example.morphtin.dishes;


import android.content.DialogInterface;


import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity {
    private RecyclerView ItemRecyclerView;
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);


//        Intent intent = getIntent();
//        company = intent.getParcelableExtra(DetailFragment.EXTRA_MESSAGE);

        ItemRecyclerView = (RecyclerView) findViewById(R.id.item_recycler_view);
        ItemRecyclerView.setLayoutManager (new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<String> cookList = new ArrayList<String>();
        cookList.add("土豆");
        cookList.add("屁股");

        adapter = new ItemAdapter(cookList);
        ItemRecyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(ItemRecyclerView); //set swipe to recylcerview

    }

    private class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private String ItemName;
        private TextView mTv;

        public ItemHolder(View itemView) {
            super(itemView);
            // super(inflater.inflate(R.layout.item_item,,false));
            itemView.setOnClickListener(this);

            mTv = itemView.findViewById(R.id.item_tv);
        }

        public void bind(String name){
            ItemName = name;
            mTv.setText(name);
        }


        @Override
        public void onClick(View view) {

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
