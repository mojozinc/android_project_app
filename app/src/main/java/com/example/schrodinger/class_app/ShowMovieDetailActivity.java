package com.example.schrodinger.class_app;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class ShowMovieDetailActivity extends Activity {
    private RatingBar rate;
    private TextView name,desc;
    private DbManager dbm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movie_detail);
        dbm=new DbManager(getApplicationContext());

        int movie_id=getIntent().getIntExtra("movie_id",0);
        init();
        populate_views(movie_id);

    }
    private void populate_views(int id){
        dbm.open();
        movie_object m=dbm.fetch_movie(id);
        dbm.close();
        name.setText(m.name);
        desc.setText(m.description);
        rate.setRating(m.rating);
    }

    private void init(){

        name=(TextView)findViewById(R.id.detail_name);
        desc=(TextView)findViewById(R.id.detail_description);
        rate=(RatingBar)findViewById(R.id.detail_rating);

        Drawable progress = rate.getProgressDrawable();
        DrawableCompat.setTint(progress, ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));

    }

}
