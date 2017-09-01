package com.test.viewdemo.xview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created  on 2017/8/23.
 *
 * @author xyb
 */

public class MarqueeView extends LinearLayout {
    List<String> mShowedStrList;
    private ObjectAnimator mLastObjectAnimator;

    public MarqueeView(Context context) {
        this(context, null);
    }

    public MarqueeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MarqueeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
    }


    public void setShowedStrList(List<String> showedStrList) {
        mShowedStrList = showedStrList;
    }

    public void startMarquee() {
        if(mShowedStrList ==null|| mShowedStrList.size()==0){
            return;
        }
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                getViewTreeObserver().removeOnPreDrawListener(this);

                AnimatorSet animatorSet = new AnimatorSet();
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
                    float textWidth = textView.getPaint().measureText(str);
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(textView, "translationX", getWidth(), -textWidth);
                    objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
                    objectAnimator.setDuration(5000);

                    if(mLastObjectAnimator==null){
                        animatorSet.play(objectAnimator);
                        mLastObjectAnimator=objectAnimator;
                    }else{
                        animatorSet.play(mLastObjectAnimator).before(objectAnimator);
                        mLastObjectAnimator=objectAnimator;
                    }

                }

                animatorSet.start();
                return true;
            }
        });
    }
}
