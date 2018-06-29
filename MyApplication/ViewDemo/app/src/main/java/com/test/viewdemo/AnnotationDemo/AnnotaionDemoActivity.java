package com.test.viewdemo.AnnotationDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.test.viewdemo.R;

public class AnnotaionDemoActivity extends AppCompatActivity {

    @MyButterKnife(R.id.my_btn)
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotaion_demo);
        ButterKnifeProc.bind(this);
        mButton.setText("222");
    }
}
