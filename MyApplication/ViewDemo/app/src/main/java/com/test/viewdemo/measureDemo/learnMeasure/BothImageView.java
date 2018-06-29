package com.test.viewdemo.measureDemo.learnMeasure;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.test.viewdemo.R;

/**
 * Created by xuyabo on 2018/4/29.
 * 1. 覆盖onMeasure，完全自定义尺寸
 * 2. 覆盖onLayout,计算子View的位置，实现mImageView1与mImageView2的垂直线性布局
 */

public class BothImageView extends ViewGroup {
    private ImageView mImageView1;
    private ImageView mImageView2;

    public BothImageView(Context context) {
        this(context, null);
    }

    public BothImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        mImageView1 = new ImageView(getContext());
        mImageView2 = new ImageView(getContext());
        mImageView1.setImageResource(R.drawable.sun);
        mImageView2.setImageResource(R.drawable.robot);
        addView(mImageView1);
        addView(mImageView2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec,heightMeasureSpec);

        //模拟子View的尺寸测量
        int drawable1Width = mImageView1.getDrawable().getIntrinsicWidth();
        int drawable1Height = mImageView2.getDrawable().getIntrinsicWidth();
        int drawable2Width = mImageView1.getDrawable().getIntrinsicHeight();
        int drawable2Height = mImageView2.getDrawable().getIntrinsicHeight();

        int measureSpecWidth1 = MeasureSpec.makeMeasureSpec(drawable1Width, MeasureSpec.EXACTLY);
        int measureSpecHeight1 = MeasureSpec.makeMeasureSpec(drawable2Width, MeasureSpec.EXACTLY);
        int measureSpecWidth2 = MeasureSpec.makeMeasureSpec(drawable1Height, MeasureSpec.EXACTLY);
        int measureSpecHeight2 = MeasureSpec.makeMeasureSpec(drawable2Height, MeasureSpec.EXACTLY);

        //注意，要手动测量View的大小要调用View.measure方法（这是测量尺寸开始的函数）
        mImageView1.measure(measureSpecWidth1,measureSpecHeight1);
        mImageView2.measure(measureSpecWidth2,measureSpecHeight2);

        //设置当前容器的尺寸
        int thisSpecWidth = MeasureSpec.makeMeasureSpec(Math.max(mImageView1.getMeasuredWidth(),mImageView2.getMeasuredWidth()), MeasureSpec.EXACTLY);
        int thisSpecHeight = MeasureSpec.makeMeasureSpec(mImageView1.getMeasuredHeight()+mImageView2.getMeasuredHeight(), MeasureSpec.EXACTLY);
        setMeasuredDimension(thisSpecWidth, thisSpecHeight);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();

        int imageView1Bottom = paddingTop + mImageView2.getMeasuredHeight();
        int imageView1Top = paddingTop;
        mImageView1.layout(paddingLeft, imageView1Top,paddingLeft+mImageView1.getMeasuredWidth(), imageView1Bottom);

        mImageView2.layout(paddingLeft, imageView1Bottom,paddingLeft+mImageView2.getMeasuredWidth(), imageView1Bottom+mImageView2.getMeasuredHeight());
    }
}
