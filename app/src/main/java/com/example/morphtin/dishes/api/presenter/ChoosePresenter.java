package com.example.morphtin.dishes.api.presenter;

import com.example.morphtin.dishes.api.contract.ChooseContract;
import com.example.morphtin.dishes.bean.MaterialBean;
import com.example.morphtin.dishes.ui.activity.ChooseMaterialActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elevation on 18-5-14.
 */

public class ChoosePresenter implements ChooseContract.Presenter {
    private ChooseContract.View view;

    public ChoosePresenter(ChooseContract.View view) {
        this.view = view;
    }

    @Override
    public void loadAllMaterials() {

    }

    @Override
    public void select(List<MaterialBean> data) {

    }

    @Override
    public void selectByPhoto(ArrayList<String> photoPaths) {

    }
}
