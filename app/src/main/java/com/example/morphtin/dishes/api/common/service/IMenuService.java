package com.example.morphtin.dishes.api.common.service;

import com.example.morphtin.dishes.bean.MenuBean;
import com.example.morphtin.dishes.bean.http.BaseResponse;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by elevation on 18-5-4.
 */

public interface IMenuService {
    @POST("api/menu/add")
    Flowable<BaseResponse> add(@Body MenuBean menu);

    @GET("api/menu/get")
    Flowable<MenuBean> get(@Query("menu_id") String menu_id);

    @GET("api/menu/download")
    Flowable<MenuBean> download(@Query("menu_id") String menu_id);

}
