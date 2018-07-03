package com.test.viewdemo.learnStructure.mvvm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.test.viewdemo.R;

public class MVVMDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvmdemo);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content, new MVVMSearchFragment())
                .commit();
    }
}
