package com.test.viewdemo.RecyclerViewDemo.AdapterDemo;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;

import java.util.ArrayList;
import java.util.List;



public abstract class RecyclerViewBaseAdapter<T> extends RecyclerView.Adapter<RecyclerViewBaseAdapter.MyViewHolder> {

    private static final String TAG = "RecyclerViewBaseAdapter";

    List<T> mDataList;
    private RemoveInterface mRemoveInterface;

    public RecyclerViewBaseAdapter() {
        this(new ArrayList<T>(0));
    }

    public RecyclerViewBaseAdapter(List<T> mDataList) {
        this.mDataList = mDataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(
                layoutInflater, viewType, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(binding);
        initViewHolder(myViewHolder,viewType);
        onCreatedViewHolder(myViewHolder);
        return myViewHolder;
    }



    public void initViewHolder(MyViewHolder myViewHolder,
                               int viewType){}

    public void onCreatedViewHolder(MyViewHolder myViewHolder){

    }


    @Override
    public void onBindViewHolder(MyViewHolder holder,
                                 int position) {


        Object viewModel = getViewModelForPosition(holder, position);
        holder.bindViewModel(viewModel);
        bindOtherViewModel(holder, position);

    }

    public void bindOtherViewModel(MyViewHolder holder,
                                   int position) {

    }

    @Override
    public int getItemViewType(int position) {

        return getLayoutIdForPosition(position);
    }

    protected abstract Object getViewModelForPosition(MyViewHolder viewHolder, int position);

    protected abstract int getLayoutIdForPosition(int position);


    protected int getAllCount() {
        return mDataList != null ? mDataList.size() : 0;
    }

    @Override
    public int getItemCount() {
        return getAllCount();
    }


    public T getItem(int posotion) {
        return mDataList.get(posotion);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        public MyViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindViewModel(Object viewModel) {
            binding.setVariable(BR.viewmodel, viewModel);
            binding.executePendingBindings();
        }


        public ViewDataBinding getBinding() {
            return binding;
        }
    }

    public void replaceDatas(List<T> datas) {
        mDataList.clear();
        mDataList.addAll(datas);
        notifyDataSetChanged();
    }

    public void setDatas(List<T> datas) {
        mDataList = datas;
        notifyDataSetChanged();
    }

    public void removeData(T object) {
        int inext = mDataList.indexOf(object);
        mDataList.remove(object);
        notifyItemRemoved(inext);
        notifyItemRangeChanged(inext, mDataList.size());
        if (mRemoveInterface != null) {
            mRemoveInterface.afterRemoveData(object,mDataList);
        }
    }

    public void setRemoveInterface(RemoveInterface removeInterface) {
        mRemoveInterface = removeInterface;
    }

    public  interface RemoveInterface<T>{
         void afterRemoveData(T removeObject, List<T> datas);
    }

    public List<T> getDataList() {
        return mDataList;
    }


}