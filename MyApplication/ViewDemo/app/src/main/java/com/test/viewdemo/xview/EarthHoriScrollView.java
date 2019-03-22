package com.test.viewdemo.xview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;


public class EarthHoriScrollView extends HorizontalScrollView {

    private static final String TAG = "EarthHoriScrollView";
    private Handler mHandler;
    private int mWidth;
    private Runnable mRunnable;

    public EarthHoriScrollView(Context context) {
        super(context);
    }

    public EarthHoriScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = (int) (getMeasuredHeight() * 2 / 2.7);
        mWidth = height * 884 / 576;
        setMeasuredDimension(
                MeasureSpec.makeMeasureSpec(mWidth, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.UNSPECIFIED));

        View image1 = ((ViewGroup) getChildAt(0)).getChildAt(0);
        View image2 = ((ViewGroup) getChildAt(0)).getChildAt(1);

        ViewGroup.LayoutParams layoutParams = image1.getLayoutParams();
        layoutParams.width = mWidth;
        layoutParams.height = height;
        image1.setLayoutParams(layoutParams);

        image2.setLayoutParams(layoutParams);


    }


    @Override
    @TargetApi(23)
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        autoSmooth();
        setOnScrollChangeListener(new OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollX >= mWidth&&(dire<0||auto)) {
                    scrollTo(0, 0);
                    Log.i(TAG, "onScrollChange: return 0");
                }else if(scrollX==0&dire>0){
                    scrollTo(mWidth-1,0);
                    Log.i(TAG, "onScrollChange: return mWidth");
                }
            }
        });
    }

    private void autoSmooth() {
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                auto=true;
                scrollBy(1, 0);
                mHandler.postDelayed(this, 20);
            }
        };
        mHandler.post(mRunnable);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    float lastX;
    float dire;
    boolean auto;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                lastX=ev.getX();
                mHandler.removeCallbacks(mRunnable);
                auto=false;
                break;
            case MotionEvent.ACTION_MOVE:
                float nowX=ev.getX();
                dire=nowX-lastX;
                lastX=ev.getX();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mHandler.post(mRunnable);
                dire=0;
                break;

        }

        return super.onTouchEvent(ev);
    }


}
