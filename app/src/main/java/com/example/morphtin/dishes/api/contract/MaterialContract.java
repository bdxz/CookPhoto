package com.example.morphtin.dishes.api.contract;

import com.example.morphtin.dishes.bean.MaterialBean;

import java.util.ArrayList;

/**
 * Created by elevation on 18-5-14.
 */

public interface MaterialContract {
    interface View{
        void showMaterials(ArrayList<MaterialBean> data);

        void showAddUI();

        void showMenusUI();
    }

    interface Presenter{
        void loadSelected();

        void matchMenus(ArrayList<MaterialBean> data);

        void unselect(MaterialBean data);
    }
}
