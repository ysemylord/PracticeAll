package com.test.viewdemo.mySwipeRefreshLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.test.viewdemo.R;

/**
 * Created by xuyabo on 2018/5/1.
 */

public class XybSwipeRefreshLayout extends ViewGroup {
    private ImageView mCircleImageView;
    private int CIRCLE_DIAMETER = 40;
    private int DEFAULT_CIRCLE_TARGET = 60;
    private View mTarget;
    private int mCircleDimeter;
    private int mCurrentTargetOffsetTop;//圆圈现在的偏移量
    private int mOriginalOffsetTop;
    private static final String TAG = "XybSwipeRefreshLayout";

    public XybSwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public XybSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        createProgressView();
    }

    private void createProgressView() {
        mCircleImageView = new ImageView(getContext());
        mCircleImageView.setImageResource(R.drawable.robot);
        addView(mCircleImageView);
        mCircleDimeter = CIRCLE_DIAMETER;
        mCurrentTargetOffsetTop=-mCircleDimeter;
        mOriginalOffsetTop = DEFAULT_CIRCLE_TARGET;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);//确定XybSwipeRefreshLayout自己的大小
        if (mTarget == null) {
            ensureTarget();
        }
        if (mTarget == null) {
            Log.e(TAG, "onMeasure: mTarge is null");
        }

        mTarget.measure(
                MeasureSpec.makeMeasureSpec(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(getMeasuredHeight() - getTop() - getPaddingBottom(), MeasureSpec.EXACTLY)

        );
        mCircleImageView.measure(MeasureSpec.makeMeasureSpec(mCircleDimeter, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(mCircleDimeter, MeasureSpec.EXACTLY));
    }

    /**
     * 找出SwipeRefreshLayout下嵌套的子View
     */
    private void ensureTarget() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if (view != mCircleImageView) {
                mTarget = view;
                break;
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
           int targetLeft=getPaddingLeft();
           int targetTop=getPaddingTop();
           mTarget.layout(targetLeft,targetTop,targetLeft+mTarget.getMeasuredWidth(),targetTop+mTarget.getMeasuredHeight());
           int circleWidth=mCircleImageView.getMeasuredWidth();
           int circleHeight=mCircleImageView.getMeasuredHeight();
           mCircleImageView.layout(
                   (getMeasuredWidth()-circleWidth)/2,
                   mCurrentTargetOffsetTop,
                   (getMeasuredWidth()-mCircleDimeter)/2+circleWidth,
                   mCurrentTargetOffsetTop+circleHeight);
    }


}
