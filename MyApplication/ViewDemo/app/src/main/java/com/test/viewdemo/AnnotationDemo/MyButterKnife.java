package com.test.viewdemo.AnnotationDemo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xuyabo on 2018/6/29.
 *
 */

@Target(ElementType.FIELD)//作用范围是属性
@Retention(RetentionPolicy.RUNTIME)//保留时期是运行时
public @interface MyButterKnife {
    int value();//控件的id值
}
