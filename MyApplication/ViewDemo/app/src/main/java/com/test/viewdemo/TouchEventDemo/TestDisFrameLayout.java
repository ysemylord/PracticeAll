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
    public boolean onTouchEvent(MotionEvent event) {
        boolean res=super.onTouchEvent(event);
        Log.i("TestDisFrameLayout", "onTouchEvent: "+MotionEventHelper.getActionString(event)+" "+res);
        return res;
    }
}
