package com.example.xuyabo.androidperformance;

import android.app.Application;

import com.example.xuyabo.androidperformance.smoothDetection.SmoothDetectionCallack;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by xuyabo on 2018/3/8.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        SmoothDetectionCallack.start(this);
        registerActivityLifecycleCallbacks(new SmoothDetectionCallack.SDActivityLifeRecyclerBack());
        if (LeakCanary.isInAnalyzerProcess(this)) {//1
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }

        LeakCanary.install(this);

    }
}
