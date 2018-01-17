package com.example.xuyabo.fourcomponent.serveiDemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.xuyabo.fourcomponent.R;

public class ServiceDemoActivity extends AppCompatActivity {
    private static final String TAG = "ServiceDemoActivity";
    private MyService.MyBinder mMyBinder;
    private boolean mIsBound = false;
    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMyBinder = (MyService.MyBinder) service;
            Log.i(TAG, "onServiceConnected: ");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mMyBinder = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_demo);
    }

    public void startService(View view) {
        Intent intent = new Intent(this, MyService.class);

        startService(intent);
    }

    public void stopService(View view) {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

    public void bindService(View view) {
        Intent intent = new Intent(this, MyService.class);

        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    public void unBindService(View view) {
        if (mIsBound) {
            unbindService(mServiceConnection);
            mIsBound = false;
        }
    }

    public void skip(View view) {
        startActivity(new Intent(this, ServiceDemoActivity.class));
    }
}
