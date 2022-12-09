package com.portfioly.Services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;
import com.portfioly.R;

import java.util.Objects;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final String NOTIFICATION_CHANNEL_ID = "Portfioly";
    private static final String NOTIFICATION_CHANNEL_NAME = "Portfioly";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);


        if(message.getData().size() > 0){
            //handle the data message here
        }

        String title = message.getNotification().getTitle();
        String body = message.getNotification().getBody();
        System.out.println(body);
        NotificationManager notificationManager = (NotificationManager) getSystemService ( Context.NOTIFICATION_SERVICE );
        if (!Objects.equals ( null, message.getNotification () )) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
                notificationManager.createNotificationChannel(notificationChannel);
            }
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
            notificationBuilder.setAutoCancel(true)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(message.getNotification().getBody()))
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setTicker(message.getNotification().getTitle())
                    .setPriority(Notification.PRIORITY_MAX)
                    .setContentTitle(message.getNotification().getTitle())
                    .setContentText(message.getNotification().getBody());
            notificationManager.notify(1, notificationBuilder.build());
        }
    }
}
