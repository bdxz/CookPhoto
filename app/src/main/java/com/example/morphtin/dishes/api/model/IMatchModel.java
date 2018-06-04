package com.example.morphtin.dishes.api.model;

import com.example.morphtin.dishes.bean.MaterialBean;
import com.example.morphtin.dishes.bean.MenuBean;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by elevation on 18-5-14.
 */

public interface IMatchModel {
    Observable<List<MenuBean>> match(List<MaterialBean> data);

    Flowable<List<MenuBean>> get();

    void clear();

    void setMatch(List<MenuBean> menuBeans);
}
