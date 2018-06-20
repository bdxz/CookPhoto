package com.example.morphtin.dishes.api.model;

import com.example.morphtin.dishes.bean.Message;

import java.util.List;

/**
 * Created by elevation on 18-6-20.
 */

public interface IMessageModel {
    List<Message> getMessage();

    void addMessage(Message m);
}
