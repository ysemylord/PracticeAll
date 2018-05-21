package com.example.xuyabo.androidperformance.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.DynamicLayout;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xuyabo on 2018/5/21.
 */

public class CustomPerformanceLayout extends View {
    private DynamicLayout mNameSaticLayout;
    private DynamicLayout mDesSaticLayout;
    private TextPaint mNameTextPaint;
    private TextPaint mDesTextPaint;
    private String mNameStr;
    private String mDesStr;
    private int mTextOffset=0;

    public CustomPerformanceLayout(Context context) {
        this(context,null);
    }

    public CustomPerformanceLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mNameTextPaint=new TextPaint();
        mNameTextPaint.setTextSize(25);
        mNameTextPaint.setColor(Color.BLACK);
        mNameTextPaint.setAntiAlias(true);
        mDesTextPaint=new TextPaint();
        mDesTextPaint.setTextSize(40);
        mDesTextPaint.setColor(Color.GRAY);
        mDesTextPaint.setAntiAlias(true);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int viewWidth=MeasureSpec.getSize(widthMeasureSpec);
        int paddingTop=getPaddingTop();
        int paddingBottom=getBottom();
        float nameWidth=viewWidth;
        float desWidth=viewWidth*2-10;
        CharSequence showName= TextUtils.ellipsize(mNameStr,mNameTextPaint,nameWidth,TextUtils.TruncateAt.END);
        CharSequence showDes= TextUtils.ellipsize(mDesStr,mDesTextPaint,desWidth,TextUtils.TruncateAt.END);
        mNameSaticLayout=new DynamicLayout(showName,mNameTextPaint,viewWidth, Layout.Alignment.ALIGN_NORMAL,1,0,false);
        mDesSaticLayout=new DynamicLayout(showDes,mDesTextPaint,viewWidth, Layout.Alignment.ALIGN_NORMAL,1,0,false);
        int textHeight=mNameSaticLayout.getHeight()+mDesSaticLayout.getHeight();
        int viewHeight=textHeight+paddingTop+paddingBottom;
        setMeasuredDimension(viewWidth,viewHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(0,getPaddingTop());
        mNameSaticLayout.draw(canvas);
        canvas.translate(0,mNameSaticLayout.getHeight());
        mDesSaticLayout.draw(canvas);
        canvas.restore();
    }

    public void setNameAndDes(String name,String des){
        mNameStr=name;
        mDesStr=des;
    }
}
