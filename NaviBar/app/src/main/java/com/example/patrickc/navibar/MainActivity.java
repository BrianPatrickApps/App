package com.example.patrickc.navibar;

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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Database db;
    Button j1;
    Button stormy;
    Button rainy;
    Button overcast;
    Button cloudy;
    Button sunny;
    Button submit;
    ButtonController control;

    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Database(this);
        j1 = (Button)findViewById(R.id.button);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView= (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        RelativeLayout rel = (RelativeLayout)findViewById(R.id.relLay);
        rel.setOnClickListener(tapScreen);
        j1.setOnClickListener(press);

        stormy = (Button)findViewById(R.id.Stormy);
        rainy = (Button)findViewById(R.id.Rain);
        overcast = (Button)findViewById(R.id.Overcast);
        cloudy = (Button)findViewById(R.id.Cloudy);
        sunny = (Button)findViewById(R.id.Sunny);
        submit = (Button)findViewById(R.id.submit);

         control = new ButtonController(stormy,rainy,overcast,cloudy,sunny,submit,this);

    }
    private View.OnClickListener press = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
            TextView t = (TextView)findViewById(R.id.textTest);
            Typeface typeface=Typeface.createFromAsset(getAssets(), "fonts/Futura Medium.ttf");
            t.setTypeface(typeface);
            t.setText("Button used");
        }
    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private View.OnClickListener tapScreen = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.openDrawer(GravityCompat.START);
            TextView t = (TextView)findViewById(R.id.textTest);
            Typeface typeface=Typeface.createFromAsset(getAssets(), "fonts/comicSans.ttf");
            t.setTypeface(typeface);
            t.setText("Screen used");
        }
    }  ;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
        // Handle navigation view item clicks here.
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

    private void selectItem(int position) {
        Intent i;
        switch(position) {
            case 1:
                loginID();
                break;
            case 2:
                loginFinger();
                break;
            case 3:
                i = new Intent(MainActivity.this,DataScreen.class);
                startActivity(i);
                break;
            default:
        }
    }

    private void loginID(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Login with ID");
        alert.setMessage("Please use your 6 digit code");

// Set an EditText view to get user input
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
                        String query = "INSERT into nurses(`id`,`input`,`date`)" +
                                "VALUES('" + id + "','"+ 0 +"','"+ currentDateTimeString +"');";
                        //db.execSQL(query);
                        j1.setText(control.tester());
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

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                Toast.makeText(getApplicationContext(), "Back to the menu ", Toast.LENGTH_SHORT).show();
            }
        });

        alert.show();
    }
    private void loginFinger(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Login with Fingerprint");
        alert.setMessage("Finger");

// Set an EditText view to get user input
        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getApplicationContext(), "Finger Print Scanner unavailable", Toast.LENGTH_LONG).show();
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getApplicationContext(), "Back to the menu", Toast.LENGTH_SHORT).show();
            }
        });

        alert.show();
    }


}
