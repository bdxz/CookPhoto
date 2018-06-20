package com.example.morphtin.dishes.api.model.impl;

import android.util.Log;

import com.example.morphtin.dishes.api.common.ServiceFactory;
import com.example.morphtin.dishes.api.common.service.IHomeService;
import com.example.morphtin.dishes.api.common.service.IMenuService;
import com.example.morphtin.dishes.api.model.IMenuModel;
import com.example.morphtin.dishes.bean.MenuBean;
import com.example.morphtin.dishes.bean.http.BaseResponse;
import com.example.morphtin.dishes.common.Constant;
import com.example.morphtin.dishes.common.URL;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by elevation on 18-5-14.
 */

public class MenuModelImpl implements IMenuModel {
    private static final String TAG = "MenuModelImpl";
    private static final MenuModelImpl ourInstance = new MenuModelImpl();

    public static MenuModelImpl getInstance() {
        return ourInstance;
    }

    private IMenuService mMenuService;
    private IHomeService mHomeService;

    private MenuModelImpl() {
        if(Constant.DEBUG){
            mMenuService = ServiceFactory.createService(URL.HOST_URL_DEBUG,IMenuService.class);
            mHomeService = ServiceFactory.createService(URL.HOST_URL_DEBUG,IHomeService.class);
        }else{
            mMenuService = ServiceFactory.createService(URL.HOST_URL_CUSTOM,IMenuService.class);
            mHomeService = ServiceFactory.createService(URL.HOST_URL_CUSTOM,IHomeService.class);
        }
    }

    @Override
    public Flowable<List<MenuBean>> getBannerMenus() {
        return mHomeService.getBanner();
    }

    @Override
    public Flowable<MenuBean> getMenu(String menu_id) {
        Log.d(TAG, "getMenu: "+menu_id);
        return mMenuService.get(menu_id);
    }

    @Override
    public void saveMenu(MenuBean menu) {
        Log.d(TAG, "saveMenu: "+menu.getTitle());
        mMenuService.add(menu).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new FlowableSubscriber<BaseResponse>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(1);
            }

            @Override
            public void onNext(BaseResponse baseResponse) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public Flowable<MenuBean> downloadMenu(String menu_id){
        return mMenuService.download(menu_id);
    }
}
