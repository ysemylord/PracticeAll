package com.test.viewdemo.viewPager.viewpagerListener.customSlidingTabLayout;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.Button;

import com.test.viewdemo.R;
import com.test.viewdemo.viewPager.viewpagerListener.SimpleViewPagerAdapter;
import com.test.viewdemo.viewPager.viewpagerListener.customSlidingTabLayout.mySlindingTabLayout3.MySlindingTabLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyTabLayoutActivity extends AppCompatActivity {

    @Bind(R.id.horizontal_scrollView)
    MySlindingTabLayout mHorizontalScrollView;
    @Bind(R.id.tab)
    com.test.viewdemo.viewPager.viewpagerListener.customSlidingTabLayout.mySlindingTabLayout4.MySlindingTabLayout mTav;
    @Bind(R.id.view_page)
    ViewPager mViewPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tab_layout);
        ButterKnife.bind(this);
        for (int i = 0; i < 20; i++) {
            Button textView = new Button(this);
            textView.setText(i + "");
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(200, ViewGroup.LayoutParams.WRAP_CONTENT);
            mHorizontalScrollView.putView(textView, layoutParams);
        }
        mViewPage.setAdapter(new SimpleViewPagerAdapter());
        mTav.setViewPater(mViewPage);
    }
}
