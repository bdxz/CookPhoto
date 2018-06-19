package com.example.morphtin.dishes.api.model.impl;

import com.example.morphtin.dishes.api.common.ServiceFactory;
import com.example.morphtin.dishes.api.common.service.IUserService;
import com.example.morphtin.dishes.api.model.IUserModel;
import com.example.morphtin.dishes.bean.User;
import com.example.morphtin.dishes.common.Constant;
import com.example.morphtin.dishes.common.URL;

import io.reactivex.Flowable;

public class UserModelImp implements IUserModel{
    private static final String TAG = "UserModelImpl";
    private static final UserModelImp ourInstance = new UserModelImp();

    private IUserService userService;
    public static UserModelImp getInstance() {
        return ourInstance;
    }

    public UserModelImp(){
        if(Constant.DEBUG){
            userService = ServiceFactory.createService(URL.HOST_URL_DEBUG,IUserService.class);
        }else{
            userService = ServiceFactory.createService(URL.HOST_URL_CUSTOM,IUserService.class);
        }
    }

    @Override
    public Flowable<User> login(String phone, String pwd){
        return userService.get(phone);
    }

}
