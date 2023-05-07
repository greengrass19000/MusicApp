package com.example.musicapp.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class NotificationBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int actionMusic = intent.getIntExtra("ACTION", 0);
        context.sendBroadcast(new Intent("ACTION2").putExtra("actionMusic", actionMusic));
    }
}
