package com.example.patrickc.navibar;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class LoadingScreen extends AppCompatActivity {

    ProgressBar spinnerBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

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
