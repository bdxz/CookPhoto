package com.example.morphtin.dishes.api.presenter.impl;

import android.util.Log;

import com.example.morphtin.dishes.api.model.IMenuModel;
import com.example.morphtin.dishes.api.model.impl.MenuModelImpl;
import com.example.morphtin.dishes.api.presenter.IMenuPresenter;
import com.example.morphtin.dishes.api.view.IMenuView;
import com.example.morphtin.dishes.bean.MenuBean;
import com.example.morphtin.dishes.bean.http.BaseResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by elevation on 18-5-4.
 */

public class MenuPresenterImpl implements IMenuPresenter {
    private static final String TAG = "MenuPresenterImpl";
    private IMenuModel model;
    private IMenuView view;

    public MenuPresenterImpl(IMenuView view) {
        this.view = view;
        model = new MenuModelImpl();
    }

    @Override
    public void uploadMenu(MenuBean menu) {
        model.uploadMenu(menu, new Observer<BaseResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
                
            }

            @Override
            public void onNext(BaseResponse baseResponse) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void loadMenuDetail(String menu_id) {
        model.loadMenuDetail(menu_id, new Observer<MenuBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MenuBean menuBean) {
                Log.d(TAG, "onNext: "+menuBean);
                view.updateView(menuBean);
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
