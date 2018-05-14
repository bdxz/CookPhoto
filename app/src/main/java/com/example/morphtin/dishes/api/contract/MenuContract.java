package com.example.morphtin.dishes.api.contract;

import com.example.morphtin.dishes.bean.MenuBean;

public interface MenuContract {
    interface View{
        void showDetail(MenuBean data);
    }

    interface Presenter{
        void loadDetail(String menu_id);

        void addMenu(MenuBean menu);
    }
}
