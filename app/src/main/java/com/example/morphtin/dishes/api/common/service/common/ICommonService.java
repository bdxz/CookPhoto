package com.example.morphtin.dishes.api.common.service.common;

import com.example.morphtin.dishes.bean.http.UploadResponse;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by elevation on 18-5-4.
 */

public interface ICommonService {
    @Multipart
    @POST("api/file/upload")
    Observable<UploadResponse> uploadFile(@Part() MultipartBody.Part file);

    @Multipart
    @POST("api/file/uploads")
    Observable<UploadResponse> uploadMultiFile(@Part() List<MultipartBody.Part> parts);

}
