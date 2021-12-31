package com.tgm.notificator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.tgm.notificator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.showSimpleNotificationBtn.setOnClickListener(view -> {
            createNotification(NotificationController.createPendingIntent(this, NotificationActivity.class));
        });

        binding.showSimpleNotificationBtnWithUrl.setOnClickListener(view -> {
            createNotification(NotificationController.createPendingIntent(this, Uri.parse(Constant.OPEN_URL)));
        });

    }

    private void createNotification(PendingIntent pendingIntent){
        // create notification
        // Create an explicit intent for an Activity in your app
//        Intent intent = new Intent(this, NotificationActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, Constant.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_account_circle_24)
                .setContentTitle(Constant.MESSAGE_TITLE)
                .setContentText(Constant.MESSAGE)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_baseline_account_circle_big))
                .setAutoCancel(true); // Notice this code calls setAutoCancel(), which automatically removes the notification when the user taps it.

        createNotificationChannel();

        // show notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        // notificationId is a unique int for each notification that you must define.
        // Remember to save the notification ID that you pass to NotificationManagerCompat.notify()
        // because you'll need it later if you want to update or remove the notification.
        notificationManager.notify(Constant.NOTIFICATION_ID, builder.build());
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(Constant.CHANNEL_ID, Constant.CHANNEL_NAME, importance);
            channel.setDescription(Constant.CHANNEL_DISC);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}