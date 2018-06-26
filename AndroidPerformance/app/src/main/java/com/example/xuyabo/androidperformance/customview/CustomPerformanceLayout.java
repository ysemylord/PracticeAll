package com.example.xuyabo.androidperformance.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.DynamicLayout;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.example.xuyabo.androidperformance.R;

/**
 * Created by xuyabo on 2018/5/21.
 */

public class CustomPerformanceLayout extends View  {
    private DynamicLayout mNameSaticLayout;
    private DynamicLayout mDesSaticLayout;
    private TextPaint mNameTextPaint;
    private TextPaint mDesTextPaint;
    private String mNameStr;
    private String mDesStr;
    private Drawable mIconDrawable ;
    private int mDrawableSize =170;
    private int mDrawableMargin=20;
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
        mIconDrawable=  getResources().getDrawable(R.mipmap.ic_launcher);

        mIconDrawable.setBounds(0,0, mDrawableSize, mDrawableSize);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int viewWidth=MeasureSpec.getSize(widthMeasureSpec);
        int paddingTop=getPaddingTop();
        int paddingBottom=getBottom();
        float nameWidth=viewWidth-mDrawableMargin*2-mDrawableSize;
        float desWidth=(viewWidth-mDrawableMargin*2-mDrawableSize)*2;
        CharSequence showName= TextUtils.ellipsize(mNameStr,mNameTextPaint,nameWidth,TextUtils.TruncateAt.END);
        CharSequence showDes= TextUtils.ellipsize(mDesStr,mDesTextPaint,desWidth,TextUtils.TruncateAt.END);
        mNameSaticLayout=new DynamicLayout(showName,mNameTextPaint,viewWidth, Layout.Alignment.ALIGN_NORMAL,1,0,false);
        mDesSaticLayout=new DynamicLayout(showDes,mDesTextPaint,viewWidth, Layout.Alignment.ALIGN_NORMAL,1,0,false);
        int textHeight=mNameSaticLayout.getHeight()+mDesSaticLayout.getHeight();
        int viewHeight=Math.max(textHeight, mDrawableSize)+paddingTop+paddingBottom;
        setMeasuredDimension(viewWidth,viewHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(mDrawableMargin,0);
        mIconDrawable.draw(canvas);
        canvas.translate(mDrawableSize+mDrawableMargin,0);
        canvas.translate(0,getPaddingTop());
        mNameSaticLayout.draw(canvas);
        canvas.translate(0,mNameSaticLayout.getHeight());
        mDesSaticLayout.draw(canvas);
        canvas.restore();
    }

    public void setNameAndDes(String name,String des,String imageUrl){
        mNameStr=name;
        mDesStr=des;

        Glide.with(getContext())
                .load(imageUrl)
                .centerCrop()//按比例缩放剪切
                .listener(new ImageRequestListener())
                .into(new SimpleTarget<GlideBitmapDrawable>(mDrawableSize, mDrawableSize) {
                    @Override
                    public void onResourceReady(GlideBitmapDrawable resource, GlideAnimation glideAnimation) {
                        Drawable currentDrawable = resource.getCurrent();
                        currentDrawable.setBounds(mIconDrawable.getBounds());//必须要设置currentDrawable，不然currentDrawable绘制不出来。（因为不然currentDrawable绘制不出来的bouds初始为(0,0,0,0)）
                        mIconDrawable= currentDrawable;
                        invalidate();
                    }
                });


    }
    static class ImageRequestListener implements RequestListener {

        @Override
        public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
            Log.e("loadeImage", e != null ? e.getLocalizedMessage() +"": "exception is null" + " " + model + " " + isFirstResource);
            return false;
        }

        @Override
        public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
            Log.i("loadeImage", "isFromMemoryCache" + isFromMemoryCache + "\n");
            return false;
        }
    }



}
