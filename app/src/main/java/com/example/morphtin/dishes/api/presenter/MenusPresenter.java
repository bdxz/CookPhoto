package com.example.morphtin.dishes.api.presenter;

import com.example.morphtin.dishes.api.contract.MenusContract;
import com.example.morphtin.dishes.bean.MenuBean;
import com.example.morphtin.dishes.ui.activity.MenuListActivity;

/**
 * Created by elevation on 18-5-14.
 */

public class MenusPresenter implements MenusContract.Presenter {
    private MenusContract.View view;

    public MenusPresenter(MenusContract.View view) {
        this.view = view;
    }

    @Override
    public void loadMenus() {

    }

    @Override
    public void loadMatchMenu() {

    }

    @Override
    public void openDetail(MenuBean menu) {

    }
}
