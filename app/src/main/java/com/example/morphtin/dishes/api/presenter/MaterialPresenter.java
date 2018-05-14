package com.example.morphtin.dishes.api.presenter;

import com.example.morphtin.dishes.api.contract.MaterialContract;
import com.example.morphtin.dishes.bean.MaterialBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elevation on 18-5-14.
 */

public class MaterialPresenter implements MaterialContract.Presenter {
    private MaterialContract.View view;

    public MaterialPresenter(MaterialContract.View view) {
        this.view = view;
    }

    @Override
    public void loadSelected() {

    }

    @Override
    public void matchMenus(List<MaterialBean> data) {

    }

    @Override
    public void unselect(MaterialBean data) {

    }
}
