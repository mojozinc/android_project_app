package com.example.schrodinger.class_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="class_app.db";
    public static final String TABLE_NAME="users";
    public static final String KEY_USERNAME="username";
    public static final String KEY_PASSWORD="password";
    public static final String KEY_IMAGE="image";
    public static final String KEY_ID="_id";
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("+KEY_USERNAME+" TEXT,"+KEY_PASSWORD+" TEXT,"+KEY_IMAGE+" TEXT,"+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT);";
        Log.i("Helper", "create table query is:" + CREATE_TABLE);
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
        }
}