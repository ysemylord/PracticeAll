package com.test.viewdemo.longImageLoad;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xuyabo on 2018/7/4.
 */

public class LongLoadImageView extends View {
    private static final String TAG = "LongLoadImageView";

    private BitmapRegionDecoder mBitmapRegionDecoder;
    private int mImageWidth, mImageHeight;
    private Rect mBitmapShowdRect = new Rect();//加载的图片区域
    private BitmapFactory.Options mRegionBitmapOption;

    public LongLoadImageView(Context context) {
        super(context);
        init(context);
    }

    public LongLoadImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void setInputStream(InputStream inputStream) throws IOException {
        BitmapFactory.Options tempOpteions = new BitmapFactory.Options();
        tempOpteions.inJustDecodeBounds = true;//只计算边界，不加载原图
       /* tempOpteions.inSampleSize=4;
        tempOpteions.inPreferredConfig= Bitmap.Config.RGB_565;*/
        Bitmap justBundsBitmap = BitmapFactory.decodeStream(inputStream, null, tempOpteions);
        mImageWidth = tempOpteions.outWidth;
        mImageHeight = tempOpteions.outHeight;

        mBitmapRegionDecoder = BitmapRegionDecoder.newInstance(inputStream, false);
        invalidate();

    }

    private void init(Context context) {
        mRegionBitmapOption = new BitmapFactory.Options();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        //初始时，图片的加载区域
        mBitmapShowdRect.left = 0;
        mBitmapShowdRect.top = 0;
        mBitmapShowdRect.right = width;
        mBitmapShowdRect.bottom = height;
    }

    private int mLastX;
    private int mLastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastX = (int) event.getX();
                mLastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                int nowX = (int) event.getX();
                int nowY = (int) event.getY();
                int delX = nowX - mLastX;
                int delY = nowY - mLastY;
                int regionMoveX = -delX;
                int regionMoveY = -delY;
                regionMove(regionMoveX, regionMoveY);
                mLastX = nowX;
                mLastY = nowY;
                break;
        }
        return true;
    }

    private void regionMove(int regionMoveX, int regionMoveY) {

        //todo mBitmapShowdRect边界控制
        mBitmapShowdRect.offset(regionMoveX, regionMoveY);

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap regionBitmap = mBitmapRegionDecoder.decodeRegion(mBitmapShowdRect, mRegionBitmapOption);
        canvas.drawBitmap(regionBitmap, 0, 0, null);
        Log.i(TAG, "onDraw: "+mBitmapShowdRect.toString());

    }
}
