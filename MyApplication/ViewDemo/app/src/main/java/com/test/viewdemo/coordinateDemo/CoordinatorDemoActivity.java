package com.test.viewdemo.coordinateDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.test.viewdemo.R;

public class CoordinatorDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_demo);
        findViewById(R.id.button).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    float nowX = event.getX();
                    float nowY = event.getY();
                    v.setX(nowX-v.getWidth()/2);
                    v.setY(nowY-v.getHeight()/2);
                }
                return true;
            }
        });
    }
}
