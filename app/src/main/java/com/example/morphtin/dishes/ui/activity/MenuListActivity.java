package com.example.morphtin.dishes.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.api.contract.MenusContract;
import com.example.morphtin.dishes.api.presenter.MenusPresenter;
import com.example.morphtin.dishes.bean.MenuBean;
import com.example.morphtin.dishes.database.GeneratesFakeMenu;
import com.example.morphtin.dishes.ui.adapter.MenuAdapter;
import com.example.morphtin.dishes.ui.base.BaseActivity;

import java.util.ArrayList;

import io.rmiri.skeleton.Master.IsCanSetAdapterListener;

public class MenuListActivity extends BaseActivity implements MenusContract.View{
    private RecyclerView recyclerView;
    private MenuAdapter adapterSample1;
    private ArrayList<MenuBean> dataObjects = new ArrayList<>();

    private MenusContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);


        // Toolbar
        ((Toolbar) findViewById(R.id.toolbar)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Initial recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        // Set adapter in recyclerView
        adapterSample1 = new MenuAdapter(getApplicationContext(), dataObjects,recyclerView, new IsCanSetAdapterListener() {
            @Override
            public void isCanSet() {
                recyclerView.setAdapter(adapterSample1);
            }
        });

        showMenus(dataObjects);

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {
        presenter = new MenusPresenter(this);
        presenter.loadMatchMenus();
    }

    @Override
    public void showMenus(ArrayList<MenuBean> data) {
        //TODO 将MenuBean的List渲染到界面上
        data = new GeneratesFakeMenu().generateDataFake();
        adapterSample1.addMoreDataAndSkeletonFinish(data);

    }


    @Override
    public void showDetailUI(String menu_id) {
        Intent intent = new Intent(this,MenuDetailActivity.class);
        intent.putExtra(MenuDetailActivity.EXTRA_MENU_ID,menu_id);
        startActivity(intent);
    }

    private void ClickItem(String menu_id){
        //TODO 点击某一个菜谱应调用的代码
        presenter.openDetail(menu_id);
    }
}
