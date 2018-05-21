package com.example.morphtin.dishes.api.common.service;

import com.example.morphtin.dishes.bean.MaterialBean;
import com.example.morphtin.dishes.bean.MenuBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.POST;

/**
 * Created by elevation on 18-5-15.
 */

public interface IMatchService {
    @POST("api/match")
    Flowable<List<MenuBean>> match(List<MaterialBean> data);
}
