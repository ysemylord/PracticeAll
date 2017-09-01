package com.test.viewdemo.xview.marqueeView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created  on 2017/8/23.
 *利用属性动画制作一个跑马灯。
 *
 * 缺点
 * 1.创建多个ObjectAnimator
 * 2.创建多个textView
 * 优点
 * 1.代码相对清新
 * 2.停止跑马灯较为方便
 * @author xyb
 */

public class MarqueeViewByAniamtion extends LinearLayout {
    private static final String TAG = "MarqueeView";
    List<String> mShowedStrList;
    private ObjectAnimator mLastObjectAnimator;
    private AnimatorSet animatorSet;

    public MarqueeViewByAniamtion(Context context) {
        this(context, null);
    }

    public MarqueeViewByAniamtion(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MarqueeViewByAniamtion(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public void setShowedStrList(List<String> showedStrList) {
        mShowedStrList = showedStrList;
    }

    public void startMarquee() {
        if (mShowedStrList == null || mShowedStrList.size() == 0) {
            return;
        }
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                getViewTreeObserver().removeOnPreDrawListener(this);

                animatorSet = new AnimatorSet();
                for (final String str : mShowedStrList
                        ) {
                    TextView textView = new android.support.v7.widget.AppCompatTextView(getContext()) {
                        @Override
                        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                            int w = View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
                            int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                            super.onMeasure(w, h);
                        }
                    };
                    addView(textView);
                    textView.setText(str);
                    textView.setX(getWidth());
                    float textWidth = textView.getPaint().measureText(str);
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(textView, "X", getWidth(), -textWidth);
                    objectAnimator.setInterpolator(new LinearInterpolator());
                    objectAnimator.setDuration((long) textWidth * 20);//动画时间和文本长度正相关

                    objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            Log.i(TAG, "动画执行中");
                        }
                    });

                    if (mLastObjectAnimator == null) {
                        animatorSet.play(objectAnimator);
                        mLastObjectAnimator = objectAnimator;
                    } else {
                        animatorSet.play(mLastObjectAnimator).before(objectAnimator);
                        mLastObjectAnimator = objectAnimator;
                    }

                }


                animatorSet.addListener(new AnimatorListenerAdapter() {

                    boolean mCancel;

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        super.onAnimationCancel(animation);
                        mCancel = true;
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        if (mCancel != true) {
                            animatorSet.start();
                        }
                    }
                });
                animatorSet.start();
                return true;
            }
        });
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if(animatorSet==null){
            return;
        }
        final boolean visible = visibility == VISIBLE && getVisibility() == VISIBLE;
        if (!visible) {
            animatorSet.cancel();
        }else{
            animatorSet.start();
        }
    }

    public void stop() {
        animatorSet.cancel();
    }
}
