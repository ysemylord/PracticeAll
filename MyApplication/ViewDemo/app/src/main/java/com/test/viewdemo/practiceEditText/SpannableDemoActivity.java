package com.test.viewdemo.practiceEditText;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.widget.EditText;

import com.test.viewdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;

public class SpannableDemoActivity extends AppCompatActivity {

    @Bind(R.id.EditText1)
    EditText mTextView1;
    @Bind(R.id.EditText2)
    EditText mTextView2;
    @Bind(R.id.EditText3)
    EditText mTextView3;
    @Bind(R.id.EditText4)
    EditText mTextView4;
    @Bind(R.id.EditText5)
    EditText mEditText5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spannable_demo);
        ButterKnife.bind(this);

        //flag参数的使用
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary));
        String string = "012345678";
        SpannableStringBuilder spannableString1 = new SpannableStringBuilder(string);
        SpannableStringBuilder spannableString2 = new SpannableStringBuilder(string);
        SpannableStringBuilder spannableString3 = new SpannableStringBuilder(string);
        SpannableStringBuilder spannableString4 = new SpannableStringBuilder(string);
        int start = 3, end = 7;
        spannableString1.setSpan(foregroundColorSpan, start, end, SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString2.setSpan(foregroundColorSpan, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString3.setSpan(foregroundColorSpan, start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        spannableString4.setSpan(foregroundColorSpan, start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        spannableString1.append("323");
        spannableString2.append("323");
        spannableString3.append("323");
        spannableString4.append("323");
        mTextView1.setText(spannableString1);
        mTextView2.setText(spannableString2);
        mTextView3.setText(spannableString3);
        mTextView4.setText(spannableString4);

        //ImageSpan
        String str="你好[图片]";
        SpannableStringBuilder spannableStringBuilder=new SpannableStringBuilder(str);
        ImageSpan imageSpan=new ImageSpan(this, BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        spannableStringBuilder.setSpan(imageSpan,2,str.length(),SPAN_EXCLUSIVE_EXCLUSIVE);
        mEditText5.setText(spannableStringBuilder);



    }
}
