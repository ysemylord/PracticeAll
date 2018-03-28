package com.example.xuyabo.androidperformance.smoothDetection.systraceUse;

import android.os.Bundle;
import android.os.Trace;
import android.support.v7.app.AppCompatActivity;

import com.example.xuyabo.androidperformance.R;

public class UseTraceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_trace);
        Trace.beginSection("mysection");
        for (int i=0;i<10000000;i++);
        Trace.endSection();
    }
}
