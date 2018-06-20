package com.example.morphtin.dishes.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.api.contract.ChooseContract;
import com.example.morphtin.dishes.api.presenter.ChoosePresenter;
import com.example.morphtin.dishes.bean.MaterialBean;
import com.example.morphtin.dishes.ui.adapter.TagAdapter;
import com.example.morphtin.dishes.ui.adapter.TagHolder;
import com.example.morphtin.dishes.ui.adapter.TagsAdapter;
import com.example.morphtin.dishes.ui.base.BaseActivity;
import com.hhl.library.FlowTagLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ChooseMaterialActivity extends BaseActivity implements ChooseContract.View{
    private static final String TAG = "ChooseMaterialActivity";
    private ChooseContract.Presenter presenter;
    private HashMap<String,List<MaterialBean>> mTagsMap = new HashMap<>();

    private RecyclerView TagRecyclerView;
    private TagsAdapter tagsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_material);
        getSupportActionBar().setTitle("文字找菜");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        TagRecyclerView = (RecyclerView) findViewById(R.id.tag_recycler_view);
        TagRecyclerView.setLayoutManager (new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        tagsAdapter = new TagsAdapter(this,mTagsMap);
        TagRecyclerView.setAdapter(tagsAdapter);

    }

    @Override
    protected void initData(){
        presenter = new ChoosePresenter(this);
        presenter.loadAllMaterials();
    }

    @Override
    protected void initEvents() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void sendCookList(View v){
        select();
    }

    private void select(){
        //TODO 点击确认提交的方法，参数为MaterialBean的列表
        List<MaterialBean> materialBeanList = new ArrayList<>();
        for(TagHolder th: tagsAdapter.getHolderList()){
            materialBeanList.addAll(th.getSelectedMaterialBeanList());
        }
        presenter.select(materialBeanList);
    }

    @Override
    public void showMaterials(List<MaterialBean> data) {
        //TODO 显示所有原材料以及选中状态
        for (MaterialBean material:data) {
            Log.d(TAG, "showMaterials: "+material.getTitle());
            if(mTagsMap.containsKey(material.getCatelog())){
                mTagsMap.get(material.getCatelog()).add(material);
            }else{
                List<MaterialBean> list = new ArrayList<>();
                list.add(material);
                mTagsMap.put(material.getCatelog(),list);
            }
        }

        tagsAdapter.notifyDataSetChanged();

    }



    @Override
    public void showSelected(List<MaterialBean> data) {
        Intent intent = new Intent(this,MaterialListActivity.class);
        startActivity(intent);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void cancelLoading() {

    }
}
