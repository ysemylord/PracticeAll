package com.test.viewdemo.nestedScrollDemo.practice;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by xuyabo on 2018/4/27.
 */

public class MyNestedScrollingParent extends LinearLayout implements NestedScrollingParent {
    private NestedScrollingParentHelper mNestedScrollingParentHelper;
    private View mFirstView;
    private View mSecondView;
    public MyNestedScrollingParent(Context context) {
        this(context,null);
    }

    public MyNestedScrollingParent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        if (mNestedScrollingParentHelper == null) {
            mNestedScrollingParentHelper=new NestedScrollingParentHelper(this);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mFirstView=getChildAt(0);
        mSecondView=getChildAt(1);
    }

    /**
     * 返回true表示支持嵌套滑动
     * @param child
     * @param target
     * @param nestedScrollAxes
     * @return
     */
    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        Log.i("TEST", "onStartNestedScroll()");
        return true;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        Log.i("TEST", "onNestedScrollAccepted()");
        mNestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes);
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.i("TEST", "onNestedScrollAccepted()");
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
               if(consume(dy)){
                   consumed[1]=dy/2;
                   scrollBy(0,consumed[1]);
               }
    }

    private boolean consume(int dy) {
        //下面这个数学计算抄的
        if(dy < 0){
            if(getScrollY() > 0 && mSecondView.getScrollY() <= 0){
                return true;
            }
        }
        if(dy > 0){
            if(getScrollY() < mFirstView.getHeight()){
                return true;
            }
        }

        return false;
    }

    @Override
    public void onStopNestedScroll(View child) {
        Log.i("TEST", "onStopNestedScroll()");
        mNestedScrollingParentHelper.onStopNestedScroll(child);
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        Log.i("TEST", "onNestedPreFling()");
        return false;
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        Log.i("TEST", "onNestedFling()");
        return false;
    }

    @Override
    public int getNestedScrollAxes() {

        Log.i("TEST", "getNestedScrollAxes()");

        return mNestedScrollingParentHelper.getNestedScrollAxes();
    }
}
