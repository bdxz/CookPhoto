package com.example.morphtin.dishes.api.presenter;

import com.example.morphtin.dishes.api.contract.HomeContract;
import com.example.morphtin.dishes.ui.fragment.HomeFragment;

/**
 * Created by elevation on 18-5-14.
 */

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View view;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void load() {

    }
}
