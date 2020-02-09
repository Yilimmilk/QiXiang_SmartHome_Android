package com.example.demoe;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
  
    public static String illumination_table = "illumination_table";
    public static String run_table = "run_table";
    public static String device_table = "device_table";
    
    public DatabaseHelper(Context context) {
        super( context, "e.db", null, 1 );
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    	
        db.execSQL( "create table " + illumination_table + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "device text,"+
                "value text"+
                ")" );
        
        db.execSQL( "create table " +run_table + "(" +
        		  "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                  "device text,"+
                  "state text,"+
                  "time text"+
                  ")" );
        
        
        db.execSQL( "create table " +device_table + "(" +
      		  "deviceId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "device text,"+
                "state text"+
                ")" );
    
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}