package com.example.morphtin.dishes.api.model;

import com.example.morphtin.dishes.bean.MaterialBean;
import com.example.morphtin.dishes.bean.MenuBean;

import java.util.ArrayList;

import io.reactivex.Flowable;

/**
 * Created by elevation on 18-5-14.
 */

public interface IMatchModel {
    void match(ArrayList<MaterialBean> data);

    Flowable<ArrayList<MenuBean>> get();

    void clear();
}
