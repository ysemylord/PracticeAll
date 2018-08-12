package com.example.xuyabo.fourcomponent.aidlDemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

import test.IAddManager;
import test.IOnNumArrived;

public class AddService extends Service {
    private static final String TAG = "AddService";
    private AtomicBoolean isDestroyed = new AtomicBoolean(false);
    private RemoteCallbackList<IOnNumArrived> mRemoteCallbackList = new RemoteCallbackList();
    Binder mBinder = new IAddManager.Stub() {
        @Override
        public int add(int a, int b) throws RemoteException {
            return a + b;
        }

        @Override
        public void registerOnNumArrived(IOnNumArrived iOnNumArrived) throws RemoteException {
            mRemoteCallbackList.register(iOnNumArrived);
        }

        @Override
        public void unRegisterOnNumArrived(IOnNumArrived iOnNumArrived) throws RemoteException {
            mRemoteCallbackList.unregister(iOnNumArrived);
        }
    };

    public AddService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isDestroyed.get()) {
                    try {
                        Thread.sleep(5000);
                        final int num = mRemoteCallbackList.beginBroadcast();
                        for(int i = 0; i< num; i++){
                            IOnNumArrived iOnNumArrived=mRemoteCallbackList.getBroadcastItem(i);
                            if (iOnNumArrived != null) {
                                iOnNumArrived.onNumArrive(500);
                                Log.i(TAG, "run: onNumArrive invoked");
                            }
                        }
                        mRemoteCallbackList.finishBroadcast();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        isDestroyed.set(false);
    }
}
