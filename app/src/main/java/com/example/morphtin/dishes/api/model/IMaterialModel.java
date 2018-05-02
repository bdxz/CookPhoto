package com.example.morphtin.dishes.api.model;

import com.example.morphtin.dishes.bean.MaterialBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;

/**
 * Created by elevation on 18-4-25.
 */

public interface IMaterialModel {
    void loadMaterialList(ArrayList<String> photoPaths, Observer<List<MaterialBean>> listener);
}
