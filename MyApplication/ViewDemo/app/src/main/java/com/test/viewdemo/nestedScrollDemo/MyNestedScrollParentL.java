package com.test.viewdemo.nestedScrollDemo;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyNestedScrollParentL extends LinearLayout implements NestedScrollingParent {

    private ImageView mImage;
    private TextView mTvTitle;
    private MyNestedScrollChildL mMyNestedScrollChildL;

    private int mImageHeight;
    private int mTvTitleHeight;

    private NestedScrollingParentHelper mNestedScrollingParentHelper;

    public MyNestedScrollParentL(Context context) {
        super(context);
        init();
    }

    public MyNestedScrollParentL(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);  //相关事情都交给NestedScrollingParentHelper做
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mImage = (ImageView)getChildAt(0);//获取第一个视图
        mTvTitle = (TextView)getChildAt(1);
        mMyNestedScrollChildL = (MyNestedScrollChildL)getChildAt(2);

//OnGlobalLayoutListener 是ViewTreeObserver的内部类，当一个视图树的布局发生改变时，可以被ViewTreeObserver监听到

//获取图片的高度
        mImage.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(mImageHeight <= 0){
                    mImageHeight = mImage.getMeasuredHeight();
                    Log.i("TEST", "mImageHeight:" + mImageHeight);
                }
            }
        });

 //获取标题的高度     
   mTvTitle.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(mTvTitleHeight <= 0){
                    mTvTitleHeight = mTvTitle.getMeasuredHeight();
                    Log.i("TEST", "mTvTitleHeight:" + mTvTitleHeight);
                }
            }
        });

    }

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
        Log.i("TEST", "onNestedPreScroll()");
//父布局中除去child部分之外的视图
        if(isShowImage(dy) || isHideImage(dy)){
            consumed[1] = dy/2;//消耗一半
            scrollBy(0, dy);     // scrollBy 其实会调用我们重写的scrollTo 方法；
        }
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

        return 0;
    }

    @Override
    public void scrollTo(int x, int y) {

        Log.i("TEST", "scrollTo():" + "x=" + x + "__y=" + y);

        if( y < 0){ //父布局中除去child部分之外的视图保持不动
            y = 0;
        }
        if(y >= mImageHeight){//父布局中除去child部分之外的视图保持图片的高度
            y = mImageHeight;
        }
        super.scrollTo(x, y);
    }

    /**
     * 判断下拉的时候是否需要向下滑动显示图片
     * 下拉的时候 dy < 0
     */
    public boolean isShowImage(int dy){

        Log.i("TEST", "getScrollY():" + getScrollY());
        Log.i("TEST", "mMyNestedScrollChildL.getScrollY():" + mMyNestedScrollChildL.getScrollY());

        // dy< 0 表示在下拉；
        if(dy < 0){
            if(getScrollY() > 0 && mMyNestedScrollChildL.getScrollY() == 0){
                return true;
            }
        }
        return false;
    }

    /**
     * 上滑的时候；
     * @param dy> 0 表示在上滑
     * @return
     */
   public boolean isHideImage(int dy){

       Log.i("TEST", "getScrollY():" + getScrollY());
       Log.i("TEST", "mMyNestedScrollChildL.getScrollY():" + mMyNestedScrollChildL.getScrollY());

       if(dy > 0){
           if(getScrollY() < mImageHeight){
               return true;
           }
       }
       return false;
   }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("TEST", "onTouchEvent():" + event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i("TEST","dispatchTouchEvent _  getY():getRawY:"+event.getRawY());
        return super.dispatchTouchEvent(event);
    }
}