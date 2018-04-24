package com.test.viewdemo.viewPager.viewpagerListener;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * ViewPager的指示器
 * 思路监听ViewPager的滑动
 * 调整画的圆的位置
 * 重点：
 * 1.监听ViewPager
 *
 * 难点：
 * 2.确定圆的位置的数学计算
 */
class IndicatorPoint extends View {
    private Paint paint = new Paint();
    private ViewPager mViewPager;
    private int mPosition;
    private float mPositionOffset;
    private float radius=10;
    private float gap=10;//点与点间的距离
    private float startX=radius/2;
    private float startY;

    public void setViewPager(ViewPager viewPager) {
        mViewPager=viewPager;
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mPosition=position;
                mPositionOffset=positionOffset;
                IndicatorPoint.this.invalidate();//ViewPager有变化就重新绘制IndicatorPoint
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public IndicatorPoint(Context context) {
        super(context);
    }

    public IndicatorPoint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        startY=getMeasuredHeight()/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       // canvas.drawCircle(startX+mPosition*radius*2+10,startY,radius,paint);
        float diameter = radius * 2;//圆的直径
        float xMove = (mPosition + mPositionOffset) *(diameter + gap);//
        canvas.drawCircle(startX + xMove,startY,radius,paint);
    }
}
