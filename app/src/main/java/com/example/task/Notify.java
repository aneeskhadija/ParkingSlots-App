
package com.example.task;

public class Notify {

//    private static final int NOTIFICATION_ID = 99;
//    private static final String CHANNEL_ID = "service_channel";
//    private static final String CHANNEL_ID2 = "missed_call";
//
//    @SuppressLint("StaticFieldLeak")
//    private static NotificationCompat.Builder notificationBuilder;
//    private static NotificationManager notificationManager;
//
//    public static Notification remindUser(Context context, String title, String text, String bigText) {
//        notificationManager = (NotificationManager) context
//                .getSystemService(Context.NOTIFICATION_SERVICE);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel mChannel = new NotificationChannel(
//                    CHANNEL_ID,
//                    "mName",
//                    NotificationManager.IMPORTANCE_HIGH);
//            notificationManager.createNotificationChannel(mChannel);
//        }
//
//        notificationBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
//                .setColor(ContextCompat.getColor(context, R.color.purple_200))
//                .setLargeIcon(largeIcon(context))
//                .setContentTitle(title)
//                .setContentText(text)
//                .setStyle(new NotificationCompat.BigTextStyle().bigText(bigText))
//                .setPriority(Notification.PRIORITY_HIGH)
//                .setDefaults(Notification.DEFAULT_VIBRATE)
//                .setAutoCancel(true);
//
//        if (Build.VERSION.SDK_INT >= 21) {
//            notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
//        }
//
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
//            notificationBuilder.setPriority(NotificationCompat.PRIORITY_LOW);
//        }
//
//        return notificationBuilder.build();
//    }
//
//    public static void remind(CountDownTimer context) {
//        notificationManager = (NotificationManager) this
//                .getSystemService(Context.NOTIFICATION_SERVICE);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel mChannel = new NotificationChannel(
//                    CHANNEL_ID2,
//                    "mName",
//                    NotificationManager.IMPORTANCE_LOW);
//            notificationManager.createNotificationChannel(mChannel);
//        }
//
//        notificationBuilder = new NotificationCompat.Builder(context, CHANNEL_ID2)
//                .setColor(ContextCompat.getColor(context, R.color.purple_200))
//                .setLargeIcon(largeIcon(context))
//                .setContentTitle("")
//                .setContentText("")
//                .setStyle(new NotificationCompat.BigTextStyle().bigText(
//                        ""))
//                .setPriority(Notification.PRIORITY_LOW)
//                .setAutoCancel(true);
//        if (Build.VERSION.SDK_INT >= 21) {
//            notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
//        }
//
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
//            notificationBuilder.setPriority(NotificationCompat.PRIORITY_LOW);
//        }
//
//        notificationBuilder.build();
//    }
//
//    public static void writeNotification(String title, String desc) {
//        if (notificationBuilder != null) {
//            notificationBuilder.setContentTitle(title)
//                    .setContentText(desc);
//            notificationManager.notify(1, notificationBuilder.build());
//        }
//    }
//
//    private static Bitmap largeIcon(Context context) {
//        Resources resources = context.getResources();
//        return BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher);
//    }
}