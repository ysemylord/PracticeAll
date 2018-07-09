package com.example.xuyabo.fourcomponent.serveiDemo;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.example.xuyabo.fourcomponent.R;

public class MyService extends Service {
    private static final String TAG = "AddService";
    private Thread mThread;
    private boolean mStop = false;
    private int mCount=0;
    private Notification mNotification;
    private final int RELATEDNOTIFYID = 1;


    public MyService() {
    }


    @Override
    public void onCreate() {
        init();
        Log.i(TAG, "call onCreate...");
    }

    private void init() {
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!mStop) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mCount++;
                    Log.i(TAG, "run: 发送请求");
                }
            }
        });

        forground();
    }

    /**
     * 设置为前台服务
     */
    private void forground() {

        Intent intent = new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER)
                .setComponent(getPackageManager().getLaunchIntentForPackage(getPackageName()).getComponent());
        PendingIntent contentIntent = PendingIntent.getActivity(getBaseContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //将本服务设置为前台服务
        NotificationCompat.Builder mNotificationbuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setPriority(Notification.PRIORITY_MAX)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("我送")
                .setContentText("随时为你服务")
                .setContentIntent(contentIntent);


        mNotification = mNotificationbuilder.build();

        startForeground(RELATEDNOTIFYID, mNotification);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "call onStartCommand...");
        Log.i(TAG, "onStartCommand: "+flags);
        mThread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        mStop = true;
        Log.i(TAG, "call onDestroy...");
    }

    // 添加一个类继承Binder
    public  class MyBinder extends Binder {
        // 添加要与外界交互的方法
        public int  getCountInfo(){
            return mCount;
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "call onBind...");
        return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "call onUnbind...");
        return super.onUnbind(intent);
    }
}
