package com.test.viewdemo.viewPager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TextFragment extends  android.support.v4.app.Fragment{
    public static TextFragment newInstance(String text){
        TextFragment textFragment=new TextFragment();
        Bundle bundle=new Bundle();
        bundle.putString("text",text);
        textFragment.setArguments(bundle);
        return  textFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView=new TextView(getContext());
        textView.setText(getArguments().getString("text"));
        return textView;
    }
}