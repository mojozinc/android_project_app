package com.example.schrodinger.class_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

public class DbManager {
    private SQLiteDatabase db;
    private DataBaseHelper db_helper;
    private Context context;//To save the instance of the current activity the DataBase is activated
    private static int row_count=0;
    public DbManager(Context c){
        context=c;
    }
    public void open(){
        db_helper=new DataBaseHelper(context);
        db = db_helper.getReadableDatabase();
    }
    public void close(){
        db_helper.close();
    }
    public void insert_movie(movie_object m){
        ContentValues values=new ContentValues();
        values.put("name",m.name);
        values.put("description",m.description);
        values.put("rating",m.rating);
        db.insert(DataBaseHelper.TABLE_MOVIE, null, values);
    }
    public void delete_movie(int id){
        db.delete("movies", "_id=" + id, null);
    }
    public Cursor fetch_movie_list_cursor(){
        Cursor c = db.rawQuery("select * from movies;",null);
        c.moveToFirst();
        return c;
    }

    public ArrayList<movie_object> fetch_movie_list_array(){
        Cursor c=fetch_movie_list_cursor();
        ArrayList<movie_object> arrayList=new ArrayList<>(c.getCount());
        for(int i=0;i<c.getCount();i++){
            arrayList.add(new movie_object(c.getString(0),c.getString(1),c.getFloat(2),c.getInt(3)));
            c.moveToNext();
        }
        return arrayList;
    }
}

class movie_object{
    public String name;
    public String description;
    public float rating;
    int id;
    public movie_object(){}
    public movie_object(String n,String d,float r,int i){
        name=n;
        description=d;
        rating=r;
        id=i;
    }
}