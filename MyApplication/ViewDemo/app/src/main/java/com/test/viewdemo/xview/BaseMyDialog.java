package com.test.viewdemo.xview;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.test.viewdemo.R;
import com.test.viewdemo.Util;


/**
 * Created by sijia on 2016/1/2.
 * 继承此基类实现自定义的Dialog
 */
public class BaseMyDialog extends AlertDialog {
    private boolean mDimEnable = true;
    private Context mContext;
    private View mRootView;

    public BaseMyDialog(Context context) {
        super(context);
        mContext=context;
    }

    public BaseMyDialog(Context context, int themeResId) {
        super(context, themeResId);
        mContext=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
         mRootView= LayoutInflater.from(mContext).inflate(R.layout.dialog_test,null,false);
        setContentView(mRootView);
    }


    /**
     * 设置背景是否变暗
     *
     * @param dimEnable
     */
    public void setDimEnable(boolean dimEnable) {
        mDimEnable = dimEnable;
    }

    /**
     * @param lp
     */
    private void showDim(WindowManager.LayoutParams lp) {
        float alpha = (mDimEnable == true ? lp.dimAmount : 0f);
        lp.dimAmount = alpha;
    }

    /**
     * 只能设置垂直方向的Gravity
     * 水平方向的Gravity设置无效
     */
    public void showInBottom() {
        show();
        Window window = getWindow();
        window.setWindowAnimations(R.style.mypopwindow_anim_style);
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = Util.getWindowWidth(getContext());
        showDim(lp);
        getWindow().setAttributes(lp);
    }

    public void showInTop() {
        show();
        Window window = getWindow();
        window.setGravity(Gravity.TOP);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = Util.getWindowWidth(getContext());
        showDim(lp);
        getWindow().setAttributes(lp);
    }

    public void showInCenter() {
        show();
        Window window = getWindow();
        window.setGravity(Gravity.CENTER_VERTICAL);
        WindowManager.LayoutParams lp = window.getAttributes();
        showDim(lp);
        getWindow().setAttributes(lp);

    }
}
