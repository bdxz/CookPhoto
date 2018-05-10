package com.example.morphtin.dishes.api.view;

import com.example.morphtin.dishes.bean.MenuBean;

/**
 * Created by elevation on 18-5-4.
 */

public interface IMenuView {
    void showProgress();
    void hideProgress();
    void updateView(MenuBean data);
    void showMessage(String msg);
}
