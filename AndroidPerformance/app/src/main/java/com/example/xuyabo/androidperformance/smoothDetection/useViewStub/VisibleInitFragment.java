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
 * Fragment第一次可见时，初始化界面和数据
 */

public class VisibleInitFragment extends Fragment {
    private static final String TAG = "TestHintFragment";
    @Bind(R.id.view_stub)
    ViewStub mViewStub;
    private String pageTag;

    public static VisibleInitFragment getInstance(String pageTag) {
        Log.i(TAG, "getInstance: " + pageTag);
        VisibleInitFragment testHintFragment = new VisibleInitFragment();
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint()) {
            inflateViewAndLoadData();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    TextView inflatedTextView;

    private void inflateViewAndLoadData() {

        if (mViewStub == null //布局未加载，即Fragment的周期函数为调用
                || mViewStub.getParent() == null//mViewStub已从parent中移出，即inflateViewAndLoadData已经加载过
                ) {
            return;
        }
        inflatedTextView = (TextView) mViewStub.inflate();
        try {
            Thread.sleep(1000);//模拟网络请求
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        inflatedTextView.setText(pageTag+ new Random().nextInt());

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
