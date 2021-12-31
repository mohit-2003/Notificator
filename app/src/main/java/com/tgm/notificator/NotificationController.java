package com.tgm.notificator;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationController {

    // TODO : TaskStackBuilder not working..

    private Context context;
    private NotificationManagerCompat notificationManagerCompat;
    private NotificationManager notificationManager;

    public NotificationController(Context context) {
        this.context = context;
        notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public PendingIntent createPendingIntent(Class mClass, int pendingIntentFlag, boolean isIntermediateActivity) {
        // Create an explicit intent for an Activity in your app

        Intent intent = new Intent(context, mClass);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, pendingIntentFlag);
        if (isIntermediateActivity){
            TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
            taskStackBuilder.addNextIntentWithParentStack(intent);
            pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        }
        return pendingIntent;
    }

    public PendingIntent createPendingIntent(Uri uri, int pendingIntentFlag, boolean isIntermediateActivity) {

        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(uri);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, pendingIntentFlag);
        if (isIntermediateActivity){
            TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
            taskStackBuilder.addNextIntentWithParentStack(intent);
            pendingIntent = taskStackBuilder.getPendingIntent(0, pendingIntentFlag);
        }
        return pendingIntent;
    }

    private void registerNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(Constant.CHANNEL_ID, Constant.CHANNEL_NAME, importance);
            channel.setDescription(Constant.CHANNEL_DISC);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void showNotification(String channelId, String msgTitle, String message, String bigText, int priority, boolean autoCancel, int notificationId, PendingIntent pendingIntent) {

        // create notification channel for above Android 8.0
        registerNotificationChannel();

        // create notification
//        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_baseline_account_circle_big);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_baseline_account_circle_24)
                .setContentTitle(msgTitle)
                .setContentText(message)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setPriority(priority)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(bigText))
                .setAutoCancel(autoCancel); // Notice this code calls setAutoCancel(), which automatically removes the notification when the user taps it.

        // show notification
        // notificationId is a unique int for each notification that you must define.
        // Remember to save the notification ID that you pass to NotificationManagerCompat.notify()
        // because you'll need it later if you want to update or remove the notification.
        notificationManagerCompat.notify(notificationId, builder.build());
    }

    public void showNotification(String channelId, String msgTitle, String message, int priority, boolean autoCancel, int notificationId, PendingIntent pendingIntent) {

        // create notification channel for above Android 8.0
        registerNotificationChannel();

        // create notification
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_baseline_account_circle_big);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_baseline_account_circle_24)
                .setContentTitle(msgTitle)
                .setContentText(message)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setPriority(priority)
                .setAutoCancel(autoCancel); // Notice this code calls setAutoCancel(), which automatically removes the notification when the user taps it.

        // show notification
        // notificationId is a unique int for each notification that you must define.
        // Remember to save the notification ID that you pass to NotificationManagerCompat.notify()
        // because you'll need it later if you want to update or remove the notification.
        notificationManagerCompat.notify(notificationId, builder.build());
    }

    public void showNotification(String channelId, String msgTitle, String message, String bigText, int priority, boolean autoCancel, int notificationId) {

        // create notification channel for above Android 8.0
        registerNotificationChannel();

        // create notification
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_baseline_account_circle_big);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_baseline_account_circle_24)
                .setContentTitle(msgTitle)
                .setContentText(message)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(bigText))
                // Set the intent that will fire when the user taps the notification
                .setPriority(priority)
                .setAutoCancel(autoCancel); // Notice this code calls setAutoCancel(), which automatically removes the notification when the user taps it.

        // show notification
        // notificationId is a unique int for each notification that you must define.
        // Remember to save the notification ID that you pass to NotificationManagerCompat.notify()
        // because you'll need it later if you want to update or remove the notification.
        notificationManagerCompat.notify(notificationId, builder.build());
    }

    public void showNotification(String channelId, String msgTitle, String message, int priority, boolean onGoing, boolean autoCancel, int notificationId, PendingIntent pendingIntent) {

        // create notification channel for above Android 8.0
        registerNotificationChannel();

        // create notification

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_baseline_account_circle_24)
                .setContentTitle(msgTitle)
                .setContentText(message)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setPriority(priority)
                .setOngoing(onGoing)
                .setAutoCancel(autoCancel); // Notice this code calls setAutoCancel(), which automatically removes the notification when the user taps it.

        // show notification
        // notificationId is a unique int for each notification that you must define.
        // Remember to save the notification ID that you pass to NotificationManagerCompat.notify()
        // because you'll need it later if you want to update or remove the notification.
        notificationManagerCompat.notify(notificationId, builder.build());

    }

    public void showNotification(String channelId, String msgTitle, String message, int largeIcon, int bigPicture, int priority, boolean autoCancel, int notificationId, PendingIntent pendingIntent) {

        // create notification channel for above Android 8.0
        registerNotificationChannel();

        // create notification
        Bitmap big_picture = BitmapFactory.decodeResource(context.getResources(), bigPicture);
        Bitmap large_icon = BitmapFactory.decodeResource(context.getResources(), largeIcon);
        long[] pattern = {500, 500, 500, 500, 500, 500, 500, 500, 500};

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_baseline_account_circle_24)
                .setContentTitle(msgTitle)
                .setContentText(message)
                .setLargeIcon(large_icon)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setPriority(priority)
                .setColor(Color.RED)
                .setLights(Color.BLUE, 500, 500)
                .setVibrate(pattern)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(big_picture))
                .setAutoCancel(autoCancel); // Notice this code calls setAutoCancel(), which automatically removes the notification when the user taps it.

        // show notification
        // notificationId is a unique int for each notification that you must define.
        // Remember to save the notification ID that you pass to NotificationManagerCompat.notify()
        // because you'll need it later if you want to update or remove the notification.
        notificationManagerCompat.notify(notificationId, builder.build());
    }

}
