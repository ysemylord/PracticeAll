package com.test.viewdemo.coordinateDemo.viewCompatOffsetDemo;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
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
public class ViewCompatDemoActivity extends AppCompatActivity {
    private static final String TAG="ViewCompatDemoActivity";
    private View mTargetView;
    private EditText mOffsetValueEt;
    private int mInitTop;//targetView初始时的偏移量
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_compat_demo);
        mTargetView =findViewById(R.id.targeView);
        mOffsetValueEt= (EditText) findViewById(R.id.offset_value_et);
        mTargetView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mTargetView.getViewTreeObserver().removeOnPreDrawListener(this);
                mInitTop = mTargetView.getTop();
                return true;
            }
        });

    }
     //mOffsetTop - (mView.getTop() - mLayoutTop)
    public void move(View view) {
        Log.i(TAG, ""+view.getTop());
        int desOffsetTop=Integer.parseInt(mOffsetValueEt.getText().toString().trim());
        //offsetTopAndBottom 设置view在竖直方向上,在当前位置的基础上偏移offsett，改变的是getTop（）的值
        //desOffsetTop - (view.getTop() - mInitTop)计算的值是在初始位置的基础上偏移desOffsetTop的值
        int offset = desOffsetTop - (view.getTop() - mInitTop);
        ViewCompat.offsetTopAndBottom(view, offset);
        Log.i(TAG, "move: "+view.getScrollY());
    }
}
