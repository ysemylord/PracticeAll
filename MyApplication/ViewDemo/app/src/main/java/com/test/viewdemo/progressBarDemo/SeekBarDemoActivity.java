package com.test.viewdemo.progressBarDemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.test.viewdemo.MyRingDrawable;
import com.test.viewdemo.R;
import com.test.viewdemo.xview.myprogressBar.IndicateProgressView;
import com.test.viewdemo.xview.myprogressBar.NumberInProgressView;

import java.util.concurrent.TimeUnit;

public class SeekBarDemoActivity extends AppCompatActivity {
    IndicateProgressView mIndicateProgressView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar_demo);
        final SeekBar seekBar= (SeekBar) findViewById(R.id.seekBar);

        ImageView imageView= (ImageView) findViewById(R.id.image_view);
        final MyRingDrawable myRingDrawable = new MyRingDrawable();
        imageView.setBackgroundDrawable(myRingDrawable);

        //seekBar.setEnabled(false);
        mIndicateProgressView=(IndicateProgressView)findViewById(R.id.indicate_progress_view);
        final NumberInProgressView numberInProgressView= (NumberInProgressView) findViewById(R.id.number_in_progress_view);
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                int progress=0;
                while (progress<=100){
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mIndicateProgressView.setProgress(progress++);
               }
            }
        });
       thread.start();

       Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                int progress=0;
                while (progress<=100){
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    seekBar.setProgress(progress++);
               }
            }
        });
       thread1.start();


       Thread thread3=new Thread(new Runnable() {
            @Override
            public void run() {
                int progress=0;
                while (progress<=100){
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    numberInProgressView.setProgress(progress++);
               }
            }
        });
       thread3.start();

       Thread thread4=new Thread(new Runnable() {
            @Override
            public void run() {
                int progress=0;

                while (progress<=100){
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progress++;
                    final int nowProgress=progress;
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            myRingDrawable.setPercent(nowProgress*1f/100);

                        }
                    });

                    if(progress==100){
                        progress=0;
                    }
               }
            }
        });
       thread4.start();
    }
}
