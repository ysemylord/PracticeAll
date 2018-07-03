package com.test.viewdemo.learnStructure.mvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.viewdemo.R;
import com.test.viewdemo.databinding.FragmentMvvmBinding;

/**
 * Created by xuyabo on 2018/7/3.
 */

public class MVVMSearchFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentMvvmBinding fragmentMvvmBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_mvvm,container,false);
        fragmentMvvmBinding.setViewmodel(new SearchViewModel());
        return fragmentMvvmBinding.getRoot();
    }
}
