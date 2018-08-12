package com.example.xuyabo.androidperformance.memoryDemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.xuyabo.androidperformance.R;

public class LeakCanaryHostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak_canary_host);
    }

    public void skipToLeack(View view) {
        Intent intent=new Intent(this,LeakCanaryDemoActivity.class);
        startActivity(intent);
    }
}
