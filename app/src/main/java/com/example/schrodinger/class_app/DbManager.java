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
    public void insert(String username,String password){
        ContentValues values=new ContentValues();
        values.put(DataBaseHelper.KEY_USERNAME,username);
        values.put(DataBaseHelper.KEY_PASSWORD, password);
        db.insert(DataBaseHelper.TABLE_NAME, null, values);
    }
    public Cursor fetch_username_suggestions(String partial_string){
        Cursor c=db.rawQuery("SELECT "+DataBaseHelper.KEY_USERNAME+","+DataBaseHelper.KEY_ID+" FROM "+DataBaseHelper.TABLE_NAME+" WHERE "+DataBaseHelper.KEY_USERNAME+" LIKE '%"+partial_string+"%';",null);
        Log.d("DB QUERY", c.getColumnCount() + " " + c.getCount());
        c.moveToFirst();
        return c;
    }
    public String [] fetch_username_list(){
        Cursor c = db.rawQuery("SELECT "+DataBaseHelper.KEY_USERNAME+" FROM "+DataBaseHelper.TABLE_NAME+";",null);
        c.moveToFirst();
        String [] list = new String[c.getCount()];
        for(int i = 0;i<list.length;i++){
            list[i] = c.getString(1);
            c.moveToNext();
        }



        return list;
    }
    public void insert_if_new(String username,String password){
        Cursor c=db.rawQuery("select username from users where username='"+username+"';",null);
        if(c.getCount()==0)
            insert(username,password);
    }

    public Cursor fetch(long id){
        return db.rawQuery("SELECT * FROM "+DataBaseHelper.TABLE_NAME+" WHERE "+DataBaseHelper.KEY_ID+"=?"+id+";",null);
    }

    public void delete(long id){
        db.delete(DataBaseHelper.TABLE_NAME,DataBaseHelper.KEY_ID+"="+id,null);
        row_count--;
    }
}
