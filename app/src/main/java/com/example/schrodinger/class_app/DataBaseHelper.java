package com.example.schrodinger.class_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="class_app.db";
    public static final String TABLE_MOVIE="movies";
    public static final String MOVIE_NAME="name";
    public static final String MOVIE_DESCRIPTION="description";
    public static final String MOVIE_RATING="rating";
    public static final String MOVIE_ID="_id";

    public static final String TABLE_PERSON="people";
    public static final String PERSON_NAME="name";
    public static final String PERSON_ABOUT="about";

    public static final String TABLE_MOVIE_PERSON_RELATION="worked_in";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MOVIETABLE=
                "create table movies (" +
                        "name varchar(100), " +
                        "description varchar(1500), " +
                        "rating float, " +
                        "_id integer primary key autoincrement);";

        String CREATE_RELATIONTABLE=
                "create table worked_in (" +
                        "_id integer primary key autoincrement," +
                        "movie_id int not null references movies(id)," +
                        "person_id not null references people(id)," +
                        "role varchar(255)," +
                        "role_description varchar(1500)" +
                        ");";

        String CREATE_PERSONTABLE=
                "create table people (" +
                        "_id integer primary key autoincrement," +
                        "name varchar(255)," +
                        "about varchar(1500)," +
                        "dob date);";

        db.execSQL(CREATE_MOVIETABLE);
        db.execSQL(CREATE_PERSONTABLE);
        db.execSQL(CREATE_RELATIONTABLE);
        Log.e("DBHelper","tables created");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists movies;");
        db.execSQL("drop table if exists worked_in;");
        db.execSQL("drop table if exists people;");
        onCreate(db);
        }
}