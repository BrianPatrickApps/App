package com.example.patrickc.navibar;
/**
 * Created by Windows 10 on 03/07/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;
import android.util.Log;

import java.io.Serializable;
import java.io.SerializablePermission;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Hospital Data";

    private static final int DATABASE_VERSION = 2;

    // Database creation sql statement
    private  String DATABASE_CREATE_USERS = ("CREATE TABLE IF NOT EXISTS users(id_Number INT, id_Input INT, median INT, time datetime);");
   // private static final String DATABASE_id = ("CREATE TABLE IF NOT EXISTS a(id INT);");
    private String DATABASE_NURSES = ("CREATE TABLE IF NOT EXISTS nurses(id INT,input INT,average DOUBLE,date String,shift_id INT);");
    //shift_id is set Shift 1 7.30 - 16.00 Shift 2 16.00-22.30 Shift 3 22.30-7.30
    //Update table at end of each shift from avgRoom
    //This one shows the median of the room throughout the day during the shift and what time of the day
    private String DATABASE_AVG = ("CREATE TABLE IF NOT EXISTS avgShift(shift_id INT,average DOUBLE,date String);");
    //Set to 0 after starting a new shift
    //the last median of the shift
    private String DATABASE_ROOM_AVG = ("CREATE TABLE IF NOT EXISTS avgRoom(key_id INT,median DOUBLE);");




    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_USERS);
        //database.execSQL(DATABASE_id);
        database.execSQL(DATABASE_NURSES);
        database.execSQL(DATABASE_AVG);
        database.execSQL(DATABASE_ROOM_AVG);


        database.execSQL("INSERT INTO avgRoom VALUES('0','0');");
        database.execSQL("INSERT INTO avgRoom VALUES('1','0');");
        database.execSQL("INSERT INTO avgRoom VALUES('2','0');");
    }

    /* Method is called during an upgrade of the database, */
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        Log.w(DatabaseHelper.class.getName(),
                "Upgrading database from " + oldVersion +" to "+newVersion);
        database.execSQL("DROP TABLE IF EXISTS users");
        onCreate(database);
    }
}