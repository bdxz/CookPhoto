package com.example.morphtin.dishes.api.presenter;

import com.example.morphtin.dishes.api.contract.MenuContract;
import com.example.morphtin.dishes.bean.MenuBean;
import com.example.morphtin.dishes.ui.activity.MenuListActivity;

/**
 * Created by elevation on 18-5-14.
 */

public class MenuPresenter implements MenuContract.Presenter {
    private MenuContract.View view;

    public MenuPresenter(MenuContract.View view) {
        this.view = view;
    }

    @Override
    public void loadDetail(String menu_id) {

    }

    @Override
    public void addMenu(MenuBean menu) {

    }
}
