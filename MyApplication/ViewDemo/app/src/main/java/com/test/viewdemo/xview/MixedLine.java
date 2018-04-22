package com.test.viewdemo.xview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xuyabo on 2018/3/27.
 */

public class MixedLine extends View {
    private Paint mPaint;
    public MixedLine(Context context) {
        super(context);
    }

    public MixedLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint=new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(20);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0,10,500,10,mPaint);
        mPaint.setColor(Color.RED);

        canvas.drawLine(0,50,500,50,mPaint);
        mPaint.setColor(Color.RED);
        //canvas.drawLine(500,10,getMeasuredWidth(),10,mPaint);
    }
}
