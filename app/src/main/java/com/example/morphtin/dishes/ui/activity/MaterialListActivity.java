package com.example.morphtin.dishes.ui.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
import com.example.morphtin.dishes.api.contract.MaterialContract;
import com.example.morphtin.dishes.api.presenter.MaterialPresenter;
import com.example.morphtin.dishes.bean.MaterialBean;
import com.example.morphtin.dishes.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MaterialListActivity extends BaseActivity implements MaterialContract.View{
    private static final int ADD_COOK_ITEM = 1;

    private RecyclerView ItemRecyclerView;
    private ItemAdapter adapter;
    private ArrayList<MaterialBean> cookList= new ArrayList<MaterialBean>();

    private MaterialContract.Presenter presenter;

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
                matchMenus();
            default:
                return super.onOptionsItemSelected(item);
        }
    } //onOptionsItemsSelected

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_list);
        getSupportActionBar().setTitle("已选食材");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ItemRecyclerView = (RecyclerView) findViewById(R.id.item_recycler_view);
        ItemRecyclerView.setLayoutManager (new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new ItemAdapter(cookList);
        ItemRecyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(ItemRecyclerView); //set swipe to recylcerview

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {
        presenter = new MaterialPresenter(this);
        presenter.loadSelected();
    }

    public void matchMenus(){
        //TODO 匹配菜谱,参数为已选中的原材料列表
        List<MaterialBean> data = cookList;
        presenter.matchMenus(data);
    }

    public void unselect(MaterialBean data){
        //TODO 取消选中时调用,参数为要被取消选中的原材料
        presenter.unselect(data);
    }


    @Override
    public void showMaterials(List<MaterialBean> data) {
        //TODO 显示所有已选中的原材料
        setCookItemOntoView(data);

    }

    public void setCookItemOntoView(List<MaterialBean> data){
        cookList.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showAddUI() {
        Intent intent = new Intent(this,ChooseMaterialActivity.class);
        startActivity(intent);
    }

    @Override
    public void showMenusUI() {
        Intent intent = new Intent(this,MenuListActivity.class);
        startActivity(intent);
    }

    private class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView nameTv;
        private TextView catelogTv;
        private ImageView imageView;

        public ItemHolder(View itemView) {
            super(itemView);
            // super(inflater.inflate(R.layout.item_item,,false));
            itemView.setOnClickListener(this);
            catelogTv = itemView.findViewById(R.id.cook_item_detail);
            nameTv = itemView.findViewById(R.id.cook_item_title);
            imageView = itemView.findViewById(R.id.cook_item_photo);
        }

        public void bind(String name,String catelog){

            nameTv.setText(name);
            catelogTv.setText(catelog);
            imageView.setImageResource(R.drawable.vegetable);

        }


        @Override
        public void onClick(View view) {

            AlertDialog.Builder builder = new AlertDialog.Builder(MaterialListActivity.this);//MainActivity.this); //alert for confirm
            builder.setMessage("++++++"); //set message
            builder.show();

        }

    }

    public void addCookItem(View view) {
        Intent intent = new Intent(this,ChooseMaterialActivity.class);
        startActivityForResult(intent, ADD_COOK_ITEM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == ADD_COOK_ITEM){
            ArrayList<MaterialBean> list = data.getParcelableArrayListExtra("ADDCOOKLIST");
            cookList.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

        private ArrayList<MaterialBean> mData;

        public ItemAdapter(ArrayList<MaterialBean> beans) {
            mData = beans;
        }

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cook_item, parent, false);
            return new ItemHolder(v);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            String name = mData.get(position).getTitle();
            String catelog = mData.get(position).getCatelog();
            holder.bind(name,catelog);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(MaterialListActivity.this);//MainActivity.this); //alert for confirm
                builder.setMessage("确认删除?"); //set message
                builder.setPositiveButton("是的", new DialogInterface.OnClickListener() { //when click on DELETE
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        unselect(adapter.mData.get(position));
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
