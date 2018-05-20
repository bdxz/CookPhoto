package com.example.morphtin.dishes.api.contract;

import com.example.morphtin.dishes.bean.MaterialBean;

import java.util.ArrayList;

/**
 * Created by elevation on 18-5-14.
 */

public interface ChooseContract {
    interface View{
        void showMaterials(ArrayList<MaterialBean> data);

        void showSelected(ArrayList<MaterialBean> data);
    }
    interface Presenter{
        void loadAllMaterials();

        void select(ArrayList<MaterialBean> data);

        void selectByPhoto(ArrayList<String> photoPaths);
    }
}
