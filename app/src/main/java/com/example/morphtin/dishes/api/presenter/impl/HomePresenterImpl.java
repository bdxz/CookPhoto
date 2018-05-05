package com.example.morphtin.dishes.api.presenter.impl;

import android.util.Log;

import com.example.morphtin.dishes.api.model.IHomeModel;
import com.example.morphtin.dishes.api.model.impl.HomeModelImpl;
import com.example.morphtin.dishes.api.presenter.IHomePresenter;
import com.example.morphtin.dishes.api.view.IHomeView;
import com.example.morphtin.dishes.bean.BannerItem;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by elevation on 18-5-5.
 */

public class HomePresenterImpl implements IHomePresenter {
    private static final String TAG = "HomePresenterImpl";
    private IHomeModel model;
    private IHomeView view;

    public HomePresenterImpl(IHomeView view) {
        this.view = view;
        model = new HomeModelImpl();
    }

    @Override
    public void loadHome() {
        model.loadHomeInfo(new Observer<List<BannerItem>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<BannerItem> data) {
                for(BannerItem banner:data){
                    Log.d(TAG, "onNext: "+banner.toString());
                }
                view.updateView(data);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
