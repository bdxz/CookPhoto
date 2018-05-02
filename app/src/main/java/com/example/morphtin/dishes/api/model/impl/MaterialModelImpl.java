package com.example.morphtin.dishes.api.model.impl;

import android.util.Log;

import com.example.morphtin.dishes.api.common.ServiceFactory;
import com.example.morphtin.dishes.api.common.service.IUploadPicService;
import com.example.morphtin.dishes.api.model.IMaterialModel;
import com.example.morphtin.dishes.bean.MaterialBean;
import com.example.morphtin.dishes.common.Constant;
import com.example.morphtin.dishes.common.URL;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by elevation on 18-4-25.
 */

public class MaterialModelImpl implements IMaterialModel {
    private static final String TAG = "MaterialModelImpl";

    @Override
    public void loadMaterialList(ArrayList<String> photoPaths, final Observer<List<MaterialBean>> listener) {
        IUploadPicService service;
        if(Constant.DEBUG){
            service = ServiceFactory.createService(URL.HOST_URL_DEBUG,IUploadPicService.class);
        }else{
            service = ServiceFactory.createService(URL.HOST_URL_CUSTOM,IUploadPicService.class);
        }

        List<MultipartBody.Part> partList = new ArrayList<>();
        for (String photoPath: photoPaths) {
            File file = new File(photoPath);
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);


            partList.add(MultipartBody.Part.createFormData("photo",file.getName(),requestBody));
        }

        service.getMaterials(partList).subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {

            }
        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe(listener);
    }
}
