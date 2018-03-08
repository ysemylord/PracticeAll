package com.example.xuyabo.androidperformance;

import android.app.Application;

import com.example.xuyabo.androidperformance.smoothDetection.SmoothDetectionCallack;

/**
 * Created by xuyabo on 2018/3/8.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        SmoothDetectionCallack.start(this);
        registerActivityLifecycleCallbacks(new SmoothDetectionCallack.SDActivityLifeRecyclerBack());
    }
}
