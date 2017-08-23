package com.test.viewdemo.xview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created  on 2017/8/23.
 *
 * @author xyb
 */

public class MarqueeView extends LinearLayout {
    TextView mTextView;
    int mNowTran = 0;
    int index = 0;
    public MarqueeView(Context context) {
        super(context);
    }

    public MarqueeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void init(){
        mTextView=new android.support.v7.widget.AppCompatTextView(getContext()){

        };
        addView(mTextView);
    }

    public void startMarquee(){

    }
}
