package com.example.morphtin.dishes.api.common.service;

import com.example.morphtin.dishes.bean.MaterialBean;
import com.example.morphtin.dishes.bean.MenuBean;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by elevation on 18-5-15.
 */

public interface IMatchService {
    @POST("api/match")
    Observable<List<MenuBean>> match(@Body List<MaterialBean> data);
}
