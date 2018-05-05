package com.example.morphtin.dishes.api.common.service;

import com.example.morphtin.dishes.bean.MenuBean;
import com.example.morphtin.dishes.bean.http.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by elevation on 18-5-4.
 */

public interface IMenuService {
    @POST("api/menu/add")
    Observable<BaseResponse> addMenu(@Body MenuBean menu);
}
