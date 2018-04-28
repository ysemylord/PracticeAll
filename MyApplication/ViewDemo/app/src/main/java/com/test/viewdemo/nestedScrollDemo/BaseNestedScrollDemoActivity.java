package com.test.viewdemo.nestedScrollDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.test.viewdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BaseNestedScrollDemoActivity extends AppCompatActivity {

    @Bind(R.id.show_content)
    TextView mShowContent;
    @Bind(R.id.show_content2)
    TextView mShowContent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_nested_scroll_demo);
        ButterKnife.bind(this);
        for (int i = 0; i < 1000; i++) {
            mShowContent.append(i + "");
            mShowContent2.append(i + "");
        }
    }
}
