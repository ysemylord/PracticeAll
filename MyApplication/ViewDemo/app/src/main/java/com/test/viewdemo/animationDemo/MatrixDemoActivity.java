package com.test.viewdemo.animationDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;

import com.test.viewdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MatrixDemoActivity extends AppCompatActivity {
    private static final String TAG = "MatrixDemoActivity";

    @Bind(R.id.root)
    ViewGroup mRoot;
    @Bind(R.id.btn)
    Button mBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_demo);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.btn)
    public void onViewClicked() {
        mRoot.scrollBy(0,-20);
        Log.i(TAG, "onViewClicked: getY"+mBtn.getY());
        Log.i(TAG, "onViewClicked: getTop"+mBtn.getTop());
        Log.i(TAG, "onViewClicked: getScrollY"+mBtn.getScrollY());


    }
}
