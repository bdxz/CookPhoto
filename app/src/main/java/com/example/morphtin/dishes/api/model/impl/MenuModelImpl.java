package com.example.morphtin.dishes.api.model.impl;

import com.example.morphtin.dishes.api.common.ServiceFactory;
import com.example.morphtin.dishes.api.common.service.IMenuService;
import com.example.morphtin.dishes.api.model.IMenuModel;
import com.example.morphtin.dishes.bean.MenuBean;
import com.example.morphtin.dishes.bean.http.BaseResponse;
import com.example.morphtin.dishes.common.Constant;
import com.example.morphtin.dishes.common.URL;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by elevation on 18-5-4.
 */

public class MenuModelImpl implements IMenuModel {
    private static final String TAG = "MenuModelImpl";
    private IMenuService service;

    public MenuModelImpl() {
        if(Constant.DEBUG){
            service = ServiceFactory.createService(URL.HOST_URL_DEBUG,IMenuService.class);
        }else{
            service = ServiceFactory.createService(URL.HOST_URL_CUSTOM,IMenuService.class);
        }
    }

    @Override
    public void uploadMenu(MenuBean menu, Observer<BaseResponse> listener) {
        service.addMenu(menu).subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {

            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(listener);
    }

    @Override
    public void loadMenuDetail(String menu_id, Observer<MenuBean> observer) {
        service.loadMenuDetail(menu_id).subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {

            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
}
