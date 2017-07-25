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
        Database db = new Database(context);
        db.setShift(3);
        db.saveDB();
        Log.d(TAG,"Shift number gone up "+  +db.getShiftNumber()+"Receiver 3 has gone off");
        Intent i = new Intent();
        i.setClass(context, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.getApplicationContext().startActivity((i));
    }

}
