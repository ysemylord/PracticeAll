package com.test.rxdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadImageTest();
    }

    //由 id 取得图片并显示
    private void loadImageTest() {
        final ImageView imageView = (ImageView) findViewById(R.id.image_view);
        Observable.create(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        int dr = R.mipmap.ic_launcher;

                        subscriber.onNext(dr);
                    }
                })
                .subscribeOn(Schedulers.io())//指定subscribe发生的线程(Observable.OnSubscribe.subscribe其实调用的是call()方法)
                .observeOn(AndroidSchedulers.mainThread())//指定subscrber发生的线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        imageView.setImageResource(integer);
                    }
                });
    }
}
