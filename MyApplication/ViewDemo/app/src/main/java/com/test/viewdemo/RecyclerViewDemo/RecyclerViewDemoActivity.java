package com.test.viewdemo.RecyclerViewDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.test.viewdemo.R;
import com.test.viewdemo.RecyclerViewDemo.itemDecoration.GridSpaceItemDecor;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewDemoActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<String> mNameList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_demo);
        mRecyclerView= (RecyclerView) findViewById(R.id.recycler_view);

       // testStaggeredGridLayout();
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getApplicationContext(),4);

        mRecyclerView.setLayoutManager(gridLayoutManager);
        for (int i = 0; i <20; i++) {
            mNameList.add(i+"");
        }
        mRecyclerView.addItemDecoration(new GridSpaceItemDecor(10,20,4));
        MyAdapter myAdapter=new MyAdapter(mNameList);
        mRecyclerView.setAdapter(myAdapter);
    }

    private void testStaggeredGridLayout() {
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL){};

        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        for (int i = 0; i <20; i++) {
            mNameList.add(i+"");
        }
        MyAdapterMultiTypeForStaggeredGridLayoutManager myAdapter=new MyAdapterMultiTypeForStaggeredGridLayoutManager(mNameList);
        mRecyclerView.setAdapter(myAdapter);
    }
}
