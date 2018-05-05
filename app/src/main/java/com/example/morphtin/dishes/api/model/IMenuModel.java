package com.example.morphtin.dishes.api.model;

import com.example.morphtin.dishes.bean.MenuBean;
import com.example.morphtin.dishes.bean.http.BaseResponse;

import io.reactivex.Observer;

/**
 * Created by elevation on 18-5-4.
 */

public interface IMenuModel {
    void uploadMenu(MenuBean menu, Observer<BaseResponse> listener);
}
