package com.example.xuyabo.androidperformance.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.xuyabo.androidperformance.R;

public class CustomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        CustomPerformanceLayout customPerformanceLayout= (CustomPerformanceLayout) findViewById(R.id.performanceview);
        customPerformanceLayout.setNameAndDes("冷冻披萨冷冻披萨冷冻披萨冷冻披萨冷冻披萨冷冻披萨冷冻披萨冷冻披萨冷冻披萨冷冻披萨冷冻披萨冷冻披萨","喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵" +
                "喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵喵");
    }
}
