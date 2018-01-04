package com.test.viewdemo.drawableDemo;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.test.viewdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DrawableDemoActivity extends AppCompatActivity {

    @Bind(R.id.my_checkbox)
    MyCheckBox mMyCheckbox;
    @Bind(R.id.clip_bg_textview)
    TextView mClipBgTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_demo);
        ButterKnife.bind(this);
        ClipDrawable clipDrawable= (ClipDrawable) mClipBgTextview.getBackground();
        clipDrawable.setLevel(10000);
    }

    public void changeEnable(View view) {
        mMyCheckbox.setEnabled(!mMyCheckbox.isEnabled());
    }
}
