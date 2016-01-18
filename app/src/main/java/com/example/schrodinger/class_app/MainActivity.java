package com.example.schrodinger.class_app;

import java.lang.Math;
import java.util.Random;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.widget.*;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    EditText edit_username,edit_password;
    RelativeLayout main_screen;

    int[] color_array=new int[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        edit_username = (EditText) findViewById(R.id.editText);
        edit_password=(EditText)findViewById(R.id.editText2);
        main_screen = (RelativeLayout)findViewById(R.id.screen_main);
/*
        color_array[0]=getResources().getColor(R.color.color1, null);
        color_array[1]=getResources().getColor(R.color.color2, null);
        color_array[2]=getResources().getColor(R.color.color3,null);
        color_array[3]=getResources().getColor(R.color.color4,null);
        color_array[4]=getResources().getColor(R.color.color5,null);
*/

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
    public void login_action(View button_signup){
        boolean error=false;
        Intent dashboard_intent =new Intent(this,dashboard.class);
        String username = edit_username.getText().toString();
        String password = edit_password.getText().toString();

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

        dashboard_intent.putExtra("username",username);
        dashboard_intent.putExtra("password", password);

        startActivity(dashboard_intent);
    }
    public void cancel_action(View button_cancel){
       // main_screen.setBackgroundColor(color_array[((int) ((10 * Math.random()) % 5))]);

    }
}
