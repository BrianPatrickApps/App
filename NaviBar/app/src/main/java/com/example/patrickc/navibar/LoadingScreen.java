package com.example.patrickc.navibar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadingScreen extends AppCompatActivity {
    private static final String TAG = "BB";
    ProgressBar spinnerBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Typeface typeface=Typeface.createFromAsset(getAssets(), "fonts/Futura Medium.ttf");
        TextView title = (TextView)findViewById(R.id.AppTitle);
        title.setTypeface(typeface);
        spinnerBar = (ProgressBar)findViewById(R.id.progressBar);
        Handler handler = new Handler();
        ActivityCompat.requestPermissions(LoadingScreen.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                1);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LoadingScreen.this,MainActivity.class));
                finish();
            }
        },2500);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
