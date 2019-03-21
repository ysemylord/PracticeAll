package com.test.viewdemo.xview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.test.viewdemo.R;

public class EarthView extends View {
    private Bitmap mAtmosphereBitmap;//大气光环
    private Bitmap mHaloBitmap;//光环
    private Bitmap mHaloWithHollow;
    private int mScreenHeight;
    private Paint mHaloPaint;
    private Paint mScallopPaint;
    private Bitmap mScallopBitmap;

    public EarthView(Context context) {
        super(context);
        init();
    }

    public EarthView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        mScreenHeight = displayMetrics.heightPixels;
        mHaloPaint = new Paint();
        mHaloPaint.setAntiAlias(true);
        mHaloPaint.setDither(true);

        mScallopPaint = new Paint();
        mScallopPaint.setAntiAlias(true);
        mScallopPaint.setDither(true);
        mScallopPaint.setColor(Color.parseColor("#af000000"));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //大气
        if (mAtmosphereBitmap == null) {
            mAtmosphereBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lib_bg_atmosphere);
            int atmosphereWidth = (int) (mScreenHeight * 9f / 10);
            mAtmosphereBitmap = Bitmap.createScaledBitmap(mAtmosphereBitmap, atmosphereWidth * 688 / 592, atmosphereWidth, false);
        }

        //光环
        if (mHaloBitmap == null) {
            mHaloBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mc_bg_map_halo);
            int haloWidth = (int) (mScreenHeight * 9f / 10);
            mHaloBitmap = Bitmap.createScaledBitmap(mHaloBitmap, haloWidth, haloWidth, false);
            mHaloWithHollow = haloWithHollow();
        }

        //月牙
        if (mScallopBitmap == null) {
            mScallopBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(mScallopBitmap);

            mScallopPaint.setColor(Color.parseColor("#5F000000"));
            int scallopRadius = (int) (mHaloBitmap.getHeight() / 2 * 0.83f);
            canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, scallopRadius , mScallopPaint);

            mScallopPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

            int offset = 50;
            canvas.drawCircle(getMeasuredWidth() / 2 + offset, getMeasuredHeight() / 2 - offset, scallopRadius , mScallopPaint);
            mScallopPaint.setXfermode(null);

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawBitmap(mAtmosphereBitmap, (getMeasuredWidth() - mAtmosphereBitmap.getWidth()) >> 1, (getMeasuredHeight() - mAtmosphereBitmap.getHeight()) >> 1, mHaloPaint);


        canvas.drawBitmap(mHaloWithHollow, 0, 0, mHaloPaint);
        canvas.drawBitmap(mScallopBitmap, 0, 0, mHaloPaint);


    }

    private Bitmap haloWithHollow() {
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        canvas.drawBitmap(mHaloBitmap, (getMeasuredWidth() - mHaloBitmap.getWidth()) >> 1, (getMeasuredHeight() - mHaloBitmap.getHeight()) >> 1, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        int hollowRadius = (int) (mHaloBitmap.getHeight() / 2 * 0.83f);
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, hollowRadius, paint);
        paint.setXfermode(null);
        return bitmap;
    }
}
