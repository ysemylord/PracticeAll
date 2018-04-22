package com.test.viewdemo.notificationDemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.test.viewdemo.AnimatorActivity;
import com.test.viewdemo.R;

public class NotificationDemoActivity extends AppCompatActivity {
    int notifcation_id = 1;
    int stack_notifcation_id = 2;
    int lauchInSingleTask_notifcation_id = 3;
    int progress_notifcation_id = 4;
    NotificationCompat.Builder mBuilder;
    private NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_demo);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, AnimatorActivity.class), PendingIntent.FLAG_CANCEL_CURRENT);
        mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("ContentTitle")
                        .setContentText("ContentText")
                        .setContentIntent(pendingIntent)
        ;

        Notification notification = mBuilder.build();
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notifcation_id, notification);
    }

    public void extensionLayout(View view) {
        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
        mBuilder.setStyle(style);//Style不同Notification样式不同
        for (int i = 0; i < 10; i++) {
            style.addLine("第"+i+"行");

        }

        style.setBigContentTitle("BigContentTitle");
        style.setSummaryText("SummaryText");
        mNotificationManager.notify(notifcation_id, mBuilder.build());
    }

    public void cancelNotification(View view) {
        mNotificationManager.cancel(notifcation_id);
    }

    public void taskStackBuilder(View view) {
        Intent resultIntent = new Intent(this, TaskStackBuilderActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        // Adds the back stack
        stackBuilder.addParentStack(TaskStackBuilderActivity.class);
        // Adds the Intent to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        // Gets a PendingIntent containing the entire back stack
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent)
                .setContentTitle("TaskStackBuilder");
        mNotificationManager.notify(stack_notifcation_id, mBuilder.build());

    }

    public void lauchInSingleTask(View view) {
        Intent intent = new Intent(this, InSingleTaskActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//在一个新的任务栈中其中
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);//清除InSingleTaskActivity任务栈中已存在的Activity
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        mBuilder.setContentIntent(pendingIntent)
                .setContentTitle("lauchInSingleTask");
        mNotificationManager.notify(lauchInSingleTask_notifcation_id, mBuilder.build());

    }

    public void progress_notifcation_id(View view) {
        mBuilder.setContentTitle("progress notificatin");
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    mBuilder.setProgress(100,i,false);
                    mNotificationManager.notify(progress_notifcation_id,mBuilder.build());
                    try {
                        Thread.sleep(1*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                mBuilder.setProgress(0,0,false);//取消通知栏
                mNotificationManager.notify(progress_notifcation_id,mBuilder.build());


            }
        }).start();
    }
}
