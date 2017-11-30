package com.test.viewdemo.viewAnimationDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.test.viewdemo.R;

/**
 * 视图动画基础
 */
public class BaseViewAnimationDemoActivity extends AppCompatActivity {

    View mCenterView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        mCenterView=findViewById(R.id.center_view);

    }

    public void scale(final View view) {
        final ScaleAnimation scaleAnimationSmall=new ScaleAnimation(1,0.5f,1,0.5f);
        scaleAnimationSmall.setDuration(1000);
        final ScaleAnimation scaleAnimationBig=new ScaleAnimation(0.5f,1,0.5f,1, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimationBig.setDuration(1000);


        scaleAnimationBig.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mCenterView.startAnimation(scaleAnimationSmall);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });





        mCenterView.startAnimation(scaleAnimationSmall);

    }

    public void rotate(View view) {
        final RotateAnimation rotateAnimationCW=new RotateAnimation(0,180);
        rotateAnimationCW.setDuration(1000);
        final RotateAnimation rotateAnimationCCW=new RotateAnimation(180,0);
        rotateAnimationCCW.setDuration(1000);



        rotateAnimationCW.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mCenterView.startAnimation(rotateAnimationCCW);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mCenterView.startAnimation(rotateAnimationCW);
    }

    public void tranlate(View view) {
        TranslateAnimation translateAnimationTopLeftCorner=new TranslateAnimation(0,-200,0,-200);
        translateAnimationTopLeftCorner.setDuration(2000);
        final TranslateAnimation translateAnimationReturn=new TranslateAnimation(-200,0,-200,0);
        translateAnimationReturn.setDuration(2000);
        translateAnimationTopLeftCorner.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                 mCenterView.startAnimation(translateAnimationReturn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mCenterView.startAnimation(translateAnimationTopLeftCorner);
    }

    public void tranlate2(View view) {
        TranslateAnimation translateAnimationTopLeftCorner=new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,-0.3f,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,-0.3f);
        translateAnimationTopLeftCorner.setDuration(2000);
        final TranslateAnimation translateAnimationReturn=new TranslateAnimation(Animation.RELATIVE_TO_PARENT,-0.3f,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,-0.3f,Animation.RELATIVE_TO_PARENT,0);
        translateAnimationReturn.setDuration(2000);
        translateAnimationTopLeftCorner.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                 mCenterView.startAnimation(translateAnimationReturn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mCenterView.startAnimation(translateAnimationTopLeftCorner);
    }

    public void alpha(View view) {
        final AlphaAnimation alphaAnimation1=new AlphaAnimation(1,0.2f);
        alphaAnimation1.setDuration(2000);
        final AlphaAnimation alphaAnimation2=new AlphaAnimation(0.2f,1f);
        alphaAnimation2.setDuration(2000);
        alphaAnimation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mCenterView.startAnimation(alphaAnimation2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mCenterView.startAnimation(alphaAnimation1);

    }
}
