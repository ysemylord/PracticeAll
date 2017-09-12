package com.test.viewdemo.viewPager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TextFragment extends  android.support.v4.app.Fragment{
    private static final String TAG="TextFragment";
    private String mText;

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }


    public TextFragment() {
        Log.i(TAG, "TextFragment 创建 ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView=new TextView(getContext());
        textView.setText(mText);
        return textView;
    }
}