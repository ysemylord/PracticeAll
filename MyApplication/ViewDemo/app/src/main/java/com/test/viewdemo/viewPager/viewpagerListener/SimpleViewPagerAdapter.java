package com.test.viewdemo.viewPager.viewpagerListener;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by xuyabo on 2018/4/23.
 */

public class SimpleViewPagerAdapter extends PagerAdapter {
    @Override
    public int getCount() {
        return 10;
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

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return position+"";
    }
}
