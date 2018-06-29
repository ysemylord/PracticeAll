package com.test.viewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.test.viewdemo.mydrawable.PriceFrameDrawable;
import com.test.viewdemo.mydrawable.RmdBgDrawable;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DrawableTestActivity extends AppCompatActivity {

    @Bind(R.id.test_price_drawable)
    View mTestPriceDrawable;
    @Bind(R.id.rmd_bg)
    View mRmdBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_test);
        ButterKnife.bind(this);
        mTestPriceDrawable.setBackgroundDrawable(new PriceFrameDrawable());
        mRmdBg.setBackgroundDrawable(new RmdBgDrawable());

    }
}
