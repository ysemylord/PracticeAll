package com.test.viewdemo.RxAndroidDemo;

import android.support.annotation.NonNull;

/**
 * Created by xuyabo on 2018/7/27.
 */
class AppInfo implements Comparable<AppInfo> {
    private String mName;

    public AppInfo(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }


    @Override
    public int compareTo(@NonNull AppInfo appInfo) {
        if (mName.length() > appInfo.mName.length()) {
            return 1;
        } else if (mName.length() < appInfo.mName.length()) {
            return -1;
        }
        return 0;
    }
}
