package com.test.viewdemo.practiceEditText;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.widget.TextView;

import com.test.viewdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TextViewDemoActivity extends AppCompatActivity {

    @Bind(R.id.textView)
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_demo);
        ButterKnife.bind(this);
        SpannableString spannableString=new SpannableString("我是一只小小鸟");
        Drawable drawable = getResources().getDrawable(R.drawable.shape);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());//必须要设置这个
        spannableString.setSpan(new ImageSpan(drawable),0,3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTextView.setText(spannableString);
    }
}
