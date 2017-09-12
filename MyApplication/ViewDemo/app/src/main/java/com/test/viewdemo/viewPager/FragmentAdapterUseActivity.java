package com.test.viewdemo.viewPager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.test.viewdemo.R;

public class FragmentAdapterUseActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_adapter_use);
        mViewPager = (ViewPager) findViewById(R.id.view_page);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return TextFragment.newInstance(position+"");
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
    }


}
