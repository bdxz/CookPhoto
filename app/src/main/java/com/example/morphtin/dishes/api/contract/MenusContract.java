package com.example.morphtin.dishes.api.contract;

import com.example.morphtin.dishes.bean.MenuBean;

import java.util.List;

/**
 * Created by elevation on 18-5-14.
 */

public interface MenusContract {
    interface View{
        void showMenus(List<MenuBean> data);

        void showDetailUI(String menu_id);
    }
    interface Presenter{
        void loadMenus();

        void loadMatchMenu();

        void openDetail(MenuBean menu);
    }
}

