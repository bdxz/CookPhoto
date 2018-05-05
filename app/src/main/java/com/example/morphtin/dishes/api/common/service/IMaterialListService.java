package com.example.morphtin.dishes.api.common.service;

import com.example.morphtin.dishes.bean.MaterialBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by elevation on 18-4-9.
 */

public interface IMaterialListService {
    @Multipart
    @POST("api/get_materials")
    Observable<List<MaterialBean>> getMaterials(@Part() List<MultipartBody.Part> parts);
}