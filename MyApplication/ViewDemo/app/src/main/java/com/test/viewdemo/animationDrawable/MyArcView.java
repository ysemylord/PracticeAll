package com.test.viewdemo.animationDrawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by xuyabo on 2017/11/28.
 */

public class MyArcView extends View{
    private  Paint mPaint;
    private  float mPercent=0.5f;
    public MyArcView(Context context) {
        this(context,null);
    }

    public MyArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint=new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        RectF rectF=new RectF(0,0,getMeasuredWidth(),getMeasuredHeight());
        canvas.drawArc(rectF,0,360*mPercent,false,mPaint );

    }



    public float getPercent() {
        return mPercent;
    }

    public void setPercent(float percent) {
        this.mPercent = percent;
        Log.i("MyCircleView", "setPercent: "+percent);
        postInvalidate();
    }
}
