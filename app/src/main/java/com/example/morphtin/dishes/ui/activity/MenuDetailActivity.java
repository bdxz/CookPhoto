package com.example.morphtin.dishes.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.bean.MenuBean;
import com.example.morphtin.dishes.bean.MenuStep;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MenuDetailActivity extends AppCompatActivity {

    private MenuBean menuBean;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private ImageView mImageView;
    private RecyclerView ItemRecyclerView;
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);

        menuBean = getIntent().getParcelableExtra("MENUBEAN");

        initView();
    }



    public void initView(){
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mImageView = findViewById(R.id.menu_detail_image);
        Picasso.get()
                .load(menuBean.getImage())
                .into(mImageView);
        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        mCollapsingToolbarLayout.setTitle(menuBean.getTitle());
        //通过CollapsingToolbarLayout修改字体颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色

        ItemRecyclerView = (RecyclerView) findViewById(R.id.menuDetailRecyclerView);

        adapter = new ItemAdapter(menuBean.getSteps());
        ItemRecyclerView.setAdapter(adapter);

        ItemRecyclerView.setLayoutManager (new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }



    private class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        private TextView mTv;
        private ImageView imageView;

        public ItemHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTv = itemView.findViewById(R.id.menu_item_title);
            imageView = itemView.findViewById(R.id.menu_item_photo);
        }

        public void bind(String name,String image){
            mTv.setText(name);
            Picasso.get()
                    .load(image)
                    .into(imageView);

        }


        @Override
        public void onClick(View view) {

        }

    }

    private class ItemAdapter extends RecyclerView.Adapter<MenuDetailActivity.ItemHolder> {

        private ArrayList<MenuStep> mData;

        public ItemAdapter(ArrayList<MenuStep> steps) {
            mData = steps;
        }

        @Override
        public MenuDetailActivity.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menulist_item, parent, false);
            return new MenuDetailActivity.ItemHolder(v);
        }

        @Override
        public void onBindViewHolder(MenuDetailActivity.ItemHolder holder, int position) {
            String name = mData.get(position).getDescription();
            String image = mData.get(position).getImageUrl();
            holder.bind(name,image);
        }


        @Override
        public int getItemCount() {
            return mData == null ? 0 : mData.size();
        }


    }
}
