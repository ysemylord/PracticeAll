package com.test.viewdemo.util;


import android.util.Log;

/**
 * Created  on 2017/7/20.
 *
 * @author xyb
 */

public class CanStopLoopThread {
    private static final String TAG="CanStopLoopThread";
    private Thread thread;
    private volatile boolean stop = false;
    private long sleepTime=1000;

    public CanStopLoopThread(final Runnable continueRunnable) {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (stop) {
                        Log.i(TAG, "线程"+Thread.currentThread()+"开启");
                        return;
                    }
                    Log.i(TAG, "线程"+Thread.currentThread()+"运行");
                    continueRunnable.run();
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void start() {
        thread.start();
    }

    public void setStop() {
        this.stop = true;
    }

    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }
}
