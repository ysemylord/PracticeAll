package com.test.viewdemo.viewPager.extensionvsion2.tabLayoutByViewPager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.test.viewdemo.R;

/**
 * Created by xuyabo on 2018/4/17.
 */

public class ViewPagerTalLayoutFragment extends Fragment {
    private static final String TAG = "ViewPagerTalLayoutFragm";
    private RecyclerView mTabRecyclerView;
    private String[] tabStrings = new String[16];
    private int pageIndex;


    public static ViewPagerTalLayoutFragment newInstance(String[] tabStrings, int pageIndex) {
        ViewPagerTalLayoutFragment viewPagerTalLayoutFragment = new ViewPagerTalLayoutFragment();
        Bundle args = new Bundle();
        args.putStringArray("tabStrings", tabStrings);
        args.putInt("pageIndex",pageIndex);
        viewPagerTalLayoutFragment.setArguments(args);
        return viewPagerTalLayoutFragment;
    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        tabStrings = bundle.getStringArray("tabStrings");
        pageIndex=bundle.getInt("pageIndex");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_tablayout_fragment, container, false);
        mTabRecyclerView = view.findViewById(R.id.tab_recycler_view);
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
        return view;
    }




}
