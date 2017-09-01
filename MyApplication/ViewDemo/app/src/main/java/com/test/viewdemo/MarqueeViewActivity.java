package com.test.viewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.test.viewdemo.xview.marqueeView.MarqueeViewByAniamtion;

import java.util.ArrayList;
import java.util.List;

public class MarqueeViewActivity extends AppCompatActivity {

    private MarqueeViewByAniamtion marqueeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marquee_view);
         marqueeView= (MarqueeViewByAniamtion) findViewById(R.id.marquee_view);
        List<String> mStrings=new ArrayList<>();
        mStrings.add("1.大家都是好人啦啦啦啦啦啦阿里");
        mStrings.add("2.大家都是好人啦啦啦啦啦啦腾讯");
        mStrings.add("3.大家都是好人啦啦啦啦啦啦百度");
        marqueeView.setShowedStrList(mStrings);
        marqueeView.startMarquee();
    }

    public void finishPage(View view) {
        finish();
    }

    public void finishAniamtion(View view) {
        marqueeView.stop();
    }
}
