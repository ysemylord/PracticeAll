package com.test.viewdemo.viewPager.changeData;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.test.viewdemo.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapterUseActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private FragmentPagerAdapter mFragmentPagerAdapter;
    private List<String> mDatas;
    private static final String TAG="FragmentAdapter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDatas=new ArrayList<>();
        mDatas.add("第一页");
        mDatas.add("第二页");
        mDatas.add("第三页");
        mDatas.add("第四页");
        mDatas.add("第五页");
        mDatas.add("第六页");

        setContentView(R.layout.activity_fragment_adapter_use);
        mViewPager = (ViewPager) findViewById(R.id.view_page);
        mViewPager.setAdapter(mFragmentPagerAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            /**
             * 必须继承该方法
             * @param position
             * @return
             */
            @Override
            public Fragment getItem(int position) {
                Log.i(TAG, "getItem:"+(position+1));
                return new TextFragment();
            }

            /**
             * 必须继承该方法
             * @return
             */
            @Override
            public int getCount() {
                return mDatas.size();
            }

            @Override
            public int getItemPosition(Object object) {
                Log.i(TAG, "getItemPosition");
                return POSITION_NONE;//必须返回POSITION_NONE才能及时更新被缓存的视图
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                Log.i(TAG, "getItemPosition: "+(position+1));
                TextFragment textFragment= (TextFragment) super.instantiateItem(container, position);
                textFragment.setText(mDatas.get(position));
                return textFragment;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                Log.i(TAG, "destroyItem: "+(position+1));
                super.destroyItem(container, position, object);
            }
        });
    }


    public void changeOutData(View view) {
        String text0 = mDatas.get(0);
        mDatas.set(0,text0+"改");
        mFragmentPagerAdapter.notifyDataSetChanged();
    }

    public void skip(View view) {
        Intent intent=new Intent(this,FragmentAdapterUseActivity.class);
        startActivity(intent);
    }
}
