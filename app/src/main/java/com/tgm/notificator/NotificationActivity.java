package com.tgm.notificator;

import static com.tgm.notificator.Constant.NOTIFICATION_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tgm.notificator.databinding.ActivityNotificationBinding;

public class NotificationActivity extends AppCompatActivity {

    private ActivityNotificationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // we need to pass intent.putExtra


        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
                if (remoteInput != null) {
                    String reply = remoteInput.getCharSequence(Constant.KEY_REPLY).toString();

                    //Set the inline reply text in the TextView
                    binding.messageTitle.setText(reply);

                }
            }
        }, new IntentFilter("Hi"));

        // Update the notification to show that the reply was received.
        NotificationController controller = new NotificationController(this);
        controller.showNotification(Constant.CHANNEL_ID, "Message Received", "All Done", NotificationCompat.PRIORITY_DEFAULT,
                true, NOTIFICATION_ID);


    }

    @Override
    protected void onNewIntent(Intent intent) {
        binding.messageTitle.setText(intent.getStringExtra(Constant.MESSAGE_TITLE));
        Toast.makeText(this, "" + intent.getStringExtra(Constant.MESSAGE_TITLE), Toast.LENGTH_SHORT).show();
        super.onNewIntent(intent);
    }
}