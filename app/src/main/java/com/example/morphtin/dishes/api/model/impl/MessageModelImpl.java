package com.example.morphtin.dishes.api.model.impl;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.api.model.IMessageModel;
import com.example.morphtin.dishes.bean.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elevation on 18-6-20.
 */

public class MessageModelImpl implements IMessageModel{
    private static final MessageModelImpl ourInstance = new MessageModelImpl();

    public static MessageModelImpl getInstance() {
        return ourInstance;
    }

    private MessageModelImpl() {
        Message message3 = new Message(R.drawable.chushi, "厨你拍新功能上线了！", "制作你的菜谱，分享到平台，共同体验做菜的乐趣", "2018.6.4");
        Message message4 = new Message(R.drawable.chushi2, "加入厨你拍大家庭！", "厨你拍团队招募，只要你有热情，爱做菜，快来加入我们吧", "2018.6.4");
        data.add(message3);
        data.add(message4);
    }

    private List<Message> data = new ArrayList<>();

    @Override
    public List<Message> getMessage() {
        return data;
    }

    @Override
    public void addMessage(Message m) {
        data.add(m);
    }
}
