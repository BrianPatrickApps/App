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

import java.util.ArrayList;

public class Database{

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
        Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
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
                     "\nAverage: " + c.getString(2)+
                     "\nDate: " + c.getString(3)
                    ;
            theArray.add(result);
        }
        return theArray;
    }
}

