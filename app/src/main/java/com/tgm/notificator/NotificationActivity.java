package com.tgm.notificator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.tgm.notificator.databinding.ActivityNotificationBinding;

public class NotificationActivity extends AppCompatActivity {

    private ActivityNotificationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // we need to pass intent.putExtra

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        binding.messageTitle.setText(intent.getStringExtra(Constant.MESSAGE_TITLE));
    }
}