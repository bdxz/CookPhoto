package com.example.morphtin.dishes.ui.activity;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
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
import com.example.morphtin.dishes.api.contract.MenuContract;
import com.example.morphtin.dishes.api.presenter.MenuPresenter;
import com.example.morphtin.dishes.bean.MenuBean;
import com.example.morphtin.dishes.bean.MenuStep;
import com.example.morphtin.dishes.ui.base.BaseActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuDetailActivity extends BaseActivity implements MenuContract.View{
    private static final String TAG = "MenuDetailActivity";

    public static final String EXTRA_MENU_ID = "EXTRA_MENU_ID";

    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.menu_detail_image)
    ImageView mImageView;
    @BindView(R.id.menuDetailRecyclerView)
    RecyclerView ItemRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private ItemAdapter adapter;
    private MenuContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);

        ButterKnife.bind(this);

    }

    @Override
    protected void initData() {
        String menu_id = getIntent().getStringExtra(EXTRA_MENU_ID);
        presenter = new MenuPresenter(this);
        presenter.loadDetail(menu_id);
    }

    @Override
    protected void initEvents() {

    }


    public void initView(MenuBean menu){
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Picasso.get()
                .load(menu.getImageUrl())
                .into(mImageView);
        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
        mCollapsingToolbarLayout.setTitle(menu.getTitle());
        //通过CollapsingToolbarLayout修改字体颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色

        adapter = new ItemAdapter(menu.getSteps());
        ItemRecyclerView.setAdapter(adapter);

        ItemRecyclerView.setLayoutManager (new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    @Override
    public void showDetail(MenuBean data) {
        //TODO
        initView(data);
    }

    private class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        private TextView mTv;
        private TextView tvTopLine, tvDot;
        private TextView titleTextView;
        private ImageView imageView;
        private TextView indexTextView;

        public ItemHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            tvTopLine = itemView.findViewById(R.id.tvTopLine);
           tvDot = itemView.findViewById(R.id.tvDot);
           indexTextView = itemView.findViewById(R.id.menu_item_index);
            titleTextView = itemView.findViewById(R.id.menu_item_title);
            imageView = itemView.findViewById(R.id.menu_item_photo);
        }

        public void bind(String name,String image,int index){
            int count = index+1;
            indexTextView.setText("第"+count+"步：");
            titleTextView.setText(name);
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
        private static final int TYPE_TOP = 0x0000;
        private static final int TYPE_NORMAL= 0x0001;
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
            if (getItemViewType(position) == TYPE_TOP) {
                               holder.tvTopLine.setVisibility(View.INVISIBLE);
                                holder.tvDot.setBackgroundResource(R.drawable.timelline_dot_first);
                          }else if (getItemViewType(position) == TYPE_NORMAL) {
                                holder.tvTopLine.setVisibility(View.VISIBLE);
                               holder.tvDot.setBackgroundResource(R.drawable.timelline_dot_normal);
                           }
            String name = mData.get(position).getDescription();
            String image = mData.get(position).getImageUrl();
            holder.bind(name,image,position);
        }


        @Override
        public int getItemCount() {
            return mData == null ? 0 : mData.size();
        }
    }
}
