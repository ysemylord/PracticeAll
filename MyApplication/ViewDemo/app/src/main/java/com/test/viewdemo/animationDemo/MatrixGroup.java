package com.test.viewdemo.animationDemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by xuyabo on 2018/3/14.
 */

public class MatrixGroup extends LinearLayout{
    public MatrixGroup(Context context) {
        super(context);
    }

    public MatrixGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MatrixGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void dispatchDraw(Canvas canvas) {
        Matrix matrix=new Matrix();
        matrix.setTranslate(400,400);
        canvas.setMatrix(matrix);
        super.dispatchDraw(canvas);
    }
}
