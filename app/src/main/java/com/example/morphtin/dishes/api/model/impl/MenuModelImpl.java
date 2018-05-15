package com.example.morphtin.dishes.api.model.impl;

import com.example.morphtin.dishes.api.common.ServiceFactory;
import com.example.morphtin.dishes.api.common.service.IHomeService;
import com.example.morphtin.dishes.api.common.service.IMenuService;
import com.example.morphtin.dishes.api.model.IMenuModel;
import com.example.morphtin.dishes.bean.MenuBean;
import com.example.morphtin.dishes.common.Constant;
import com.example.morphtin.dishes.common.URL;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by elevation on 18-5-14.
 */

public class MenuModelImpl implements IMenuModel {
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
        return mMenuService.get(menu_id).subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer<Subscription>() {
            @Override
            public void accept(Subscription subscription) throws Exception {

            }
        });
    }

    @Override
    public void saveMenu(MenuBean menu) {
        mMenuService.add(menu);
    }
}
