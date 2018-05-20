package com.example.morphtin.dishes.api.model;

import com.example.morphtin.dishes.bean.MaterialBean;

import java.util.ArrayList;

import io.reactivex.Flowable;

/**
 * Created by elevation on 18-4-25.
 */

public interface IMaterialModel {
    Flowable<ArrayList<MaterialBean>> getMaterials();

    Flowable<ArrayList<MaterialBean>> getMaterials(ArrayList<String> photoPaths);

    Flowable<ArrayList<MaterialBean>> getSelected();

    void setSelected(ArrayList<MaterialBean> data);

    void setUnselect(MaterialBean data);
}
