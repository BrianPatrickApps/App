package com.example.patrickc.navibar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Windows 10 on 18/07/2017.
 */

public class MyReceiver4 extends BroadcastReceiver{
    private static final String TAG = "BB";
    @Override
    public void onReceive(Context context, Intent in) {
        Database db = new Database(context);
        db.updateShift();
        Log.d(TAG,"Shift number gone up "+  +db.getShiftNumber()+"Receiver 4 has gone off");
        Intent i = new Intent();
        i.setClass(context, LoadingScreen.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.getApplicationContext().startActivity((i));
    }
}
