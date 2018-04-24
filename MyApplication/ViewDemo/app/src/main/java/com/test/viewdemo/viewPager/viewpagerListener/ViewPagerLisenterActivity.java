package com.test.viewdemo.viewPager.viewpagerListener;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.viewdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.support.v4.view.ViewPager.SCROLL_STATE_DRAGGING;
import static android.support.v4.view.ViewPager.SCROLL_STATE_SETTLING;
import static android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE;

public class ViewPagerLisenterActivity extends AppCompatActivity {
    private static final String TAG = "ViewPagerLisenterActivi";
    @Bind(R.id.view_page)
    ViewPager mViewPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_lisenter);
        ButterKnife.bind(this);
        mViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i(TAG, "onPageScrolled-"+"position:"+position+"|offset:"+positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "onPageSelected: "+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                String stateStr="";
                switch (state){
                    case SCROLL_STATE_IDLE:
                        stateStr="SCROLL_STATE_IDLE";
                        break;
                    case SCROLL_STATE_DRAGGING:
                        stateStr="SCROLL_STATE_DRAGGING";
                        break;
                    case SCROLL_STATE_SETTLING:
                        stateStr="SCROLL_STATE_SETTLING";
                        break;
                }
                Log.i(TAG, "onPageScrollStateChanged: "+stateStr);
            }
        });

        mViewPage.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 10;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                TextView child = new TextView(container.getContext());
                container.addView(child);
                child.setText(position+"");
                return child;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

                return view==object;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                View view = (View) object;//因为view是当做object返回的，所以以这样转化
                container.removeView(view);
            }
        });
    }
}
