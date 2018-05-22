package com.example.morphtin.dishes.api.presenter;

import android.util.Log;

import com.example.morphtin.dishes.api.contract.HomeContract;
import com.example.morphtin.dishes.api.model.IMenuModel;
import com.example.morphtin.dishes.api.model.impl.MenuModelImpl;
import com.example.morphtin.dishes.api.model.mock.FakeMatchModel;
import com.example.morphtin.dishes.api.model.mock.FakeMenuModel;
import com.example.morphtin.dishes.bean.BannerItem;
import com.example.morphtin.dishes.bean.MenuBean;
import com.example.morphtin.dishes.common.Constant;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by elevation on 18-5-14.
 */

public class HomePresenter implements HomeContract.Presenter {
    private static final String TAG = "HomePresenter";

    private IMenuModel mMenuModel;
    private HomeContract.View mHomeView;

    public HomePresenter(HomeContract.View view) {
        this.mHomeView = view;
        if(Constant.MOCK){
            mMenuModel = FakeMenuModel.getInstance();
        }else{
            mMenuModel = MenuModelImpl.getInstance();
        }
    }

    @Override
    public void load() {
        mMenuModel.getBannerMenus().flatMap(new Function<List<MenuBean>, Publisher<MenuBean>>() {
            @Override
            public Publisher<MenuBean> apply(List<MenuBean> menuBeans) throws Exception {
                return Flowable.fromIterable(menuBeans);
            }
        }).map(new Function<MenuBean, BannerItem>() {
            @Override
            public BannerItem apply(MenuBean menuBean) throws Exception {
                return new BannerItem(menuBean.getImageUrl(),"",menuBean.getMenu_id());
            }
        }).toList().toFlowable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new FlowableSubscriber<List<BannerItem>>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(1);
            }

            @Override
            public void onNext(List<BannerItem> bannerItems) {
                Log.d(TAG, "onNext: "+bannerItems.get(0).getMenu_id());
                mHomeView.setBanner(bannerItems);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
