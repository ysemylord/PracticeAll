package com.test.viewdemo.TouchEventDemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by xuyabo on 2018/5/13.
 */

public class TestDisView extends View {
    public TestDisView(Context context) {
        super(context);
    }

    public TestDisView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.i("TestDisView", "onTouchEvent: "+MotionEventHelper.getActionString(event));
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            return false;
        }else {
            return false;
        }
    }
}
