package com.example.task;

import static com.example.task.MainActivity.NOTIFY_CHANNEL;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class MyServices extends Service {

    private CountDownTimer timer;
    private String message = "null";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("backgroundService", "service stated");
    }

    private void generateNotification() {
        timer = new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished) {
                message = "seconds remaining to generate notification: " + millisUntilFinished / 1000;
            }
            public void onFinish() {
                message = "notification generated!";
            }
        }.start();

        Notification builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = AboveOreoNotify();
        } else {
            builder = BelowOreo();
        }
        NotificationManager notificationManager =
                (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder);
    }

    private Notification BelowOreo() {
        Notification builder;
        builder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Testing")
                .setAutoCancel(true)
                .setContentText(message)
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_MAX)
                .build();
        return builder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private Notification AboveOreoNotify() {
        Notification builder;
        builder =  new Notification.Builder(this, NOTIFY_CHANNEL)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Testing")
                .setAutoCancel(true)
                .setContentText(message)
                .setWhen(System.currentTimeMillis())
                .build();
        return builder;
    }

    @Override
    public void onDestroy() {
        Log.d("backgroundService", "service stopped");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        generateNotification();
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
}
