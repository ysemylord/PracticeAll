package com.test.rxdemo;

import org.junit.Test;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class RxBaseTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    //create()创建被观察者
    @Test
    public void createTest() {
        //被观察者 提供信息，向观察者发送通知（即调用subscriber.onNext(方法)）
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("我是jack choue");
                subscriber.onNext("你是谁");
                subscriber.onCompleted();
            }
        });

        //观察者 定义被通知时的操作
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                print("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                print(e.getLocalizedMessage());
            }

            @Override
            public void onNext(String s) {
                print(s);
            }
        };


        //调用subscribe方法，被观察者向观察者发送通知发送通知（）
        observable.subscribe(observer);

    }


    void print(String string) {
        System.out.println(string);
    }

    //打印字符串数组


    /**
     * 模仿Obervale.from方法
     *
     * @param arr
     * @return
     */
    private Observable imitationFrom(final String[] arr) {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                for (int i = 0; i < arr.length; i++) {
                    subscriber.onNext(arr[i]);
                }
                subscriber.onCompleted();
            }
        });
        return observable;
    }

    private Observable imitationJust(final String... arr) {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                for (int i = 0; i < arr.length; i++) {
                    subscriber.onNext(arr[i]);
                }
                subscriber.onCompleted();
            }
        });
        return observable;
    }

    @Test
    public void imitationFromTest() {

        imitationFrom(new String[]{"倒计时:1", "倒计时:2", "倒计时:3"})
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        print("倒计时完毕");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        print("收到倒计时" + s);
                    }
                });
    }

    @Test
    public void imitationJustsTest() {

        imitationJust("倒计时:1", "倒计时:2", "倒计时:3");


        imitationJust("倒计时:1", "倒计时:2", "倒计时:3")
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        print("倒计时完毕");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        print("收到倒计时" + s);
                    }
                });
    }


    @Test
    public void ObservablefromTest() {
        Observable.from(new String[]{"倒计时:1", "倒计时:2", "倒计时:3"})
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        print("收到倒计时" + s);
                    }
                });
    }


    @Test
    public void ObservableJustTest() {
        Observable.just("倒计时:1", "倒计时:2", "倒计时:3")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        print("收到倒计时" + s);
                    }
                });
    }
}

