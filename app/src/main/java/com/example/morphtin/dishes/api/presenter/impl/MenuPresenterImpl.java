package com.example.morphtin.dishes.api.presenter.impl;

import com.example.morphtin.dishes.api.model.IMenuModel;
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
}
