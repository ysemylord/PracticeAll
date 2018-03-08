package com.example.xuyabo.androidperformance.smoothDetection;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Choreographer;

import com.example.xuyabo.androidperformance.BaseApplication;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class SmoothDetectionCallack implements Choreographer.FrameCallback {
    private static final String TAG = "SmoothDetectionCallack";
    public long lastFrameTimeNanos;
    private short countInSecond = 0;
    private static SmoothDetectionCallack mInstance;
    private static boolean mIsStart = false;

    public static synchronized SmoothDetectionCallack getInstance() {
        if (mInstance == null) {
            mInstance = new SmoothDetectionCallack();
        }
        return mInstance;
    }

    private String mActivityName;

    public static void start() {
        if (!mIsStart) {
            Choreographer.getInstance().postFrameCallback(getInstance());
        } else {
            Log.e(TAG, "SmoothDetectionCallack had called");
        }

    }

    public static void start(BaseApplication baseApplication) {
        baseApplication.registerActivityLifecycleCallbacks(new SDActivityLifeRecyclerBack());
        start();
    }

    private SmoothDetectionCallack() {
    }

    @Override
    public void doFrame(long frameTimeNanos) {
        if (lastFrameTimeNanos == 0) {
            lastFrameTimeNanos = frameTimeNanos;
            Choreographer.getInstance().postFrameCallback(this);
            return;
        }
        long currentFrameTimeNanos = frameTimeNanos;
        // 计算两次doFrame的时间间隔,毫秒
        long value = (currentFrameTimeNanos - lastFrameTimeNanos) / 1000000;
        if (value >= 1000) {//超过一秒
            //正常情况下，每一秒Loop调用的次数为60左右，符合预期
            Log.i(TAG, mActivityName + " loop count per second " + countInSecond);
            countInSecond = 0;
            lastFrameTimeNanos = frameTimeNanos;
        } else {
            countInSecond++;
        }


       /* Log.e(TAG, "两次绘制时间间隔value=" + value +
                "  frameTimeNanos=" + frameTimeNanos +
                "  currentFrameTimeNanos=" + currentFrameTimeNanos
        );*/

        Choreographer.getInstance().postFrameCallback(this);
    }

    public void setActivityName(String activityName) {
        mActivityName = activityName;
    }

    //<editor-fold desc="SDActivityLifeRecyclerBack">

    public static class SDActivityLifeRecyclerBack implements Application.ActivityLifecycleCallbacks {
        private static final String TAG = "MyActivityLifeRecyclerB";

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            Log.i(TAG, "onActivityCreated: ");
            getInstance().setActivityName(activity.getClass().getSimpleName());
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    }
    //</editor-fold>

}