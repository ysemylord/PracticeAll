package com.example.xuyabo.fourcomponent.aidlDemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.xuyabo.fourcomponent.R;

import test.IAddManager;

public class BinderDemoActivity extends AppCompatActivity {
    private static final String TAG = "BinderDemoActivity";

    private IAddManager mAddMananger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_demo);
        Intent addServiceIntent = new Intent(this, AddService.class);
        bindService(addServiceIntent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                IAddManager iAddManager = IAddManager.Stub.asInterface(service);
                mAddMananger = iAddManager;
                try {
                    int res = iAddManager.add(100, 200);
                    Log.i(TAG, "onServiceConnected: IAddManager 的计算结果为 " + res);

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }

    public void onAddBtnClicked(View view) throws RemoteException {
        int res=mAddMananger.add(10,12);
        Log.i(TAG, "onAddBtnClicked: "+res);
    }
}
