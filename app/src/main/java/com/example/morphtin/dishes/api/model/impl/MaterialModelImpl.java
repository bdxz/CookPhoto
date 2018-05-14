package com.example.morphtin.dishes.api.model.impl;

import com.example.morphtin.dishes.api.common.ServiceFactory;
import com.example.morphtin.dishes.api.common.service.IMaterialService;
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

/**
 * Created by elevation on 18-4-25.
 */

public class MaterialModelImpl implements IMaterialModel {
    private static final String TAG = "MaterialModelImpl";

    private IMaterialService service;

    public MaterialModelImpl() {
        if(Constant.DEBUG){
            service = ServiceFactory.createService(URL.HOST_URL_DEBUG,IMaterialService.class);
        }else{
            service = ServiceFactory.createService(URL.HOST_URL_CUSTOM,IMaterialService.class);
        }
    }

    @Override
    public void loadMaterialList(ArrayList<String> photoPaths, final Observer<List<MaterialBean>> listener) {
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
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(listener);
    }

    @Override
    public void loadMaterialList(Observer<List<MaterialBean>> listener) {
        service.getMaterials();
    }
}
