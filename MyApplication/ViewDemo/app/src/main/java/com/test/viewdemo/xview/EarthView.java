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
    private Paint mHaloPaint;
    private Bitmap mScallopBitmap;
    private Bitmap mBgBitmap;
    private Paint mSimplePaint;

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
        mHaloPaint = new Paint();
        mHaloPaint.setAntiAlias(true);
        mHaloPaint.setDither(true);


        mSimplePaint = new Paint();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //大气
        if (mAtmosphereBitmap == null) {
            mAtmosphereBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lib_bg_atmosphere);
            int atmosphereWidth = (int) (getMeasuredHeight() * 9f / 10);
            mAtmosphereBitmap = Bitmap.createScaledBitmap(mAtmosphereBitmap, atmosphereWidth * 688 / 592, atmosphereWidth, false);
        }

        //光环
        if (mHaloBitmap == null) {
            mHaloBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mc_bg_map_halo);
            int haloWidth = (int) (getMeasuredHeight() * 9f / 10);
            mHaloBitmap = Bitmap.createScaledBitmap(mHaloBitmap, haloWidth, haloWidth, false);
            mHaloWithHollow = haloWithHollow();
        }

        //月牙
        int scallopRadius = (int) (mHaloBitmap.getHeight() / 2 * 0.83f);
        if (mScallopBitmap == null) {

            Paint scallopPaint = new Paint();
            scallopPaint.setAntiAlias(true);
            scallopPaint.setDither(true);
            scallopPaint.setColor(Color.parseColor("#4f000000"));

            mScallopBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            Canvas scallopCanvas = new Canvas(mScallopBitmap);

            scallopCanvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, scallopRadius, scallopPaint);

            scallopPaint.reset();//画笔的透明度会对最终的效果有影响
            scallopPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

            int offset = 50;
            scallopCanvas.drawCircle(getMeasuredWidth() / 2 + offset, getMeasuredHeight() / 2 - offset, scallopRadius, scallopPaint);
            scallopPaint.setXfermode(null);

        }

        //背景
        if (mBgBitmap == null) {
            Bitmap canvasBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas bgCanvas = new Canvas(canvasBitmap);

            Bitmap bgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lib_base_bg, new BitmapFactory.Options());
            bgBitmap = Bitmap.createScaledBitmap(bgBitmap, getWidth(), getWidth()*720/1080, false);

            Paint bgPaint = new Paint();
            bgCanvas.drawBitmap(bgBitmap, 0, 0, bgPaint);
            bgPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            bgCanvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, scallopRadius, bgPaint);
            bgPaint.setXfermode(null);
            mBgBitmap=canvasBitmap;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);




        canvas.drawBitmap(mBgBitmap,0,0, mSimplePaint);

        //大气
        canvas.drawBitmap(mAtmosphereBitmap, (getMeasuredWidth() - mAtmosphereBitmap.getWidth()) >> 1, (getMeasuredHeight() - mAtmosphereBitmap.getHeight()) >> 1, mSimplePaint);



        //光环
        canvas.drawBitmap(mHaloWithHollow, 0, 0, mSimplePaint);


        //月牙
        canvas.drawBitmap(mScallopBitmap, 0, 0, mSimplePaint);

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
