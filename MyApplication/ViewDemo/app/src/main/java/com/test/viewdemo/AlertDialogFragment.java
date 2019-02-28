package com.test.viewdemo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class AlertDialogFragment extends DialogFragment {
    private TextView mConfirmTextView;
    private TextView mCancelTextView;
    private TextView mTitleTextView;
    private TextView mContentTextView;
    private String mTitle="询问";
    private String mContent="";
    private String mCancelText ="取消";
    private String mConfirmText ="确认";
    private Runnable mOnCancelListener;
    private Runnable mOnConfirmListener;

/*
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.mc_alert_dialog, null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view);
        return builder.create();
    }*/


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(android.app.DialogFragment.STYLE_NORMAL,
                android.R.style.Theme_Material_Light_NoActionBar);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
       // getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_bg, container);
        return view;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public void setCancelText(String cancelText) {
        mCancelText = cancelText;
    }

    public void setConfirmText(String confirmText) {
        mConfirmText = confirmText;
    }

    public Runnable getOnCancelListener() {
        return mOnCancelListener;
    }

    public void setOnCancelListener(Runnable onCancelListener) {
        mOnCancelListener = onCancelListener;
    }

    public Runnable getOnConfirmListener() {
        return mOnConfirmListener;
    }

    public void setOnConfirmListener(Runnable onConfirmListener) {
        mOnConfirmListener = onConfirmListener;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Window window = getDialog().getWindow();
        window.getAttributes().dimAmount=1;
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#af000000")));
        getView().startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.popshow_anim));

    }
}
