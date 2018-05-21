package com.example.morphtin.dishes.ui.activity;

import android.content.Intent;
import android.os.Bundle;
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

    private LayoutInflater inflater;
    // 获取需要被添加控件的布局
    private LinearLayout lin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_material);
        getSupportActionBar().setTitle("文字找菜");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inflater = LayoutInflater.from(this);
        lin = (LinearLayout) findViewById(R.id.tagContianer);
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

    }

    private void select(){
        //TODO 点击确认提交的方法，参数为MaterialBean的列表
        List<MaterialBean> data = new ArrayList<>();
        presenter.select(data);
    }

    @Override
    public void showMaterials(List<MaterialBean> data) {
        //TODO 显示所有原材料以及选中状态
        HashMap<String,List<MaterialBean>> map = new HashMap<>();
        for (MaterialBean material:data) {
            Log.d(TAG, "showMaterials: "+material.getTitle());
            if(map.containsKey(material.getCatelog())){
                map.get(material.getCatelog()).add(material);
            }else{
                List<MaterialBean> list = new ArrayList<>();
                list.add(material);
                map.put(material.getCatelog(),list);
            }
        }

        Iterator<Map.Entry<String, List<MaterialBean>>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<MaterialBean>> entry = iterator.next();
            String catelog = entry.getKey();
            List<MaterialBean> list = entry.getValue();
            addTags(catelog,list);
        }
    }

    private void addTags(String catelog, List<MaterialBean> list) {
        // 获取需要添加的布局
        LinearLayout layout = (LinearLayout) inflater.inflate(
                R.layout.title_tags, null).findViewById(R.id.titleTags);
        // 将布局加入到当前布局中
        lin.addView(layout);

        TextView textView = (TextView)findViewById(R.id.catelog);
        textView.setText(catelog);
        FlowTagLayout flowTagLayout = (FlowTagLayout)findViewById(R.id.flow_layout);

        TagAdapter adapter = new TagAdapter<>(this);
        flowTagLayout.setAdapter(adapter);

        adapter.onlyAddAll(list);
    }

    @Override
    public void showSelected(List<MaterialBean> data) {
        Intent intent = new Intent(this,MaterialListActivity.class);
        startActivity(intent);
    }
}
