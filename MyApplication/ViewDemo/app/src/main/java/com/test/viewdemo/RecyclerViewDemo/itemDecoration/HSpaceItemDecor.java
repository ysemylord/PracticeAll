package com.test.viewdemo.RecyclerViewDemo.itemDecoration;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.test.viewdemo.util.MyUtil;

/**
 * 水平列表，给每项底部设置边距。
 * firstChildLeftSpace，firstChildLeftSpace制最左边和最右边的边距
 * itemSpace 控制中间列表项的边距
 * topSpace，bottomSpace列表顶部和底部的边距
 */
public class HSpaceItemDecor extends RecyclerView.ItemDecoration {

    int itemSpace = 0;
    int firstChildLeftSpace = 0;
    int lastChildeRightSpace = 0;
    int topSpace = 0;
    int bottomSpace = 0;

    public HSpaceItemDecor(Context context, int itemSpace) {
        this.itemSpace = (int) MyUtil.dpToPx(context, itemSpace);
        this.firstChildLeftSpace = this.itemSpace;
        this.lastChildeRightSpace = this.itemSpace;
    }

    public HSpaceItemDecor(Context context, int itemSpace, int firstChildLeftSpace, int lastChildeRightSpace) {
        this.itemSpace = (int) MyUtil.dpToPx(context, itemSpace);
        this.firstChildLeftSpace = (int) MyUtil.dpToPx(context, firstChildLeftSpace);
        this.lastChildeRightSpace = (int) MyUtil.dpToPx(context, lastChildeRightSpace);
    }



    public void setTopSpace(Context context,int topSpace) {
        this.topSpace = (int) MyUtil.dpToPx(context,topSpace);
    }


    public void setBottomSpace(Context context,int bottomSpace) {
        this.bottomSpace =(int) MyUtil.dpToPx(context,bottomSpace);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int allCount = parent.getAdapter().getItemCount();
        int postionInParent = parent.getChildAdapterPosition(view);
        if (postionInParent == 0) {
            outRect.set(firstChildLeftSpace, topSpace, itemSpace, bottomSpace);
        } else if (postionInParent == allCount - 1) {
            outRect.set(0, topSpace, lastChildeRightSpace, bottomSpace);
        } else {
            outRect.set(0, topSpace, itemSpace, bottomSpace);
        }
    }

}