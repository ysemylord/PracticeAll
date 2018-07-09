package com.example.xuyabo.fourcomponent.MessengerDemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.xuyabo.fourcomponent.R;

import static com.example.xuyabo.fourcomponent.MessengerDemo.MessengerDemoService.TO_CLIENT;

public class MessengerDemoActivity extends AppCompatActivity {
    private static final String TAG = "Messenger";
    private Messenger mMessenger;

    private static class ReceiveMsgFromServerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case TO_CLIENT:
                    Bundle bundle = msg.getData();
                    String replyMsgFromServer = bundle.getString("reply");
                    Log.i(TAG, "Client：收到服务端的回复 " + replyMsgFromServer);
                    break;
            }
        }
    }

    private static Handler mReceiveMsgFromServerHandler = new ReceiveMsgFromServerHandler();
    private Messenger clientMenger = new Messenger(mReceiveMsgFromServerHandler);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger_demo);
        Intent intent = new Intent(this, MessengerDemoService.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mMessenger = new Messenger(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);
    }

    public void sendMessgerToServer(View view) throws RemoteException {
        Message message = new Message();
        Bundle data = new Bundle();
        data.putString("msg", "你好服务端");
        message.setData(data);
        Log.i(TAG, "sendMessgerToServer");

        //回复消息
        message.replyTo = clientMenger;


        mMessenger.send(message);
    }
}
