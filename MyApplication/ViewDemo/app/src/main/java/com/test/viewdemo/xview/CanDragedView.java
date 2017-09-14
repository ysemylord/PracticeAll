package com.test.viewdemo.xview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Created by xuyabo on 2017/9/10.
 */

public class CanDragedView extends View {
    private boolean mIsDragging;
    private int mPointActivtyId = -1;
    private int mTouchSlop = -1;//用来判断手势移动的距离是否达到滑动的标准
    private float mLastY = -1;

    public CanDragedView(Context context) {
        super(context);
    }

    public CanDragedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanDragedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mTouchSlop < 0) {
            //getScaledTouchSlop()，一个距离常量，用来判断用户的行为是否是滑动。手势滑动的距离大于这个值
            //就认为是滑动。
            mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
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
                setTranslationY(getTranslationY() + dy);
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
