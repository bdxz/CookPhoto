package com.example.morphtin.dishes.api.presenter;

import com.example.morphtin.dishes.api.contract.UserContract;

public class UserPresenter implements UserContract.Presenter{

    @Override
    public boolean login(String phone, String pwd){
            return  true;
    }

    @Override
    public boolean register(String phone, String pwd){
        return  true;
    }
}
