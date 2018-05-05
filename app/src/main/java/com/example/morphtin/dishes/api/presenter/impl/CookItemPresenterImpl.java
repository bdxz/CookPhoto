package com.example.morphtin.dishes.api.presenter.impl;

import com.example.morphtin.dishes.api.model.ICookItemModel;
import com.example.morphtin.dishes.api.model.IMenuModel;
import com.example.morphtin.dishes.api.model.impl.CookItemModelImpl;
import com.example.morphtin.dishes.api.model.impl.MenuModelImpl;
import com.example.morphtin.dishes.api.presenter.ICookItemPresenter;
import com.example.morphtin.dishes.api.presenter.IMenuPresenter;
import com.example.morphtin.dishes.api.view.ICookItemView;
import com.example.morphtin.dishes.api.view.IMenuView;
import com.example.morphtin.dishes.bean.MenuBean;
import com.example.morphtin.dishes.bean.http.BaseResponse;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by elevation on 18-5-4.
 */

public class CookItemPresenterImpl implements ICookItemPresenter {
    private static final String TAG = "CookItemPresenterImpl";
    private ICookItemModel model;
    private ICookItemView view;

    public CookItemPresenterImpl(ICookItemView view) {
        this.view = view;
        model = new CookItemModelImpl();
    }

    @Override
    public void uploadCookItem(List<String> list) {
        model.uploadCookItem(list, new Observer<List<MenuBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                
            }

            @Override
            public void onNext(List<MenuBean> list) {

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
