package com.example.morphtin.dishes.api.presenter;

import android.util.Log;

import com.example.morphtin.dishes.api.contract.MenusContract;
import com.example.morphtin.dishes.api.model.IMatchModel;
import com.example.morphtin.dishes.api.model.IMenuModel;
import com.example.morphtin.dishes.api.model.impl.MatchModelImpl;
import com.example.morphtin.dishes.api.model.mock.FakeMatchModel;
import com.example.morphtin.dishes.bean.MenuBean;
import com.example.morphtin.dishes.common.Constant;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by elevation on 18-5-14.
 */

public class MenusPresenter implements MenusContract.Presenter {
    private static final String TAG = "MenusPresenter";
    private IMatchModel mMatchModel;
    private MenusContract.View mMenusView;

    public MenusPresenter(MenusContract.View view) {
        this.mMenusView = view;
        if(Constant.MOCK){
            mMatchModel = FakeMatchModel.getInstance();
        }else{
            mMatchModel = MatchModelImpl.getInstance();
        }
    }

    @Override
    public void loadMatchMenus() {
        mMatchModel.get().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new FlowableSubscriber<List<MenuBean>>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(100);
            }

            @Override
            public void onNext(List<MenuBean> menuBeans) {
                Log.d(TAG, "onNext: ");
                if(menuBeans instanceof ArrayList){
                    Log.d(TAG, "onNext: "+menuBeans.size());
                    mMenusView.showMenus((ArrayList<MenuBean>) menuBeans);
                }
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
    public void openDetail(String menu_id) {
        mMenusView.showDetailUI(menu_id);
    }
}
