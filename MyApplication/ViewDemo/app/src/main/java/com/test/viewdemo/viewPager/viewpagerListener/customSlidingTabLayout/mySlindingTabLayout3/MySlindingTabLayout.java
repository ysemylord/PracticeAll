package com.test.viewdemo.viewPager.viewpagerListener.customSlidingTabLayout.mySlindingTabLayout3;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;

/**
 * 添加绑定ViewPager的功能
 * 注意点
 * 1. MySlidingTabStrip要设置setWillNotDraw(false);不然即便调用了invalidate也不会导致onDraw的调用
 * 难点
 * 1. MySlidingTabStrip指示器位置的确定
 *
 * 涉及到的信息组块
 * 1.ViewPagerAdater
 *    + getTitleString
 *    + getCount()
 *
 * 2.HorizontalScrollView scrollTo
 *
 * 3.Canvas.drawLine
 *
 * 4.数学计算
 */

public class MySlindingTabLayout extends HorizontalScrollView {
    //之所以要加上一个mViewContainer是因为HorizontalScrollView只能存放一个childView,所以这里mViewContainer作为新添加进的Child的容器
    private MySlidingTabStrip mMySlidingTabStrip;
    private ViewPager mViewPager;
    private int mOffset=-100;
    private OnClickListener onTabClickListener=new OnClickListener() {
        @Override
        public void onClick(View v) {
            for(int i = 0; i< mMySlidingTabStrip.getChildCount(); i++){
                View child= mMySlidingTabStrip.getChildAt(i);
                if(child==v){
                   mViewPager.setCurrentItem(i);
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




    public void scrollToTab(int childPosition, float positionOffset) {
        int childeCount = mMySlidingTabStrip.getChildCount();
        if (childPosition < 0 || childPosition >= childeCount) {
            return;
        }
        View view = mMySlidingTabStrip.getChildAt(childPosition);
        int left=view.getLeft()+mOffset;
        scrollTo((int) (left+view.getWidth()*positionOffset),getScrollY());

    }

    public void setOffset(int offset) {
        mOffset = offset;
    }

    public void putView(View view, ViewGroup.LayoutParams layoutParams){
        mMySlidingTabStrip.addView(view,layoutParams);
        view.setOnClickListener(onTabClickListener);
    }

    public void setViewPater(ViewPager viewPager){
        if(viewPager==null){
            return;
        }
        mViewPager=viewPager;
        PagerAdapter adapter = viewPager.getAdapter();
        for(int i = 0; i< adapter.getCount();i++){
            String titles= (String) adapter.getPageTitle(i);
            Button textView=new Button(getContext());
            textView.setText(titles);
            ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            putView(textView,layoutParams);
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                scrollToTab(position,positionOffset);
                mMySlidingTabStrip.onViewPagerChange(position,positionOffset);

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
