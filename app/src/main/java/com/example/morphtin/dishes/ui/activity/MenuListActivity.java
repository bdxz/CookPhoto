package com.example.morphtin.dishes.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.api.contract.MenusContract;
import com.example.morphtin.dishes.api.presenter.MenusPresenter;
import com.example.morphtin.dishes.bean.MenuBean;
import com.example.morphtin.dishes.bean.Menu_in_List;
import com.example.morphtin.dishes.database.GeneratesFakeMenu;
import com.example.morphtin.dishes.ui.adapter.MenuAdapter;
import com.example.morphtin.dishes.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import io.rmiri.skeleton.Master.IsCanSetAdapterListener;

public class MenuListActivity extends BaseActivity implements MenusContract.View{
    private RecyclerView recyclerView;
    private MenuAdapter adapterSample1;
    private ArrayList<Menu_in_List> dataObjects = new ArrayList<>();
    private ImageView imageView;
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


        // After 5 second get data fake
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dataObjects = new GeneratesFakeMenu().generateDataFake();
                adapterSample1.addMoreDataAndSkeletonFinish(dataObjects);
            }
        }, 50);
        final CardView cardview = (CardView)getLayoutInflater().inflate(R.layout.item_cookbook,null);
        cardview.findViewById(R.id.cardView);
        imageView =(ImageView)cardview.findViewById(R.id.photoACImgV);
        imageView.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuListActivity.this);//MainActivity.this); //alert for confirm
                builder.setMessage("++++++"); //set message
                builder.show();
            }
        });
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
    public void showMenus(List<MenuBean> data) {
        //TODO 将MenuBean的List渲染到界面上

    }

    @Override
    public void showDetailUI(String menu_id) {
        Intent intent = new Intent(this,MenuDetailActivity.class);
        intent.putExtra(MenuDetailActivity.EXTRA_MENU_ID,menu_id);
        startActivity(intent);
    }


}
