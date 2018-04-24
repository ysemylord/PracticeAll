package com.test.rxdemo;

import com.test.rxdemo.bean.Course;
import com.test.rxdemo.bean.Student;

import org.junit.Test;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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

    }

    /**
     * flatMap变化打印学生课程
     */
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

                })
                .subscribe(new Action1<Course>() {
                    @Override
                    public void call(Course course) {
                        Util.print("课程名: " + course.getCourseName());
                    }

                });
    }

    /**
     * lift变化,模拟map
     */
    @Test
    public void liftToMap() {
        Observable.just(1, 2, 3)
                .lift(new Observable.Operator<String, Integer>() {
                    @Override
                    public Subscriber<? super Integer> call(final Subscriber<? super String> subscriber) {
                        return new Subscriber<Integer>() {
                            @Override
                            public void onCompleted() {
                                subscriber.onCompleted();

                            }

                            @Override
                            public void onError(Throwable e) {
                                subscriber.onError(e);
                            }

                            @Override
                            public void onNext(Integer integer) {
                                subscriber.onNext(integer + "");
                            }
                        };
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });
    }

    /**
     * lift变化模拟flatMap
     */
    @Test
    public void liftFlatMap() {
        final Student[] students = new Student[]{
                new Student("1"),
                new Student("2"),
                new Student("3"),
                new Student("4"),
                new Student("5"),
        };
        Observable.from(students)
                .lift(new Observable.Operator<Course, Student>() {
                    @Override
                    public Subscriber<? super Student> call(final Subscriber<? super Course> subscriber) {
                        return new Subscriber<Student>() {
                            @Override
                            public void onCompleted() {
                                subscriber.onCompleted();

                            }

                            @Override
                            public void onError(Throwable e) {
                                subscriber.onError(e);
                            }

                            @Override
                            public void onNext(Student student) {

                                Observable.from(student.getCourses()).subscribe(subscriber);
                            }
                        };
                    }
                })
                .subscribe(new Action1<Course>() {
                    @Override
                    public void call(Course course) {
                        System.out.println(course.getCourseName());
                    }
                });
    }

    @Test
    public void composeDemo() {
        Observable.just(1)
                .compose(new DemoTransformer())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });
    }

    class DemoTransformer implements Observable.Transformer<Integer, String> {
        @Override
        public Observable<String> call(Observable<Integer> integerObservable) {
            Observable<String> needObervable = integerObservable.
                    subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.newThread())
                    .map(new Func1<Integer, String>() {
                        @Override
                        public String call(Integer integer) {
                            return integer + "";
                        }
                    });
            return needObervable;
        }
    }
}
