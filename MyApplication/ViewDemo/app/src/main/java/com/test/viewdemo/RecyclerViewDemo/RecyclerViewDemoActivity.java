package com.test.viewdemo.RecyclerViewDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.test.viewdemo.R;

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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        for (int i = 0; i <20; i++) {
            mNameList.add(i+"");
        }
        MyAdapter myAdapter=new MyAdapter(mNameList);
        mRecyclerView.setAdapter(myAdapter);
    }
}
