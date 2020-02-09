package com.example.democ;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
  
    public static String Illumination_table = "illumination_table";
    
    public DatabaseHelper(Context context) {
        super( context, "e.db", null, 1 );
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "create table " + Illumination_table + "(" +
                "id INTEGER PRIMARY KEY Autoincrement, " +
                "tempe text,"+
                "illumination text"+
                ")" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}