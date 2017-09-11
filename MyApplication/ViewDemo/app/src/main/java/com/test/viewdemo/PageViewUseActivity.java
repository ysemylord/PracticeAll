package com.test.viewdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class PageViewUseActivity extends AppCompatActivity {
    ViewPager mViewPager;
    List<View> mViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_view_use);
        mViews = new ArrayList<>();
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View view1 = new View(this);
        view1.setLayoutParams(layoutParams);
        view1.setBackgroundColor(Color.RED);
        View view2 = new View(this);
        view2.setLayoutParams(layoutParams);
        view2.setBackgroundColor(Color.GREEN);
        View view3 = new View(this);
        view3.setBackgroundColor(Color.BLUE);
        view3.setLayoutParams(layoutParams);
        mViews.add(view1);
        mViews.add(view2);
        mViews.add(view3);
        mViewPager = (ViewPager) findViewById(R.id.view_page);
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mViews.size();
            }

            /**
             * 判断view和key是否是匹配的
             * @param view
             * @param object
             * @return
             */
            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            /**
             * 将页面的view加入container中，
             * 并返回的key
             * @param container
             * @param position
             * @return
             */
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = mViews.get(position);
                container.addView(view);
                return view;//将page的view作为page的Object
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mViews.get(position));
            }
        });
    }
}
