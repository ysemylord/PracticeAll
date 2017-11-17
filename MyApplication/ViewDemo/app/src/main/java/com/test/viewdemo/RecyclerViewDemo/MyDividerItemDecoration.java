package com.test.viewdemo.RecyclerViewDemo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created  on 2017/11/16.
 *
 * @author xyb
 */

public class MyDividerItemDecoration extends RecyclerView.ItemDecoration {
    private int mDividerHeight;
    private boolean mShowLast = true;
    private int mDividerColor=Color.BLACK;

    public MyDividerItemDecoration() {

    }

    public void setShowLast(boolean showLast) {
        mShowLast = showLast;
    }

    public void setDivider(int dividerColor,int dividerHeight) {
        mDividerColor = dividerColor;
        mDividerHeight=dividerHeight;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        ColorDrawable colorDrawable = new ColorDrawable();
        colorDrawable.setColor(mDividerColor);
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 0; i < parent.getChildCount(); i++) {
            if(i==parent.getChildCount()-1&&!mShowLast){
                return;
            }
            View itemView = parent.getChildAt(i);
            int bottom = itemView.getBottom();
            int top = bottom + mDividerHeight;
            colorDrawable.setBounds(left, top, right, bottom);//设置Drawable的坐标
            colorDrawable.draw(c);
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);


    }


    /**
     * @param outRect 用于控制ItemView四周的边距的矩形 outRect.left,outRect.top,outRect.right,outRect.bottom分别控制了ItemView,左，上，右，下的边距
     * @param view    要显示的ItemView
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        /**
         * 这个方法还可针对某些特殊的列表项设置不同的间距
         */
        int inAdapterPosition = parent.getChildAdapterPosition(view);
        int lastItemPosition = parent.getAdapter().getItemCount() - 1;
        if (!mShowLast && inAdapterPosition == lastItemPosition) {
            return;
        }
        outRect.bottom = mDividerHeight;
    }
}
