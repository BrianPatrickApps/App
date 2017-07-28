package com.example.patrickc.navibar;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Database implements Serializable{

    private DatabaseHelper dbHelper;
    transient private SQLiteDatabase database;
    Context context;
    Counter counter;


    public Database(Context context){
        this.context = context;
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        counter = new Counter();
    }


    protected void execSQL(String s) {
        database.execSQL(s);
    }

    protected ArrayList<String> collectAllUsers(){

        Cursor c = database.rawQuery("Select * from nurses;",null);
        ArrayList<String>theArray = new ArrayList<>();
        if(c.getCount() ==0){
            Toast.makeText(context, "Empty", Toast.LENGTH_SHORT).show();
        }
        while(c.moveToNext()){
            String result = "User ID: " +c.getString(0)+
                    "\t\t\tinput: " + c.getString(1)+
                    "\t\t\tMedian: " + c.getString(2)+
                    "\t\t\tDate: " + c.getString(3)+
                    "\t\t\tShift: " + c.getString(4)
                    ;
            theArray.add(result);
        }
        Log.d("BB","All nurses collected");
        c.close();
        return theArray;

    }

    //Gets the median
    protected double getAverage(double mood){
        Log.d("BB","Select * from nurses WHERE shift_id = '"+ getShiftNumber()+"' AND inputDate ='"+ getDay()+"';");
        Cursor c = database.rawQuery("Select * from nurses WHERE shift_id = '"+ getShiftNumber()+"' AND inputDate ='"+ getDay()+"';",null);
        ArrayList<Double> theArray = new ArrayList<>();
        if(c.getCount() ==0){
        }
        while(c.moveToNext()){
            Double result = c.getDouble(1);
            theArray.add(result);
        }
        theArray.add(mood);
        Collections.sort(theArray);
        double median;
        if (theArray.size() % 2 == 0) {
            median = (theArray.get(theArray.size()/2) + theArray.get(theArray.size()/2 - 1))/2;
        } else {
            median = theArray.get(theArray.size()/2);
        }
        c.close();
        return median;
    }
    //Adds Median to avgShift and avgRoom

    protected void addMedian(double median,String date,int shift){
        String query = "INSERT into avgShift(`shift_id`,`average`,`inputDate`)" +
                "VALUES('" + shift + "','"+ median +"','"+ date +"');";
        database.execSQL(query);
        String updateMedian = "UPDATE avgRoom set median = '"+ median +"',inputDate='"+ getDay()+"' WHERE key_id = '"+getShiftNumber()+"';";
        database.execSQL(updateMedian);
        Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show();
    }

    //Collects the median of the shift
    protected double getMedian(){
        ArrayList<Double> theArray = new ArrayList<>();
        Cursor c = database.rawQuery("Select * from avgRoom where key_id = '"+getShiftNumber()+"'AND inputDate ='"+ getDay()+"';",null);
        if(c.getCount() ==0){
            return 0.0;
        }
        else{
            while(c.moveToNext())
            {
                Double median = c.getDouble(1);
                theArray.add(median);
            }
        }
        Log.d("Median: ",String.valueOf(theArray.get(0)));
        c.close();
        return theArray.get(0);

    }

    //gets called when the broadcast reciever fires
    protected void updateShift(){
        String query = "UPDATE key set key_id = '"+(getShiftNumber()+1)+"' WHERE key_id ='"+getShiftNumber()+"';";
        execSQL(query);
        resetKey();
        Log.d("BB","Update Query: "+ query);
    }

    protected void setShift(int number){
        String query = "UPDATE key set key_id = '"+(number)+"' WHERE key_id ='"+getShiftNumber()+"';";
        resetKey();
        Log.d("BB","Update Query: "+ query);
        execSQL(query);
        Log.d("BB","Shift Number has been updated: "+ getShiftNumber());
    }

    protected int getShiftNumber(){
        //resetKey();
        Cursor c = database.rawQuery("Select * from key;",null);
        ArrayList<Integer>theArray = new ArrayList<>();
        if(c.getCount() ==0){
            Toast.makeText(context, "Empty", Toast.LENGTH_SHORT).show();
        }
        while(c.moveToNext()){
            int result = c.getInt(0);
            theArray.add(result);
        }
        c.close();
        return theArray.get(0);
    }

    private void resetKey(){
        Cursor c = database.rawQuery("Select * from key;",null);
        ArrayList<Integer>theArray = new ArrayList<>();
        if(c.getCount() ==0){
            Toast.makeText(context, "Empty", Toast.LENGTH_SHORT).show();
        }
        while(c.moveToNext()){
            int result = c.getInt(0);
            theArray.add(result);
        }
        int key = theArray.get(0);
        String query = "UPDATE key set key_id = '"+0+"' WHERE key_id ='"+getShiftNumber()+"';";
        if(key ==4
                ) {
            execSQL(query);
            Log.d("BB","Shift Number Resetted to: " + getShiftNumber());
        }
        c.close();
    }

    protected int getCountNumber(){
        //resetKey();
        Cursor c = database.rawQuery("Select * from counter;",null);
        ArrayList<Integer>theArray = new ArrayList<>();
        if(c.getCount() ==0){
            Toast.makeText(context, "Empty", Toast.LENGTH_SHORT).show();
        }
        while(c.moveToNext()){
            int result = c.getInt(0);
            theArray.add(result);
        }
        c.close();
        return theArray.get(0);
    }

    protected String getDay(){
        Cursor c = database.rawQuery("Select * from day;",null);
        ArrayList<String>theArray = new ArrayList<>();
        if(c.getCount() ==0){
            Toast.makeText(context, "Empty", Toast.LENGTH_SHORT).show();
        }
        while(c.moveToNext()){
            String result = c.getString(1);
            theArray.add(result);
        }
        c.close();
        return theArray.get(0);
    }

    protected void updateDate(String newDate){
        String query = "UPDATE day set inputDate = '"+newDate+"' WHERE key_id ='"+0+"';";
        Log.d("BB","Update Query: "+ query);
        resetKey();
        execSQL(query);

    }



    protected void saveDB() {
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        File exportDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"WorkWeather");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }
        Log.d("BB",exportDir.toString());
        File file = new File(exportDir, currentDateTimeString+ " " +getCountNumber()+ ".csv");
        Log.d("BB",file.toString());
        try {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor curCSV = db.rawQuery("SELECT * FROM nurses", null);
            csvWrite.writeNext(curCSV.getColumnNames());
            while (curCSV.moveToNext()) {
                //Which column you want to exprort
                String arrStr[] = {curCSV.getString(0), curCSV.getString(1), curCSV.getString(2), curCSV.getString(3), curCSV.getString(4)};
                csvWrite.writeNext(arrStr);
                Log.d("BB",arrStr[0]);
            }
            csvWrite.close();
            curCSV.close();
            MediaScannerConnection.scanFile(context, new String[] {exportDir.toString()}, null, null);
            String query2 = "UPDATE counter set key_id = '"+(getCountNumber()+1)+"' WHERE key_id ='"+getCountNumber()+"';";
            execSQL(query2);
            Log.d("BB","Update Query: "+ query2);
        } catch (Exception sqlEx) {
            Log.d("BB", sqlEx.getMessage()+ "Exception", sqlEx);
        }

    }
}

