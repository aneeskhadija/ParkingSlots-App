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

import androidx.annotation.RequiresApi;

public class MediaObserver extends Service {

        private final static String TAG = "BroadcastService";
        private Context context;

        public static final String COUNTDOWN_BR = "your_package_name.countdown_br";

        CountDownTimer cdt = null;

        @Override
        public void onCreate() {
            super.onCreate();

            context = getApplicationContext();
            cdt = new CountDownTimer(5000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Log.i("countdown_time", "Countdown seconds remaining: " + millisUntilFinished / 1000);
                }
                @Override
                public void onFinish() {
                    generateNotification();
                    stopSelf();
                }
            };
            cdt.start();
        }
    private void generateNotification() {

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
                .setContentText("Notification Generated")
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
                .setContentText("Notification Generated")
                .setWhen(System.currentTimeMillis())
                .build();
        return builder;
    }

    @Override
        public void onDestroy() {
            cdt.cancel();
            Log.d("serviceDestroyed", "yessssss");
            super.onDestroy();
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            return START_STICKY;
        }

        @Override
        public IBinder onBind(Intent arg0) {
            return null;
        }
}