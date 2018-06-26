package com.example.xuyabo.androidperformance.smoothDetection.useViewStub;

import android.databinding.DataBindingUtil;
import android.databinding.ViewStubProxy;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;

import com.example.xuyabo.androidperformance.R;
import com.example.xuyabo.androidperformance.databinding.ActivityViewStubAndDataBindingBinding;

public class ViewStubAndDataBindingActivity extends AppCompatActivity {

    private ActivityViewStubAndDataBindingBinding mActivityViewStubAndDataBindingBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_view_stub_and_data_binding);
        mActivityViewStubAndDataBindingBinding = DataBindingUtil.setContentView(this, R.layout.activity_view_stub_and_data_binding);
        mActivityViewStubAndDataBindingBinding.inflateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewStubProxy viewStubProxy = mActivityViewStubAndDataBindingBinding.viewStub;
                viewStubProxy.getViewStub().inflate();
                viewStubProxy.getBinding();//如果填充的布局是DataBiniding布局文件，则可以通过此方法获取相应的Binding类
            }
        });
        ViewStubProxy viewStubProxy = mActivityViewStubAndDataBindingBinding.viewStub;
        viewStubProxy.setOnInflateListener(new ViewStub.OnInflateListener() {
            @Override
            public void onInflate(ViewStub stub, View inflated) {

            }
        });
    }
}
