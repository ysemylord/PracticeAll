package com.test.viewdemo.RecyclerViewDemo.itemDcorarionDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.test.viewdemo.R;
import com.test.viewdemo.RecyclerViewDemo.MyAdapter;
import com.test.viewdemo.RecyclerViewDemo.itemDecoration.VSpaceItemDecor;

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
       /* VDividerItemDecoration decor = new VSpaceItemDecoration(10);
        decor.setDivider(Color.BLACK,10);
        decor.setShowLastDiliver(false);
        decor.setShowTopDiliver(true);*/

        VSpaceItemDecor decor = new VSpaceItemDecor(this,10);

        mRecyclerView.addItemDecoration(decor);
        for (int i = 0; i <20; i++) {
            mNameList.add(i+"");
        }
        MyAdapter myAdapter=new MyAdapter(mNameList);
        mRecyclerView.setAdapter(myAdapter);
    }
}