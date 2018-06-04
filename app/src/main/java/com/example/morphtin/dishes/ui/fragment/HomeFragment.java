package com.example.morphtin.dishes.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.api.contract.HomeContract;
import com.example.morphtin.dishes.api.presenter.HomePresenter;
import com.example.morphtin.dishes.bean.BannerItem;
import com.example.morphtin.dishes.ui.activity.CreateDishActivity;
import com.example.morphtin.dishes.ui.activity.DishDetailActivity;
import com.example.morphtin.dishes.ui.activity.MenuDetailActivity;
import com.example.morphtin.dishes.ui.activity.MenuListActivity;
import com.example.morphtin.dishes.ui.activity.UploadMenuActivity;
import com.example.morphtin.dishes.ui.base.BaseFragment;
import com.squareup.picasso.Picasso;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.List;

import butterknife.BindView;

import static com.example.morphtin.dishes.R.id.home1;
import static com.example.morphtin.dishes.R.id.home2;
import static com.example.morphtin.dishes.R.id.home3;

/**
 * Created by elevation on 18-4-4.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View{
    private static final String TAG = "HomeFragment";

    @BindView(R.id.banner)
    MZBannerView mMZBanner;

    private List<BannerItem> data;
    private HomeContract.Presenter presenter;

    public static HomeFragment newInstance() {
        Bundle bundle = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_home, container, false);
        View home = mRootView.findViewById(home1);

        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),
                        MenuListActivity.class);
                startActivity(intent);
            }
        });

        View detail = mRootView.findViewById(home2);

        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),
                        DishDetailActivity.class);
                startActivity(intent);
            }
        });

        View btn3 = mRootView.findViewById(home3);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),
                        UploadMenuActivity .class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initEvents() {
        mMZBanner.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
            @Override
            public void onPageClick(View view, int position) {
                Intent intent = new Intent(getActivity(), MenuDetailActivity.class);
                String menu_id = data.get(position).getMenu_id();
                Log.d(TAG, "onPageClick: "+menu_id);
                intent.putExtra(MenuDetailActivity.EXTRA_MENU_ID, menu_id);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData(boolean isSavedNull) {
        presenter = new HomePresenter(this);
        presenter.load();
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
    public void setBanner(List<BannerItem> data) {
        this.data = data;
        // 设置数据
        mMZBanner.setPages(data, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
        mMZBanner.start();
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
