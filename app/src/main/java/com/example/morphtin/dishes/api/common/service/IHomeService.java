package com.example.morphtin.dishes.api.common.service;

import com.example.morphtin.dishes.bean.MenuBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by elevation on 18-5-15.
 */

public interface IHomeService {
    @GET("api/home/banner")
    Flowable<List<MenuBean>> getBanner();
}
