package com.example.demog;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
  
    public static String data_table = "data_table";
    
    public DatabaseHelper(Context context) {
        super( context, "G.db", null, 1 );
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "create table " + data_table + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
        		"device text, "+
        		"time text, "+
                "number integer"+
                ")" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}