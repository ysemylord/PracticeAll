package com.test.viewdemo.RecyclerViewDemo.itemDecoration;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.test.viewdemo.util.MyUtil;

/**
 * 垂直ReyclcerView的ItemDecoration，可以添加Space
 */
public class VSpaceItemDecor extends RecyclerView.ItemDecoration {

    int itemSpace = 0;
    int lastChildeBottomSpace =0;
    int firstChildeTopSpace =0;

    public VSpaceItemDecor() {

    }

    public VSpaceItemDecor(Context context, int itemSpace) {
        this.itemSpace = (int) MyUtil.dpToPx(context,itemSpace);
        this.firstChildeTopSpace=this.itemSpace;
        this.lastChildeBottomSpace=this.itemSpace;
    }

    public VSpaceItemDecor(Context context,int itemSpace,int firstChildeTopSpace,int lastChildeBottomSpace ) {
        this.itemSpace = (int) MyUtil.dpToPx(context,itemSpace);
        this.firstChildeTopSpace=(int) MyUtil.dpToPx(context,firstChildeTopSpace);
        this.lastChildeBottomSpace=(int) MyUtil.dpToPx(context,lastChildeBottomSpace);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int allCount = parent.getAdapter().getItemCount();
        int postionInParent = parent.getChildAdapterPosition(view);
        if (postionInParent == 0) {
            outRect.set(0, firstChildeTopSpace, 0, itemSpace);
        } else if (postionInParent == allCount - 1) {
            outRect.set(0, 0, 0, lastChildeBottomSpace);
        } else {
            outRect.set(0, 0, 0, itemSpace);
        }
    }


    public void setItemSpace(int itemSpace) {
        this.itemSpace = itemSpace;
    }



    public void setLastChildeBottomSpace(int lastChildeBottomSpace) {
        this.lastChildeBottomSpace = lastChildeBottomSpace;
    }

    public void setFirstChildeTopSpace(int firstChildeTopSpace) {
        this.firstChildeTopSpace = firstChildeTopSpace;
    }
}