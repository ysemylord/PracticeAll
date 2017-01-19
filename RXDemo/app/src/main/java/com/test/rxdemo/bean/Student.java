package com.test.rxdemo.bean;

/**
 * Created by Administrator on 2017/1/19.
 */
public class Student {
    private String name;
    private Course[] courses=new Course[]{new Course("数学"),new Course("英语"),new Course("语文")};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, Course[] courses) {
        this.name = name;
        this.courses = courses;
    }

    public Course[] getCourses() {
        return courses;
    }

    public void setCourses(Course[] courses) {
        this.courses = courses;
    }


}


