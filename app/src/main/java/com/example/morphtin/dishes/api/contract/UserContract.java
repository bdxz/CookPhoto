package com.example.morphtin.dishes.api.contract;

public interface UserContract {
    interface View{
        void setLogin();

        void setRegister();
    }

    interface Presenter{
        boolean login(String phone,String pwd);

        boolean register(String phone,String pwd);
    }
}
