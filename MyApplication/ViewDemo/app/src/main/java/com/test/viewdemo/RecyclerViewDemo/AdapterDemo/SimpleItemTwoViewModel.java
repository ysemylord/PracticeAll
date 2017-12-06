package com.test.viewdemo.RecyclerViewDemo.AdapterDemo;

import android.view.View;
import android.widget.Toast;

/**
 * Created  on 2017/12/6.
 *
 * @author xyb
 */

public class SimpleItemTwoViewModel {
    public String name;
    public void onItemClick(View view){
        Toast.makeText(view.getContext(),name,Toast.LENGTH_SHORT).show();
    }

    public SimpleItemTwoViewModel(String name) {
        this.name = name;
    }
}
