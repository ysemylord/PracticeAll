package com.test.viewdemo.RecyclerViewDemo.itemDecoration;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.test.viewdemo.util.MyUtil;


/**
 *两列网格布局，设置padding
 */
public class GSpaceItemDecor extends RecyclerView.ItemDecoration {
    private static final String TAG = "GrideSpaceItemDecorNormal";
    int item_left;
    int item_middle;
    int item_right;
    int item_bottom;
    int last_item_bottom_space;
    int first_item_top_space;


    public GSpaceItemDecor(Context context, int line_left, int line_middle, int line_right, int line_bottom) {
        this.item_left = (int) MyUtil.dpToPx(context, line_left);
        this.item_middle = (int) MyUtil.dpToPx(context, line_middle);
        this.item_right = (int) MyUtil.dpToPx(context, line_right);
        this.item_bottom = (int) MyUtil.dpToPx(context, line_bottom);

        this.first_item_top_space =line_bottom;
        this.last_item_bottom_space =line_bottom;
    }

    public void setLastLineSpaceByDp(Context context, int last_line_space) {
        this.last_item_bottom_space = (int) MyUtil.dpToPx(context, last_line_space);
    }

    public void setFirstLineTopSpaceByDp(Context context, int first_line_top_space) {
        this.first_item_top_space = (int) MyUtil.dpToPx(context, first_line_top_space);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int postionInParent = parent.getChildAdapterPosition(view);
        int allCount = parent.getAdapter().getItemCount();
        int item_bottom=this.item_bottom;
        int item_top=0;

        if (postionInParent == 0 || postionInParent == 1) {//前两项
            item_top = first_item_top_space;
        }

        if(parent.getAdapter().getItemCount()%2==0) {//item个数为偶数个
            if (postionInParent == allCount - 1 || postionInParent == allCount - 2) {//最后两项
                item_bottom = last_item_bottom_space;
            }
        }else{//奇数个
            if (postionInParent == allCount - 1 ) {//最后一项
                item_bottom = last_item_bottom_space;
            }
        }

        if (postionInParent % 2 == 0) {//偶数位
            outRect.set(item_left, item_top, item_middle / 2, item_bottom);
        } else {
            outRect.set(item_middle / 2, item_top, item_right, item_bottom);
        }

    }


}