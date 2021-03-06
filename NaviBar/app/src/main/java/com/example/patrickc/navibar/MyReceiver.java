package com.example.patrickc.navibar;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.util.Log;


import java.io.Serializable;

public class MyReceiver extends BroadcastReceiver implements Serializable {
        private static final String TAG = "BB";
        @Override
        public void onReceive(Context context, Intent in) {
            Database db = new Database(context);
            db.setShift(0);
            db.saveDB();
            Log.d(TAG,"Shift number gone up "+  +db.getShiftNumber()+"Receiver 1 has gone off");
            Intent i = new Intent();
            i.setClass(context, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.getApplicationContext().startActivity((i));
        }
}
