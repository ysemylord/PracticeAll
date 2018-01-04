package com.test.viewdemo.viewPager.verifyFragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.test.viewdemo.R;
import com.test.viewdemo.viewPager.TextFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 验证一个Activity一个独立的FragmentManager
 */
public class VerifyFragmentActivity extends AppCompatActivity {

    @Bind(R.id.container)
    FrameLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_fragment);
        ButterKnife.bind(this);
        getSupportFragmentManager()
                .beginTransaction()
                .add(new TextFragment(),getClass().getSimpleName())
                .commit();

    }

    public void skip(View view) {
        startActivity(new Intent(this,VerifyFragmentActivity.class));
    }

    public void add(View view) {
    }
}
