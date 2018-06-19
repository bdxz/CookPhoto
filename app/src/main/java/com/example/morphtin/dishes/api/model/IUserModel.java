package com.example.morphtin.dishes.api.model;

import com.example.morphtin.dishes.bean.User;

import io.reactivex.Flowable;

public interface IUserModel {
    Flowable<User> login(String phone, String pwd);
}
