package com.test.viewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.test.viewdemo.xview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

public class MarqueeViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marquee_view);
        MarqueeView marqueeView= (MarqueeView) findViewById(R.id.marquee_view);
        List<String> mStrings=new ArrayList<>();
        mStrings.add("测试数据1");
        mStrings.add("测试数据2");
        mStrings.add("测试数据3");
        marqueeView.setShowedStrList(mStrings);
        marqueeView.startMarquee();
    }
}
