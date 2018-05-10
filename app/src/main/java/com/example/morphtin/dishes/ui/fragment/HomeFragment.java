package com.example.morphtin.dishes.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.api.presenter.IHomePresenter;
import com.example.morphtin.dishes.api.presenter.impl.HomePresenterImpl;
import com.example.morphtin.dishes.api.view.IHomeView;
import com.example.morphtin.dishes.bean.BannerItem;
import com.example.morphtin.dishes.ui.activity.MenuDetailActivity;
import com.example.morphtin.dishes.ui.base.BaseFragment;
import com.squareup.picasso.Picasso;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by elevation on 18-4-4.
 */

public class HomeFragment extends BaseFragment implements IHomeView {
    private static final String TAG = "HomeFragment";

    @BindView(R.id.banner)
    MZBannerView mMZBanner;
    private IHomePresenter presenter;
    private List<BannerItem> data;

    public static HomeFragment newInstance() {
        Bundle bundle = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    protected void initEvents() {
        mMZBanner.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
            @Override
            public void onPageClick(View view, int position) {
                Intent intent = new Intent(getActivity(), MenuDetailActivity.class);
                intent.putExtra("MENU_ID", data.get(position).getMenu_id().toString());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData(boolean isSavedNull) {
        presenter = new HomePresenterImpl(this);
        presenter.loadHome();
    }


    private void setBanner(List<BannerItem> list) {
        // 设置数据
        mMZBanner.setPages(list, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
        mMZBanner.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMZBanner.pause();//暂停轮播
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMZBanner.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMZBanner.start();//开始轮播
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void updateView(List<BannerItem> data) {
        this.data = data;
        for(BannerItem banner:data){
            Log.d(TAG, "updateView: "+banner.getImageUrl());
        }
        setBanner(data);
    }


    @Override
    public void showMessage(String msg) {

    }


    public static class BannerViewHolder implements MZViewHolder<BannerItem> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, BannerItem data) {
            // 数据绑定
            Picasso.get().load(data.getImageUrl()).into(mImageView);
        }
    }
}
