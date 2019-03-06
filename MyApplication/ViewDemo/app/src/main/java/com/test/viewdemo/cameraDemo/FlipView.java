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
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;

import com.test.viewdemo.R;

public class FlipView extends View {
    private static final String TAG = "FlipView";
    private Bitmap mFirstBitmap;
    private Bitmap mSecondBitmap;
    private float angle = 0;//模拟翻书时被翻动的页的角度（从0到180）
    private Paint mPaint;
    private Camera mCamera;
    private Matrix mMatrix;

    private int mTouchSlop;//被认为是滑动的最小距离
    private Scroller mScroller;
    private int mMinimumFlingVelocity;//被认为是惯性滑动的最小距离
    private int mMaximumFlingVelocity;//被认为是惯性滑动的最大距离
    private VelocityTracker mVelocityTracker;
    private static final int INVALID_POINT = -1;
    private static int mActivePointerId = INVALID_POINT;

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

        mScroller = new Scroller(getContext(), new LinearInterpolator());
        final ViewConfiguration configuration = ViewConfiguration.get(getContext());
        mTouchSlop = configuration.getScaledPagingTouchSlop();
        mMinimumFlingVelocity = configuration.getScaledMinimumFlingVelocity();
        mMaximumFlingVelocity = configuration.getScaledMaximumFlingVelocity();
        mVelocityTracker = VelocityTracker.obtain();

    }

    private float mLastX;
    private boolean mIsFling = false;


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.i(TAG, "onTouchEvent: MotionEvent: " + event.hashCode() + "|" + (event.getAction()
                & MotionEvent.ACTION_POINTER_INDEX_MASK));
        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mActivePointerId = event.getPointerId(0);//触发MotionEvent.ACTION_DOWN事件的手指通常在数组中的第一个，故pointerIndex=0
                mLastX = event.getX(0);
                recycleVelocityTracker();
                Log.i(TAG, "onTouchEvent: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                onSecondaryPointerDown(event);
                Log.i(TAG, "onTouchEvent: ACTION_POINTER_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                int pointerIndex = event.findPointerIndex(mActivePointerId);
                float nowX = event.getX(pointerIndex);
                float deltaX = nowX - mLastX;
                float absDeltaX = Math.abs(deltaX);

                if (absDeltaX >= mTouchSlop) {
                    mIsFling = true;
                }

                if (mIsFling) {
                    float flipDistance = -deltaX / (getWidth() / 180);
                    angle += flipDistance;
                    angle = Math.max(angle, 0);
                    angle = Math.min(angle, 180);
                    invalidate();
                    mLastX = nowX;
                    Log.i(TAG, "onTouchEvent: angle " + angle);
                } else {
                    Log.i(TAG, "onTouchEvent: angle " + angle);
                }

                traceVelocity(event);

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (mIsFling) {

                    mIsFling = false;
                    if (mVelocityTracker != null) {
                        final int startX = (int) angle;
                        final int dx;
                        final int duration;
                        mVelocityTracker.computeCurrentVelocity(1000, mMaximumFlingVelocity);
                        float xVelocity = mVelocityTracker.getXVelocity();
                        if (Math.abs(xVelocity) >= mMinimumFlingVelocity) {//惯性滑动
                            if (angle >= 0 && angle <= 90) {
                                dx = (int) (180 - angle);
                                duration = (1000 * dx / 180);//1度滑1秒
                                mScroller.startScroll(startX, 0, dx, 0, duration);
                            } else if (angle >= 90 && angle <= 180) {
                                dx = (int) (0 - angle);
                                duration = 1000 * (Math.abs(dx)) / 180;
                                mScroller.startScroll(startX, 0, dx, 0, duration);
                            }
                            Log.i(TAG, "onTouchEvent: fling");
                        } else {//不是惯性滑动

                            if (this.angle >= 0 && angle <= 90) {
                                dx = (int) -angle;
                                duration = (int) (1000 * (angle) / 180);
                                mScroller.startScroll(startX, 0, dx, 0, duration);
                            } else if (this.angle <= 180 && angle >= 90) {
                                dx = (int) (180 - angle);
                                duration = (int) (1000 * (180 - angle) / 180);
                                mScroller.startScroll(startX, 0, dx, 0, duration);
                            }
                        }
                        recycleVelocityTracker();
                        invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                onSecondaryPointerUp(event);
                break;
        }

        return true;
    }

    private void onSecondaryPointerDown(MotionEvent event) {
        mActivePointerId = event.getPointerId(event.getActionIndex());
        mLastX = event.getX(event.getActionIndex());
    }

    private void onSecondaryPointerUp(MotionEvent ev) {
        final int pointerIndex = ev.getActionIndex();
        final int pointerId = ev.getPointerId( pointerIndex);
        if (pointerId == mActivePointerId) {
            // This was our active pointer going up. Choose a new
            // active pointer and adjust accordingly.
            final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
            mLastX = ev.getX( newPointerIndex);
            mActivePointerId = ev.getPointerId(newPointerIndex);
            if (mVelocityTracker != null) {
                mVelocityTracker.clear();
            }
        }
    }

    private void traceVelocity(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    private void recycleVelocityTracker() {
        if (mVelocityTracker != null) {
            mVelocityTracker.clear();
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
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
        if (!mScroller.isFinished() && mScroller.computeScrollOffset()) {
            angle = mScroller.getCurrX();
            invalidate();
            Log.i(TAG, "onDraw mScroller: " + angle);
        }

        //绘制第一页的左边
        canvas.save();
        Rect rectClipRight = new Rect(0, 0, getWidth() / 2, getHeight());
        canvas.clipRect(rectClipRight);
        canvas.drawBitmap(mFirstBitmap, 0, 0, mPaint);
        canvas.restore();

        //绘制第二页的右边
        canvas.save();
        Rect rectClipLeft = new Rect(getWidth() / 2, 0, getWidth(), getHeight());
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
        positionMatrix();
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

    private void positionMatrix() {
        //因为直接投影，投影会较大，所以用preScale缩小坐标，投影过后,然后使用postScale放大坐标
        //所以下面两行代码可以使用mCamera.setLocation()代替

        mMatrix.preScale(0.25f, 0.25f);
        mMatrix.preTranslate(-getWidth() / 2, -getHeight() / 2);
        mMatrix.postScale(4f, 4f);
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
