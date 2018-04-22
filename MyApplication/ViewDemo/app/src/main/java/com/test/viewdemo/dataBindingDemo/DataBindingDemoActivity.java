package com.test.viewdemo.dataBindingDemo;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.test.viewdemo.R;

public class DataBindingDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this,R.layout.activity_data_binding_demo);
    }
}
