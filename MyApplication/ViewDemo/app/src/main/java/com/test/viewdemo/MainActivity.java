package com.test.viewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.test.viewdemo.xview.CircleSeekBar;
import com.test.viewdemo.xview.RippleView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.schedulers.IoScheduler;

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final CircleSeekBar circleSeekBar=findViewById(R.id.circleSeekBar);
        circleSeekBar.setThumbBitmap(R.drawable.mc_shape_play_seek_tag);
        circleSeekBar.setMaxProgress(200154);


        Flowable.interval(3,1, TimeUnit.SECONDS)
                .subscribeOn(new IoScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.i(TAG, "accept: "+aLong);
                        circleSeekBar.setCurrent((int) ((count++)*1000));
                    }
                });


          RippleView rippleView= findViewById(R.id.rippleView);
          rippleView.start();
    }
}
