package com.example.morphtin.dishes.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.api.contract.MenuContract;
import com.example.morphtin.dishes.api.model.Trace;
import com.example.morphtin.dishes.api.presenter.MenuPresenter;
import com.example.morphtin.dishes.bean.MenuBean;
import com.example.morphtin.dishes.bean.MenuStep;
import com.example.morphtin.dishes.ui.adapter.TraceListAdapter;
import com.example.morphtin.dishes.ui.base.BaseActivity;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuDetailActivity extends BaseActivity implements MenuContract.View{
    private static final String TAG = "MenuDetailActivity";

    public static final String EXTRA_MENU_ID = "EXTRA_MENU_ID";

    /*@BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.iv_back)
    ImageView mImageView;
    @BindView(R.id.menuDetailRecyclerView)
    RecyclerView ItemRecyclerView;*/
    @BindView(R.id.toolbar)

    Toolbar mToolbar;
    ImageView iv_maincover;
    TextView tv_title;
    ImageView iv_mainPic;
    TextView tv_author;
    TextView tv_description;
    RecyclerView rvTrace;

    private ItemAdapter adapter;
    private MenuContract.Presenter presenter;
    private TraceListAdapter traceListadapter;
    private List<Trace> traceList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);

        ButterKnife.bind(this);
        findView();
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

    private void findView() {
        rvTrace = (RecyclerView) findViewById(R.id.rvTrace);
        tv_description = (TextView)findViewById(R.id.tv_description);
        tv_author = (TextView)findViewById(R.id.tv_author);
        iv_mainPic = (ImageView)findViewById(R.id.iv_mainPic);
        tv_title = (TextView)findViewById(R.id.tv_title);
        iv_maincover = (ImageView)findViewById(R.id.iv_maincover);
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
        //Picasso.get()
        //        .load(menu.getImage())
        //        .into(mImageView);
        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
        //mCollapsingToolbarLayout.setTitle(menu.getTitle());
        //通过CollapsingToolbarLayout修改字体颜色
        //mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        //mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色

        //adapter = new ItemAdapter(menu.getSteps());
        //ItemRecyclerView.setAdapter(adapter);

        //ItemRecyclerView.setLayoutManager (new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    /*
      菜谱信息模拟数据
     */
    public MenuBean zaojia(){
        MenuBean mb = new MenuBean();
        mb.setDescription("这是菜谱描述");
        mb.setImageTitle("url");
        mb.setPhoto(1);
        mb.setTitle("菜谱");
        ArrayList<MenuStep> list = new ArrayList<>();
        MenuStep ms1 = new MenuStep("这是步骤","hhh");
        MenuStep ms2 = new MenuStep("这是步骤","hhh");
        MenuStep ms3 = new MenuStep("这是步骤","hhh");
        MenuStep ms4 = new MenuStep("这是步骤","hhh");
        list.add(ms1);
        list.add(ms2);
        list.add(ms3);
        list.add(ms4);
        mb.setSteps(list);
        return mb;
    }

    @Override
    public void showDetail(MenuBean data) {
        Trace trace = new Trace();
        String stepCountDesc = "";

        data = zaojia();//zaojia()为测试时模拟的数据，当正式使用showdetail()函数时应删除该行代码

        for(int i=0;i < data.getSteps().size();i++){
            stepCountDesc = "第"+(i+1)+"步";
            trace = new Trace(stepCountDesc,data.getSteps().get(i).getDescription(),data.getSteps().get(i).getImageUrl());
            traceList.add(trace);
        }
        traceListadapter = new TraceListAdapter(this, traceList);
        rvTrace.setLayoutManager(new LinearLayoutManager(this));
        rvTrace.setAdapter(traceListadapter);

        Bitmap bimage=  getBitmapFromURL(data.getImage());
        iv_maincover.setImageBitmap(bimage);
        tv_title.setText(data.getTitle());
        //Bitmap bimage=  getBitmapFromURL(data.getImage()); //显示作者头像
        //iv_mainPic.setImageBitmap(bimage);

        //tv_author.setText("作者名称");// 显示作者名称
        tv_description.setText(data.getDescription());

    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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
