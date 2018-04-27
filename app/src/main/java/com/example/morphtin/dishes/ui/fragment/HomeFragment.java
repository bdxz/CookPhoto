package com.example.morphtin.dishes.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.bean.BannerItem;
import com.example.morphtin.dishes.ui.activity.WebActivity;
import com.example.morphtin.dishes.ui.base.BaseFragment;
import com.squareup.picasso.Picasso;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elevation on 18-4-4.
 */

public class HomeFragment extends BaseFragment {
    public static final String EXTRA_MESSAGE = "WEBLINKMESSAGE";

    private MZBannerView mMZBanner;
    private List<BannerItem> list = new ArrayList<BannerItem>();

    public static HomeFragment newInstance() {
        Bundle bundle = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mMZBanner = (MZBannerView) view.findViewById(R.id.banner);
        mMZBanner.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
        @Override
        public void onPageClick(View view, int position) {
            Intent intent = new Intent(getActivity(), WebActivity.class);
            intent.putExtra(EXTRA_MESSAGE,list.get(position).getLink());
            startActivity(intent);

            //Toast.makeText(getContext(),"click page:"+position, Toast.LENGTH_LONG).show();
            }
        });

        getList();
        setBanner(list);
        return view;
    }

    private List<BannerItem> getList(){
        list.add(new BannerItem("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524745128749&di=2987e5162bd809acd55bfd63770e5840&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fblog%2F201409%2F12%2F20140912223817_f2YZs.thumb.700_0.jpeg","https://www.baidu.com/"));
        list.add(new BannerItem("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=897200727,4104403946&fm=27&gp=0.jpg","https://www.baidu.com"));
        list.add(new BannerItem("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524745170708&di=93e97272736e5bbde488b350606371b0&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fblog%2F201409%2F12%2F20140912223610_tvekx.thumb.700_0.jpeg","https://www.baidu.com/"));

        return list;
    }

    private void setBanner(List<BannerItem> list){
        // 设置数据
        mMZBanner.setPages(list, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
//        new Thread() {
//            @Override
//            public void run() {
                mMZBanner.start();
//             }
//        }.start();

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



    public static class BannerViewHolder implements MZViewHolder<BannerItem> {
        private ImageView mImageView;
        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item,null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, BannerItem data) {
            // 数据绑定
            //mImageView.setImageResource(data);
            Picasso.get().load(data.getImageUrl()).into(mImageView);
        }


    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData(boolean isSavedNull) {

    }
}
