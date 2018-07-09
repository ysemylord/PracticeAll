package com.example.xuyabo.fourcomponent.aidlDemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import test.IAddManager;

public class AddService extends Service {

    Binder mBinder = new IAddManager.Stub() {
        @Override
        public int add(int a, int b) throws RemoteException {
            return a + b;
        }
    };

    public AddService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
