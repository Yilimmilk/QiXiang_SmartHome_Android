package com.example.login_sqlite;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Show extends Activity {
	ListView list;
	private MyDBHelper dbHelper;
	private ArrayList<String> arraylist =new ArrayList<String>();
	int i=0;
	private ArrayAdapter<String> arrayAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show);
		list=(ListView) findViewById(R.id.listView1);
		 dbHelper = new MyDBHelper(this,"UserStore.db",null,1);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "select * from userData ";
        Cursor cursor = db.rawQuery(sql, null);
        
       
        while (cursor.moveToNext()) { 
         
          
        	String name=cursor.getString(cursor.getColumnIndex("name"));
        	String word=cursor.getString(cursor.getColumnIndex("password"));
           // Toast.makeText(Show.this, name+word, Toast.LENGTH_SHORT).show();
           arraylist.add(name+"-------"+word);
           
        }
        //arraylist.add("1");
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arraylist);
        list.setAdapter(arrayAdapter);
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_show, menu);
		return true;
	}

}
