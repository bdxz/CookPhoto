package com.example.morphtin.dishes.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.MenuItem;
import android.view.View;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.api.contract.ChooseContract;
import com.example.morphtin.dishes.api.presenter.ChoosePresenter;
import com.example.morphtin.dishes.bean.MaterialBean;
import com.example.morphtin.dishes.ui.adapter.TagAdapter;
import com.example.morphtin.dishes.ui.base.BaseActivity;
import com.hhl.library.FlowTagLayout;
import com.hhl.library.OnTagSelectListener;

import java.util.ArrayList;
import java.util.List;

public class ChooseMaterialActivity extends BaseActivity implements ChooseContract.View{
    private ChooseContract.Presenter presenter;

    private FlowTagLayout mVegetableFlowTagLayout;
    private FlowTagLayout mMeatFlowTagLayout;
    private TagAdapter<String> mVegetableTagAdapter;
    private TagAdapter<String> mMeatTagAdapter;
    private ArrayList<String> cookList=new ArrayList<>();
    private ArrayList<String> cookList2=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_material);
        getSupportActionBar().setTitle("文字找菜");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mVegetableFlowTagLayout = (FlowTagLayout) findViewById(R.id.vegetable_flow_layout);
        mMeatFlowTagLayout = (FlowTagLayout) findViewById(R.id.meat_flow_layout);

        //多选标签
        mVegetableTagAdapter = new TagAdapter<>(this);
        mVegetableFlowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);
        mVegetableFlowTagLayout.setAdapter(mVegetableTagAdapter);
        mVegetableFlowTagLayout.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    if(cookList.size() != 0){
                    cookList.clear();}
                    for (int i : selectedList) {
                        String item =  String.valueOf(parent.getAdapter().getItem(i));
                        sb.append(item);
                        sb.append(":");
                        cookList.add(item);
                    }
                    Snackbar.make(parent, "蔬菜:" + sb.toString(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else{
                    Snackbar.make(parent, "没有选择标签", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        mMeatTagAdapter = new TagAdapter<>(this);
        mMeatFlowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);
        mMeatFlowTagLayout.setAdapter(mMeatTagAdapter);
        mMeatFlowTagLayout.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb2 = new StringBuilder();
                    if(cookList2.size() != 0){
                        cookList2.clear();}

                    for (int i : selectedList) {
                        String item = String.valueOf(parent.getAdapter().getItem(i));
                        sb2.append(item);
                        sb2.append(":");
                        cookList2.add(item);
                    }
                    Snackbar.make(parent, "肉类:" + sb2.toString(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else{
                    Snackbar.make(parent, "没有选择标签", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        initVegetableData();
        initMeatData();
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
        Intent data = new Intent();
        cookList.addAll(cookList2);
        data.putStringArrayListExtra("ADDCOOKLIST", cookList);
        setResult(RESULT_OK, data);
        finish();
    }

    private void initVegetableData() {
        List<String> dataSource = new ArrayList<>();
        dataSource.add("白菜");
        dataSource.add("荠菜");
        dataSource.add("娃娃菜");
        dataSource.add("韭菜");
        dataSource.add("菠菜");
        dataSource.add("胡萝卜");
        dataSource.add("西红柿");
        dataSource.add("西兰花");
        dataSource.add("甘蓝");
        dataSource.add("油麦菜");
        mVegetableTagAdapter.onlyAddAll(dataSource);
    }

    private void initMeatData() {
        List<String> dataSource = new ArrayList<>();
        dataSource.add("猪肉");
        dataSource.add("牛肉");
        dataSource.add("羊肉");
        dataSource.add("鱼肉");
        dataSource.add("鸡肉");
        dataSource.add("鸭肉");
        mMeatTagAdapter.onlyAddAll(dataSource);
    }


    private void select(){
        //TODO 点击确认提交的方法，参数为MaterialBean的列表
        ArrayList<MaterialBean> data = new ArrayList<>();
        presenter.select(data);
    }

    @Override
    public void showMaterials(ArrayList<MaterialBean> data) {
        //TODO 显示所有原材料以及选中状态
    }

    @Override
    public void showSelected(ArrayList<MaterialBean> data) {
        Intent intent = new Intent(this,MaterialListActivity.class);
        startActivity(intent);
    }
}
