package com.example.login_sqlite;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private MyDBHelper dbHelper;
    private EditText username;
    private EditText  userpassword;
    Button button1,show;
    Button button2;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        dbHelper = new MyDBHelper(this,"UserStore.db",null,1);
        button1=(Button)findViewById(R.id.button3);
        button2=(Button)findViewById(R.id.button2);
        username=(EditText) findViewById(R.id.editText3);
        userpassword=(EditText) findViewById(R.id.editText4);
        
        db = dbHelper.getWritableDatabase();
        String sql = "select * from userData";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToLast()) {
        	 username.setText(cursor.getString(0));
        	//Toast.makeText(MainActivity.this,cursor.getString(0), Toast.LENGTH_SHORT).show();
        	 userpassword.setText(cursor.getString(1));
        }
        
        
        
        show=(Button) findViewById(R.id.show);
        
        
        button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,MainActivity2.class);
		        startActivity(intent);
			}
		});
        show.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,Show.class);
		        startActivity(intent);
			}
		});
        button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
		        String userName=username.getText().toString();
		        String passWord=userpassword.getText().toString();
		        if (login(userName,passWord)) {
		            Toast.makeText(MainActivity.this, "µÇÂ½³É¹¦£¨ZY£¬111£©", Toast.LENGTH_SHORT).show();
		        }
		        else {
		            Toast.makeText(MainActivity.this, "µÇÂ½Ê§°Ü", Toast.LENGTH_SHORT).show();
		        }
			}
		});
    
    }
    	
    

    //ÑéÖ¤µÇÂ¼
    public boolean login(String username,String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "select * from userData where name=? and password=?";
        Cursor cursor = db.rawQuery(sql, new String[] {username, password});
        if (cursor.moveToFirst()) {
        	Toast.makeText(MainActivity.this, cursor.getString(0)+"+"+cursor.getString(1), Toast.LENGTH_SHORT).show();
            cursor.close();
            return true;
        }
        return false;
    }
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	String sql = "select * from userData";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToLast()) {
        	 username.setText(cursor.getString(0));
        	//Toast.makeText(MainActivity.this,cursor.getString(0), Toast.LENGTH_SHORT).show();
        	 userpassword.setText(cursor.getString(1));
        }
    }
    
}
