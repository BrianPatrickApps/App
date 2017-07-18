package com.example.patrickc.navibar;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.Serializable;

import static android.app.PendingIntent.FLAG_ONE_SHOT;

/**
 * Created by Brian on 17/07/2017.
 */

public class MyReceiver extends BroadcastReceiver implements Serializable {
        private static final String TAG = "BB";
        @Override
        public void onReceive(Context context, Intent in) {
            //Database db = (Database) in.getSerializableExtra("db");
            Database db = new Database(context);
            db.reset();
            Log.d(TAG,"Loaded");
           // Toast.makeText(context, "Empty", Toast.LENGTH_SHORT).show();
           // db.execSQL("INSERT into nurses(`id`,`input`,`average`,`date`)" +
            //       "VALUES('" + "1" + "','"+ "1" +"','"+ "1" +"','"+ "1" +"');");

            Intent i = context.getPackageManager().
                    getLaunchIntentForPackage(context.getPackageName());
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);

        }
}
