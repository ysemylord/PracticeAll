package com.test.viewdemo.AnnotationDemo;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;

/**
 * Created by xuyabo on 2018/6/29.
 * ButterKnife的处理器（运行时处理器）
 */

public class ButterKnifeProc {
    private static final String TAG = "ButterKnifeProc";

    public static void bind(Activity activity) {
        Field[] fields = activity.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(MyButterKnife.class)) {//含有MyButterKnife注解
                MyButterKnife myButterKnife = field.getAnnotation(MyButterKnife.class);
                int id = myButterKnife.value();
                View findedView = activity.findViewById(id);
                try {
                    field.set(activity, findedView);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    Log.e(TAG, "bind: " + e.getMessage());
                }
            }
        }
    }

    public static void bind(Object ojbct, View view) {
        Field[] fields = ojbct.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(MyButterKnife.class)) {//含有MyButterKnife注解
                MyButterKnife myButterKnife = field.getAnnotation(MyButterKnife.class);
                int id = myButterKnife.value();//读取到注解中设置的id
                View findedView = view.findViewById(id);//获取到需要的view
                try {
                    field.set(ojbct, findedView);//将获取到的view赋值当前对象的field。
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    Log.e(TAG, "bind: " + e.getMessage());
                }
            }
        }
    }

}
