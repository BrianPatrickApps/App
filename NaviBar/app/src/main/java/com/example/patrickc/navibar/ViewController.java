package com.example.patrickc.navibar;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by patrickc on 12-Jul-17.
 */

public class ViewController {

    private RelativeLayout nurse;
    private RelativeLayout inputScreen;
    private ImageView rainOverlay;
    private ImageView weatherOverlay;

    protected ViewController(RelativeLayout nurse,RelativeLayout inputScreen, ImageView rainOverlay, ImageView weatherOverlay){
        this.nurse = nurse;
        this.inputScreen = inputScreen;
        this.rainOverlay = rainOverlay;
        this.weatherOverlay = weatherOverlay;
    }

    protected void viewNurses() {
        nurse.setVisibility(View.VISIBLE);
        inputScreen.setVisibility(View.GONE);
    }

    protected void viewInput(){
        inputScreen.setVisibility(View.VISIBLE);
        nurse.setVisibility(View.GONE);
    }

    protected void setRain(){
        rainOverlay.setVisibility(View.VISIBLE);
    }

    protected void stopRain(){
        rainOverlay.setVisibility(View.GONE);
    }

    protected void showSun(){
        weatherOverlay.setImageResource(R.drawable.mood_sun);
    }

    protected void showClouds(){
        //weatherOverlay.setImageResource();
    }

    protected void showOvercast(){
        //weatherOverlay.setImageResource();
    }

    protected void showRainMood(){
        //weatherOverlay.setImageResource();
    }

    protected void showThunder(){
        setRain();
    }
    protected void startUp(){
        weatherOverlay.setImageResource(0);
        stopRain();
    }


}
