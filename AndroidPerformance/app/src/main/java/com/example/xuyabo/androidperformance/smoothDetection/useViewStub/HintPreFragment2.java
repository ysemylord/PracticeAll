package com.example.xuyabo.androidperformance.smoothDetection.useViewStub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

import com.example.xuyabo.androidperformance.R;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xuyabo on 2018/6/26.
 * Framgent每次可见时，做某些操作
 */

public class HintPreFragment2 extends Fragment {
    private static final String TAG = "TestHintFragment";
    @Bind(R.id.view_stub)
    ViewStub mViewStub;
    private String pageTag;

    public static HintPreFragment2 getInstance(String pageTag) {
        Log.i(TAG, "getInstance: " + pageTag);
        HintPreFragment2 testHintFragment = new HintPreFragment2();
        Bundle arguments = new Bundle();
        arguments.putString("pageTag", pageTag);
        testHintFragment.setArguments(arguments);
        return testHintFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageTag = getArguments().getString("pageTag");
        Log.i(TAG, "onCreate:" + pageTag);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_with_stubview, container, false);

        Log.i(TAG, "onCreateView:" + pageTag);
        ButterKnife.bind(this, view);
        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            inflateViewAndLoadData();
        }
    }

    TextView inflatedTextView;
    private void inflateViewAndLoadData() {
        if (mViewStub == null //布局未加载，即Fragment的周期函数未调用
                ) {
            return;
        }

        if(mViewStub.getParent()!=null) {
            inflatedTextView= (TextView) mViewStub.inflate();
        }
        inflatedTextView.setText(pageTag+ new Random().nextInt());
        try {
            Thread.sleep(1000);//模拟网络请求
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        inflateViewAndLoadData();
        Log.i(TAG, "setUserVisibleHint " + pageTag + ":" + isVisibleToUser);
    }

    @Override
    public boolean getUserVisibleHint() {
        Log.i(TAG, "getUserVisibleHint");
        return super.getUserVisibleHint();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
