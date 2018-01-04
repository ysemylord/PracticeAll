package com.test.viewdemo.RecyclerViewDemo.scrollDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.test.viewdemo.R;
import com.test.viewdemo.RecyclerViewDemo.AdapterDemo.RecyclerViewBaseAdapter;
import com.test.viewdemo.RecyclerViewDemo.AdapterDemo.SimpleItemOneViewModel;
import com.test.viewdemo.RecyclerViewDemo.AdapterDemo.SimpleItemTwoViewModel;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class RecyclerViewScrollDemoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText mPositionEditeTextView;
    private SmoothOnScrollListener mSmoothOnScrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_scroll_demo);
        mPositionEditeTextView = (EditText) findViewById(R.id.position_edit_text);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
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
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            datas.add((i + ""));
        }
        recyclerViewBaseAdapter.setDatas(datas);
        mSmoothOnScrollListener=new SmoothOnScrollListener(recyclerView);
        recyclerView.addOnScrollListener(mSmoothOnScrollListener);
    }


    public void scrollToPosition(View view) {
        //RecyclerView.scrollToPosition调用的就是LayoutManager.scrollToPosition
        recyclerView.scrollToPosition(parseInt(mPositionEditeTextView.getText().toString()));
    }

    public void smoothScrollToPosition(View view) {
        recyclerView.smoothScrollToPosition(parseInt(mPositionEditeTextView.getText().toString()));

    }

    public void scrolltoPositionWithOffset(View view) {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        linearLayoutManager.scrollToPositionWithOffset(parseInt(mPositionEditeTextView.getText().toString()), 0);

    }

    public void scrollBy(View view) {
        int offset = Integer.parseInt(mPositionEditeTextView.getText().toString());
        recyclerView.smoothScrollBy(0, offset);//ScrollBy
    }


    public void finalMoothToPosition(View view) {
        int position = Integer.parseInt(mPositionEditeTextView.getText().toString());
        mSmoothOnScrollListener.scrollToPosition(position);
    }

}
