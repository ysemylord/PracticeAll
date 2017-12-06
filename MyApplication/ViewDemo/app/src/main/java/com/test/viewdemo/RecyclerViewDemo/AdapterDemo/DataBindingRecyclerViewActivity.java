package com.test.viewdemo.RecyclerViewDemo.AdapterDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.test.viewdemo.R;

import java.util.ArrayList;
import java.util.Arrays;

public class DataBindingRecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_binding_recycler_view);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewBaseAdapter recyclerViewBaseAdapter;
        recyclerView.setAdapter(recyclerViewBaseAdapter = new RecyclerViewBaseAdapter<String>() {

            @Override
            protected Object getViewModelForPosition(MyViewHolder viewHolder, int position) {
                if (position % 2 == 0) {
                    return new SimpleItemOneViewModel(getItem(position));
                } else {
                    return new SimpleItemTwoViewModel(getItem(position));
                }
            }

            @Override
            protected int getLayoutIdForPosition(int position) {
                if (position % 2 == 0) {
                    return R.layout.simple_one_item;
                } else {
                    return R.layout.simple_two_item;
                }
            }
        });
        recyclerViewBaseAdapter.setDatas(new ArrayList(Arrays.asList("jack","lord","land")));
    }
}
