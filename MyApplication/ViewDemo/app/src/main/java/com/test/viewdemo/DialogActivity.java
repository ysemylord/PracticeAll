package com.test.viewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

import com.test.viewdemo.xview.BaseMyDialog;
import com.test.viewdemo.xview.MyPopWindow;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

    }

    public void pop(View view) {
        BaseMyDialog dialog= new BaseMyDialog(this,R.style.myDilaog);
        dialog.showInBottom();
    }

    public void pop_window(View view) {
        new MyPopWindow(this).showAtLocation(findViewById(android.R.id.content), Gravity.BOTTOM,0,0);
    }
}
