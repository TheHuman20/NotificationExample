package com.example.osads.notificationexample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private NotificationManager nm;
    private final int NOTIFICATION_ID = 100;  //Magic number

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nm = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void onClickNotification(View view) {

        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setLargeIcon(BitmapFactory.decodeResource(getApplication().getResources(), R.drawable.ic_launcher_foreground))
                .setTicker("New message")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle("Message")
                .setContentText("Important message")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setProgress(100, 50, false);
        long[] vibrate = new long[]{1000, 1500, 1000, 1500};
        Notification notification = notificationBuilder.build();
        notification.defaults = notification.DEFAULT_SOUND;
        notification.vibrate = vibrate;
        notification.flags = notification.flags | Notification.FLAG_INSISTENT | Notification.FLAG_ONGOING_EVENT;
        nm.notify(NOTIFICATION_ID, notification);
    }
}
