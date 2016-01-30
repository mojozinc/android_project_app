package com.example.schrodinger.class_app;

import java.lang.Math;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.widget.*;
import android.view.MenuItem;

import static com.example.schrodinger.class_app.R.drawable.batman_logo;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView edit_username;
    EditText edit_password;
    RelativeLayout main_screen;
    String username,password;
    private SimpleCursorAdapter autocomplete_adapter;
    private DbManager dbm;
    final String [] from = new String[]{DataBaseHelper.KEY_USERNAME,DataBaseHelper.KEY_ID};
    final int [] to = new int[]{R.id.autocomplete_suggestion_field,R.id._id};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbm = new DbManager(getApplicationContext());

        edit_username = (AutoCompleteTextView) findViewById(R.id.username_field);

        edit_username.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                String partial_username = edit_username.getText().toString();
                dbm.open();
                autocomplete_adapter=new SimpleCursorAdapter(getApplicationContext(), R.layout.autocomplete_layout, dbm.fetch_username_suggestions(partial_username), from, to,0);
                autocomplete_adapter.notifyDataSetChanged();
                edit_username.setAdapter(autocomplete_adapter);
                dbm.close();
                return true;
            }
        });
        edit_password=(EditText)findViewById(R.id.password_field);
        main_screen = (RelativeLayout)findViewById(R.id.screen_main);

    /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    */
    public void login_action(View button_signup){
        boolean error=false;

        username = edit_username.getText().toString();
        password = edit_password.getText().toString();

        if(username.isEmpty()){
            edit_username.setHint("** Cannot be empty **");
            edit_username.setHintTextColor(Color.RED);
            error=true;
        }

        if(password.length() < 8){
            edit_password.setHintTextColor(Color.RED);
            if (password.isEmpty())
                edit_password.setHint("** Cannot be empty **");
            else {
                edit_password.setText("");
                edit_password.setHint("Must be 8 Characters long");
                }
            error=true;
        }

        if(error) return;

        goto_dashboard();
    }
    public void cancel_action(View button_cancel){
       // main_screen.setBackgroundColor(color_array[((int) ((10 * Math.random()) % 5))]);

    }
    void goto_dashboard(){
        Intent dashboard_intent = new Intent(this,dashboard.class);

        dashboard_intent.putExtra("username",username);
        dashboard_intent.putExtra("password", password);

        startActivity(dashboard_intent);
    }
}
