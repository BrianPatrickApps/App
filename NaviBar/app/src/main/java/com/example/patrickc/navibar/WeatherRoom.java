package com.example.patrickc.navibar;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WeatherRoom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_room);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Database db = new Database(this);
        Double a = db.getMedian();
        TextView i = (TextView)findViewById(R.id.idScreen);
        i.setText(String.valueOf(a));

    }
}
