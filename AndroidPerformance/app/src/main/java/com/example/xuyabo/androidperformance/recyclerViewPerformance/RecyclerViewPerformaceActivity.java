package com.example.xuyabo.androidperformance.recyclerViewPerformance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.xuyabo.androidperformance.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewPerformaceActivity extends AppCompatActivity {

    private List<String> mNameList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_performace);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        for (int i = 0; i <200; i++) {
            mNameList.add(i+"");
        }
        final MyAdapter myAdapter=new MyAdapter(mNameList);
        recyclerView.setAdapter(myAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState){
                    case RecyclerView.SCROLL_STATE_IDLE:
                        myAdapter.setScrolling(false);
                       LinearLayoutManager linearLayoutManager= (LinearLayoutManager) recyclerView.getLayoutManager();
                       int first=linearLayoutManager.findFirstVisibleItemPosition();
                       int last=linearLayoutManager.findLastVisibleItemPosition();
                        myAdapter.notifyItemRangeChanged(first,last-first+1 );
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        myAdapter.setScrolling(true);
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}
