package com.example.xuyabo.androidperformance.memoryDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.xuyabo.androidperformance.R;

public class LeakCanaryDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak_canary_demo);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
