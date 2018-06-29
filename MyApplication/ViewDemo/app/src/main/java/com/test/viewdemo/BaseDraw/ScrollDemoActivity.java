package com.test.viewdemo.BaseDraw;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.test.viewdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ScrollDemoActivity extends AppCompatActivity {

    @Bind(R.id.scroll_btn)
    Button mScrollBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_demo);
        ButterKnife.bind(this);
        mScrollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScrollBtn.scrollBy(0,10);
            }
        });
    }
}
