package com.example.schrodinger.class_app;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import com.example.schrodinger.class_app.dashboard;

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
public class CustomAdapter extends ArrayAdapter<ListModel> {
    private int layoutRsc;
    public CustomAdapter(Context context,int layout,ArrayList<ListModel> data){
        super(context,layout,data);
        layoutRsc=layout;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        View view = convertView;
        if(view==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(layoutRsc,null);
        }
        ListModel elem=getItem(position);
        if(elem!=null){
            ImageView img = (ImageView)view.findViewById(R.id.list_image);
            TextView text = (TextView)view.findViewById(R.id.row_text);
            if(img!=null)
                img.setBackground(elem.img);
            if(text!=null)
               text.setText(elem.text);
        }
        return view;
    }
}

