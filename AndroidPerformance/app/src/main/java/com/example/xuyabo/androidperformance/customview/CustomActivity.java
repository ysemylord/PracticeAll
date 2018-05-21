package com.example.xuyabo.androidperformance.customview;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.xuyabo.androidperformance.R;

public class CustomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        CustomPerformanceLayout customPerformanceLayout= (CustomPerformanceLayout) findViewById(R.id.performanceview);
        customPerformanceLayout.setNameAndDes("冷冻披萨冷冻披萨冷冻披萨冷冻披萨冷冻披萨冷冻披萨冷冻披萨冷冻披萨冷冻披萨冷冻披萨冷冻披萨冷冻披萨","喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵" +
                "喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵","http://p3.pstatp.com/large/pgc-image/1526533051610bbce40dad0");
        final ImageView imageView= (ImageView) findViewById(R.id.imageview);
        Glide.with(this)
                .load("http://p3.pstatp.com/large/pgc-image/1526533051610bbce40dad0")
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        Drawable mIconDrawable=(resource.getCurrent());
                        imageView.setImageDrawable(mIconDrawable);
                    }
                });
    }
}
