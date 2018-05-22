package com.example.morphtin.dishes.api.model.mock;

import android.util.Log;

import com.example.morphtin.dishes.api.model.IMaterialModel;
import com.example.morphtin.dishes.bean.MaterialBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

/**
 * Created by elevation on 18-5-21.
 */

public class FakeMaterialModel implements IMaterialModel {
    private static final String TAG = "FakeMaterialModel";
    private static final FakeMaterialModel ourInstance = new FakeMaterialModel();

    public static FakeMaterialModel getInstance() {
        return ourInstance;
    }

    private FakeMaterialModel() {
    }

    @Override
    public Flowable<List<MaterialBean>> getMaterials() {
        List<MaterialBean> data = new ArrayList<>();
        MaterialBean material = new MaterialBean();
        material.setCatelog("蔬菜");
        material.setTitle("茄子");
        MaterialBean egg = new MaterialBean();
        egg.setCatelog("蛋");
        egg.setTitle("鸡蛋");
        data.add(egg);
        data.add(material);
        return Flowable.just(data);
    }

    @Override
    public Flowable<List<MaterialBean>> getMaterials(ArrayList<String> photoPaths) {
        List<MaterialBean> data = new ArrayList<>();
        MaterialBean material = new MaterialBean();
        material.setCatelog("蔬菜");
        material.setTitle("茄子");
        data.add(material);
        return Flowable.just(data);
    }

    @Override
    public Flowable<List<MaterialBean>> getSelected() {
        List<MaterialBean> data = new ArrayList<>();
        MaterialBean material = new MaterialBean();
        material.setCatelog("蔬菜");
        material.setTitle("茄子");
        data.add(material);
        return Flowable.just(data);
    }

    @Override
    public void setSelected(List<MaterialBean> data) {

    }

    @Override
    public void setUnselect(MaterialBean data) {

    }
}
