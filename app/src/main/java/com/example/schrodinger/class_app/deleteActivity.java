package com.example.schrodinger.class_app;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;

public class deleteActivity extends Activity {
    DbManager dbm;
    Button delete_butt;
    int movie_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        delete_butt=(Button)findViewById(R.id.delete_movie_button);
        dbm=new DbManager(getApplicationContext());
        Intent i=getIntent();
        movie_id=i.getIntExtra("movie_id",0);

        delete_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbm.open();
                dbm.delete_movie(movie_id);
                dbm.close();
                finish();
            }
        });

    }
}
