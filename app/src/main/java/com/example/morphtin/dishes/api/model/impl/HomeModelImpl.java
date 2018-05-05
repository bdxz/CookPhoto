package com.example.morphtin.dishes.api.model.impl;

import com.example.morphtin.dishes.api.common.ServiceFactory;
import com.example.morphtin.dishes.api.common.service.IHomeService;
import com.example.morphtin.dishes.api.model.IHomeModel;
import com.example.morphtin.dishes.bean.BannerItem;
import com.example.morphtin.dishes.common.Constant;
import com.example.morphtin.dishes.common.URL;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by elevation on 18-5-5.
 */

public class HomeModelImpl implements IHomeModel {
    @Override
    public void loadHomeInfo(Observer<List<BannerItem>> listener) {
        IHomeService service;
        if(Constant.DEBUG){
            service = ServiceFactory.createService(URL.HOST_URL_DEBUG,IHomeService.class);
        }else{
            service = ServiceFactory.createService(URL.HOST_URL_CUSTOM,IHomeService.class);
        }

        service.getBanner().subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {

            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(listener);
    }
}
