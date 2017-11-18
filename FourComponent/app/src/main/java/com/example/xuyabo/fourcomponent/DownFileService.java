package com.example.xuyabo.fourcomponent;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.xuyabo.fourcomponent.serviceDemo.IntentServiceActivity;

/**
 * Service必须在AndroidManifest中声明
 * 必须有一个无参构造函数
 */

public class DownFileService extends IntentService{
    public static final String FILENAME="file name";
    String TAG="DownFileService";

    public DownFileService() {
        super("DownFileService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String filename=intent.getStringExtra(FILENAME);

        //模拟下载文件
        try {
            Log.i(TAG, "onHandleIntent:  down file"+filename);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent finishedIntent=new Intent(IntentServiceActivity.UP_FINISHED);
        finishedIntent.putExtra(FILENAME,filename);
        sendBroadcast(finishedIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: Service creat");
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i(TAG, "onCreate: Service start");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onCreate: Service destroyed");
    }
}
