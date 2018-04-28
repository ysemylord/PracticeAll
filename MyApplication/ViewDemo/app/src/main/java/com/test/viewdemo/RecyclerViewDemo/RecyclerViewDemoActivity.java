package com.test.viewdemo.RecyclerViewDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

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

        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL){};

        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
       /* VDividerItemDecoration decor = new VSpaceItemDecoration(10);
        decor.setDivider(Color.BLACK,10);
        decor.setShowLastDiliver(false);
        decor.setShowTopDiliver(true);*/

        //VSpaceItemDecor decor = new VSpaceItemDecor(this,10);

        //mRecyclerView.addItemDecoration(decor);
        for (int i = 0; i <20; i++) {
            mNameList.add(i+"");
        }
        MyAdapterMultiTypeForStaggeredGridLayoutManager myAdapter=new MyAdapterMultiTypeForStaggeredGridLayoutManager(mNameList);
        mRecyclerView.setAdapter(myAdapter);
    }
}
