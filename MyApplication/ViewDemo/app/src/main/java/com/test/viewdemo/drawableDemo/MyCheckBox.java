package com.test.viewdemo.drawableDemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by xuyabo on 2018/1/3.
 */

public class MyCheckBox extends android.support.v7.widget.AppCompatCheckBox {
    private static final String TAG = "MyCheckBox";
    public MyCheckBox(Context context) {
        super(context);
    }

    public MyCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 控件状态{pressed,checked,checkable,fouce}，状态改变时会调用该方法
     *所以如果在控件状态改变时想要做额外的操作，可以调用该方法
     * 如果根据按钮是否可用，改变文字大小（当然可以覆盖setEnable或者在调用setEnalbe方法时更改字体大小）
     */
    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] state=getDrawableState();
        String needStr="";
        boolean isEnable=false;
        for (int need : state) {
            needStr+=","+need;
            if(need==android.R.attr.state_enabled){
                isEnable=true;
            }
        }
        if(isEnable){
            setTextSize(12);
        }else{
            setTextSize(28);
        }
        Log.i(TAG, "drawableStateChanged: 状态改变 "+needStr);
        Log.i(TAG, "-----------------------------");
    }
}
