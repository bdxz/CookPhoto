package com.example.morphtin.dishes.api.presenter.impl;

import com.example.morphtin.dishes.api.model.IMaterialModel;
import com.example.morphtin.dishes.api.presenter.IMaterialPresenter;
import com.example.morphtin.dishes.api.view.IMaterialView;
import com.example.morphtin.dishes.bean.MaterialBean;

import java.util.ArrayList;

/**
 * Created by elevation on 18-4-25.
 */

public class MaterialPresenterImpl implements IMaterialPresenter{
    private IMaterialModel mMaterialModel;
    private IMaterialView mMaterialView;

    public MaterialPresenterImpl(IMaterialView view) {
        this.mMaterialView = view;
    }

    @Override
    public void loadMaterials(ArrayList<String> photoPaths) {
    }
}
