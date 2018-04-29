package com.test.viewdemo.measureDemo.learnMeasure;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xuyabo on 2018/4/29.
 * 重写onMeasure,根据已有尺寸自定义View的尺寸
 */

public class SquareView extends View {
    public SquareView(Context context) {
        super(context);
    }

    public SquareView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth=getMeasuredWidth();
        int measuredHeight=getMeasuredHeight();
        int max=Math.max(measureWidth,measuredHeight);
        int newWidthMeasureSpec=MeasureSpec.makeMeasureSpec(max,MeasureSpec.getMode(widthMeasureSpec));
        int newHeightMeasureSpec=MeasureSpec.makeMeasureSpec(max,MeasureSpec.getMode(widthMeasureSpec));
        setMeasuredDimension(newWidthMeasureSpec,newHeightMeasureSpec);
    }
}
