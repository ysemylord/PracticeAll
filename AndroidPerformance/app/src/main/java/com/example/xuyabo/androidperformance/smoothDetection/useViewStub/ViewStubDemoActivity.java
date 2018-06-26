package com.example.xuyabo.androidperformance.smoothDetection.useViewStub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.Toast;

import com.example.xuyabo.androidperformance.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewStubDemoActivity extends AppCompatActivity {

    @Bind(R.id.view_stub)
    ViewStub mViewStub;
    @Bind(R.id.inflate_button)
    Button mInflateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stub_demo);
        ButterKnife.bind(this);
        mViewStub.setOnInflateListener(new ViewStub.OnInflateListener() {
            @Override
            public void onInflate(ViewStub stub, View inflated) {

            }
        });
    }

    @OnClick(R.id.inflate_button)
    public void onViewClicked() {
        if(mViewStub.getParent()==null){
            Toast.makeText(this,"布局已填充",Toast.LENGTH_SHORT).show();
            return;
        }
        View inflated=mViewStub.inflate();//根据ViewStub.inflate()的源码可知，如果是第二次inflate(),ViewStub已经从Parent中移除，会抛出异常。
    }
}
