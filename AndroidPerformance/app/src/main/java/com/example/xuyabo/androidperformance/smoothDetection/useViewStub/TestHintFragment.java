package com.example.xuyabo.androidperformance.smoothDetection.useViewStub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by xuyabo on 2018/6/26.
 */

public class TestHintFragment extends Fragment {
    private static final String TAG = "TestHintFragment";
    private String pageTag;
    public static TestHintFragment getInstance(String pageTag){
        Log.i(TAG, "getInstance: "+pageTag);
        TestHintFragment testHintFragment = new TestHintFragment();
        Bundle arguments = new Bundle();
        arguments.putString("pageTag",pageTag);
        testHintFragment.setArguments(arguments);
        return testHintFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageTag=getArguments().getString("pageTag");
        Log.i(TAG, "onCreate:"+pageTag);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = (TextView) inflater.inflate(android.R.layout.simple_list_item_1, container, false);
        textView.setText(pageTag);
        Log.i(TAG, "onCreateView:"+pageTag);
        return textView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i(TAG, "setUserVisibleHint "+pageTag+":"+isVisibleToUser);
    }

    @Override
    public boolean getUserVisibleHint() {
        Log.i(TAG, "getUserVisibleHint");
        return super.getUserVisibleHint();
    }
}
