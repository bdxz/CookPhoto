package com.example.morphtin.dishes.api.presenter;

import com.example.morphtin.dishes.bean.MaterialBean;

import java.util.ArrayList;

/**
 * Created by elevation on 18-4-25.
 */

public interface IMaterialPresenter {
    void loadMaterials(ArrayList<String> photoPaths);
}
