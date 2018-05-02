package com.example.morphtin.dishes.api.view;

import com.example.morphtin.dishes.bean.MaterialBean;

import java.util.List;

/**
 * Created by elevation on 18-4-25.
 */

public interface IMaterialView {
    void showProgress();
    void hideProgress();
    void updateView(List<MaterialBean> data);
    void showMessage(String msg);
}
