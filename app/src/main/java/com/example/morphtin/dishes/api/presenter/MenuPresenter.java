package com.example.morphtin.dishes.api.presenter;

import com.example.morphtin.dishes.api.contract.MenuContract;
import com.example.morphtin.dishes.api.model.IMenuModel;
import com.example.morphtin.dishes.api.model.impl.MenuModelImpl;
import com.example.morphtin.dishes.bean.MenuBean;

import org.reactivestreams.Subscription;

import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by elevation on 18-5-14.
 */

public class MenuPresenter implements MenuContract.Presenter {
    private IMenuModel mMenuModel;
    private MenuContract.View mMenuView;

    public MenuPresenter(MenuContract.View view) {
        this.mMenuView = view;
        mMenuModel = MenuModelImpl.getInstance();
    }

    @Override
    public void loadDetail(String menu_id) {
        //TODO 判断是否在缓存中，如果缓存中没有，再去请求网络
        mMenuModel.getMenu(menu_id).observeOn(AndroidSchedulers.mainThread()).subscribe(new FlowableSubscriber<MenuBean>() {
            @Override
            public void onSubscribe(Subscription s) {

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
        mMenuModel.saveMenu(menu);
    }
}
