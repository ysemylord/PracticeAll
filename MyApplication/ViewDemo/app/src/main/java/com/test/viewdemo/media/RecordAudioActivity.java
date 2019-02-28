package com.test.viewdemo.media;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.test.viewdemo.R;

public class RecordAudioActivity extends AppCompatActivity {

    private static final String TAG = "RecordAudioActivity";
    MediaRecordUtil mMediaRecordUtil = new MediaRecordUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_audio);
        mMediaRecordUtil.createAudio("temp");
    }


    public void startRecord(View view) {
        mMediaRecordUtil.startRecord();

    }

    public void resumeRecord(View view) {
        mMediaRecordUtil.resumeRecord();
    }

    public void puaseRecord(View view) {
        mMediaRecordUtil.pauseRecord();
    }

    public void stopRecord(View view) {
        mMediaRecordUtil.stopRecord();
    }


}
