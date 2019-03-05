package com.test.viewdemo.cameraDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.test.viewdemo.R;

public class CameraDActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_d);
        FlipView flipView=findViewById(R.id.flip_view);

        //flipView.autoFlip();
    }
}
