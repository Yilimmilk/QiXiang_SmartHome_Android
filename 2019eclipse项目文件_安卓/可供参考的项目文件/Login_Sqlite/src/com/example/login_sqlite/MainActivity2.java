package com.example.login_sqlite;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends Activity {

	 private MyDBHelper dbHelper;
	 Button button;
	 Thread thread;
	SimpleDateFormat    formatter;
	Date    curDate;
	String    str   ;
	TextView t;
	
 
	
	
	
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	       
	        setContentView(R.layout.activity_main_activity2);
	        
	        t=(TextView) findViewById(R.id.textView1);
	        
	     
	        
	        
	        

	        dbHelper = new MyDBHelper(this,"UserStore.db",null,1);
	        button=(Button)findViewById(R.id.button3);
	        button.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					logon(v);
				}
			});
	    }

	    public void logon(View view){
	        //SQLiteDatabase db=dbHelper.getWritableDatabase();

	        EditText editText3=(EditText)findViewById(R.id.name);
	        EditText editText4=(EditText)findViewById(R.id.ps);
	      
	        String newname =editText3.getText().toString();
	        String password=editText4.getText().toString();
	        if (CheckIsDataAlreadyInDBorNot(newname)) {
	            Toast.makeText(this,"该用户名已被注册，注册失败",Toast.LENGTH_SHORT).show();
	        }
	       else {

	            if (register(newname, password)) {
	                Toast.makeText(this, "插入数据表成功", Toast.LENGTH_SHORT).show();
	            }
	       }
	    }
	//向数据库插入数据
	    public boolean register(String username,String password){
	        SQLiteDatabase db= dbHelper.getWritableDatabase();
	        /*String sql = "insert into userData(name,password) value(?,?)";
	        Object obj[]={username,password};
	        db.execSQL(sql,obj);*/
	        ContentValues values=new ContentValues();
	        values.put("name",username);
	        values.put("password",password);
	        db.insert("userData",null,values);
	        db.close();
	        //db.execSQL("insert into userData (name,password) values (?,?)",new String[]{username,password});
	        return true;
	    }
	//检验用户名是否已存在
	    public boolean CheckIsDataAlreadyInDBorNot(String value){
	        SQLiteDatabase db=dbHelper.getWritableDatabase();
	        String Query = "Select * from userData where name =?";
	        Cursor cursor = db.rawQuery(Query,new String[] { value });
	        if (cursor.getCount()>0){
	            cursor.close();
	            return  true;
	        }
	        cursor.close();
	        return false;
	    }

}
