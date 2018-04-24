package com.test.rxdemo;

import org.junit.Test;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by xuyabo on 2018/1/10.
 */

public class RXSchedulersTest {

    Observable<String> myObservable = Observable
            .create(new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> subscriber) {
                    printThread();
                    System.out.println("各种准备");
                    System.out.println();
                    subscriber.onNext("1");
                    subscriber.onNext("2");
                    subscriber.onNext("3");
                    subscriber.onCompleted();
                }
            });

    /**
     * 使用Schedulers调度线程
     */
    @Test
    public void useScheduler() {

        myObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(Thread.currentThread());
                        System.out.println("s = [" + s + "]");
                    }
                });
    }

    private void printThread() {
        System.out.println(Thread.currentThread());
    }

    /**
     * lift模仿ObeservableOn切换线程
     */
    @Test
    public void liftToObservableOn() {
        myObservable
                .lift(new Observable.Operator<String, String>() {
                    @Override
                    public Subscriber<? super String> call(final Subscriber<? super String> subscriber) {
                        return new Subscriber<String>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(final String s) {
                                      new Thread(new Runnable() {
                                          @Override
                                          public void run() {
                                              subscriber.onNext(s);
                                          }
                                      }).start();
                            }
                        };
                    }
                })

                .subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(Thread.currentThread());
                System.out.println("s = [" + s + "]");
            }
        });
    }
}
