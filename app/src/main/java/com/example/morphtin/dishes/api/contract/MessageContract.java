package com.example.morphtin.dishes.api.contract;

import com.example.morphtin.dishes.bean.MenuBean;
import com.example.morphtin.dishes.bean.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elevation on 18-6-20.
 */

public interface MessageContract {
    interface View{
        void showMessage(List<Message> data);
    }
    interface Presenter{
        void loadMessage();
    }
}
