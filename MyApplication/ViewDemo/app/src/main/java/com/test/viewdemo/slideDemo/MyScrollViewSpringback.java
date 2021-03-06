package com.test.viewdemo.slideDemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.VelocityTrackerCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * Created by xuyabo on 2017/9/10.
 * Scroller.start()实现回弹
 */

public class MyScrollViewSpringback extends FrameLayout {
    private static final String TAG = "MyScrollView";
    private int mPointActivtyId = -1;
    private int mTouchSlop = -1;//用来判断手势移动的距离是否达到滑动的标准
    private float mLastY = -1;
    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;

    public MyScrollViewSpringback(Context context) {
        this(context, null);
    }

    public MyScrollViewSpringback(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollViewSpringback(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mScroller = new Scroller(getContext());
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mTouchSlop < 0) {
            //getScaledTouchSlop()，一个距离常量，用来判断用户的行为是否是滑动。手势滑动的距离大于这个值
            //就认为是滑动。
            mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        }


        if (mScroller == null) {
            mScroller = new Scroller(getContext());
        }
        //int action = MotionEventCompat.getActionMasked(event);
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                int index= event.getActionIndex();
                mPointActivtyId = event.getPointerId(index);
                mLastY = event.getY();
                if (mVelocityTracker == null) {
                    mVelocityTracker = VelocityTracker.obtain();
                } else {
                    mVelocityTracker.clear();
                }
                mVelocityTracker.addMovement(event);
                break;
            case MotionEvent.ACTION_MOVE:
                mVelocityTracker.addMovement(event);
                int pointIndex = event.findPointerIndex(mPointActivtyId);
                if (pointIndex == -1) {
                    break;
                }
                float y = event.getY();
                float dy = y - mLastY;
                scrollBy(0, (int) -dy);//距离设置dy的相值
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:


                mVelocityTracker.computeCurrentVelocity(1000);
                float yvel = VelocityTrackerCompat.getYVelocity(mVelocityTracker,
                        mPointActivtyId);
                Log.i(TAG, "onTouchEvent: " + yvel);

                int dyTo;
                if(yvel>0){
                    dyTo=200;
                }else{
                    dyTo=-200;
                }

                mScroller.startScroll(getScrollX(),getScrollY(),0, dyTo);

                mPointActivtyId = -1;
                mLastY = -1;
                break;
            case MotionEvent.ACTION_CANCEL:
                mPointActivtyId = -1;
                mLastY = -1;
                mVelocityTracker.recycle();
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
            Log.i(TAG, "computeScroll: 重绘");
        }
    }
}
