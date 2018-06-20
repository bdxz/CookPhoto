package com.example.morphtin.dishes.api.contract;

import com.example.morphtin.dishes.bean.MaterialBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elevation on 18-5-14.
 */

public interface ChooseContract {
    interface View{
        void showMaterials(List<MaterialBean> data);

        void showSelected(List<MaterialBean> data);

        void showLoading();

        void cancelLoading();
    }
    interface Presenter{
        void loadAllMaterials();

        void select(List<MaterialBean> data);

        void selectByPhoto(ArrayList<String> photoPaths);
    }
}
