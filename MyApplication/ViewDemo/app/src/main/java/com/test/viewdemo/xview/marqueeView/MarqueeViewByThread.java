package com.test.viewdemo.xview.marqueeView;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.viewdemo.util.CanStopLoopThread;


/**
 * Created  on 2017/8/23.
 *利用线程实现跑马灯效果
 * 优点
 * 只有一个TextView与一个线程
 * 缺点
 * 线程的关闭不易控制
 * 需要借助CanStopLoopThread类
 *
 * @author xyb
 */

public class MarqueeViewByThread extends LinearLayout {
    private static final String TAG = "MarqueeView";
    TextView mTextView;
    int index = 0;
    Handler mHandler = new Handler();
    private String[] mDatas;
    private final int INTERNAL = 10;//每interal毫秒移动一像素
    private CanStopLoopThread mCanStopLoopThread;

    public MarqueeViewByThread(Context context) {
        this(context,null);
    }

    public MarqueeViewByThread(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        mTextView = new android.support.v7.widget.AppCompatTextView(getContext()) {
            @Override
            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                int w = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
                int h = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
                super.onMeasure(w, h);
            }
        };
        addView(mTextView);

    }


    public void startMarquee() {
        if (mDatas == null || mDatas.length == 0) {
            return;
        }

            getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    getViewTreeObserver().removeOnPreDrawListener(this);

                    mTextView.setText(mDatas[0]);
                    mTextView.setTranslationX(getWidth());
                    index=0;

                    setStop();//先停止之前的线程
                    mCanStopLoopThread = new CanStopLoopThread(new Runnable() {
                        @Override
                        public void run() {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Log.i(TAG, "getX(): " + mTextView.getX());
                                    Log.i(TAG, "getWidth（）: " + mTextView.getWidth());

                                    mTextView.setTranslationX(mTextView.getX() - 1);//相对于left的平移
                                    Log.i(TAG, "左移");
                                    if (mTextView.getX() <= -mTextView.getWidth()) {//向左移动完了
                                        mTextView.setX(getWidth());
                                        if (index == mDatas.length - 1) {
                                            index = 0;
                                        } else {
                                            index++;
                                        }
                                        mTextView.setText(mDatas[index]);
                                        Log.i(TAG, "放到右边");
                                    }
                                }
                            });
                        }

                    });
                    mCanStopLoopThread.setSleepTime(INTERNAL);
                    mCanStopLoopThread.start();
                    Log.i(TAG, "线程开启 ");
                    return true;
                }
            });

    }

    public void setDatas(String[] datas) {
        mDatas = datas;
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);

        final boolean visible = visibility == VISIBLE && getVisibility() == VISIBLE;
        if (!visible) {
            setStop();
        }
    }

    public void setStop() {
        if (mCanStopLoopThread != null) {
            mCanStopLoopThread.setStop();
            Log.i(TAG, "线程停止: ");
        }
    }
}
