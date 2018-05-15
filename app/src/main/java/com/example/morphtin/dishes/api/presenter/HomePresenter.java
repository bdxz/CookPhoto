package com.example.morphtin.dishes.api.presenter;

import com.example.morphtin.dishes.api.contract.HomeContract;
import com.example.morphtin.dishes.api.model.IMenuModel;
import com.example.morphtin.dishes.api.model.impl.MenuModelImpl;
import com.example.morphtin.dishes.bean.BannerItem;
import com.example.morphtin.dishes.bean.MenuBean;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;

/**
 * Created by elevation on 18-5-14.
 */

public class HomePresenter implements HomeContract.Presenter {
    private IMenuModel mMenuModel;
    private HomeContract.View mHomeView;

    public HomePresenter(HomeContract.View view) {
        this.mHomeView = view;
        mMenuModel = MenuModelImpl.getInstance();
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
                return new BannerItem(menuBean.getImage(),"","");
            }
        }).toList().toFlowable().observeOn(AndroidSchedulers.mainThread()).subscribe(new FlowableSubscriber<List<BannerItem>>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(List<BannerItem> bannerItems) {
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
