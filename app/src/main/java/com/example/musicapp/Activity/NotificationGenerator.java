package com.example.musicapp.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.musicapp.R;

public class NotificationGenerator {

    public  static final String CHANNEL_ID_1 = "CHANNEL_1";
    public  static final String CHANNEL_ID_2 = "CHANNEL_2";
    private static final int NOTIFICATION_ID_OPEN_ACTIVITY = 9;
    public static final String NOTIFY_PREVIOUS = "com.example.musicapp.previous";
    public static final String NOTIFY_DELETE = "com.example.musicapp.delete";
    public static final String NOTIFY_PAUSE = "com.example.musicapp.pause";
    public static final String NOTIFY_PLAY = "com.example.musicapp.play";
    public static final String NOTIFY_NEXT = "com.example.musicapp.next";

    @SuppressLint("RestrictedApi")
    public static void customBigNotification(Context context) {
        RemoteViews expandedView = new RemoteViews(context.getPackageName(), R.layout.big_notification);

        NotificationCompat.Builder nc = new NotificationCompat.Builder(context);
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        Intent notifyIntent = new Intent(context, PlayerActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);



        nc.setContentIntent(pendingIntent);
        nc.setSmallIcon(R.mipmap.ic_launcher_round);
//        nc.setAutoCancel(true);
        nc.setCustomBigContentView(expandedView);
        nc.setContentTitle("Music Player");
        nc.setContentText("Control Audio");
        nc.getBigContentView().setTextViewText(R.id.textviewsongnamenotification, "Songs"); //@SuppressLint("RestrictedApi")


        setListeners(expandedView, context);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        nm.notify(NOTIFICATION_ID_OPEN_ACTIVITY, nc.build());

        Log.d("BBB", "generated notification!!");
    }

    public static void customBigNotification2(Context context) {

        Log.d("BBB", "generated notification!!");
    }

    private static void setListeners(RemoteViews view, Context context) {
        Intent previous = new Intent(NOTIFY_PREVIOUS);
        Intent delete = new Intent(NOTIFY_DELETE);
        Intent pause = new Intent(NOTIFY_PAUSE);
        Intent next = new Intent(NOTIFY_NEXT);
        Intent play = new Intent(NOTIFY_PLAY);

        PendingIntent pPrevious = PendingIntent.getBroadcast(context, 0, previous, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.imagebuttonpreviewnotification, pPrevious);

        PendingIntent pDelete = PendingIntent.getBroadcast(context, 0, delete, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.buttonClose, pDelete);

        PendingIntent pPause = PendingIntent.getBroadcast(context, 0, pause, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.imagebuttonplaynotification, pPause);

        PendingIntent pPlay = PendingIntent.getBroadcast(context, 0, play, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.imagebuttonplaynotification, pPlay);

        PendingIntent pNext = PendingIntent.getBroadcast(context, 0, next, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.imagebuttonnextnotification, pNext);
    }
}
