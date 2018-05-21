package com.example.xuyabo.androidperformance.recyclerViewPerformance;

import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.example.xuyabo.androidperformance.R;

import java.util.List;
import java.util.Random;

/**
 * Created  on 2017/11/16.
 *
 * @author xyb
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private static final String TAG = "MyAdapter";
    private List<String> mNames;

    public MyAdapter(List<String> names) {
        this.mNames = names;
    }

    private boolean mScrolling;


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inner_recyclerview_layout, parent, false);
        MyAdapter.MyViewHolder myViewHolder = new MyViewHolder(view, parent);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        if (!mScrolling) {
            holder.mImageView.setBackgroundColor(Color.argb(255, new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255)));
            SimpleAdapter simpleAdapter = (SimpleAdapter) holder.mRecyclerView.getAdapter();
            simpleAdapter.setSize(position);
        } else {
            reset(holder,position);
        }
    }

    private void reset(MyViewHolder holder, int count) {
        holder.mImageView.setImageDrawable(null);
        RecyclerView recyclerView = holder.mRecyclerView;
        ViewGroup.LayoutParams layoutParams = recyclerView.getLayoutParams();
        layoutParams.height=itemHeight*(count/3)+ recyclerView.getPaddingTop()+ recyclerView.getPaddingBottom();
        recyclerView.setLayoutParams(layoutParams);
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    private static int itemHeight = -1;

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        RecyclerView mRecyclerView;

        public MyViewHolder(View itemView, ViewGroup parent) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.imageview);


            mRecyclerView = itemView.findViewById(R.id.recycler_view);
            mRecyclerView.setLayoutManager(new GridLayoutManager(itemView.getContext(), 3));

            mRecyclerView.setAdapter(new SimpleAdapter());

            mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                    super.getItemOffsets(outRect, view, parent, state);
                    outRect.right = 15;
                }
            });

            if (itemHeight < 0) {
                /*ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) mImageView.getLayoutParams();
                String dimensionRatio = layoutParams.dimensionRatio;
                String[] ratio = dimensionRatio.split(":");
                int wRatio = Integer.parseInt(ratio[0]);
                int hRatio = Integer.parseInt(ratio[1]);

                int imageViewHeight = parent.getMeasuredWidth() * wRatio / hRatio;*/
                int innerItemHeiht = SimpleAdapter.getHeight(mRecyclerView);
                itemHeight=innerItemHeiht;
                mRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Log.i(TAG, "MyViewHolder:real measureHeight" + mRecyclerView.getMeasuredHeight());

                    }
                });
                Log.i(TAG, "MyViewHolder: measureHeight" + itemHeight);
            }

        }
    }

    public boolean isScrolling() {
        return mScrolling;
    }

    public void setScrolling(boolean scrolling) {
        mScrolling = scrolling;
    }
}
