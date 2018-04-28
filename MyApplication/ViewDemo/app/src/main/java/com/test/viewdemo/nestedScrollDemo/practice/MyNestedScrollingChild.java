package com.test.viewdemo.nestedScrollDemo.practice;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by xuyabo on 2018/4/27.
 */

/**
 * NestedScrollingChild中的工作全部交给NestedScrollingChildHelper实现，
 * NestedScrollingChildHelper会通知NestedScrollingChildParent
 */
public class MyNestedScrollingChild extends LinearLayout implements NestedScrollingChild {
    private static final String TAG = "MyNestedScrollingChild";
    private NestedScrollingChildHelper mNestedScrollingChildHelper;
    private float mLastTouchX;
    private float mLastTouchY;
    private int[] mConsumed=new int[2];
    private int[] mOffsetInWindow=new int[2];
    public MyNestedScrollingChild(Context context) {
        super(context, null);
    }

    public MyNestedScrollingChild(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        if (mNestedScrollingChildHelper == null) {
            mNestedScrollingChildHelper = new NestedScrollingChildHelper(this);
            mNestedScrollingChildHelper.setNestedScrollingEnabled(true);
        }
    }


    public void setNestedScrollingEnabled(boolean enabled) {
        mNestedScrollingChildHelper.setNestedScrollingEnabled(enabled);
    }

    public boolean isNestedScrollingEnabled() {
        return mNestedScrollingChildHelper.isNestedScrollingEnabled();
    }

    public boolean startNestedScroll(int axes) {
        return mNestedScrollingChildHelper.startNestedScroll(axes);
    }

    public void stopNestedScroll() {
        mNestedScrollingChildHelper.stopNestedScroll();
    }

    @Override
    public boolean hasNestedScrollingParent() {
        return mNestedScrollingChildHelper.hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed,
                                        int dyUnconsumed, int[] offsetInWindow) {
        return mNestedScrollingChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed,
                dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return mNestedScrollingChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return mNestedScrollingChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return mNestedScrollingChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float x=event.getX();
        float y=event.getY();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                mLastTouchX=x;
                mLastTouchY=y;
                //关键点 通知Parent开始滑动
                startNestedScroll(SCROLL_AXIS_HORIZONTAL);
                break;
            case MotionEvent.ACTION_MOVE:
                int dx= (int) (mLastTouchX-x);
                int dy= (int) (mLastTouchY-y);
                mLastTouchX=x;
                mLastTouchY=y;
                //自己消耗时间前，将事件分发给Parent
                //返回true表示Parent消耗了事件，其实是consume中是否有值大于1来决定的，消耗的偏移量在mConsumed中
                if(dispatchNestedPreScroll(dx,dy,mConsumed, mOffsetInWindow)){
                    Log.i(TAG, "Parent 消耗了 "+mConsumed[1]);
                    int leaveY=dy-mConsumed[1];
                    scrollBy(0,leaveY);//消耗剩余的事件
                    return true;
                }
                scrollBy(0,dy);
                break;
        }
        return true;
    }
}
