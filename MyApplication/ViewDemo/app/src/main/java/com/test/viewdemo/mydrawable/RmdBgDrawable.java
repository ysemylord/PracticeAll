package com.test.viewdemo.mydrawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.test.viewdemo.R;

/**
 * Created by xuyabo on 2018/5/7.
 */

public class RmdBgDrawable extends Drawable {
    private static final String TAG = "PriceFrameDrawable";
    Paint mPaint;
    Path mPath;
    RectF oval;
    private int diameter = 100;//圆弧直径


    {
        mPaint = new Paint();
        mPaint.setColor(R.color.check_btn_color);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath = new Path();
        oval=new RectF();

    }

    @Override
    public void draw(@NonNull Canvas canvas) {

        int width = canvas.getWidth();
        int height = canvas.getHeight();
        mPath.lineTo(width- diameter, 0);



        oval.left=width- diameter;
        oval.top=0;
        oval.right=width;
        oval.bottom=diameter;
        mPath.arcTo(oval,270,90,false);

        mPath.lineTo(width,height);

        mPath.lineTo(width/2+10,height);
        mPath.lineTo(width/2,height-10);
        mPath.lineTo(width/2-10,height);
        mPath.lineTo(0, height);

        oval.left=0;
        oval.top=0;
        oval.right=diameter;
        oval.bottom=diameter;
        mPath.arcTo(oval,180,90,false);


        mPaint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawPath(mPath, mPaint);


    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
