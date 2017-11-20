package com.test.viewdemo.RecyclerViewDemo.itemDecoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created  on 2017/11/16.
 *
 * @author xyb
 */

public class VDividerItemDecoration extends RecyclerView.ItemDecoration {
    private int mDividerHeight;
    private boolean mShowLastBottom = true;
    private boolean mShowFirstTop = false;
    ColorDrawable mColorDrawable = new ColorDrawable();

    public VDividerItemDecoration() {

    }

    /**
     * 最后一项的底部是否显示分割线
     * @param showLastBottom
     */
    public void setShowLastDiliver(boolean showLastBottom) {
        mShowLastBottom = showLastBottom;
    }

    /**
     * 第一项的顶部是否显示分割线
     * @param showFirstTop
     */
    public void setShowTopDiliver(boolean showFirstTop) {
        mShowFirstTop = showFirstTop;
    }

    public void setDivider(int dividerColor, int dividerHeight) {
        mDividerHeight = dividerHeight;
        mColorDrawable.setColor(dividerColor);

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if(mColorDrawable==null){
            return;
        }
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 0; i < parent.getChildCount(); i++) {


            View itemView = parent.getChildAt(i);
            int inAdapterPosition= parent.getChildAdapterPosition(itemView);
            int lastItemPosition = parent.getAdapter().getItemCount() - 1;
            if (notShowBottomDiliver(inAdapterPosition, lastItemPosition)) {

            }else{
                int bottom = itemView.getBottom();
                int top = bottom + mDividerHeight;
                mColorDrawable.setBounds(left, top, right, bottom);//设置Drawable的坐标
                mColorDrawable.draw(c);

            }

            if(showTopDiliver(inAdapterPosition)){
                int bottom = itemView.getTop();
                int top = bottom - mDividerHeight;
                mColorDrawable.setBounds(left, top, right, bottom);//设置Drawable的坐标
                mColorDrawable.draw(c);
            }
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
        if (notShowBottomDiliver(inAdapterPosition, lastItemPosition)) {//该view是最后一项且mShowLastBottom=false;
            //不设置bottom
        } else {
            outRect.bottom = mDividerHeight;
        }

        if (showTopDiliver(inAdapterPosition)) {//该view是第一项，且mShowFirstTop=true;
            outRect.top=mDividerHeight;
        }

    }

    private boolean showTopDiliver(int inAdapterPosition) {
        return inAdapterPosition == 0&&mShowFirstTop;
    }

    private boolean notShowBottomDiliver(int inAdapterPosition, int lastItemPosition) {
        return inAdapterPosition == lastItemPosition&&!mShowLastBottom;
    }
}
