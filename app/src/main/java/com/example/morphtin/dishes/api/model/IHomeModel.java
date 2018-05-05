package com.example.morphtin.dishes.api.model;

import com.example.morphtin.dishes.bean.BannerItem;

import java.util.List;

import io.reactivex.Observer;

/**
 * Created by elevation on 18-5-5.
 */

public interface IHomeModel {
    void loadHomeInfo(Observer<List<BannerItem>> listener);
}
