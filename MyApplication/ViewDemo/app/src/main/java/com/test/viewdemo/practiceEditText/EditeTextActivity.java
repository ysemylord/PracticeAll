package com.test.viewdemo.practiceEditText;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.test.viewdemo.DialogActivity;
import com.test.viewdemo.R;

import static android.view.inputmethod.InputMethodManager.SHOW_IMPLICIT;

public class EditeTextActivity extends AppCompatActivity {

    EditText mEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edite_text);
        mEditText= (EditText) findViewById(R.id.edit_textview);
        mEditText.setText("212");
    }

    public void show(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //inputMethodManager.showSoftInputFromInputMethod(mEditText.getWindowToken(),0); 不可以
        inputMethodManager.showSoftInput(mEditText, SHOW_IMPLICIT); //可以
    }

    public void skip(View view) {
        Intent intent=new Intent(this,DialogActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();


    }

    public void hide(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //inputMethodManager.hideSoftInputFromInputMethod(mEditText.getWindowToken(),0); 无效
        inputMethodManager.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }

    public void toggle(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

    }

}
