package com.test.viewdemo.animationDemo;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by xuyabo on 2018/3/14.
 */

public class MatrixGroup extends FrameLayout {
    public MatrixGroup(Context context) {
        this(context,null);
    }

    public MatrixGroup(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MatrixGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(0,400);
        super.dispatchDraw(canvas);

        canvas.restore();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
