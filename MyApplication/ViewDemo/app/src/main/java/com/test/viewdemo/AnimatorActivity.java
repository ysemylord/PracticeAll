package com.test.viewdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * 说明
 * --------------------------------------------------------------
 * 情况1:ObjectAnimator.ofFloat(mTargetView,"属性",值x)
 * 和
 * 情况2:ObjectAnimator.ofFloat(mTargetView,"属性",值x1,值x2，值x3)
 * <p>
 * 情况1 初始值是 当前值 是get属性，最终值是 x
 * 情况2 初始值是 当前值 是x1，最终值是x2
 * ---------------------------------------------
 * <p>
 * ----------------------------------------------
 * ObjectAnimator.ofFloat(mTargetView,"属性",值x1,值x2，值x3)
 * <p>
 * 值x1,x2,x3是相对于最初始情况下的取值
 * 例如:ObjectAnimator.ofFloat(mTargetView,"scaleX",0.5f,1f，1.5f)
 * 其中0.5f，1f,1.5f是相对最初始状态下scaleX（即scaleX==1）的缩放程度;
 * <p>
 * ObjectAnimator.ofFloat(mTargetView,"tranlateX",100,200，400)
 * 其中100,200,400实现对于最初状态下translateX(即translateX==0)的取值
 * <p>
 * 其他的设置同期
 * <p>
 * ----------------------------------------------
 */
public class AnimatorActivity extends AppCompatActivity {
    private static final String TAG = "AnimatorActivity";
    View mTargetView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        mTargetView = findViewById(R.id.center_view);
    }

    public void scale1(View view) {
        ObjectAnimator objectAnimatorScaleX = ObjectAnimator.ofFloat(mTargetView, "scaleX", mTargetView.getScaleX(), 2f, 0.5f, 1f).setDuration(1000);
        ObjectAnimator objectAnimatorScaleY = ObjectAnimator.ofFloat(mTargetView, "scaleY", mTargetView.getScaleX(), 2f, 0.5f, 1f).setDuration(1000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorScaleX, objectAnimatorScaleY);
        animatorSet.start();
        //注
        //情况1:ObjectAnimator.ofFloat(mTargetView,"scaleX",一个值)
        //和
        // 情况2:ObjectAnimator.ofFloat(mTargetView,"scaleX",第一个值,多个值，最后一个值)
        // 的效果是不一样的
        //情况1 取值的变化范围是 view.getScaleX到"第一个值"，起始值为view.getScaleX
        //情况2 取值的变化范围是 "第一个值"到"最后一个值"，起始值为"第一个值"
    }

    /**
     * 证明了
     * 最终的宽度大小=最初始的视图大小*scale
     * 而不是 最终的宽度大小=现在的视图大小*scale
     *
     * @param view
     */
    public void scale2(View view) {
        ObjectAnimator objectAnimatorScaleX = ObjectAnimator.ofFloat(mTargetView, "scaleX", mTargetView.getScaleX(), 2f).setDuration(1000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(objectAnimatorScaleX);
        objectAnimatorScaleX.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Log.i(TAG, "onAnimationStart1: " + mTargetView.getScaleX());
                ObjectAnimator objectAnimatorScaleX2 = ObjectAnimator.ofFloat(mTargetView, "scaleX", mTargetView.getScaleX(), 1f).setDuration(1000);
                objectAnimatorScaleX2.start();
            }
        });


        animatorSet.start();

    }

    public void rotate1(View view) {
        mTargetView.setPivotX(0);
        mTargetView.setPivotY(0);
        ObjectAnimator objectAnimatorRotate = ObjectAnimator.ofFloat(mTargetView, "rotation", mTargetView.getRotation(), 180, 0).setDuration(2000);
        objectAnimatorRotate.start();
    }

    public void rotate2(View view) {
        mTargetView.setPivotX(0);
        mTargetView.setPivotY(0);
        ObjectAnimator objectAnimatorRotate = ObjectAnimator.ofFloat(mTargetView, "rotation", mTargetView.getRotation(), 180).setDuration(2000);
        objectAnimatorRotate.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ObjectAnimator objectAnimatorRotate = ObjectAnimator.ofFloat(mTargetView, "rotation", 0).setDuration(2000);
                objectAnimatorRotate.start();

            }
        });
        objectAnimatorRotate.start();
    }


    public void alpha(View view) {
        ObjectAnimator objectAnimatorAlpah = ObjectAnimator.ofFloat(mTargetView, "alpha", mTargetView.getAlpha(), 0.5f, 1f).setDuration(2000);
        objectAnimatorAlpah.start();
    }

    public void tranlate(View view) {
        //mTargetView.getTranslationX();
        ObjectAnimator objectAnimatorTranlateX = ObjectAnimator.ofFloat(mTargetView, "translationX", mTargetView.getTranslationX(), 200, 400).setDuration(2000);
        ObjectAnimator objectAnimatorTranlateX2 = ObjectAnimator.ofFloat(mTargetView, "translationX", 400).setDuration(2000);
        AnimatorSet objectAnimator = new AnimatorSet();
        objectAnimator.play(objectAnimatorTranlateX).before(objectAnimatorTranlateX2);
        objectAnimator.start();

    }
}
