package com.test.viewdemo.viewPager.verifyFragmentManager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.viewdemo.BaseFragment;

/**
 * Created by xuyabo on 2017/12/27.
 */

public class Fragment2 extends BaseFragment{


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
        TextView textView=new TextView(getContext());
        textView.setText("Fragment2");
        return textView;
    }
}
