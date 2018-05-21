package com.example.xuyabo.androidperformance.recyclerViewPerformance;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xuyabo.androidperformance.R;

/**
 * Created by xuyabo on 2018/5/3.
 */

public class SimpleAdapter extends RecyclerView.Adapter {
    private static final String TAG = "SimpleAdapter";


    int size=0;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=  LayoutInflater.from(parent.getContext()).inflate(R.layout.inner_item,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view,parent);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        notifyDataSetChanged();
    }
    private static int height=-1;


    class MyViewHolder extends RecyclerView.ViewHolder{
        public MyViewHolder(final View itemView, ViewGroup parent) {
            super(itemView);

       /*     if(height==-1) {
                RecyclerView recyclerView = (RecyclerView) parent;
                int parentWidth = 1080 - recyclerView.getPaddingLeft() - recyclerView.getPaddingRight();
                itemView.measure(View.MeasureSpec.makeMeasureSpec(parentWidth / 3 - 15, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.AT_MOST));
                height = itemView.getMeasuredHeight();
                Log.i(TAG, "MyViewHolder: " + height);
            }*/

        }
    }

    public  static  int getHeight(ViewGroup viewGroup){
        View itemView=  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inner_item,viewGroup,false);
        int parentWidth = 1080 - viewGroup.getPaddingLeft() - viewGroup.getPaddingRight();
        itemView.measure(View.MeasureSpec.makeMeasureSpec(parentWidth / 3 - 15, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.AT_MOST));
        int height = itemView.getMeasuredHeight();
        return height;

    }
}
