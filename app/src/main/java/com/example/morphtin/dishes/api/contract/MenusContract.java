package com.example.morphtin.dishes.api.contract;

import com.example.morphtin.dishes.bean.MenuBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elevation on 18-5-14.
 */

public interface MenusContract {
    interface View{
        void showMenus(ArrayList<MenuBean> data);

        void showDetailUI(String menu_id);
    }
    interface Presenter{
        void loadMatchMenus();

        void openDetail(String menu_id);
    }
}

