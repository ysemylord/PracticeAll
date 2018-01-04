package com.test.viewdemo.viewPager.verifyFragmentManager;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.test.viewdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VerifyFragmentTranctionActivity extends AppCompatActivity {
    private static final String TAG = "VerifyFragmentTranction";
    @Bind(R.id.container)
    FrameLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_fragment_method);
        ButterKnife.bind(this);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, new Fragment1(),"Fragment1")
                .commit();
    }

    public void onlyAddFragment(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container,new Fragment2(),getClass().getSimpleName())
                .commit();
    }

    public void addFragment(View view) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager
                .beginTransaction()
                .remove(supportFragmentManager.findFragmentById(R.id.container))//先remove原来的Fragment
                .add(R.id.container,new Fragment2(),getClass().getSimpleName())
                .commit();
    }

    public void detachFragment(View view) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Fragment1 fragment1= (Fragment1) supportFragmentManager.findFragmentById(R.id.container);
        supportFragmentManager
                .beginTransaction()
                .detach(fragment1)
                .commit();
        supportFragmentManager
                .beginTransaction()
                .attach(supportFragmentManager.findFragmentByTag("Fragment1"))
                .commit();
    }

}
