package com.example.morphtin.dishes.api.model.impl;

import com.example.morphtin.dishes.api.common.ServiceFactory;
import com.example.morphtin.dishes.api.common.service.IMaterialService;
import com.example.morphtin.dishes.api.model.IMaterialModel;
import com.example.morphtin.dishes.bean.MaterialBean;
import com.example.morphtin.dishes.common.Constant;
import com.example.morphtin.dishes.common.URL;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.SingleSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by elevation on 18-5-14.
 */

public class MaterialModelImpl implements IMaterialModel {
    private static final MaterialModelImpl ourInstance = new MaterialModelImpl();

    private static List<MaterialBean> selectedMaterials = new ArrayList<>();

    private IMaterialService mMaterialService;

    public static MaterialModelImpl getInstance() {
        return ourInstance;
    }

    private MaterialModelImpl() {
        if (Constant.DEBUG) {
            mMaterialService = ServiceFactory.createService(URL.HOST_URL_DEBUG, IMaterialService.class);
        } else {
            mMaterialService = ServiceFactory.createService(URL.HOST_URL_CUSTOM, IMaterialService.class);
        }

    }

    @Override
    public Flowable<List<MaterialBean>> getMaterials() {
        return mMaterialService.getMaterials().map(new Function<List<MaterialBean>, List<MaterialBean>>() {
            @Override
            public List<MaterialBean> apply(List<MaterialBean> materialBeans) throws Exception {
                for(MaterialBean material:materialBeans){
                    if(selectedMaterials.contains(material)){
                        material.setStatus(true);
                    }
                }
                return materialBeans;
            }
        });
    }

    @Override
    public Flowable<List<MaterialBean>> getMaterials(ArrayList<String> photoPaths) {
        return Flowable.just(photoPaths).flatMap(new Function<ArrayList<String>, Publisher<String>>() {
            @Override
            public Publisher<String> apply(ArrayList<String> strings) throws Exception {
                return Flowable.fromIterable(strings);
            }
        }).map(new Function<String, MultipartBody.Part>() {
            @Override
            public MultipartBody.Part apply(String photoPath) throws Exception {
                File file = new File(photoPath);
                RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);

                return MultipartBody.Part.createFormData("photo", file.getName(), requestBody);
            }
        }).toList().toFlowable().flatMap(new Function<List<MultipartBody.Part>, Publisher<List<MaterialBean>>>() {
            @Override
            public Publisher<List<MaterialBean>> apply(List<MultipartBody.Part> parts) throws Exception {
                return mMaterialService.getMaterials(parts);
            }
        });
    }

    @Override
    public Flowable<List<MaterialBean>> getSelected() {
        return Flowable.just(selectedMaterials);
    }

    @Override
    public void setSelected(List<MaterialBean> data) {
        selectedMaterials = data;
        for (MaterialBean material: data) {
            material.setStatus(true);
        }
    }

    @Override
    public void setUnselect(MaterialBean data) {
        selectedMaterials.remove(data);
    }
}
