package com.test.viewdemo.viewPager.extension;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuyabo on 2018/4/17.
 * 用作指示器的ViwPager
 */

public class TabLayoutViewPager extends ViewPager {
    public static int Row = 4;//每页的行数
    public static int Col = 4;//每页的列数
    public  RadioButton choosedRadioButton;//选中的单选按钮
    private ViewPager contentViewPager;//显示内容的ViewPager
    private String[] allTabTitls;//contentViewPager的所有标题
    private List<ViewPagerTalLayoutFragment> mViewPagerTalLayoutFragments=new ArrayList<>();

    public TabLayoutViewPager(@NonNull Context context) {
        super(context);
    }

    public TabLayoutViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setContentViewPager(ViewPager contentViewPager, FragmentManager fragmentManager) {
        this.contentViewPager = contentViewPager;

        PagerAdapter contentViewPagerAdapter = contentViewPager.getAdapter();
        int titltCount = contentViewPagerAdapter.getCount();
        allTabTitls=new String[titltCount];
        for(int i = 0; i< titltCount; i++){
            String value = contentViewPagerAdapter.getPageTitle(i).toString();
            allTabTitls[i]=value;
        }


        contentViewPager.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int selectedPage=position/(Row*Col);//contentViewPager中的第positon个对应的在TabLayoutViewPager中的第几页
                int selectedIndexInPage=position%(Row*Col);//contentViewPager中的第positon个对应的在TabLayoutViewPager中的第selectedPage页中的第几个
                TabLayoutViewPager.this.setCurrentItem(selectedPage);
                ViewPagerTalLayoutFragment viewPagerTalLayoutFragment= mViewPagerTalLayoutFragments.get(selectedPage);
                viewPagerTalLayoutFragment.changeChoosed(selectedIndexInPage);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        final FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int pagePosition) {
                int start = pagePosition * (Row * Col);
                int end = start + Row * Col;
                end = end < allTabTitls.length ? end : allTabTitls.length;
                String[] tabStrings = new String[end - start];
                int index = 0;
                for (int i = start; i < end; i++) {
                    tabStrings[index++] = allTabTitls[i];
                }
                ViewPagerTalLayoutFragment viewPagerTalLayoutFragment = ViewPagerTalLayoutFragment.newInstance(tabStrings, pagePosition);
                mViewPagerTalLayoutFragments.add(pagePosition,viewPagerTalLayoutFragment);
                viewPagerTalLayoutFragment.setOnItemClick(new ViewPagerTalLayoutFragment.OnTabItemClick() {
                    @Override
                    public void tabClick(int page, int indexInPage) {
                        TabLayoutViewPager.this.contentViewPager.setCurrentItem(page*(Row*Col)+indexInPage);
                    }
                });
                viewPagerTalLayoutFragment.setViewPagerTabLayout(TabLayoutViewPager.this);
                return viewPagerTalLayoutFragment;
            }

            @Override
            public int getCount() {
                int number = allTabTitls.length / (Row * Col);
                int more = allTabTitls.length % (Row * Col);
                return number + (more == 0 ? 0 : 1);
            }
        };
        this.setAdapter(fragmentPagerAdapter);



    }
}
