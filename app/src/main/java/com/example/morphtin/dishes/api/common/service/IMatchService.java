package com.example.morphtin.dishes.api.common.service;

import com.example.morphtin.dishes.bean.MaterialBean;
import com.example.morphtin.dishes.bean.MenuBean;

import java.util.ArrayList;

import io.reactivex.Flowable;

/**
 * Created by elevation on 18-5-15.
 */

public interface IMatchService {
    Flowable<ArrayList<MenuBean>> match(ArrayList<MaterialBean> data);
}
