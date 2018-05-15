package com.example.morphtin.dishes.api.common.service;

import com.example.morphtin.dishes.bean.MaterialBean;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by elevation on 18-4-9.
 */

public interface IMaterialService {
    @Multipart
    @POST("api/material/get_materials")
    Flowable<List<MaterialBean>> getMaterials(@Part() List<MultipartBody.Part> parts);

    @GET("api/material/get")
    Flowable<List<MaterialBean>> getMaterials();
}
