package com.test.viewdemo.viewPager.viewpagerListener.customSlidingTabLayout.mySlindingTabLayout3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * 在被点击的按钮下面画一条指示线
 */

public class MySlidingTabStrip extends LinearLayout {
    private Paint mPaint=new Paint();
    private int mPositionClicked;
    {
        mPaint.setStrokeWidth(10);
    }
    public MySlidingTabStrip(Context context) {
        this(context,null);
    }

    public MySlidingTabStrip(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //https://stackoverflow.com/questions/17595546/why-ondraw-is-not-called-after-invalidate
        //todo 注意
        setWillNotDraw(false);
    }

    public void onTabChanged(int positionClick){
        mPositionClicked=positionClick;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        View child=getChildAt(mPositionClicked);
        int left=child.getLeft();
        int right=child.getRight();
        canvas.drawLine(left,child.getBottom(),right,child.getBottom(),mPaint);
    }
}
