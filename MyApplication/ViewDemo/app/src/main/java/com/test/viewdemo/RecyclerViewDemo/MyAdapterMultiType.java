package com.test.viewdemo.RecyclerViewDemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.viewdemo.R;

import java.util.List;

/**
 * Created  on 2017/11/16.
 *
 * @author xyb
 */

public class MyAdapterMultiType extends RecyclerView.Adapter{
    public static final int TYPE_ONE=1;
    public static final int TYPE_TWO=2;
    private List<String> mNames;
    public MyAdapterMultiType(List<String> names) {
        this.mNames=names;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_type_one, parent, false);
            OneViewHolder myViewHolder=new OneViewHolder(view);
            return myViewHolder;
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_type_two, parent, false);
            TwoViewHolder myViewHolder=new TwoViewHolder(view);
            return myViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof OneViewHolder){
            OneViewHolder oneViewHolder= (OneViewHolder) holder;
            oneViewHolder.mTextView.setText(mNames.get(position));
        }else if(holder instanceof  TwoViewHolder){
            TwoViewHolder twoViewHolder= (TwoViewHolder) holder;
            twoViewHolder.mTextView.setText(mNames.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position%2==0){
            return TYPE_ONE;
        }
        return TYPE_TWO;
    }



    class OneViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        public OneViewHolder(View itemView) {
            super(itemView);
            mTextView= (TextView) itemView.findViewById(R.id.text_view);
        }
    }

    class TwoViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        public TwoViewHolder(View itemView) {
            super(itemView);
            mTextView= (TextView) itemView.findViewById(R.id.text_view);
        }
    }
}
