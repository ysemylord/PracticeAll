package com.example.xuyabo.scatteredpractice;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import static android.R.attr.data;
import static android.os.Environment.DIRECTORY_MOVIES;
import static android.os.Environment.DIRECTORY_MUSIC;
import static android.os.Environment.getDownloadCacheDirectory;
import static android.os.Environment.getExternalStorageDirectory;
import static android.os.Environment.getExternalStoragePublicDirectory;
import static android.os.Environment.getRootDirectory;

public class StoragePraticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_pratice);
        String d1=Environment.getRootDirectory().toString();//  /system
        String d2= Environment.getDataDirectory().toString();// /data
        String d3=Environment.getDownloadCacheDirectory().toString();// /cache

        //应先查询状态
        String d4=Environment.getExternalStorageDirectory().toString();// /storage/emulated/0
        String d5=Environment.getExternalStoragePublicDirectory(DIRECTORY_MUSIC).toString();// /storage/emulated/0/Music
        Log.i("Environment", d1);
        Log.i("Environment", d2);
        Log.i("Environment", d3);
        Log.i("Environment", d4);
        Log.i("Environment", d5);

        Context context=getApplicationContext();
        Log.i("Context 外部存储", context.getExternalFilesDir(DIRECTORY_MOVIES).toString());///storage/emulated/0/Android/data/com.example.xuyabo.scatteredpractice/files/Movies
        Log.i("Context 外部存储", context.getExternalCacheDir().toString());// /storage/emulated/0/Android/data/com.example.xuyabo.scatteredpractice/cache
        Log.i("Context 内部存储", context.getFilesDir().toString());
        Log.i("Context 内部存储", context.getCacheDir().toString());




    }
}
