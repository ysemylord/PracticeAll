package com.example.xuyabo.fourcomponent.MessengerDemo;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

/**
 *
 */
public class MessengerDemoService extends Service {
    private static final String TAG = "Messenger";
    public static final int FROM_CLIENT = 0;
    public static final int TO_CLIENT = 0;


    private Messenger mMessenger=new Messenger(new MesengerHandler());
    private static class MesengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case FROM_CLIENT:
                    //收消息
                    Bundle data = msg.getData();
                    String clientSendMsg = data.getString("msg");
                    Log.i(TAG, "Server: 收到客户端的消息 " + clientSendMsg);
                    //回复消息
                    Messenger clientMessenger= msg.replyTo;
                    Message replyMessage=new Message();
                    Bundle replyBundle=new Bundle();
                    replyBundle.putString("reply","我收到你的消息了");
                    replyMessage.what=TO_CLIENT;
                    replyMessage.setData(replyBundle);
                    try {
                        clientMessenger.send(replyMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }


                    break;
            }
        }
    }

    public MessengerDemoService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
      return mMessenger.getBinder();
    }
}
