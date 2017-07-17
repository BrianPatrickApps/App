package com.example.patrickc.navibar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Database db;
    Button stormy;
    Button rainy;
    Button overcast;
    Button cloudy;
    Button sunny;
    ImageView weatherOverlay;
    ImageView rainOverlay;
    ImageView inputOverlay;
    ButtonController control;
    ViewController viewController;
    private boolean sub = false;
    private int count = 0;
    ImageView nurse1;
    ImageView nurse2;
    ImageView nurse3;
    ImageView nurse4;
    ImageView nurse5;
    ImageView nurse6;
    ImageView nurse7;
    ImageView nurse8;
    ArrayList<ImageView> nurseArray;

    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        db = new Database(this);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView= (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        RelativeLayout rel = (RelativeLayout)findViewById(R.id.relLay);
        rel.setOnClickListener(tapScreen);

        //Initialize buttons for ButtonController class
        stormy = (Button)findViewById(R.id.Stormy);
        rainy = (Button)findViewById(R.id.Rain);
        overcast = (Button)findViewById(R.id.Overcast);
        cloudy = (Button)findViewById(R.id.Cloudy);
        sunny = (Button)findViewById(R.id.Sunny);
        weatherOverlay = (ImageView)findViewById(R.id.moodOverlay);
        inputOverlay = (ImageView)findViewById(R.id.inputWeather);
        rainOverlay = (ImageView)findViewById(R.id.rainOverlay);

        Glide.with(getApplicationContext()).load(R.drawable.rain_drops).into(rainOverlay);

        //rainOverlay.setVisibility(View.GONE);
        control = new ButtonController(stormy,rainy,overcast,cloudy,sunny,inputOverlay,this);
        //Makes buttons invisible
        control.setInvisible();
        RelativeLayout rel3 = (RelativeLayout)findViewById(R.id.inputScreen);
        RelativeLayout rel2 = (RelativeLayout)findViewById(R.id.Nurse);
        viewController = new ViewController(rel,rel2,rel3,rainOverlay,weatherOverlay);
        checkWeather();
        viewController.startUp();

        nurse1 = (ImageView)findViewById(R.id.nurse1);
        nurse2 = (ImageView)findViewById(R.id.nurse2);
        nurse3 = (ImageView)findViewById(R.id.nurse3);
        nurse4 = (ImageView)findViewById(R.id.nurse4);
        nurse5 = (ImageView)findViewById(R.id.nurse5);
        nurse6 = (ImageView)findViewById(R.id.nurse6);
        nurse7 = (ImageView)findViewById(R.id.nurse7);
        nurse8 = (ImageView)findViewById(R.id.nurse8);
        nurseArray.add(nurse1);
        nurseArray.add(nurse2);
        nurseArray.add(nurse3);
        nurseArray.add(nurse4);
        nurseArray.add(nurse5);
        nurseArray.add(nurse6);
        nurseArray.add(nurse7);
        nurseArray.add(nurse8);
    }

    //Empty OnClickListener for anything
    private View.OnClickListener press = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    //When back is pressed, not used
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    //When screen is tapped opens drawer
    private View.OnClickListener tapScreen = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.openDrawer(GravityCompat.START);
        }
    }  ;

    //Drawer Stuff
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_login) {
            selectItem(1);
        } else if (id == R.id.nav_fingerprint) {
            selectItem(2);
        } else if (id == R.id.nav_data) {
            selectItem(3);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //Which button is to be pressed. Add stuff if need be
    private void selectItem(int position) {

        switch(position) {
            case 1:
                loginID();
                control.setViewable();
                viewController.viewInput();

                break;
            case 2:
                //loginFinger();
                Toast.makeText(getApplicationContext(), "Finger Print Scanner unavailable", Toast.LENGTH_LONG).show();
                break;
            case 3:
                dataLogin();
                break;
            default:
        }
    }
    //Login alert
    private void loginID(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Login with ID");
        alert.setMessage("Please use your 6 digit code");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setFilters(new InputFilter[] { new InputFilter.LengthFilter(6)});
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                try {
                    int id = Integer.parseInt(input.getText().toString());
                    String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                    if(id > 99999 && id < 1000000){
                        control.setViewable();
                        String inputID = input.getText().toString();
                        control.getId(inputID);
                        Handler handler = new Handler();

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                viewController.viewNurses();
                                checkWeather();
                                inputOverlay.setImageResource(R.drawable.input_1);
                                showNurses();
                            }
                        },6000);

                    }
                    else
                        {
                        Toast.makeText(getApplicationContext(), "Must be 6 digits", Toast.LENGTH_SHORT).show();
                            loginID();

                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "\t\t\tSorry invalid input\nonly 6 digits are acceptable", Toast.LENGTH_LONG).show();
                    loginID();


                }
            }
        });
        //When they cancel
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                Toast.makeText(getApplicationContext(), "Back to the menu ", Toast.LENGTH_SHORT).show();
                control.setInvisible();
                viewController.viewNurses();
                checkWeather();
                inputOverlay.setImageResource(R.drawable.input_1);

            }
        });

        alert.show();
    }

    //FingerPrint method no scanner given yet
    private void loginFinger(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Login with Fingerprint");
        alert.setMessage("Finger");

        //an EditText view to get user input
        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getApplicationContext(), "Finger Print Scanner unavailable", Toast.LENGTH_LONG).show();
                Intent x =new Intent(MainActivity.this,WeatherRoom.class);
                startActivity(x);
                finish();
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getApplicationContext(), "Back to the menu", Toast.LENGTH_SHORT).show();
            }
        });

        alert.show();
    }

    private void dataLogin(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Admin Login");
        alert.setMessage("Please Enter Password");

        //an EditText view to get user input
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setFilters(new InputFilter[] { new InputFilter.LengthFilter(6)});
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                int id = Integer.parseInt(input.getText().toString());
                if(id == 000000){
                Intent i = new Intent(MainActivity.this,DataScreen.class);
                startActivity(i);
                }
                else
                Toast.makeText(getApplicationContext(), "Sorry wrong password", Toast.LENGTH_LONG).show();
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getApplicationContext(), "Back to the menu", Toast.LENGTH_SHORT).show();
            }
        });

        alert.show();
    }


    private void checkWeather(){

        Glide.with(getApplicationContext()).load(R.drawable.rain_drops).into(rainOverlay);
        Double x = db.getMedian();
        int a = x.intValue();
        if(x==0){
            viewController.startUp();
        }
        else if(x == 1)
        {
            viewController.showThunder();
        }
        else if(x ==2){
            viewController.showRainMood();
        }
        else if(x ==3)
        {
            viewController.stopRain();
            viewController.showOvercast();
        }
        else if(x ==4){
            viewController.stopRain();
            viewController.showClouds();
        }
        else if(x==5) {
            viewController.stopRain();
            viewController.showSun();
        }

    }

    public void showNurses(){
        //Available nurse image chosen by counter.
        ImageView iv = nurseArray.get(count);
        Random r = new Random();
        int Low = 0;
        int High = 9;
        int Result = r.nextInt(High-Low) + Low;
        if (!sub) { //boolean check to see if mx number of nurses already visible
            iv.setVisibility(View.VISIBLE);
            nurseTimeout(nurseArray.get(count));//calls the nurse timeout method with the imageview of the nurse that just went visible.
            count++;
            if (count == nurseArray.size()) {
                count--;
                sub = true;
            }
        }
        else{ //starts setting nurses invisible manually if the max is visible.
            iv.setVisibility(View.GONE);
            count--;
            if(count == -1){
                count++;
                sub = false;
            }
        }
    }

    public void nurseTimeout(View v){
        final ImageView iv = (ImageView) v;
        new CountDownTimer(3000, 3000) { //timer set to be 3 seconds long and tick once every 3 seconds. Will be 2 hours each for final app

            public void onTick(long millisUntilFinished) { //nothing needed here as we only disable an image after the full time
            }

            public void onFinish() {
                iv.setVisibility(View.GONE);
            }

        }.start();
    }


    //------------------------------------------------------------------------------------------

    public void databaseReset(Database db){
        Intent intent = new Intent(this, MyReceiver.class);
        intent.putExtra("db", db);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);


        //set time for 7:30 reset
        Date dat  = new Date();//initializes to now
        Calendar cal_alarm = Calendar.getInstance();
        Calendar cal_now = Calendar.getInstance();
        cal_now.setTime(dat);
        cal_alarm.setTime(dat);
        cal_alarm.set(Calendar.HOUR_OF_DAY,7);//set the alarm time
        cal_alarm.set(Calendar.MINUTE, 30);
        cal_alarm.set(Calendar.SECOND,0);
        if(cal_alarm.before(cal_now)){//if its in the past increment
            cal_alarm.add(Calendar.DATE,1);
        }
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal_alarm.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

        //set time for 16:00 reset
        Date dat2  = new Date();//initializes to now
        Calendar cal_alarm2 = Calendar.getInstance();
        Calendar cal_now2 = Calendar.getInstance();
        cal_now2.setTime(dat2);
        cal_alarm2.setTime(dat2);
        cal_alarm2.set(Calendar.HOUR_OF_DAY,16);//set the alarm time
        cal_alarm2.set(Calendar.MINUTE, 00);
        cal_alarm2.set(Calendar.SECOND,0);
        if(cal_alarm2.before(cal_now2)){//if its in the past increment
            cal_alarm2.add(Calendar.DATE,1);
        }
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal_alarm2.getTimeInMillis(), AlarmManager.INTERVAL_DAY,pendingIntent);

        //set time for 22:00 reset
        Date dat3  = new Date();//initializes to now
        Calendar cal_alarm3 = Calendar.getInstance();
        Calendar cal_now3 = Calendar.getInstance();
        cal_now3.setTime(dat3);
        cal_alarm3.setTime(dat3);
        cal_alarm3.set(Calendar.HOUR_OF_DAY,16);//set the alarm time
        cal_alarm3.set(Calendar.MINUTE, 00);
        cal_alarm3.set(Calendar.SECOND,0);
        if(cal_alarm3.before(cal_now3)){//if its in the past increment
            cal_alarm3.add(Calendar.DATE,1);
        }
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal_alarm3.getTimeInMillis(), AlarmManager.INTERVAL_DAY,pendingIntent);
    }
}




