package com.example.morphtin.dishes.api.model.impl;

import com.example.morphtin.dishes.api.common.ServiceFactory;
import com.example.morphtin.dishes.api.common.service.IMatchService;
import com.example.morphtin.dishes.api.model.IMatchModel;
import com.example.morphtin.dishes.bean.MaterialBean;
import com.example.morphtin.dishes.bean.MenuBean;
import com.example.morphtin.dishes.common.Constant;
import com.example.morphtin.dishes.common.URL;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by elevation on 18-5-14.
 */

public class MatchModelImpl implements IMatchModel {
    private static final MatchModelImpl ourInstance = new MatchModelImpl();

    public static MatchModelImpl getInstance() {
        return ourInstance;
    }

    private static ArrayList<MenuBean> matchMenus = new ArrayList<>();

    private IMatchService mMatchService;

    private MatchModelImpl() {
        if(Constant.DEBUG){
            mMatchService = ServiceFactory.createService(URL.HOST_URL_DEBUG,IMatchService.class);
        }else {
            mMatchService = ServiceFactory.createService(URL.HOST_URL_CUSTOM,IMatchService.class);
        }
    }

    @Override
    public void match(final ArrayList<MaterialBean> data) {
        clear();
        mMatchService.match(data).subscribeOn(Schedulers.io()).subscribe(new Subscriber<ArrayList<MenuBean>>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(ArrayList<MenuBean> menuBeans) {
                matchMenus.addAll(menuBeans);
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
    public Flowable<ArrayList<MenuBean>> get() {
        return Flowable.just(matchMenus);
    }

    @Override
    public void clear() {
        matchMenus.clear();
    }
}
