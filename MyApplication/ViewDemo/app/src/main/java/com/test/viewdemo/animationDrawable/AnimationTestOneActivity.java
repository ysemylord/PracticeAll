package com.test.viewdemo.animationDrawable;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.test.viewdemo.mydrawable.MyRingDrawable;
import com.test.viewdemo.R;

import java.util.concurrent.TimeUnit;

/**
 * 使用三种方式驱动动画(选用时，优先级从高到低)
 * ObjectAnimation
 * ValueAnimation
 * Thread
 */
public class AnimationTestOneActivity extends AppCompatActivity {
    Handler mHandler =new Handler();
    private MyRingDrawable myRingDrawable2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_test_one);
        ImageView imageView= (ImageView) findViewById(R.id.image_view);
        ImageView imageView2= (ImageView) findViewById(R.id.image_view_2);
        MyCircleView myCircleView= (MyCircleView) findViewById(R.id.my_circle_view);
        final MyRingDrawable myRingDrawable = new MyRingDrawable();
        imageView.setBackgroundDrawable(myRingDrawable);
         myRingDrawable2 = new MyRingDrawable();
        imageView2.setBackgroundDrawable(myRingDrawable2);

        useThread();

        useValueAnimator(myRingDrawable);

        useObjectAnimator(myCircleView);
    }

    private void useObjectAnimator(MyCircleView myCircleView) {
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(myCircleView,"percent",0.0f,1f);
        objectAnimator.setDuration(2000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.start();
    }

    /**
     * 与使用线程驱动相比
     * 使用ValueAnimator的
     * 优点
     * 使用更简单，动画效果更平滑
     *
     *
     * 缺点
     * 没有线程控制的精细
     * @param myRingDrawable
     */
    private void useValueAnimator(final MyRingDrawable myRingDrawable) {
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,1f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                myRingDrawable.setPercent((float)animation.getAnimatedValue());
            }
        });
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setDuration(2000).start();
    }

    private void useThread() {
        Thread thread4=new Thread(new Runnable() {
            @Override
            public void run() {
                int progress=0;

                while (progress<=100){

                    Log.i("useThread", "run: "+progress);
                    try {
                        TimeUnit.MICROSECONDS.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progress++;
                    final int nowProgress=progress;
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {//不能在Thread内部创建Handler

                            myRingDrawable2.setPercent(nowProgress*1f/100);

                        }
                    });

                    if(progress==100){
                        progress=0;
                    }
                }
            }
        });
        thread4.start();
    }
}
