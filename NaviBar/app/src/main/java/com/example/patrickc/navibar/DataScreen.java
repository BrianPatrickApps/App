package com.example.patrickc.navibar;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.data;

public class DataScreen extends AppCompatActivity {

    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        db = new Database(this);
        ListView list = (ListView) findViewById(R.id.dataStream);
        TextView t = (TextView) findViewById(R.id.Data_Title);
        ArrayList<String> theArray = db.collectUsers();
        //ListAdapter listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, theArray);
        //ListAdapter listAdapter = new CustomAdapter(this, android.R.layout.simple_list_item_1, theArray,"font/Futura Medium.ttf");
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Futura Medium.ttf");
        ListAdapter listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,theArray){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/comicSans.ttf");
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTypeface(typeface);
                return view;
            }
        };

        list.setAdapter(listAdapter);

        t.setTypeface(typeface);
    }


}