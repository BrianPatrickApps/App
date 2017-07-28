package com.example.patrickc.navibar;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.text.DateFormat;
import java.util.Date;


class ButtonController {
    private Database db;
    private Button stormy;
    private Button rainy;
    private Button overcast;
    private Button cloudy;
    private Button sunny;
    private ImageView weatherOverlay;
    @SuppressWarnings("unused")
    private Context context;
    private String id;
    private Double mood;


    ButtonController(Button stormy, Button rainy, Button overcast, Button cloudy, Button sunny, ImageView weatherOverlay, Context context) {

        this.stormy = stormy;
        this.rainy = rainy;
        this.overcast = overcast;
        this.cloudy = cloudy;
        this.sunny = sunny;
        this.weatherOverlay = weatherOverlay;
        this.context = context;
        db = new Database(context);
        stormy.setOnClickListener(stormyClicked);
        rainy.setOnClickListener(rainyClicked);
        overcast.setOnClickListener(overcastClicked);
        cloudy.setOnClickListener(cloudyClicked);
        sunny.setOnClickListener(sunnyClicked);

    }
    //sets all buttons visible
    void setViewable() {
        stormy.setVisibility(View.VISIBLE);
        rainy.setVisibility(View.VISIBLE);
        overcast.setVisibility(View.VISIBLE);
        cloudy.setVisibility(View.VISIBLE);
        sunny.setVisibility(View.VISIBLE);
    }
    //sets all buttons invisible
    void setInvisible() {
        stormy.setVisibility(View.GONE);
        rainy.setVisibility(View.GONE);
        overcast.setVisibility(View.GONE);
        cloudy.setVisibility(View.GONE);
        sunny.setVisibility(View.GONE);
    }

    private View.OnClickListener stormyClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mood = 1.0;

            weatherOverlay.setImageResource(R.drawable.input_thunderstorm);
            select();
        }
    };
    private View.OnClickListener rainyClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mood = 2.0;
            weatherOverlay.setImageResource(R.drawable.input2_rainy);

            select();
        }
    };
    private View.OnClickListener overcastClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mood = 3.0;
            weatherOverlay.setImageResource(R.drawable.input3_clouded);

            select();
        }
    };
    private View.OnClickListener cloudyClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mood = 4.0;
            weatherOverlay.setImageResource(R.drawable.input4_half_clouded);

            select();
        }
    };
    private View.OnClickListener sunnyClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mood = 5.0;
            weatherOverlay.setImageResource(R.drawable.input5_sunny);

            select();
        }
    };
        private void select() {
            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
            Double avg = db.getAverage(mood);
            String query = "INSERT into nurses(`id`,`input`,`median`,`date`,`shift_id`,`inputDate`)" +
                    "VALUES('" + id + "','"+ mood +"','"+ avg +"','"+ currentDateTimeString +"','"+db.getShiftNumber()+"','"+ db.getDay() +"');";
            db.addMedian(avg,currentDateTimeString,db.getShiftNumber());
            Log.d("BB","Adding: "+query);
            db.execSQL(query);

            setInvisible();
        }

    void getId(String id) {
        this.id = id;
    }

}
