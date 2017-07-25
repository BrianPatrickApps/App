package com.example.patrickc.navibar;

/**
 * Created by patrickc on 06-Jul-17.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ButtonController {
    Database db;
    Button stormy;
    Button rainy;
    Button overcast;
    Button cloudy;
    Button sunny;
    ImageView weatherOverlay;
    Context context;
    String id;
    Double mood;


    protected ButtonController(Button stormy, Button rainy, Button overcast, Button cloudy, Button sunny,ImageView weatherOverlay, Context context) {

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
    protected void setViewable() {
        stormy.setVisibility(View.VISIBLE);
        rainy.setVisibility(View.VISIBLE);
        overcast.setVisibility(View.VISIBLE);
        cloudy.setVisibility(View.VISIBLE);
        sunny.setVisibility(View.VISIBLE);
    }
    //sets all buttons invisible
    protected void setInvisible() {
        stormy.setVisibility(View.GONE);
        rainy.setVisibility(View.GONE);
        overcast.setVisibility(View.GONE);
        cloudy.setVisibility(View.GONE);
        sunny.setVisibility(View.GONE);
    }

    protected View.OnClickListener stormyClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mood = 1.0;

            weatherOverlay.setImageResource(R.drawable.input_thunderstorm);
            select();
        }
    };
    protected View.OnClickListener rainyClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mood = 2.0;
            weatherOverlay.setImageResource(R.drawable.input2_rainy);

            select();
        }
    };
    protected View.OnClickListener overcastClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mood = 3.0;
            weatherOverlay.setImageResource(R.drawable.input3_clouded);

            select();
        }
    };
    protected View.OnClickListener cloudyClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mood = 4.0;
            weatherOverlay.setImageResource(R.drawable.input4_half_clouded);

            select();
        }
    };
    protected View.OnClickListener sunnyClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mood = 5.0;
            weatherOverlay.setImageResource(R.drawable.input5_sunny);

            select();
        }
    };
        public void select() {
            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
            Double avg = db.getAverage(mood);
            String query = "INSERT into nurses(`id`,`input`,`median`,`date`,`shift_id`)" +
                    "VALUES('" + id + "','"+ mood +"','"+ avg +"','"+ currentDateTimeString +"','"+db.getShiftNumber() +"');";
            db.addMedian(avg,currentDateTimeString,db.getShiftNumber());
            Log.d("BB","Adding: "+query);
            db.execSQL(query);

            setInvisible();
        }

    //Not in use
    protected void getId(String id) {
        this.id = id;
    }

    public String tester() {
        String a = "it worked\n id: " + id.toString();
        return a;
    }
}
