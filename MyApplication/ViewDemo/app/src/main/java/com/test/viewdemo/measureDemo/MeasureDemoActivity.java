package com.test.viewdemo.measureDemo;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

import com.test.viewdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MeasureDemoActivity extends AppCompatActivity {

    @Bind(R.id.button1)
    Button button1;
    String TAG = "MeasureDemoActivity";
    @Bind(R.id.wait_measure_textview)
    TextView mWaitMeasureTextview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure_demo);
        ButterKnife.bind(this);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams layoutParams1 = button1.getLayoutParams();
                Log.i(TAG, "onClick: before hide" + button1.getWidth());
                Log.i(TAG, "onClick: before hide" + layoutParams1.height);
                // button1.setVisibility(View.INVISIBLE);
                ViewGroup.LayoutParams layoutParams2 = button1.getLayoutParams();
                Log.i(TAG, "onClick: before hide" + button1.getWidth());
                Log.i(TAG, "onClick: before hide" + layoutParams2.height);
                ValueAnimator va = ValueAnimator.ofInt(button1.getWidth(), 0);
                va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        ViewGroup.LayoutParams layoutParams = button1.getLayoutParams();
                        layoutParams.width = (int) animation.getAnimatedValue();
                        button1.setLayoutParams(layoutParams);
                    }
                });
                va.start();
            }
        });


        //无效
        int simpleGetWidth = mWaitMeasureTextview.getWidth();

        //无效
        int simpleMeasuredWidth = mWaitMeasureTextview.getMeasuredWidth();

        //对于View来说无效,
        // TextView：适合Wrap_content的情况
        int width = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);//EXACTLY,AT_MOST无效
        int height = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        mWaitMeasureTextview.measure(width, height);
        int measuredWidth = mWaitMeasureTextview.getMeasuredWidth();
        int measuredHeight = mWaitMeasureTextview.getMeasuredHeight();

        //只能用在固定宽度，即:android:layout_widht="100dp";
        int simpleLayoutParamsWidth = mWaitMeasureTextview.getLayoutParams().width;




        Log.i(TAG, "测量 getWidth " + simpleGetWidth);
        Log.i(TAG, "测量 getMeasuredWidth " + simpleMeasuredWidth);
        Log.i(TAG, "测量 getMeasuredWidth after mearsured " + measuredWidth);
        Log.i(TAG, "测量 getMeasuredHeight after mearsured " + measuredHeight);
        Log.i(TAG, "测量 getLayoutParams().width " + simpleLayoutParamsWidth);


        //有效
        mWaitMeasureTextview.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int observerWidth = mWaitMeasureTextview.getWidth();
                int observerHeight = mWaitMeasureTextview.getHeight();
                mWaitMeasureTextview.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                Log.i(TAG, "测量 observableWidth " + observerWidth);
                Log.i(TAG, "测量 observerHeight " + observerHeight);

            }
        });

        //有效
        mWaitMeasureTextview.post(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "测量 runnableWidth " + mWaitMeasureTextview.getWidth());
                Log.i(TAG, "测量 runnableHeight " + mWaitMeasureTextview.getHeight());
            }
        });

    }
}
