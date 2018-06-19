package com.example.morphtin.dishes.api.model;

import com.example.morphtin.dishes.bean.MenuBean;
import com.example.morphtin.dishes.bean.http.BaseResponse;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observer;

/**
 * Created by elevation on 18-5-4.
 */

public interface IMenuModel {
    Flowable<List<MenuBean>> getBannerMenus();

    Flowable<MenuBean> getMenu(String menu_id);

    void saveMenu(MenuBean menu);

    Flowable<MenuBean> downloadMenu(String menu_id);
}
