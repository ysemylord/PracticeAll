package com.test.viewdemo.viewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.viewdemo.R;

import java.util.ArrayList;
import java.util.List;


public class PageViewUseActivity extends AppCompatActivity {
    ViewPager mViewPager;
    PagerAdapter mPagerAdapter;
    List<TextView> mViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_view_use);
        mViews = new ArrayList<>();
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TextView textView1 = new TextView(this);
        textView1.setLayoutParams(layoutParams);
        textView1.setBackgroundColor(Color.RED);
        TextView textView2 = new TextView(this);
        textView2.setLayoutParams(layoutParams);
        textView2.setBackgroundColor(Color.GREEN);
        TextView textview3 = new TextView(this);
        textview3.setBackgroundColor(Color.BLUE);
        textview3.setLayoutParams(layoutParams);

        textView1.setText("1");
        textView2.setText("2");
        textview3.setText("3");

        mViews.add(textView1);
        mViews.add(textView2);
        mViews.add(textview3);
        
        mViewPager = (ViewPager) findViewById(R.id.view_page);
        mViewPager.setAdapter(mPagerAdapter=new PagerAdapter() {
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

    public void change1(View view) {
        TextView textView =  mViews.get(0);
        String text=  textView.getText().toString();
        textView.setText(text+"改");
    }

    public void addOnePage(View view) {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TextView textView1 = new TextView(this);
        textView1.setLayoutParams(layoutParams);
        textView1.setText("1");
        mViews.add(textView1);
        mPagerAdapter.notifyDataSetChanged();//此方法必须调用
    }
    //todo 删除一页
}
