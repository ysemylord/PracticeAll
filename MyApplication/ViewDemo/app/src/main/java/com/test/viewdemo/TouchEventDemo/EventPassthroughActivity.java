package com.test.viewdemo.TouchEventDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.test.viewdemo.R;

/**
 覆盖上层的onTouchEvent方法，返回false
 或者设置OnTouchListener 返回false
 都可以将事件传递到下层
 而且OnTouchListener是优先于onTouchEvent方法的
 */
public class EventPassthroughActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_passthrough);
        findViewById(R.id.topPanel).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

    }

    public void onClick(View view) {
        Toast.makeText(this,"按钮点击到了",Toast.LENGTH_SHORT).show();
    }
}
