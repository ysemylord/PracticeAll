package com.test.viewdemo.viewAnimationDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.test.viewdemo.R;

/**
 *
 */
public class LayoutAnimationDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_animation_demo);
        Animation animationItem = AnimationUtils.loadAnimation(this, R.anim.layout_animation_item);
        LayoutAnimationController layoutAnimationController = new LayoutAnimationController(animationItem);
        layoutAnimationController.setDelay(0.1f);
        layoutAnimationController.setOrder(LayoutAnimationController.ORDER_NORMAL);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.test_group);
        viewGroup.setLayoutAnimation(layoutAnimationController);

    }
}
