package com.example.schrodinger.class_app;

import android.app.Activity;
import android.content.Context;
import android.content.*;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.BaseAdapter;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import com.example.schrodinger.class_app.dashboard;

import org.w3c.dom.Text;

/**
 * Created by schrodinger on 20/1/16.
 */

class ListModel {
    public  String text;
    public Drawable img;
    public ListModel(String text, Drawable img){
        this.text = text;
        this.img = img;
    }
}
public class CustomAdapter extends ArrayAdapter<movie_object> {
    private int layoutRsc;
    private Context context;
    public CustomAdapter(Context context,int layout,ArrayList<movie_object> data){
        super(context,layout,data);
        this.context=context;
        layoutRsc=layout;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
        View view = inflater.inflate(layoutRsc,parent,false);

        movie_object elem=getItem(position);
        if(elem!=null){

           // ImageView img = (ImageView)view.findViewById(R.id.movie_image);
            TextView id = (TextView)view.findViewById(R.id.movie_id);
            TextView name = (TextView)view.findViewById(R.id.movie_name);
            TextView desc = (TextView)view.findViewById(R.id.movie_description);
            RatingBar rating = (RatingBar)view.findViewById(R.id.movie_ratingBar);
            Drawable progress = rating.getProgressDrawable();
            DrawableCompat.setTint(progress, ContextCompat.getColor(view.getContext(),R.color.rating_starcolor));
            id.setText(""+elem.id);
            name.setText(elem.name);
            desc.setText(elem.description);
            rating.setRating(elem.rating);

        }
        return view;
    }
}

