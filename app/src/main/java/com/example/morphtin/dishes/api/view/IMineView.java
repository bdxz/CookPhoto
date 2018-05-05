package com.example.morphtin.dishes.api.view;

import com.example.morphtin.dishes.bean.MaterialBean;
import com.example.morphtin.dishes.bean.UserInfoBean;

import java.util.List;

/**
 * Created by elevation on 18-5-5.
 */

public interface IMineView {
    void showProgress();
    void hideProgress();
    void updateView(UserInfoBean data);
    void showMessage(String msg);
}
