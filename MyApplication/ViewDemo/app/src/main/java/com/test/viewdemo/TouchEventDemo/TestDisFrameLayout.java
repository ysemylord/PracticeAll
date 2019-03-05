package com.test.viewdemo.TouchEventDemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by xuyabo on 2018/5/13.
 */

public class TestDisFrameLayout extends ScrollView {
    public TestDisFrameLayout(@NonNull Context context) {
        super(context);
    }

    public TestDisFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(ev.getAction()==MotionEvent.ACTION_DOWN){
            Log.i("TestDisFrameLayout", "onInterceptTouchEvent: false "+MotionEventHelper.getActionString(ev));
            return false;
        }else{
            Log.i("TestDisFrameLayout", "onInterceptTouchEvent: false "+MotionEventHelper.getActionString(ev));
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("TestDisFrameLayout", "onTouchEvent: "+MotionEventHelper.getActionString(event));
        return true;
    }
}
