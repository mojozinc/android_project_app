package com.example.schrodinger.class_app;

import java.lang.Math;
import java.util.Random;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.CoordinatorLayout;
import android.view.Menu;
import android.widget.*;
import android.view.MenuItem;

import static com.example.schrodinger.class_app.R.drawable.batman_logo;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView edit_username;
    EditText edit_password;
    RelativeLayout main_screen;
    String username,password;
    CoordinatorLayout root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Login");
  /*      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
*/
        edit_username = (AutoCompleteTextView) findViewById(R.id.username_field);
        edit_password = (EditText) findViewById(R.id.password_field);
        main_screen = (RelativeLayout) findViewById(R.id.screen_main);
        root = (CoordinatorLayout)findViewById(R.id.root_main);
    }

    protected void onStart(){
        super.onStart();

        edit_username.setVisibility(View.VISIBLE);
        edit_password.setVisibility(View.VISIBLE);
        findViewById(R.id.login_main).setVisibility(View.VISIBLE);
        findViewById(R.id.cancel_main).setVisibility(View.VISIBLE);



    }

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
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    void goto_dashboard(){

        edit_username.setVisibility(View.INVISIBLE);
        edit_password.setVisibility(View.INVISIBLE);
        findViewById(R.id.login_main).setVisibility(View.INVISIBLE);
        findViewById(R.id.cancel_main).setVisibility(View.INVISIBLE);
        root.setBackground(getDrawable(batman_logo));
        root.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                root.setBackgroundColor(0);
                root.setSystemUiVisibility(0);
                Intent dashboard_intent = new Intent(getApplicationContext(),dashboard.class);

                dashboard_intent.putExtra("username", username);
                dashboard_intent.putExtra("password", password);

                startActivity(dashboard_intent);

            }
        }, 1500);
    }

}
