package com.test.viewdemo.RecyclerViewDemo;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created  on 2017/11/16.
 *
 * @author xyb
 */

public class PaddingItemDecoration extends RecyclerView.ItemDecoration {
    private int mBottomMargin;
    private boolean mShowLast;

    public PaddingItemDecoration(int bottomMargin) {
        this.mBottomMargin = bottomMargin;
    }

    public void setShowLast(boolean showLast) {
        mShowLast = showLast;
    }

    /**
     * @param outRect 用于控制ItemView四周的边距的矩形
     * @param view    要显示的ItemView
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int inAdapterPosition = parent.getChildAdapterPosition(view);
        int lastItemPosition = parent.getAdapter().getItemCount() - 1;
        if (!mShowLast && inAdapterPosition == lastItemPosition) {
            return;
        }
        outRect.bottom = mBottomMargin;
    }
}
