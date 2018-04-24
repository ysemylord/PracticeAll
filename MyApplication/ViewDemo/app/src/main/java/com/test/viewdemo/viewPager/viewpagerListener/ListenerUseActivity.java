package com.test.viewdemo.viewPager.viewpagerListener;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.viewdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListenerUseActivity extends AppCompatActivity {

    @Bind(R.id.view_page)
    ViewPager mViewPage;
    @Bind(R.id.indicator)
    IndicatorPoint mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listener_use);
        ButterKnife.bind(this);
        mViewPage.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 4;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                TextView child = new TextView(container.getContext());
                container.addView(child);
                child.setText(position + "");
                return child;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

                return view == object;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                View view = (View) object;//因为view是当做object返回的，所以以这样转化
                container.removeView(view);
            }
        });
        mIndicator.setViewPager(mViewPage);
    }

}

