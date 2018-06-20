package com.example.morphtin.dishes;

import android.app.Application;

import com.example.morphtin.dishes.common.Constant;
import com.example.morphtin.dishes.common.URL;

import java.net.URISyntaxException;

import cn.jpush.android.api.JPushInterface;
import io.socket.client.IO;
import io.socket.client.Socket;

/**
 * Created by elevation on 18-4-5.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.init(this);
    }

    private Socket mSocket;
    {
        try {
            if(Constant.DEBUG){
                mSocket = IO.socket(URL.HOST_URL_DEBUG);
            }else{
                mSocket = IO.socket(URL.HOST_URL_CUSTOM);
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket() {
        return mSocket;
    }
}
