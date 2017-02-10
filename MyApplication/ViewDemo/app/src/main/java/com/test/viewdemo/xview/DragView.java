package com.test.viewdemo.xview;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/2/10.
 */
public class DragView extends View {

    float mLastX;
    float mLastY;

    public DragView(Context context) {
        super(context);
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float nowX=event.getRawX();
        float nowY=event.getRawY();
        int action= MotionEventCompat.getActionMasked(event);
        switch (action){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                float delatX=nowX-mLastX;//当前手势的触摸点与上次手势的触摸点的差值
                float delatY=nowY-mLastY;
                setTranslationX(delatX+getTranslationX());
                setTranslationY(delatY+getTranslationY());
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        mLastX=nowX;
        mLastY=nowY;
        return true;
    }
}
