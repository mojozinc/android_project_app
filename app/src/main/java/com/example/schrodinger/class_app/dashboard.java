package com.example.schrodinger.class_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
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


public class dashboard extends Activity {
    private LinearLayout screen;
    private ListView list;
    private Intent i;
    private Context context;
    private String username,password;
    private DbManager dbm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        init_views_and_widgets();

        SPLASH_INTRO();

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
                populate_list();
            }
        }, 1500);
    }

    private void populate_list(){
        dbm.open();
        final String[] users = dbm.fetch_username_list();
        dbm.close();

        ArrayList<ListModel> data_list=new ArrayList<ListModel>();

        for(int i = 0 ; i<users.length;i++)
            data_list.add(new ListModel(users[i], getDrawable(batman_logo)));

        list.setAdapter(new CustomAdapter(this,R.layout.list_row_layout,data_list));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView row = (TextView) view.findViewById(R.id.row_text);
                Toast.makeText(getApplicationContext(), "pos: "+position+"\nid: "+id,Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void init_views_and_widgets(){
        dbm = new DbManager(getApplicationContext());
        i = getIntent();
        username = i.getStringExtra("username");
        password = i.getStringExtra("password");
        dbm.open();
        dbm.insert_if_new(username, password);
        dbm.close();
        screen = (LinearLayout) findViewById(R.id.screen_dashboard);
        list = (ListView)findViewById(R.id.listView);
    }

}
