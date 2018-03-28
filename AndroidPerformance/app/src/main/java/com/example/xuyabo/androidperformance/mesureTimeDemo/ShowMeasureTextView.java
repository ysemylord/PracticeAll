package com.example.xuyabo.androidperformance.mesureTimeDemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by xuyabo on 2018/3/28.
 */

public class ShowMeasureTextView extends android.support.v7.widget.AppCompatTextView {
    private static final String TAG = "ShowMeasureTextView";
    public ShowMeasureTextView(Context context) {
        super(context);
    }

    public ShowMeasureTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i(TAG, getText()+" onMeasure: ");
    }
}
