package com.test.viewdemo.viewPager.viewpagerListener.customSlidingTabLayout.mySlindingTabLayout3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;


public class MySlidingTabStrip extends LinearLayout {
    private Paint mPaint = new Paint();
    private int mPositionClicked;
    private float mPositionOffset;

    {
        mPaint.setStrokeWidth(10);
    }

    public MySlidingTabStrip(Context context) {
        this(context, null);
    }

    public MySlidingTabStrip(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //https://stackoverflow.com/questions/17595546/why-ondraw-is-not-called-after-invalidate
        //todo 注意
        setWillNotDraw(false);
    }

    public void onViewPagerChange(int positionChoosed, float positionOffset) {
        mPositionClicked = positionChoosed;
        mPositionOffset = positionOffset;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        View child = getChildAt(mPositionClicked);
        View nextChild = getChildAt(mPositionClicked+1);

        int left = child.getLeft();
        int right = child.getRight();
        // Draw the selection partway between the tabs
        //从SlidingTabLayout copy过来的，这个计算太巧妙了
        if(mPositionClicked<getChildCount()-1) {
            View nextTitle = getChildAt(mPositionClicked + 1);
            int nextTitleLeft = nextTitle.getLeft();
            left = (int) (mPositionOffset * nextTitleLeft +
                    (1.0f - mPositionOffset) * left);
            int nextTitleRight = nextTitle.getRight();
            right = (int) (mPositionOffset * nextTitleRight +
                    (1.0f - mPositionOffset) * right);
        }

        canvas.drawLine(left, child.getBottom(), right, child.getBottom(), mPaint);
    }
}
