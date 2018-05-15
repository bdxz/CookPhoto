package com.example.morphtin.dishes.api.model;

import com.example.morphtin.dishes.bean.MaterialBean;
import com.example.morphtin.dishes.bean.MenuBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observer;

/**
 * Created by elevation on 18-4-25.
 */

public interface IMaterialModel {
    Flowable<List<MaterialBean>> getMaterials();

    Flowable<List<MaterialBean>> getMaterials(ArrayList<String> photoPaths);

    Flowable<List<MaterialBean>> getSelected();

    void setSelected(List<MaterialBean> data);

    void setUnselect(MaterialBean data);
}
