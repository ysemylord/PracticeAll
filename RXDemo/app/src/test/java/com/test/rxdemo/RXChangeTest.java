package com.test.rxdemo;

import com.test.rxdemo.bean.Course;
import com.test.rxdemo.bean.Student;

import org.junit.Test;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * RX map与flatMap变化
 */
public class RXChangeTest {


    /**
     * map变化打印学生名字
     */
    @Test
    public void pintStudentName() {
        Student[] students = new Student[]{
                new Student("1"),
                new Student("2"),
                new Student("3"),
                new Student("4"),
                new Student("5"),
        };
        Observable.from(students)
                .map(new Func1<Student, String>() {
                    @Override
                    public String call(Student student) {
                        return student.getName();
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Util.print(s);
                    }
                });
<<<<<<< HEAD

    }

=======
    }

    /**
     * flatMap变化打印学生课程
     */
>>>>>>> 17f142219a0a677ecbbb36c36c56ca62a68a85ba
    @Test
    public void pintStudentCourse() {
        Student[] students = new Student[]{
                new Student("1"),
                new Student("2"),
                new Student("3"),
                new Student("4"),
                new Student("5"),
        };

        Observable.from(students)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        Util.print("学生名: " + student.getName());
                        return Observable.from(student.getCourses());
                    }
<<<<<<< HEAD
                })
                .subscribe(new Action1<Course>() {
                    @Override
                    public void call(Course course) {
                        Util.print("课程名: " + course.getCourseName());
                    }
                });
=======
                }).subscribe(new Action1<Course>() {
            @Override
            public void call(Course course) {
                Util.print("课程名: " + course.getCourseName());
            }
        });
>>>>>>> 17f142219a0a677ecbbb36c36c56ca62a68a85ba
    }

}
