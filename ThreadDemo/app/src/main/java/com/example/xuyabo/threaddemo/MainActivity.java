package com.example.xuyabo.threaddemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import static android.R.id.message;

public class MainActivity extends AppCompatActivity {
    private String TAG="MainActivity";
    private TextView mAcceptTextView;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            if (what == 0) {
                mAcceptTextView.setText("收到消息 " + msg.obj);//主线程收到消息，更新UI
                Log.i(TAG, "线程 "+Thread.currentThread()+" 收到消息消息");
            }

        }
    };

    private Handler mHandler2=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            int what = msg.what;
            if (what == 0) {
                mAcceptTextView.setText("收到消息 " + msg.obj);//主线程收到消息，更新UI
                Log.i(TAG, "线程 "+Thread.currentThread()+" 收到消息消息");
                return true;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAcceptTextView = (TextView) findViewById(R.id.accept_text_view);
    }

    public void sendMessage(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1 * 1000);//模拟耗时任务
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Message message = mHandler.obtainMessage();
                message.what = 0;
                message.obj = "大家好";
                mHandler.sendMessage(message);//子线程发送消息
                Log.i(TAG, "线程 "+Thread.currentThread()+" 发送消息");
            }
        }).start();

    }

    public void postRunable(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1 * 1000);//模拟耗时任务
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                final  String msg="大家好";

                //子线程发送事件
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mAcceptTextView.setText(msg);//主线程处理事件
                        Log.i(TAG, "线程 "+Thread.currentThread()+" 执行事件");
                    }
                });
                Log.i(TAG, "线程 "+Thread.currentThread()+" 发送事件");
            }
        }).start();
    }
}
