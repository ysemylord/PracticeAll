package com.test.viewdemo.RecyclerViewDemo;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created  on 2017/11/16.
 *
 * @author xyb
 */

public class SimpleDecoration extends RecyclerView.ItemDecoration {
    private int bottomMargin;
    public SimpleDecoration(int bottomMargin) {
        this.bottomMargin=bottomMargin;
    }

    /**
     * @param outRect 用于控制ItemView四周的边距的矩形
     * @param view    要显示的ItemView
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom=bottomMargin;
    }
}
