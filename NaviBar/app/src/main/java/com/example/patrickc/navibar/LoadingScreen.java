package com.example.patrickc.navibar;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadingScreen extends AppCompatActivity {

    ProgressBar spinnerBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        Typeface typeface=Typeface.createFromAsset(getAssets(), "fonts/Futura Medium.ttf");
        TextView title = (TextView)findViewById(R.id.AppTitle);
        title.setTypeface(typeface);
        spinnerBar = (ProgressBar)findViewById(R.id.progressBar);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LoadingScreen.this,MainActivity.class));
                finish();
            }
        },2500);
    }
}
