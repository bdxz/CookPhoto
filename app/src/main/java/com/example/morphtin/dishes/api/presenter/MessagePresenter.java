package com.example.morphtin.dishes.api.presenter;

import com.example.morphtin.dishes.api.contract.MessageContract;
import com.example.morphtin.dishes.api.model.IMessageModel;
import com.example.morphtin.dishes.api.model.impl.MessageModelImpl;
import com.example.morphtin.dishes.bean.Message;

/**
 * Created by elevation on 18-6-20.
 */

public class MessagePresenter implements MessageContract.Presenter{
    private IMessageModel mMessageModel;
    private MessageContract.View mMessageView;

    public MessagePresenter(MessageContract.View mMessageView) {
        mMessageModel = MessageModelImpl.getInstance();
        this.mMessageView = mMessageView;
    }

    @Override
    public void loadMessage() {
        mMessageView.showMessage(mMessageModel.getMessage());
    }
}
