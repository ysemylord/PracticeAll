package com.test.viewdemo.mydrawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.test.viewdemo.R;

/**
 * Created by xuyabo on 2018/5/7.
 */

public class PriceFrameDrawable extends Drawable {
    private static final String TAG = "PriceFrameDrawable";
    Paint mPaint;
    Path mPath;

    {
        mPaint = new Paint();
        mPaint.setColor(R.color.check_btn_color);
        mPaint.setAntiAlias(true);

        mPath = new Path();
    }

    @Override
    public void draw(@NonNull Canvas canvas) {

        //getIntrinsicWidth();
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        mPath.lineTo(width, 0);
        mPath.lineTo(width - 20, height / 2);
        mPath.lineTo(width, height);
        mPath.lineTo(0, height);
        mPath.close();
        canvas.drawPath(mPath, mPaint);

        Log.i(TAG, "draw: " + width + "|" + height);

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
