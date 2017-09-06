package com.test.viewdemo.coordinateDemo.viewCompatOffsetDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;

import com.test.viewdemo.R;

/**
 * 需求
 * EditText可以设置targetView相对于初始位置是的偏移
 *
 * 储备知识
 * ViewCompat.offsetTopAndBottom（view,offset） 改变view的偏移量，即view.getTop的值
 *
 */
public class ViewCompatOffsetHelperDemoActivity extends AppCompatActivity {
    private static final String TAG="ViewCompatDemoActivity";
    private View mTargetView;
    private EditText mOffsetValueEt;
    private ViewOffsetHelper mViewOffsetHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_compat_demo);
        mTargetView =findViewById(R.id.targeView);
        mViewOffsetHelper=new ViewOffsetHelper(mTargetView);
        mOffsetValueEt= (EditText) findViewById(R.id.offset_value_et);
        mTargetView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mViewOffsetHelper.onViewLayout();
            }
        });

    }
     //mOffsetTop - (mView.getTop() - mLayoutTop)
    public void move(View view) {
        Log.i(TAG, ""+view.getTop());
        int desOffsetTop=Integer.parseInt(mOffsetValueEt.getText().toString().trim());
        mViewOffsetHelper.setTopAndBottomOffset(desOffsetTop);

    }
}
