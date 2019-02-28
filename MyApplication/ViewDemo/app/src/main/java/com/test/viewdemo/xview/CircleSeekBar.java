package com.test.viewdemo.xview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CircleSeekBar extends View {
    //最大时间
    private int mMaxProgress = 100;
    private int mCurrentProgress = 0;

    //圆形进度的背景
    private Path mProgressBgPath = new Path();
    private Paint mProgressBgPaint = new Paint();

    //圆形进度
    private Path mCurrentProgressPath = new Path();
    private Paint mCurrentProgressPaint = new Paint();

    //绘制背景
    private Paint mBgPaint = new Paint();

    //进度条顶部
    private Bitmap mThumbBitmap;

    private int mBgColor = Color.parseColor("#fdd14b");
    private int mProgressBgColor = Color.parseColor("#FF7C00");
    private int mProgressColor = Color.WHITE;
    private RectF mInnerCircleRectF;

    private PathMeasure mPathMeasure = new PathMeasure();



    public void setMaxProgress(int maxProgress) {
        this.mMaxProgress = maxProgress;
    }

    public void setCurrent(int current) {
        this.mCurrentProgress = current;
        invalidate();
    }

    public CircleSeekBar(Context context) {
        super(context);
        init();
    }

    public CircleSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //绘制背景
        mBgPaint.setAntiAlias(true);
        mBgPaint.setColor(mBgColor);
        //绘制外圆
        mProgressBgPaint.setStyle(Paint.Style.FILL);
        mProgressBgPaint.setAntiAlias(true);
        mProgressBgPaint.setStrokeWidth(10f);
        mProgressBgPaint.setColor(mProgressBgColor);
        mProgressBgPaint.setStyle(Paint.Style.STROKE);
        //绘制进度
        mCurrentProgressPaint.setAntiAlias(true);
        mCurrentProgressPaint.setStrokeWidth(10f);
        mCurrentProgressPaint.setColor(mProgressColor);
        mCurrentProgressPaint.setStyle(Paint.Style.STROKE);
    }


    /**
     * 设置进度条的顶部图形
     * @param R
     */
    public void setThumbBitmap(int R) {
        //一下代码的目的：将Drawable转化为Bitmap
        Drawable drawable = getContext().getResources().getDrawable(R);
        int width = drawable.getIntrinsicWidth();// 取drawable的长宽
        int height = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() == PixelFormat.OPAQUE ? Bitmap.Config.RGB_565 : Bitmap.Config.ARGB_8888;// 取drawable的颜色格式
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);// 建立对应bitmap
        Canvas canvas = new Canvas(bitmap);// 建立对应bitmap的画布
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);// 把drawable内容画到画布中
        mThumbBitmap = bitmap;
        invalidate();
    }

    float[] location = new float[2];

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mInnerCircleRectF == null) {
            //给thumb留出位置
            mInnerCircleRectF = new RectF(mThumbBitmap.getWidth() / 2, mThumbBitmap.getHeight() / 2,
                    getMeasuredWidth() - mThumbBitmap.getWidth() / 2, getMeasuredHeight() - mThumbBitmap.getHeight() / 2);
        }
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //背景
        float radius = Math.min(getMeasuredWidth(), getMeasuredHeight()) / 2;
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, radius - mProgressBgPaint.getStrokeWidth(), mBgPaint);

        if (mThumbBitmap != null) {
            //进度圆环的背景
            mProgressBgPath.reset();

            int startAngle = -90;
            mProgressBgPath.addArc(mInnerCircleRectF, startAngle, 360);
            canvas.drawPath(mProgressBgPath, mProgressBgPaint);


            //进度圆环的进度弧
            mCurrentProgressPath.reset();
            mCurrentProgressPath.addArc(mInnerCircleRectF, startAngle, 360 * gePercent());
            canvas.drawPath(mCurrentProgressPath, mCurrentProgressPaint);

            //进度圆环的头部
            mPathMeasure.setPath(mCurrentProgressPath, false);
            mPathMeasure.getPosTan(mPathMeasure.getLength(), location, null);
            canvas.drawBitmap(mThumbBitmap, location[0] - mThumbBitmap.getWidth() / 2, location[1] - mThumbBitmap.getHeight() / 2, mProgressBgPaint);
        }
    }

    private static final String TAG = "CircleSeekBar";
    /**
     * 获得当前百分比
     *
     * @return
     */
    public float gePercent() {
        float progress = (mCurrentProgress * 1f) / mMaxProgress;
        float res = progress == 0 ? 0.001f : progress;
        Log.i(TAG, "gePercent: "+res);
        return res;
    }


}
