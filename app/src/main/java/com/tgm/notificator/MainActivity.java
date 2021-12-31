package com.tgm.notificator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.tgm.notificator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NotificationController notificationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notificationController = new NotificationController(this);

        binding.showSimpleNotificationBtn.setOnClickListener(view -> {
            notificationController.showNotification(Constant.CHANNEL_ID, Constant.MESSAGE_TITLE,
                    Constant.MESSAGE, NotificationCompat.PRIORITY_HIGH,
                    true, Constant.NOTIFICATION_ID,
                    notificationController.createPendingIntent(NotificationActivity.class, 0, false));
        });
        binding.showSimpleNotificationBtnWithUrl.setOnClickListener(view -> {
            notificationController.showNotification(Constant.CHANNEL_ID, Constant.MESSAGE_TITLE,
                    Constant.MESSAGE, NotificationCompat.PRIORITY_HIGH,
                    true, Constant.NOTIFICATION_ID,
                    notificationController.createPendingIntent(Uri.parse(Constant.OPEN_URL), 0, true));
        });
        binding.showBigNotificationBtn.setOnClickListener(view -> {
            notificationController.showNotification(Constant.CHANNEL_ID, Constant.MESSAGE_TITLE,
                    Constant.MESSAGE, Constant.BIG_MESSAGE, NotificationCompat.PRIORITY_HIGH,
                    true, Constant.NOTIFICATION_ID);
        });
        binding.showSimpleNotificationBtnWithForce.setOnClickListener(view -> {
            notificationController.showNotification(Constant.CHANNEL_ID, Constant.MESSAGE_TITLE,
                    Constant.MESSAGE, NotificationCompat.PRIORITY_HIGH, true,
                    true, Constant.NOTIFICATION_ID,
                    notificationController.createPendingIntent(NotificationActivity.class,
                            0, true));
        });
        binding.showNotificationBtnWithBigPicture.setOnClickListener(view -> {
            notificationController.showNotification(Constant.CHANNEL_ID, Constant.MESSAGE_TITLE,
                    Constant.MESSAGE, R.drawable.m_icon, R.drawable.banner1, NotificationCompat.PRIORITY_HIGH,
                    true, Constant.NOTIFICATION_ID,
                    notificationController.createPendingIntent(NotificationActivity.class, 0,
                            true));
        });
        binding.showNotificationWithBtn.setOnClickListener(view -> {
            notificationController.showNotification(Constant.CHANNEL_ID, Constant.MESSAGE_TITLE,
                    Constant.MESSAGE, R.drawable.m_icon, 1, true, NotificationCompat.PRIORITY_HIGH,
                    true, Constant.NOTIFICATION_ID,
                    notificationController.createPendingIntent(NotificationActivity.class, 0,
                            true));
        });
        binding.showNotificationWithBtn2.setOnClickListener(view -> {
            notificationController.showNotification(Constant.CHANNEL_ID, Constant.MESSAGE_TITLE,
                    Constant.MESSAGE, R.drawable.m_icon, 0, false, NotificationCompat.PRIORITY_HIGH,
                    true, Constant.NOTIFICATION_ID,
                    notificationController.createPendingIntent(NotificationActivity.class, 0,
                            true));
        });
        binding.showNotificationBtnWithReply.setOnClickListener(view -> {
            notificationController.showNotification(Constant.CHANNEL_ID, Constant.MESSAGE_TITLE,
                    Constant.MESSAGE, NotificationCompat.PRIORITY_HIGH,
                    true, Constant.NOTIFICATION_ID);
        });
        binding.showNotificationBtnWithProgressBar.setOnClickListener(view -> {
            notificationController.showNotification(Constant.CHANNEL_ID, Constant.MESSAGE_TITLE,
                    NotificationCompat.PRIORITY_HIGH,
                    true, Constant.NOTIFICATION_ID);
        });
    }
}