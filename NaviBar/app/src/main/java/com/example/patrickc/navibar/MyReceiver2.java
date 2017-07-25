package com.example.patrickc.navibar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by patrickc on 18-Jul-17.
 */

public class MyReceiver2 extends BroadcastReceiver {
    private static final String TAG = "BB";
    public void onReceive(Context context, Intent in) {
        Database db = new Database(context);
        db.setShift(2);
        db.saveDB();
        Log.d(TAG,"Shift number gone up "+  +db.getShiftNumber()+"Receiver 2 has gone off");
        Intent i = new Intent();
        i.setClass(context, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.getApplicationContext().startActivity((i));
    }
}
