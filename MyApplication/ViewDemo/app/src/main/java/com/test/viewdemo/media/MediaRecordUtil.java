package com.test.viewdemo.media;

import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MediaRecordUtil {
    private static final String TAG = "MediaRecordUtil";
    private String fileName;
    private String mAudioFilePath;
    private File mAudioSaveDir = Environment.getExternalStoragePublicDirectory("audioSaveDir");
    private MediaRecorder mMediaRecorder;
    private List<String> mFileNames = new ArrayList<>();
    private String mFileName;


    /**
     * 创建默认的录音对象
     * <p>
     * 文件名的格式为 自己需要的目录/文件名(文件名不要写后缀)
     *
     * @param fileName 文件名
     */
    public void createAudio(String fileName) {
        mFileName = fileName;
    }

    /**
     * 开始录音
     */
    public void startRecord() {
        // 开始录音
        /* ①Initial：实例化MediaRecorder对象 */
        if (mMediaRecorder == null) {
            mMediaRecorder = new MediaRecorder();
        }
        try {

            /* ②setAudioSource/setVedioSource */
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);// 设置麦克风
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            /*
             * ②设置输出文件的格式：THREE_GPP/MPEG-4/RAW_AMR/Default THREE_GPP(3gp格式
             * ，H263视频/ARM音频编码)、MPEG-4、RAW_AMR(只支持音频且音频编码要求为AMR_NB)
             */
            /* ②设置音频文件的编码：AAC/AMR_NB/AMR_MB/Default 声音的（波形）的采样 */
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);

            fileName = mFileName+""+mFileNames.size() + ".m4a";
            mFileNames.add(fileName);
            mAudioSaveDir.mkdirs();
            mAudioFilePath = mAudioSaveDir + File.separator + fileName;
            /* ③准备 */
            mMediaRecorder.setOutputFile(mAudioFilePath);
            mMediaRecorder.prepare();
            /* ④开始 */
            mMediaRecorder.start();
        } catch (IllegalStateException e) {
            Log.i(TAG, "call startAmr(File mRecAudioFile) failed!" + e.getMessage());
        } catch (IOException e) {
            Log.i(TAG, "call startAmr(File mRecAudioFile) failed!" + e.getMessage());
        }
    }

    /**
     * 暂停录音
     */
    public void pauseRecord() {
        mMediaRecorder.stop();
    }

    /**
     * 停止录音
     */
    public void stopRecord() {
        try {
            mMediaRecorder.stop();
            mMediaRecorder.release();
            mMediaRecorder = null;
        } catch (RuntimeException e) {
            Log.i(TAG, e.toString());
            mMediaRecorder.reset();
            mMediaRecorder.release();
            mMediaRecorder = null;
        }
    }

    /**
     * 继续录音
     */
    public void resumeRecord() {
        startRecord();
    }

    /**
     * 1.释放硬件资源
     * 2.清除临时的pcm文件
     */
    public void releaseResouceAndTemp() {
        mMediaRecorder.stop();
        mMediaRecorder.release();
        mMediaRecorder = null;
        for (File recoredFile : mAudioSaveDir.listFiles()) {
            recoredFile.delete();
        }

    }

}
