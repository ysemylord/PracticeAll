package com.test.viewdemo.mydrawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.test.viewdemo.R;

/**
 * Created by xuyabo on 2018/5/7.
 */

public class PriceFrameTextView extends android.support.v7.widget.AppCompatTextView {
    Paint mPaint;
    Path mPath;

    {
        mPaint = new Paint();
        mPaint.setColor(R.color.check_btn_color);
        mPaint.setAntiAlias(true);

        mPath = new Path();
    }
    public PriceFrameTextView(Context context) {
        super(context);
    }

    public PriceFrameTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        mPath.lineTo(width, 0);
        mPath.lineTo(width - getPaddingRight()/2, height / 2);
        mPath.lineTo(width, height);
        mPath.lineTo(0, height);
        mPath.close();
        canvas.drawPath(mPath, mPaint);
        super.onDraw(canvas);
    }


}
