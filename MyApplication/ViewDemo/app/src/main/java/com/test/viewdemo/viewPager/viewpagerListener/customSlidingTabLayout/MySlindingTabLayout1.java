package com.test.viewdemo.viewPager.viewpagerListener.customSlidingTabLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * 点击HorizontalScrollView中的某个控件后，
 * HorizontalScrollView滑动，使被点击的按钮滑到最左边
 * Created by xuyabo on 2018/4/23.
 */

public class MySlindingTabLayout1 extends HorizontalScrollView {
    //之所以要加上一个mViewContainer是因为HorizontalScrollView只能存放一个childView,所以这里mViewContainer作为新添加进的Child的容器
    private LinearLayout mViewContainer;
    private int mOffset=-100;
    private OnClickListener onTabClickListener=new OnClickListener() {
        @Override
        public void onClick(View v) {
            for(int i=0;i<mViewContainer.getChildCount();i++){
                View child= mViewContainer.getChildAt(i);
                if(child==v){
                    scrollToTab1(i);
                }
            }
        }
    };
    public MySlindingTabLayout1(Context context) {
        super(context,null);
    }

    public MySlindingTabLayout1(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFillViewport(true);//如果不设置为true，则当mViewContainer不够长时，mViewContainer不能填充满HorizontalScrollView，此时sc
        mViewContainer=new LinearLayout(context);
        ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(mViewContainer,layoutParams);

    }

    /**
     * 点击HorizontalScrollView中的某个控件后，
     * HorizontalScrollView滑动，使被点击的按钮滑到最左边
     * @param childPosition
     *
     */
    public void scrollToTab1(int childPosition) {
        int childeCount = mViewContainer.getChildCount();
        if (childPosition < 0 || childPosition >= childeCount) {
            return;
        }
        View view = mViewContainer.getChildAt(childPosition);
        int left=view.getLeft();
        //smoothScrollTo(left,getScrollY());平滑滑动
        scrollTo(left,getScrollY());
    }

    /**
     * 在scrollToTab1的基础上加上一个偏移量mOffset，
     * 比如想让被点击的按钮居中，将mOffset设置为屏幕的一半即刻
     * @param childPosition
     */
    public void scrollToTab2(int childPosition) {
        int childeCount = mViewContainer.getChildCount();
        if (childPosition < 0 || childPosition >= childeCount) {
            return;
        }
        View view = mViewContainer.getChildAt(childPosition);
        int left=view.getLeft()+mOffset;
        //smoothScrollTo(left,getScrollY());
        scrollTo(left,getScrollY());
    }

    public void setOffset(int offset) {
        mOffset = offset;
    }

    public void putView(View view, ViewGroup.LayoutParams layoutParams){
        mViewContainer.addView(view,layoutParams);
        view.setOnClickListener(onTabClickListener);
    }
}
