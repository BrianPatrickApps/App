package com.example.patrickc.navibar;

/**
 * Created by Windows 10 on 03/07/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Database implements Serializable{

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    Context context;

    public final static String USERS_TABLE="users"; //table names

    public final static String USER_NAME="username";
    public final static String PASSWORD="password";

    public Database(Context context){
        this.context = context;
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public long insertQuery(String id, String name){
        ContentValues values = new ContentValues();
        values.put(USER_NAME, id);
        values.put(PASSWORD, name);
        return database.insert(USERS_TABLE, null, values);
    }

    public Cursor runQuery(String query, String[] args) {
        Cursor mCursor = database.rawQuery(query, args);
        if (mCursor.moveToFirst()) {
            mCursor.moveToFirst();
        }
        return mCursor; // returns array of data
    }

    public void execSQL(String s) {
        database.execSQL(s);
    }

    protected ArrayList<String> collectUsers(){
        Cursor c = database.rawQuery("Select * from nurses;",null);
        ArrayList<String>theArray = new ArrayList<>();
        if(c.getCount() ==0){
            Toast.makeText(context, "Empty", Toast.LENGTH_SHORT).show();
        }
        while(c.moveToNext()){
            String result = "User ID: " +c.getString(0)+
                     "\ninput: " + c.getString(1)+
                     "\nMedian: " + c.getString(2)+
                     "\nDate: " + c.getString(3)
                    ;
            theArray.add(result);
        }
        return theArray;
    }
    //Gets the median
    protected double getAverage(double mood){
        Cursor c = database.rawQuery("Select * from nurses;",null);
        ArrayList<Double> theArray = new ArrayList<>();
        if(c.getCount() ==0){
        }
        while(c.moveToNext()){
            Double result = c.getDouble(1);
            theArray.add(result);
        }
        theArray.add(mood);
        Collections.sort(theArray);
        int middle = 2;
        double median =0;
            if (theArray.size() % 2 == 0) {
                median = (theArray.get(theArray.size()/2) + theArray.get(theArray.size()/2 - 1))/2;
            } else {
                median = theArray.get(theArray.size()/2);
            }
        return median;
    }
    //Adds Median to avgShift and avgRoom
    protected void addMedian(double median,String date,int shift){
        String query = "INSERT into avgShift(`shift_id`,`average`,`date`)" +
                "VALUES('" + shift + "','"+ median +"','"+ date +"');";
        database.execSQL(query);
        String updateMedian = "UPDATE avgRoom set median = '"+ median +"' WHERE key_id = '1';";
        database.execSQL(updateMedian);
        Toast.makeText(context, "Median Updated Thank you", Toast.LENGTH_SHORT).show();
    }
    //Collects the median of the shift
    protected double getMedian(){
        ArrayList<Double> theArray = new ArrayList<>();
        Cursor c = database.rawQuery("Select * from avgRoom where key_id = '1';",null);
        if(c.getCount() ==0){
        }
        else{
            while(c.moveToNext())
            {
                Double median = c.getDouble(1);
                theArray.add(median);
            }
        }

        return theArray.get(0);
    }

    //gets called when the broadcast reciever fires
    protected void reset(){
        database.execSQL("DELETE * FROM avgShift;");
        database.execSQL("DELETE * FROM avgRoom;");
    }


}

