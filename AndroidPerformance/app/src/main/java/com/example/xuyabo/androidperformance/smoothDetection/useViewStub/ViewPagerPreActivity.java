package com.example.xuyabo.androidperformance.smoothDetection.useViewStub;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.xuyabo.androidperformance.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ViewPagerPreActivity extends AppCompatActivity {

    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_with_user_hint);
        ButterKnife.bind(this);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return VisibleInitFragment.getInstance(position+"");
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
    }
}
