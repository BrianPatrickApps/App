package com.example.patrickc.navibar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by patrickc on 18-Jul-17.
 */

public class MyReceiver3 extends BroadcastReceiver {
    private static final String TAG = "BB";
    public void onReceive(Context context, Intent in) {
        //Database db = (Database) in.getSerializableExtra("db");
        Database db = new Database(context);
        db.reset();
        Log.d(TAG,"Loaded");
        // Toast.makeText(context, "Empty", Toast.LENGTH_SHORT).show();
        // db.execSQL("INSERT into nurses(`id`,`input`,`average`,`date`)" +
        //       "VALUES('" + "1" + "','"+ "1" +"','"+ "1" +"','"+ "1" +"');");
        MainActivity.shiftNumber = 2;
        Intent i = context.getPackageManager().
                getLaunchIntentForPackage(context.getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }
}
