package com.test.viewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     /*   ImageView circle_drawable_test= (ImageView) findViewById(R.id.circle_drawable_test);
        ExpandingCircleAnimationDrawable expandingCircleAnimationDrawable= new ExpandingCircleAnimationDrawable(50f);
        circle_drawable_test.setImageDrawable(expandingCircleAnimationDrawable);
        expandingCircleAnimationDrawable.start();*/

    }
}
