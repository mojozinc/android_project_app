package com.example.schrodinger.class_app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class Insert_movie extends Activity {
    private Button add,cancel;
    private EditText name,desc;
    private RatingBar rating;
    private DbManager dbm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_movie);
        setTitle("Add Movie");

        init_views();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movie_object m = new movie_object();
                m.name = name.getText().toString();
                m.description = desc.getText().toString();
                m.rating = rating.getRating();
                dbm.open();
                dbm.insert_movie(m);
                dbm.close();
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void init_views(){
        dbm=new DbManager(getApplicationContext());
        add=(Button)findViewById(R.id.button_add_movie);
        cancel=(Button)findViewById(R.id.button_cancel);
        name=(EditText)findViewById(R.id.edit_movie_name);
        desc=(EditText)findViewById(R.id.edit_movie_desc);
        rating=(RatingBar)findViewById(R.id.ratingBar);

        Drawable progress = rating.getProgressDrawable();
        DrawableCompat.setTint(progress, ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));

    }
}
