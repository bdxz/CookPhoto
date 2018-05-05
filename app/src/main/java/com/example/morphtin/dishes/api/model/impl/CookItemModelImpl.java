package com.example.morphtin.dishes.api.model.impl;

import com.example.morphtin.dishes.api.common.ServiceFactory;
import com.example.morphtin.dishes.api.common.service.ICookItemService;
import com.example.morphtin.dishes.api.common.service.IMenuService;
import com.example.morphtin.dishes.api.model.ICookItemModel;
import com.example.morphtin.dishes.api.model.IMenuModel;
import com.example.morphtin.dishes.bean.MenuBean;
import com.example.morphtin.dishes.bean.http.BaseResponse;
import com.example.morphtin.dishes.common.Constant;
import com.example.morphtin.dishes.common.URL;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by elevation on 18-5-4.
 */

public class CookItemModelImpl implements ICookItemModel {
    private static final String TAG = "CookItemModelImpl";

    @Override
    public void uploadCookItem(List<String> list, Observer<List<MenuBean>> listener) {
        ICookItemService service;
        if(Constant.DEBUG){
            service = ServiceFactory.createService(URL.HOST_URL_DEBUG,ICookItemService.class);
        }else{
            service = ServiceFactory.createService(URL.HOST_URL_CUSTOM,ICookItemService.class);
        }

        service.addCookItem(list).subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {

            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(listener);
    }
}
