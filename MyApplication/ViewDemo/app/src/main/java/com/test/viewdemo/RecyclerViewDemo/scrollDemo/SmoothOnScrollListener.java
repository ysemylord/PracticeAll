package com.test.viewdemo.RecyclerViewDemo.scrollDemo;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by xuyabo on 2017/12/21.
 * 使用此类可以实现RecyclerView的平滑滑动
 */
class SmoothOnScrollListener extends RecyclerView.OnScrollListener{
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private boolean mSmooth=false;
    private int mGoalPosition;
    private int mPositionWithOffset=-300;


    public void setmPositionWithOffset(int positionWithOffset) {
        this.mPositionWithOffset = positionWithOffset;
    }

    public SmoothOnScrollListener(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        mLinearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        // scrollToPosition和scrollToPositionWithOffset不会触发这个函数
        //smoothScrollBy和smoothScrollToPosition会触发这个函数
        //猜想 平滑滑动才会调用触发此函数
        Log.i("scroll", "onScrollStateChanged");
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if(mSmooth){
            mSmooth=false;
            View goalView=mLinearLayoutManager.findViewByPosition(mGoalPosition);
            recyclerView.smoothScrollBy(0,goalView.getTop());
        }
    }

    public void scrollToPosition(int goalPosition){
        mGoalPosition=goalPosition;
        int fisrtPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
        int lastPositoin = mLinearLayoutManager.findLastVisibleItemPosition();
        if (goalPosition < fisrtPosition) {
            mSmooth=true;
            mLinearLayoutManager.scrollToPositionWithOffset(goalPosition, mPositionWithOffset);
            Log.i("scroll", "scrollToPositionWithOffset "+goalPosition);
        } else if (goalPosition > lastPositoin) {
            mSmooth=true;
            mLinearLayoutManager.scrollToPosition(goalPosition);
            Log.i("scroll", "scrollToPosition "+goalPosition);
        } else {
            mSmooth=false;
            View goalView = mLinearLayoutManager.findViewByPosition(goalPosition);
            int top = goalView.getTop();
            mRecyclerView.smoothScrollBy(0, top);
            Log.i("scroll", "smoothScrollBy "+top);
        }
    }
}
