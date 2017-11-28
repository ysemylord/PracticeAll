package com.test.viewdemo.animationDrawable;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.test.viewdemo.R;

/**
 * 使用三种方式驱动动画(选用时，优先级从高到低)
 * ObjectAnimation
 * ValueAnimation
 * Thread
 */
public class AnimationTestTwoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_test_one);

        MyCircleView myCircleView= (MyCircleView) findViewById(R.id.my_circle_view);
        MyArcView myArcView= (MyArcView) findViewById(R.id.my_arc_view);

        useObjectAnimatorCircle(myCircleView);

        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(myArcView,"percent",0.0f,1f);
        objectAnimator.setDuration(2000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.start();
    }

    private void useObjectAnimatorCircle(MyCircleView myCircleView) {
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(myCircleView,"percent",0.0f,1f);
        objectAnimator.setDuration(2000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.start();
    }


}
