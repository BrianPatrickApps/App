package com.example.patrickc.navibar;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import static android.app.PendingIntent.FLAG_ONE_SHOT;

/**
 * Created by Brian on 17/07/2017.
 */

public class MyReceiver extends BroadcastReceiver {

        public void onReceive(Context context, Intent in) {
            Database db = (Database) in.getSerializableExtra("db");
            db.reset();
        }
}
