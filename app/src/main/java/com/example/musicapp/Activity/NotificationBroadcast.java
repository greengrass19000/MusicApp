package com.example.musicapp.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(NotificationGenerator.NOTIFY_PLAY)) {
            Toast.makeText(context, "PLAY", Toast.LENGTH_SHORT).show();
        } else if (intent.getAction().equals(NotificationGenerator.NOTIFY_PAUSE)) {
            Toast.makeText(context, "PAUSE", Toast.LENGTH_SHORT).show();
        } else if (intent.getAction().equals(NotificationGenerator.NOTIFY_PREVIOUS)) {
            Toast.makeText(context, "PREVIOUS", Toast.LENGTH_SHORT).show();
        } else if (intent.getAction().equals(NotificationGenerator.NOTIFY_DELETE)) {
            Toast.makeText(context, "DELETE", Toast.LENGTH_SHORT).show();
        } else if (intent.getAction().equals(NotificationGenerator.NOTIFY_NEXT)) {
            Toast.makeText(context, "NEXT", Toast.LENGTH_SHORT).show();
        }
    }
}
