package com.example.patrickc.navibar;

import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WeatherRoom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_room);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Futura Medium.ttf");
        Database db = new Database(this);
        Double a = db.getMedian();
        int y = db.getShiftNumber();
        TextView i = (TextView)findViewById(R.id.idScreen);
        TextView x = (TextView)findViewById(R.id.shiftView);
        x.setTypeface(typeface);
        i.setTypeface(typeface);
        i.setText("Current Median: "+String.valueOf(a));
        x.setText("Current Shift: "+String.valueOf(y));


    }
}
