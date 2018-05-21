package com.example.xuyabo.androidperformance.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.text.DynamicLayout;
import android.text.Layout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xuyabo on 2018/5/21.
 */

public class StaticLayoutDemo  extends View {
    private DynamicLayout mStaticLayout;
    TextPaint textPaint = new TextPaint();

    public StaticLayoutDemo(Context context) {
        this(context,null);
    }

    public StaticLayoutDemo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        textPaint.setTextSize(40f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String source = "大家好大家好大家好大家好大家好大家好大家好";
        mStaticLayout=new DynamicLayout(source, textPaint, canvas.getWidth(),Layout.Alignment.ALIGN_NORMAL,1,0,false);
        mStaticLayout.draw(canvas);
    }

}
