package com.test.viewdemo.notificationDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.test.viewdemo.R;

public class InSingleTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_single_task);
        setTitle("InSingleTaskActivity");
    }
}
