package com.test.viewdemo.slideDemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * Created by xuyabo on 2017/9/10.
 */

public class MyScrollView extends FrameLayout {
    private int mPointActivtyId = -1;
    private int mTouchSlop = -1;//用来判断手势移动的距离是否达到滑动的标准
    private float mLastY = -1;
    private Scroller mScroller;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mTouchSlop < 0) {
            //getScaledTouchSlop()，一个距离常量，用来判断用户的行为是否是滑动。手势滑动的距离大于这个值
            //就认为是滑动。
            mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        }
        if(mScroller==null){
            mScroller=new Scroller(getContext());
        }
        //int action = MotionEventCompat.getActionMasked(event);
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mPointActivtyId = event.getPointerId(0);
                mLastY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int pointIndex = event.findPointerIndex(mPointActivtyId);
                if (pointIndex == -1) {
                    break;
                }
                float y = event.getRawY();
                float dy = y - mLastY;
                scrollBy(0,(int )-dy);
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mPointActivtyId = -1;
                mLastY = -1;
                break;
        }
        return true;
    }
}
