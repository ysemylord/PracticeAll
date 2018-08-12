package com.test.viewdemo.RxAndroidDemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by xuyabo on 2018/7/27.
 */
class AppAdapter extends BaseAdapter {

    private List<AppInfo> mAppInfos;

    public AppAdapter(List<AppInfo> appInfos) {
        mAppInfos = appInfos;
    }

    @Override
    public int getCount() {
        return mAppInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return mAppInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addDatas(List<AppInfo> appInfos) {
        mAppInfos.addAll(appInfos);
        notifyDataSetChanged();
    }
    public void addData(AppInfo appInfo) {
        mAppInfos.add(appInfo);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        textView.setText(mAppInfos.get(position).getName());
        return textView;
    }
}
