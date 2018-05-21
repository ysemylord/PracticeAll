package com.example.xuyabo.androidperformance.time;

import android.content.Context;

import java.util.HashMap;

/**
 *管理所有的TimeMonitor
 */
public class TimeMonitorManager {
    private static TimeMonitorManager mTimeMonitorManager = null;
    private static Context mContext  = null;
    private HashMap<String,TimeMonitor> timeMonitorList = null;//存储所有的TimeMonitor,TimeMonitor的ID->TimeMonitor
    public synchronized static TimeMonitorManager getInstance(){
        if(mTimeMonitorManager == null){
           mTimeMonitorManager = new TimeMonitorManager();
        }
        return mTimeMonitorManager;
    }
    public TimeMonitorManager(){
        timeMonitorList = new HashMap<String,TimeMonitor>();
    }

    public void resetTimeMonitor(String monitorId){
        if(timeMonitorList.get(monitorId) != null){
            timeMonitorList.remove(monitorId);
        }
        getTimeMonitor(monitorId);
    }
    public TimeMonitor getTimeMonitor(String monitorId){
        TimeMonitor monitor = timeMonitorList.get(monitorId);
        if(monitor == null){
            monitor = new TimeMonitor(monitorId);
            timeMonitorList.put(monitorId,monitor);
        }
       return monitor;
    }
}
