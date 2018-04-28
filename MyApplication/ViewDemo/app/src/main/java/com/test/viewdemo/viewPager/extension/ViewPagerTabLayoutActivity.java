package com.test.viewdemo.viewPager.extension;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.test.viewdemo.R;
import com.test.viewdemo.viewPager.changeData.TextFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ViewPagerTabLayoutActivity extends AppCompatActivity {


    @Bind(R.id.content_view_pager)
    ViewPager mContentViewPager;
    @Bind(R.id.tab_view_pager)
    com.test.viewdemo.viewPager.extensionvsion2.tabLayoutByViewPager.TabLayoutViewPager mTabViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_tab_layout);
        ButterKnife.bind(this);
        FragmentPagerAdapter contentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                TextFragment textFragment = new TextFragment();
                textFragment.setText("第" + position + "页");
                return textFragment;
            }

            @Override
            public int getCount() {
                return 35;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return "第" + position + "页";
            }
        };
        mContentViewPager.setAdapter(contentPagerAdapter);
        mTabViewPager.setContentViewPager(mContentViewPager, getSupportFragmentManager());

    }
}
