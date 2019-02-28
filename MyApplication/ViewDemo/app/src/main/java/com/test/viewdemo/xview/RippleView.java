package com.test.viewdemo.xview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 水波纹效果
 *
 * @author moujunfeng
 * @date 2018/9/12 10:04
 */
public class RippleView extends View {
    private int minRadius;
    private Paint paint;
    private List<Cricle> cricleList;
    //波纹持续的时间(从波纹创建到波纹结束)
    private long mDuration = 2000;
    private int mSpeed = 800;   // 波纹的创建速度，每800ms创建一个
    private boolean mIsRunning;


    public RippleView(Context context) {
        super(context);
        init();
    }

    public RippleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RippleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        cricleList = new ArrayList<Cricle>();
    }

    public void setPaintColor(int color){
        paint.setColor(color);
    }

    public void setMinRadius(int minRadius) {
        this.minRadius = minRadius;
    }

    public void setColor(int color) {
        paint.setColor(color);
    }

    public void setStyle(Paint.Style style) {
        paint.setStyle(style);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Iterator<Cricle> iterator = cricleList.iterator();
        while (iterator.hasNext()) {
            Cricle cricle = iterator.next();
            if (System.currentTimeMillis() - cricle.createTime < mDuration) {
                paint.setAlpha(cricle.getAlpha());
                canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, cricle.getRadius(), paint);
            } else {
                iterator.remove();
            }
        }
        if (cricleList.size() > 0) {
            postInvalidateDelayed(10);
        }
    }

    public void start() {
        if (!mIsRunning) {
            mIsRunning = true;
            mCreateCircle.run();
        }
    }

    /**
     * 停止
     */
    public void stop() {
        mIsRunning = false;
    }

    private long mLastCreateTime;
    private Runnable mCreateCircle = new Runnable() {
        @Override
        public void run() {
            if (mIsRunning) {
                long currentTime = System.currentTimeMillis();
                if (currentTime - mLastCreateTime < mSpeed) {
                    return;
                }
                Cricle circle = new Cricle();
                cricleList.add(circle);
                invalidate();
                mLastCreateTime = currentTime;
                postDelayed(mCreateCircle, mSpeed);
            }
        }
    };

    public class Cricle {
        //创建的时间
        public long createTime;

        public Cricle() {
            this.createTime = System.currentTimeMillis();
        }

        //获得半径
        public float getRadius() {
            float percent = (System.currentTimeMillis() - createTime) * 1.0f / mDuration;
            int maxRadius = Math.min(getMeasuredWidth(), getMeasuredHeight()) / 2;
            return minRadius + percent * (maxRadius - minRadius);

        }

        //获得透明度
        public int getAlpha() {
            float percent = (System.currentTimeMillis() - createTime) * 1.0f / mDuration;
            return (int) ((1.0f - percent * 255));
        }
    }
}
