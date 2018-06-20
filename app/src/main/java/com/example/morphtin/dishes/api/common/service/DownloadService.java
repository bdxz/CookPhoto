package com.example.morphtin.dishes.api.common.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadTask;
import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.util.DownloadNotificationUtil;
import com.example.morphtin.dishes.util.NotificationUtil;

public class DownloadService extends Service {

    static final int Flag_Init = 0; // 初始状态
    static final int Flag_Down = 1; // 下载状态
    static final int Flag_Pause = 2; // 暂停状态
    static final int Flag_Done = 3; // 完成状态

    private DownloadNotificationUtil downloadNotificationUtil;
    NotificationManager notifyManager;
    NotificationCompat.Builder builder;

    String url = "";
    String menu_id = "";
    String name = "";

    public DownloadService() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Aria.download(getBaseContext()).register();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        url = intent.getStringExtra("url");
        menu_id = intent.getStringExtra("menu_id");
        name = intent.getStringExtra("name");

        Aria.download(this)
                .load(url)
                .setFilePath(getBaseContext().getFilesDir().getAbsolutePath()+"/"+menu_id+".jpg")
                .start();
//        downloadNotificationUtil  = new DownloadNotificationUtil(getBaseContext());
//        downloadNotificationUtil.showNotification(100);
        notifyManager  = (NotificationManager) getSystemService(getBaseContext().NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(this)
                //设置小图标
                .setSmallIcon(R.mipmap.icon)
                //设置通知标题
                .setContentTitle("菜谱-"+name+"下载")
                .setProgress(100,0,false)
                //设置通知内容
                .setContentText("开始下载");
        //设置通知时间，默认为系统发出通知的时间，通常不用设置
        //.setWhen(System.currentTimeMillis());
        //通过builder.build()方法生成Notification对象,并发送通知,id=1
        notifyManager.notify(1, builder.build());

        new Thread(new Runnable() {
             @Override
             public void run() {
                                for(int i=0;i<100;i++){
                                         builder.setProgress(100,i,false);
                                         notifyManager.notify(1,builder.build());
                                      //下载进度提示
                                         builder.setContentText("下载"+i+"%");
                                        try {
                                                Thread.sleep(50);//演示休眠50毫秒
                                            } catch (InterruptedException e) {
                                               e.printStackTrace();
                                           }
                                   }
                                   //下载完成后更改标题以及提示信息
                 builder.setContentTitle(name+"-开始下载");
                 builder.setContentText("下载中...");
                 //设置进度为不确定，用于模拟安装
                 builder.setProgress(0,0,true);
                 notifyManager.notify(1,builder.build());
                 notifyManager.cancel(1);//设置关闭通知栏
             }

         }).start();
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
        url = intent.getStringExtra("url");
        menu_id = intent.getStringExtra("menu_id");
        return null;
    }

    //在这里处理任务执行中的状态，如进度进度条的刷新
    @Download.onTaskStart public void running(DownloadTask task) {

            //可以通过url判断是否是指定任务的回调
            Log.d("download-url", "running: "+task.getDownloadUrl()+url);
            int p = task.getPercent();	//任务进度百分比
            builder.setProgress(100,p,false);
            String speed = task.getConvertSpeed();	//转换单位后的下载速度，单位转换需要在配置文件中打开
            String speed1 = String.valueOf(task.getSpeed()); //原始byte长度速度

    }

    @Download.onTaskComplete public void taskComplete(DownloadTask task) {
        //在这里处理任务完成的状态
        builder.setProgress(100,100,false);
        builder.setContentText("下载完成");
    }
}
