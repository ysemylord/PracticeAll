package com.test.viewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;

public class AnimationActivity extends AppCompatActivity {

    View mCenterView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        mCenterView=findViewById(R.id.center_view);

    }

    public void scale(final View view) {
        final ScaleAnimation scaleAnimationSmall=new ScaleAnimation(1,0.5f,1,0.5f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
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
        //Tra
    }
}
