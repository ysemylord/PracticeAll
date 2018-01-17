package com.example.xuyabo.fourcomponent.serveiDemo;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.xuyabo.fourcomponent.MainActivity;

public class MyService extends Service {
    private static final String TAG = "MyService";
    private Thread mThread;
    private boolean mStop = false;
    private int mCount=0;

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
        Notification.Builder builder = new Notification.Builder(this.getApplicationContext()); //获取一个Notification构造器
        Intent nfIntent = new Intent(this, MainActivity.class);

        Notification notification = builder.build(); // 获取构建好的Notification
        startForeground(1,notification);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "call onStartCommand...");
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
