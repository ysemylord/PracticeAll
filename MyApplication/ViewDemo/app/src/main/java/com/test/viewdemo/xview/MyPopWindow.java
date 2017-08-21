package com.test.viewdemo.xview;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.PopupWindow;

import com.test.viewdemo.R;

/**
 * Created  on 2017/3/2.
 *
 * @author xyb
 */
public class MyPopWindow extends PopupWindow {
    private View mRootView;
    private View mShowView;
    private Context mContext;


    public MyPopWindow(Context context) {
        super(context);
        mContext = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        mRootView = inflater.inflate(R.layout.dialog_test, null);
        setContentView(mRootView);
        mShowView = mRootView.findViewById(R.id.show_view);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setAnimationStyle(R.style.mypopwindow_anim_style);
        setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x00ffffff);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.hide);
                mShowView.startAnimation(animation);
            }
        });
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        mShowView.setAnimation(AnimationUtils.loadAnimation(parent.getContext(), R.anim.show));
    }

}
