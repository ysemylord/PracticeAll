package com.test.viewdemo.viewPager.viewpagerListener.customSlidingTabLayout.mySlindingTabLayout2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

/**
 * Created by xuyabo on 2018/4/23.
 */

public class MySlindingTabLayout extends HorizontalScrollView {
    //之所以要加上一个mViewContainer是因为HorizontalScrollView只能存放一个childView,所以这里mViewContainer作为新添加进的Child的容器
    private MySlidingTabStrip mMySlidingTabStrip;
    private int mOffset=-100;
    private OnClickListener onTabClickListener=new OnClickListener() {
        @Override
        public void onClick(View v) {
            for(int i = 0; i< mMySlidingTabStrip.getChildCount(); i++){
                View child= mMySlidingTabStrip.getChildAt(i);
                if(child==v){
                    scrollToTab(i);
                    mMySlidingTabStrip.onTabChanged(i);

                }
            }
        }
    };
    public MySlindingTabLayout(Context context) {
        super(context,null);
    }

    public MySlindingTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFillViewport(true);//如果不设置为true，则当mViewContainer不够长时，mViewContainer不能填充满HorizontalScrollView，此时sc
        setHorizontalScrollBarEnabled(false);
        mMySlidingTabStrip =new MySlidingTabStrip(context);
        ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(mMySlidingTabStrip,layoutParams);

    }




    public void scrollToTab(int childPosition) {
        int childeCount = mMySlidingTabStrip.getChildCount();
        if (childPosition < 0 || childPosition >= childeCount) {
            return;
        }
        View view = mMySlidingTabStrip.getChildAt(childPosition);
        int left=view.getLeft()+mOffset;
        scrollTo(left,getScrollY());

    }

    public void setOffset(int offset) {
        mOffset = offset;
    }

    public void putView(View view, ViewGroup.LayoutParams layoutParams){
        mMySlidingTabStrip.addView(view,layoutParams);
        view.setOnClickListener(onTabClickListener);
    }
}
