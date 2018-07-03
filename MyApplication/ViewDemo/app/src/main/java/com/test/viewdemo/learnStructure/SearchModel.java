package com.test.viewdemo.learnStructure;

import com.test.viewdemo.learnStructure.mvc.SearchRes;

import java.util.Random;

/**
 * 模拟从网络获取数据
 */

public class SearchModel {
    public SearchRes search(String key){
        try {
            Thread.sleep(300);//模拟网络请求
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        StringBuilder resSB=new StringBuilder();
        Random random=new Random();
        for (int i=0;i<100;i++){
            resSB.append(random.nextInt()+"");
        }
        return new SearchRes("标题",resSB.toString());
    }
}
