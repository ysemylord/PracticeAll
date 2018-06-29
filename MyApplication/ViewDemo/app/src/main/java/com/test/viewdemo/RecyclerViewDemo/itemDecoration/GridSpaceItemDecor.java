package com.test.viewdemo.RecyclerViewDemo.itemDecoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by xuyabo on 2018/5/15.
 */

public class GridSpaceItemDecor extends RecyclerView.ItemDecoration {
    private int mColGap;//列与列之间的间隙
    private int mRowGap=0;
    private int mSpanCount;

    public GridSpaceItemDecor(int colGap, int rowGap, int spanCount) {
        mColGap = colGap;
        mRowGap = rowGap;
        mSpanCount = spanCount;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int originPosition = parent.getChildAdapterPosition(view);
        int position = (originPosition) % mSpanCount;
        if (position ==0) {//第一列
            outRect.right = mColGap / 3 * 2;
        }  else if (position ==mSpanCount-1) {//最后一列
            outRect.left = mColGap / 3 * 2;
        }else  {
            outRect.left = mColGap / 3 * 1;
            outRect.right = mColGap / 3 * 1;
        }
        if (originPosition / mSpanCount != 0) {//不是第一行的元素
            outRect.top = mRowGap;
        }
    }


}
