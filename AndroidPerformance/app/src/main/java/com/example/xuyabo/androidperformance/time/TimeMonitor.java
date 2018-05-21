package com.example.xuyabo.androidperformance.time;

import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * 一个TimeMonitor对应一个模块的记录
 */
public class TimeMonitor {
    private final String TAG = "TimeMonitor";
    private String monitorId = "-1";//要检测的模块的Id,通常使用Activity/Fragment的类名
    private LinkedHashMap<String, Long> mTimeTag = new LinkedHashMap<String, Long>();//模块下某函数，及其记录的时间点
    private long mStartTime = 0;

    public TimeMonitor(String monitorId) {
        Log.i(TAG,"init TimeMonitor id:" + monitorId);
        this.monitorId = monitorId;
        startMoniter();
    }

    public String getMonitorId() {
        return monitorId;
    }

    public void startMoniter() {
        if (mTimeTag.size() > 0) {
            mTimeTag.clear();
        }
        mStartTime = System.currentTimeMillis();
    }

    public void recodingTimeTag(String tag) {
        if (mTimeTag.get(tag) != null) {
            mTimeTag.remove(tag);
        }
        long time = System.currentTimeMillis() - mStartTime;
        Log.i(TAG, tag + ":" + time + "ms");
        mTimeTag.put(tag, time);
    }
    public void end(String tag, boolean writeLog){
        recodingTimeTag(tag);
        end(writeLog);
    }
    private void end(boolean writeLog) {
        if (writeLog) {
            //TODO write local
        }
        testShowData();
    }
    public void testShowData(){
        if(mTimeTag.size() <= 0){
            Log.e(TAG,"mTimeTag is empty!");
            return;
        }
        Iterator iterator = mTimeTag.keySet().iterator();
        while (iterator != null && iterator.hasNext()){
           String tag = (String)iterator.next();
            Log.i(TAG,tag + ":" +  mTimeTag.get(tag));
        }
    }
    public HashMap<String, Long> getTimeTags() {
        return mTimeTag;
    }
}
