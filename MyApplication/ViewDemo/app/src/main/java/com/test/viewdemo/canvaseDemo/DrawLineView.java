package com.test.viewdemo.canvaseDemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xuyabo on 2018/4/23.
 */

public class DrawLineView extends View{
    private Paint mPaint;
    private int startX=0;
    private int lineWidth=400;
    private int lineHeight=100;
    {
        mPaint=new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(lineHeight);
    }
    public DrawLineView(Context context) {
        super(context);
    }

    public DrawLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(startX,0,startX=startX+lineWidth,0,mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawLine(startX,lineHeight/2,startX=startX+lineWidth,lineHeight/2,mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawLine(startX,lineHeight/2+1,startX=startX+lineWidth,lineHeight/2+1,mPaint);
    }
}
