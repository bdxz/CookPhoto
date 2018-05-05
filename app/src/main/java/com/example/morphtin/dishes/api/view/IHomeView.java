package com.example.morphtin.dishes.api.view;

import com.example.morphtin.dishes.bean.BannerItem;

import java.util.List;

/**
 * Created by elevation on 18-5-5.
 */

public interface IHomeView {
    void showProgress();
    void hideProgress();
    void updateView(List<BannerItem> data);
    void showMessage(String msg);
}
