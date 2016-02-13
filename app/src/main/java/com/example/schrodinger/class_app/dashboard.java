package com.example.schrodinger.class_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
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
    private CustomAdapter movielist_adapter;
    private DbManager dbm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setTitle("Movie Mania");
        init_views_and_widgets();

        insert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(), Insert_movie.class), 2);
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int movie_id = Integer.parseInt(((TextView) view.findViewById(R.id.movie_id)).getText().toString());

                Intent i = new Intent(getApplicationContext(), deleteActivity.class);
                i.putExtra("movie_id", movie_id);
                startActivityForResult(i, 2);

                return true;
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int movie_id = Integer.parseInt(((TextView) view.findViewById(R.id.movie_id)).getText().toString());
                Intent i=new Intent(getApplicationContext(),ShowMovieDetailActivity.class);
                i.putExtra("movie_id",movie_id);
                startActivityForResult(i,2);
            }
        });
    }

    protected void onResume(){
        super.onResume();
        populate_list();
        Log.d("ActivityCycle", "dashboard onResume");

    }

    protected void onStart(){
        super.onStart();
        populate_list();
        Log.d("ActivityCycle","dashboard onStart");
    }

    private void populate_list(){


        dbm.open();
        movielist_adapter=new CustomAdapter(getApplicationContext(),R.layout.movie_item_layout,dbm.fetch_movie_list_array());
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
