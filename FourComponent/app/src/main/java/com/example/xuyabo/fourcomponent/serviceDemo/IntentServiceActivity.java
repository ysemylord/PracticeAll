package com.example.xuyabo.fourcomponent.serviceDemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.xuyabo.fourcomponent.R;

public class IntentServiceActivity extends AppCompatActivity {

    public static final String UP_FINISHED = "UP_FINISHED";
    TextView mContentTextView;
    private FileBroadcastReceiver mFileBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);
        mContentTextView = (TextView) findViewById(R.id.content_textView);
        IntentFilter intentFilter = new IntentFilter(UP_FINISHED);
        mFileBroadcastReceiver = new FileBroadcastReceiver();
        registerReceiver(mFileBroadcastReceiver, intentFilter);
    }

    public void start_down(View view) {
        Intent intent = new Intent(this, DownFileService.class);

        for (int i = 1; i < 5; i++) {
            intent.putExtra(DownFileService.FILENAME, "文件"+i);
            startService(intent);
        }

    }

    class FileBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (UP_FINISHED.equals(action)) {
                String fileName = intent.getStringExtra(DownFileService.FILENAME);
                mContentTextView.append(fileName + "\n");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mFileBroadcastReceiver);
    }
}

