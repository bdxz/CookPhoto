package com.example.morphtin.dishes.api.presenter;

import android.util.Log;

import com.example.morphtin.dishes.api.contract.MenuContract;
import com.example.morphtin.dishes.api.model.IMenuModel;
import com.example.morphtin.dishes.api.model.impl.MenuModelImpl;
import com.example.morphtin.dishes.api.model.mock.FakeMenuModel;
import com.example.morphtin.dishes.bean.MenuBean;
import com.example.morphtin.dishes.common.Constant;

import org.reactivestreams.Subscription;

import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by elevation on 18-5-14.
 */

public class MenuPresenter implements MenuContract.Presenter {
    private static final String TAG = "MenuPresenter";
    private IMenuModel mMenuModel;
    private MenuContract.View mMenuView;

    public MenuPresenter(MenuContract.View view) {
        this.mMenuView = view;
        if(Constant.MOCK){
            mMenuModel = FakeMenuModel.getInstance();
        }else{
            mMenuModel = MenuModelImpl.getInstance();
        }
    }

    @Override
    public void loadDetail(String menu_id) {
        mMenuModel.getMenu(menu_id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new FlowableSubscriber<MenuBean>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(1);
            }

            @Override
            public void onNext(MenuBean menuBean) {
                mMenuView.showDetail(menuBean);
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
    public void addMenu(MenuBean menu) {
        Log.d(TAG, "addMenu: "+menu.getTitle());
        mMenuModel.saveMenu(menu);
    }
}
