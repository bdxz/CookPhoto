package com.example.morphtin.dishes.api.common.service;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

/**
 * Created by elevation on 18-4-9.
 */

public interface UploadPicService {
    @Multipart
    @POST()
    Observable<ResponseBody> uploadFiles(@Url() String url, @Part() List<MultipartBody.Part> parts);
}
