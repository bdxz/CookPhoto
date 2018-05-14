package com.example.morphtin.dishes.api.contract;

import com.example.morphtin.dishes.bean.BannerItem;

import java.util.List;

/**
 * Created by elevation on 18-5-14.
 */

public interface HomeContract {
    interface View{
        void setBanner(List<BannerItem> data);
    }

    interface Presenter{
        void load();
    }
}
