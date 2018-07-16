package com.example.xuyabo.androidperformance.memoryDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LeakCanaryHostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak_canary_host);
    }
}
