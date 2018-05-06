package com.example.morphtin.dishes.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.ui.adapter.TagAdapter;
import com.hhl.library.FlowTagLayout;
import com.hhl.library.OnTagSelectListener;

import java.util.ArrayList;
import java.util.List;

public class ChooseMaterialActivity extends AppCompatActivity {
    private FlowTagLayout mVegetableFlowTagLayout;
    private FlowTagLayout mMeatFlowTagLayout;
    private TagAdapter<String> mVegetableTagAdapter;
    private TagAdapter<String> mMeatTagAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_material);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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

                    for (int i : selectedList) {
                        sb.append(parent.getAdapter().getItem(i));
                        sb.append(":");
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

                    for (int i : selectedList) {
                        sb2.append(parent.getAdapter().getItem(i));
                        sb2.append(":");
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

        //清除所有已经被选择的选项
        findViewById(R.id.bt_clear_all)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mVegetableFlowTagLayout.clearAllOption();
                        mMeatFlowTagLayout.clearAllOption();
                    }
                });
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 将数据显示在菜单
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
