package com.example.xuyabo.androidperformance.mesureTimeDemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * Created by xuyabo on 2018/3/28.
 */

public class ShowMeasureTimeLinearLayout extends LinearLayout {
    private static final String TAG = "ShowMeasureTimeLinearLa";
    public ShowMeasureTimeLinearLayout(Context context) {
        super(context);
    }

    public ShowMeasureTimeLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i(TAG, "LinearLayout onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
