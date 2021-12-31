package com.tgm.notificator;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class NotificationController {

    public static PendingIntent createPendingIntent(Context context, Class mClass) {
        Intent intent = new Intent(context, mClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return PendingIntent.getActivity(context, 0, intent, 0);
    }

    public static PendingIntent createPendingIntent(Context context, Uri uri) {

        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(uri);
        return PendingIntent.getActivity(context, 0, intent, 0);
    }

//    public static PendingIntent createPendingIntent(Context context, Activity activity) {
//        Intent intent = new Intent(context, activity.getClass());
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        return PendingIntent.getActivity(context, 0, intent, 0);
//    }
//
//    public static PendingIntent createPendingIntent(Context context, Activity activity) {
//        Intent intent = new Intent(context, activity.getClass());
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        return PendingIntent.getActivity(context, 0, intent, 0);
//    }
}
