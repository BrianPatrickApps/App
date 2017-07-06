package com.example.patrickc.navibar;

/**
 * Created by patrickc on 06-Jul-17.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.InputType;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class ButtonController {

    Button stormy;
    Button rainy;
    Button overcast;
    Button cloudy;
    Button sunny;
    Button submit;
    Context context;
    String id;

     protected ButtonController(Button stormy, Button rainy, Button overcast, Button cloudy, Button sunny, Button submit, Context context) {

         this.stormy = stormy;
         this.rainy = rainy;
         this.overcast = overcast;
         this.cloudy = cloudy;
         this.sunny = sunny;
         this.submit = submit;
         this.context = context;
         setViewable();
         stormy.setOnClickListener(stormyClicked);
     }

     protected void setViewable()
     {
         stormy.setVisibility(View.VISIBLE);
         rainy.setVisibility(View.VISIBLE);
         overcast.setVisibility(View.VISIBLE);
         cloudy.setVisibility(View.VISIBLE);
         sunny.setVisibility(View.VISIBLE);
     }
     
     protected void setInvisible(){
         stormy.setVisibility(View.INVISIBLE);
         rainy.setVisibility(View.INVISIBLE);
         overcast.setVisibility(View.INVISIBLE);
         cloudy.setVisibility(View.INVISIBLE);
         sunny.setVisibility(View.INVISIBLE);
     }

     protected View.OnClickListener stormyClicked = new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             int mood = 0;
             Toast.makeText(context, "ThunderStorm Selected", Toast.LENGTH_SHORT).show();
             moodSelected(mood);
         }
     };

     protected void getId(String id){
         this.id = id;
     }

     public String tester(){
         return "it worked";
     }






     protected void moodSelected(int selection){
        submit.setVisibility(View.VISIBLE);
         submit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

             }
         });
     }
}
