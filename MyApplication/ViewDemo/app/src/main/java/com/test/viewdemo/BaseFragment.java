package com.test.viewdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xiazdong on 17/5/24.
 */

public class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";
    @Override
    public void onAttach(Context context) {
        sout("[onAttach] BEGIN");
        super.onAttach(context);
        sout("[onAttach] END");
    }

    private void sout(String s) {
        Log.i(getClass().getSimpleName(), s);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        sout("[onCreate] BEGIN");
        super.onCreate(savedInstanceState);
        sout("[onCreate] END");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        sout("[onViewCreated] BEGIN");
        super.onViewCreated(view, savedInstanceState);
        sout("[onViewCreated] END");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        sout("[onActivityCreated] BEGIN");
        super.onActivityCreated(savedInstanceState);
        sout("[onActivityCreated] END");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sout("[onCreateView]");

        return  super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    public void onStart() {
        sout("[onStart] BEGIN");
        super.onStart();
        sout("[onStart] END");
    }

    @Override
    public void onResume() {
        sout("[onResume] BEGIN");
        super.onResume();
        sout("[onResume] END");
    }

    @Override
    public void onPause() {
        sout("[onPause] BEGIN");
        super.onPause();
        sout("[onPause] END");
    }

    @Override
    public void onStop() {
        sout("[onStop] BEGIN");
        super.onStop();
        sout("[onStop] END");
    }

    @Override
    public void onDestroyView() {
        sout("[onDestroyView] BEGIN");
        super.onDestroyView();
        sout("[onDestroyView] END");
    }

    @Override
    public void onDestroy() {
        sout("[onDestroy] BEGIN");
        super.onDestroy();
        sout("[onDestroy] END");
    }

    @Override
    public void onDetach() {
        sout("[onDetach] BEGIN");
        super.onDetach();
        sout("[onDetach] END");
    }

}
