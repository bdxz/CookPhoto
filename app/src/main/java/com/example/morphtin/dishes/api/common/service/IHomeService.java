package com.example.morphtin.dishes.api.common.service;

import com.example.morphtin.dishes.bean.BannerItem;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by elevation on 18-5-5.
 */

public interface IHomeService {
    @GET("api/home/banner")
    Observable<List<BannerItem>> getBanner();
}
