package com.test.viewdemo.mydrawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.test.viewdemo.R;

/**
 * Created by xuyabo on 2018/3/25.
 */

public class BitmapShaderDemo extends View{
    private Bitmap mShowedBitmap;
    private BitmapShader mBitmapShader;
    private Paint mPaint;
    public BitmapShaderDemo(Context context) {
        super(context);
    }

    public BitmapShaderDemo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mShowedBitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.sun);
        //对图片进行一些偏移
        //mShowedBitmap=Bitmap.createBitmap(mShowedBitmap,300,300,mShowedBitmap.getWidth()-300,mShowedBitmap.getHeight()-300);
        mBitmapShader=new BitmapShader(mShowedBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        mPaint=new Paint();
        mPaint.setShader(mBitmapShader);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int newWidthMeasureSp=MeasureSpec.makeMeasureSpec(mShowedBitmap.getWidth(),MeasureSpec.getMode(widthMeasureSpec));
        int newHeightMeasureSp=MeasureSpec.makeMeasureSpec(mShowedBitmap.getHeight(),MeasureSpec.getMode(heightMeasureSpec));
        setMeasuredDimension(newWidthMeasureSp,newHeightMeasureSp);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int cx = mShowedBitmap.getWidth() / 2;
        int cy = mShowedBitmap.getHeight() / 2;
        canvas.drawCircle(cx, cy,Math.max(cx,cy),mPaint);
    }
}
