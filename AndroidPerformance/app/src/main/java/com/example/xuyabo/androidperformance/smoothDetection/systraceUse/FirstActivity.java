package com.example.xuyabo.androidperformance.smoothDetection.systraceUse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.xuyabo.androidperformance.R;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    public void skip(View view) {
        Intent intent=new Intent(getBaseContext(),UseTraceActivity.class);
        startActivity(intent);
    }
}
