package com.example.morphtin.dishes.api.model;

import com.example.morphtin.dishes.bean.MaterialBean;
import com.example.morphtin.dishes.bean.MenuBean;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by elevation on 18-5-14.
 */

public interface IMatchModel {
    void match(List<MaterialBean> data);

    Flowable<List<MenuBean>> get();

    void clear();
}
