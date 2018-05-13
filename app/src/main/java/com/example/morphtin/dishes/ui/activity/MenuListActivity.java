package com.example.morphtin.dishes.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.bean.Menu_in_List;
import com.example.morphtin.dishes.database.GeneratesFakeMenu;
import com.example.morphtin.dishes.ui.adapter.MenuAdapter;

import java.util.ArrayList;

import io.rmiri.skeleton.Master.IsCanSetAdapterListener;

public class MenuListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MenuAdapter adapterSample1;
    private ArrayList<Menu_in_List> dataObjects = new ArrayList<>();

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
    }
}
