package com.test.viewdemo.viewPager.extensionvsion2.tabLayoutByViewPager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.test.viewdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuyabo on 2018/4/17.
 * 用作指示器的ViwPager
 */

public class TabLayoutViewPager extends ViewPager {
    public static int Row = 4;//每页的行数
    public static int Col = 4;//每页的列数
    private RadioButton choosedRadioButton;//选中的单选按钮
    private RadioButton[] mAllRadioButton ;
    private String[] allTabTitls;//contentViewPager的所有标题
    private List<ViewPagerTalLayoutFragment> mViewPagerTalLayoutFragments = new ArrayList<>();

    public TabLayoutViewPager(@NonNull Context context) {
        super(context);
    }

    public TabLayoutViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setContentViewPager(final ViewPager contentViewPager, FragmentManager fragmentManager) {

        PagerAdapter contentViewPagerAdapter = contentViewPager.getAdapter();
        int titltCount = contentViewPagerAdapter.getCount();
        allTabTitls = new String[titltCount];
        for (int i = 0; i < titltCount; i++) {
            String value = contentViewPagerAdapter.getPageTitle(i).toString();
            allTabTitls[i] = value;
        }
        mAllRadioButton=new RadioButton[allTabTitls.length];


        contentViewPager.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int selectedPage = position / (Row * Col);//contentViewPager中的第positon个对应的在TabLayoutViewPager中的第几页
                int selectedIndexInPage = position % (Row * Col);//contentViewPager中的第positon个对应的在TabLayoutViewPager中的第selectedPage页中的第几个
                RadioButton radioButton = mAllRadioButton[position];
                if(choosedRadioButton!=null){
                    choosedRadioButton.setChecked(false);
                }
                TabLayoutViewPager.this.setCurrentItem(selectedPage);
                choosedRadioButton = radioButton;
                radioButton.setChecked(true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        PagerAdapter pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                int number = allTabTitls.length / (Row * Col);
                int more = allTabTitls.length % (Row * Col);
                return number + (more == 0 ? 0 : 1);
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, final int pagePosition) {
                int start = pagePosition * (Row * Col);
                int end = start + Row * Col;
                end = end < allTabTitls.length ? end : allTabTitls.length;
                final String[] tabStrings = new String[end - start];
                int index = 0;
                for (int i = start; i < end; i++) {
                    tabStrings[index++] = allTabTitls[i];
                }

                View view = LayoutInflater.from(getContext()).inflate(R.layout.viewpager_tablayout_fragment, container, false);
                RecyclerView mTabRecyclerView = view.findViewById(R.id.tab_recycler_view);
                mTabRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), TabLayoutViewPager.Col));
                mTabRecyclerView.setAdapter(new RecyclerView.Adapter() {
                    @NonNull
                    @Override
                    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(getContext()).inflate(R.layout.viewpager_tab_text_view, parent, false);
                        return new TabViewHolder(view);
                    }

                    @Override
                    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
                        TabViewHolder tabViewHolder = (TabViewHolder) holder;
                        tabViewHolder.tabRadioButton.setText(tabStrings[position]);
                        tabViewHolder.tabRadioButton.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (choosedRadioButton != null) {
                                    choosedRadioButton.setChecked(false);
                                }
                                choosedRadioButton = (RadioButton) v;
                                choosedRadioButton.setChecked(true);
                                contentViewPager.setCurrentItem(pagePosition * (Row * Col) + position);
                            }
                        });
                        mAllRadioButton[pagePosition * (Row * Col) + position]=tabViewHolder.tabRadioButton;

                    }

                    @Override
                    public int getItemCount() {
                        return tabStrings.length;
                    }

                    class TabViewHolder extends RecyclerView.ViewHolder {
                        RadioButton tabRadioButton;

                        public TabViewHolder(View itemView) {
                            super(itemView);
                            tabRadioButton = itemView.findViewById(R.id.tab_radio_btn);
                        }
                    }
                });
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }
        };
        this.setAdapter(pagerAdapter);

    }
}
