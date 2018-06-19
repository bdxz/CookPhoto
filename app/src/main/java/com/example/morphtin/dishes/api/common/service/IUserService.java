package com.example.morphtin.dishes.api.common.service;

import com.example.morphtin.dishes.bean.User;

import io.reactivex.Flowable;

/**
 * Created by elevation on 18-5-4.
 */

public interface IUserService {
    Flowable<User> get(String phone);
}
