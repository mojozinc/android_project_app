package com.example.schrodinger.class_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.CoordinatorLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static com.example.schrodinger.class_app.R.drawable.batman_logo;
import com.example.schrodinger.class_app.CustomAdapter;
import com.example.schrodinger.class_app.ListModel;


public class dashboard extends AppCompatActivity {
    private CoordinatorLayout screen;
    private ListView list;
    private FloatingActionButton insert_button;
    private Intent i;
    private Context context;
    private String username,password;
    private SimpleCursorAdapter movielist_adapter;
    private DbManager dbm;
    final String [] from = new String[]{DataBaseHelper.MOVIE_ID,DataBaseHelper.MOVIE_NAME,DataBaseHelper.MOVIE_DESCRIPTION,DataBaseHelper.MOVIE_RATING};
    final int [] to = new int[]{R.id.movie_id,R.id.movie_name,R.id.movie_description,R.id.movie_rating};
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setTitle("Movie Mania");
        init_views_and_widgets();

        //SPLASH_INTRO();

        insert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(), Insert_movie.class), CONTEXT_INCLUDE_CODE);
            }
        });

    }

    protected void onStart(){
        super.onStart();
        populate_list();

    }

    private void SPLASH_INTRO(){
        screen.setBackground(getDrawable(batman_logo));
        screen.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                screen.setBackground(null);
                screen.setSystemUiVisibility(0);
                insert_button.setVisibility(View.VISIBLE);
                populate_list();
            }
        }, 1500);
    }

    private void populate_list(){
        dbm.open();
        movielist_adapter=new SimpleCursorAdapter(getApplicationContext(),R.layout.movie_item_layout,dbm.fetch_movie_list_cursor(),from,to,0);
        dbm.close();
        list.setAdapter(movielist_adapter);

    }

    private void init_views_and_widgets(){
        dbm = new DbManager(getApplicationContext());
        i = getIntent();
        username = i.getStringExtra("username");
        password = i.getStringExtra("password");
        insert_button=(FloatingActionButton)findViewById(R.id.insert_data);
        screen = (CoordinatorLayout) findViewById(R.id.screen_dashboard);
        list = (ListView)findViewById(R.id.listView);
    }

}
