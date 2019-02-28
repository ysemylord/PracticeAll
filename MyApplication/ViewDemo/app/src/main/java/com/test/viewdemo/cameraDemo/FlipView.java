package com.test.viewdemo.cameraDemo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.test.viewdemo.R;

public class FlipView extends View {
    private static final String TAG = "FlipView";
    private Bitmap mFirstBitmap;
    private Bitmap mSecondBitmap;
    private int angle = 0;//模拟翻书时被翻动的页的角度（从0到180）
    private Paint mPaint;
    private Camera mCamera;
    private Matrix mMatrix;

    public FlipView(Context context) {
        this(context, null);
    }

    public FlipView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {


        mPaint = new Paint();
        mCamera = new Camera();
        mMatrix = new Matrix();
    }



    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mFirstBitmap == null) {
            mFirstBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.four);
            int width = getWidth();
            int height = getHeight();
            mFirstBitmap = Bitmap.createScaledBitmap(mFirstBitmap, width, height, true);
            mSecondBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.five);
            mSecondBitmap = Bitmap.createScaledBitmap(mSecondBitmap, width, height, true);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制第一页的左边
        canvas.save();
        Rect rectClipRight=new Rect(0, 0, getWidth() / 2, getHeight());
        canvas.clipRect(rectClipRight);
        canvas.drawBitmap(mFirstBitmap, 0, 0, mPaint);
        canvas.restore();

        //绘制第二页的右边
        canvas.save();
        Rect rectClipLeft=new Rect(getWidth() / 2,0, getWidth() , getHeight());
        canvas.clipRect(rectClipLeft);
        canvas.drawBitmap(mSecondBitmap, 0, 0, mPaint);
        canvas.restore();



        canvas.save();
        mCamera.save();

        //使用Camera做3D变化
        if (angle <= 90) {
            mCamera.rotateY(-angle);
        } else if (angle > 90 && angle <= 180) {
            mCamera.rotateY(180 - angle);
        }
        mCamera.getMatrix(mMatrix);
        fixMatrix();
        canvas.setMatrix(mMatrix);

        //更具角度决定绘制的内容
        if (angle <= 90) {
            canvas.clipRect(mFirstBitmap.getWidth() / 2, 0, mFirstBitmap.getWidth(), mFirstBitmap.getHeight());
            canvas.drawBitmap(mFirstBitmap, 0, 0, mPaint);
        } else {
            canvas.clipRect(0, 0, mFirstBitmap.getWidth() / 2, mFirstBitmap.getHeight());
            canvas.drawBitmap(mSecondBitmap, 0, 0, mPaint);
        }
        mCamera.restore();
        canvas.restore();

    }

    private void fixMatrix() {
        mMatrix.postScale(4.0f, 4.0f);
        mMatrix.preScale(0.25f, 0.25f);
        mMatrix.preTranslate(-getWidth() / 2, -getHeight() / 2);
        mMatrix.postTranslate(getWidth() / 2, getHeight() / 2);
    }

    public void autoFlip() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 180);
        valueAnimator.setDuration(10 * 1000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                angle = (int) animation.getAnimatedValue();
                invalidate();
                Log.i(TAG, "onAnimationUpdate: " + angle);
            }
        });
        valueAnimator.start();

    }
}
