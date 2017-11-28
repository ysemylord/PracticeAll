package com.test.viewdemo.mydrawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created  on 2017/11/28.
 *
 * @author xyb
 */

public class MyRingDrawable extends Drawable{
    public static final String TAG="MyRingDrawable";
    float percent=0;
    @Override
    public void draw(@NonNull Canvas canvas) {

        Rect bounds=getBounds();
        int width = bounds.width();
        int height = bounds.height();

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        int radius = width / 2;
        canvas.drawCircle(width /2, height /2, percent*radius, paint);
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

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
        Log.i(TAG,percent+"");
        invalidateSelf();
    }
}
