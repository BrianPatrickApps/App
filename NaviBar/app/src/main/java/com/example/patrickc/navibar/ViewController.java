package com.example.patrickc.navibar;

import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.Serializable;


class ViewController implements Serializable{

    private RelativeLayout mainScreen;
    @SuppressWarnings("deprecation")
    private AbsoluteLayout nurse;
    private RelativeLayout inputScreen;
    private ImageView rainOverlay;
    private ImageView weatherOverlay;

    ViewController(RelativeLayout mainScreen, @SuppressWarnings("deprecation") AbsoluteLayout nurse, RelativeLayout inputScreen, ImageView rainOverlay, ImageView weatherOverlay){
        this.mainScreen = mainScreen;
        this.nurse = nurse;
        this.inputScreen = inputScreen;
        this.rainOverlay = rainOverlay;
        this.weatherOverlay = weatherOverlay;
    }

    void viewNurses() {
        nurse.setVisibility(View.VISIBLE);
        inputScreen.setVisibility(View.GONE);
    }

    void viewInput(){
        inputScreen.setVisibility(View.VISIBLE);
        nurse.setVisibility(View.GONE);
    }

    private void setRain(){
        rainOverlay.setVisibility(View.VISIBLE);
    }

    void stopRain(){
        rainOverlay.setVisibility(View.GONE);
    }

    void showSun(){
        //nurse.setBackgroundResource(R.drawable.room_sunny);
        weatherOverlay.setImageResource(R.drawable.weather_sun);
        mainScreen.setBackgroundResource(R.drawable.background5_sunny);
    }

    void showClouds(){
        weatherOverlay.setImageResource(R.drawable.weather_halfclouds);
        mainScreen.setBackgroundResource(R.drawable.background4_semi_clouded);
        //nurse.setBackgroundResource(R.drawable.room_semi);
    }

    void showOvercast(){
        weatherOverlay.setImageResource(R.drawable.weather_clouds);
        mainScreen.setBackgroundResource(R.drawable.background3_clouded);
        //nurse.setBackgroundResource(R.drawable.room_cloud);
    }

    void showRainMood(){
        weatherOverlay.setImageResource(R.drawable.weather_rain);
        setRain();
        mainScreen.setBackgroundResource(R.drawable.background2_rain);
        //nurse.setBackgroundResource(R.drawable.room_rain);
    }

    void showThunder(){
        weatherOverlay.setImageResource(R.drawable.weather_thunder);
        setRain();
        mainScreen.setBackgroundResource(R.drawable.background1_thunderstorm);
    }

    void startUp(){
        viewNurses();
        weatherOverlay.setImageResource(R.drawable.weather_start);
        mainScreen.setBackgroundResource(R.drawable.background3_clouded);
        stopRain();
    }


}
