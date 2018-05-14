package com.example.morphtin.dishes.api.contract;

import com.example.morphtin.dishes.bean.MaterialBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elevation on 18-5-14.
 */

public interface MaterialContract {
    interface View{
        void showMaterials(List<MaterialBean> data);

        void showAddUI();

        void showMenusUI();
    }

    interface Presenter{
        void loadSelected();

        void matchMenus(List<MaterialBean> data);

        void unselect(MaterialBean data);
    }
}
