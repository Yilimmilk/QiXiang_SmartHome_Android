package com.example.demoB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    /**
     * 房间
     */
    public static String room_table = "room_table";
    public static String temp_table = "temp_table";
    
    public DatabaseHelper(Context context) {
        super( context, "room.db", null, 1 );
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    	
        db.execSQL( "create table " + room_table + "(" + "roomName text UNIQUE, " + "checkIn integer" + ")" );
        
        db.execSQL( "create table " +temp_table + "(" + "value text " + ")" );
     /*   
        db.execSQL(" INSERT INTO "+room_table+" VALUES ('8101', '0' )");
        db.execSQL(" INSERT INTO "+room_table+" VALUES ('8102', '0' )");
        db.execSQL(" INSERT INTO "+room_table+" VALUES ('8103', '0' )");
        db.execSQL(" INSERT INTO "+room_table+" VALUES ('8104', '0' )");
        
        db.execSQL(" INSERT INTO "+room_table+" VALUES ('8201', '0' )");
        db.execSQL(" INSERT INTO "+room_table+" VALUES ('8201', '0' )");
        db.execSQL(" INSERT INTO "+room_table+" VALUES ('8203', '0' )");
        db.execSQL(" INSERT INTO "+room_table+" VALUES ('8204', '0' )");
        
        db.execSQL(" INSERT INTO "+room_table+" VALUES ('8301', '0' )");
        db.execSQL(" INSERT INTO "+room_table+" VALUES ('8301', '0' )");
        db.execSQL(" INSERT INTO "+room_table+" VALUES ('8303', '0' )");
        db.execSQL(" INSERT INTO "+room_table+" VALUES ('8304', '0' )");
        
        db.execSQL(" INSERT INTO "+room_table+" VALUES ('8401', '0' )");
        db.execSQL(" INSERT INTO "+room_table+" VALUES ('8401', '0' )");
        db.execSQL(" INSERT INTO "+room_table+" VALUES ('8403', '0' )");
        db.execSQL(" INSERT INTO "+room_table+" VALUES ('8404', '0' )");*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}