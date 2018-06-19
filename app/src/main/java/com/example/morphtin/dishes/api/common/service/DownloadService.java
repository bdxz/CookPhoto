package com.example.morphtin.dishes.api.common.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadTask;
import com.example.morphtin.dishes.util.DownloadNotificationUtil;
import com.example.morphtin.dishes.util.NotificationUtil;

public class DownloadService extends Service {

    static final int Flag_Init = 0; // 初始状态
    static final int Flag_Down = 1; // 下载状态
    static final int Flag_Pause = 2; // 暂停状态
    static final int Flag_Done = 3; // 完成状态

    private DownloadNotificationUtil downloadNotificationUtil;

    public DownloadService() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Aria.download(this)
                .load("http://yaobeier.cn ")
                .setFilePath(Environment.getExternalStorageDirectory().getPath()+"/menu-list")
                .start();
        downloadNotificationUtil  = new DownloadNotificationUtil(getBaseContext());
        downloadNotificationUtil.showNotification(100);


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
