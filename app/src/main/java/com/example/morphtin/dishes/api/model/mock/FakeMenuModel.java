package com.example.morphtin.dishes.api.model.mock;

import com.example.morphtin.dishes.api.model.IMenuModel;
import com.example.morphtin.dishes.bean.MenuBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by elevation on 18-5-21.
 */

public class FakeMenuModel implements IMenuModel {
    private static final FakeMenuModel ourInstance = new FakeMenuModel();

    public static FakeMenuModel getInstance() {
        return ourInstance;
    }

    private FakeMenuModel() {
    }

    @Override
    public Flowable<List<MenuBean>> getBannerMenus() {
        List<MenuBean> data = new ArrayList<>();
        return Flowable.just(data);
    }

    @Override
    public Flowable<MenuBean> getMenu(String menu_id) {
        return null;
    }

    @Override
    public void saveMenu(MenuBean menu) {

    }
}
