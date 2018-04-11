package com.example.morphtin.dishes.api.service;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Url;

/**
 * Created by elevation on 18-4-9.
 */

public interface UploadPicService {
    @Multipart
    @POST()
    Observable<ResponseBody> uploadFiles(
            @Url String url,
            @PartMap() Map<String, RequestBody> maps);
}
