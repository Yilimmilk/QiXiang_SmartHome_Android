package com.example.demoB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

public class TempActivity extends Activity{
	private LineView mLineView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_temp);
		// getData();
		mLineView=((LineView)findViewById(R.id.lineView));
		mLineView.setData( Arrays.asList("02-1","02-1","02-1","02-1","02-1","02-1","02-1"),
                Arrays.asList(1.0f,2.0f,3.0f,4.0f,5.0f,6.0f,7.0f));
	}

	private ArrayList<String> getData() {
		// TODO Auto-generated method stub
		Cursor cursor=MainActivity.mDataBase.query(MainActivity.mHelper.temp_table, null, null, null, null, null, null);
		ArrayList<String>	list=new ArrayList<String>();
		Log.e("=================", "=============="+cursor.getCount());
		if(cursor.getCount()>0){
		 while (cursor.moveToNext()) {
			 list.add(cursor.getString(cursor.getColumnIndex("roomName")));
		  }
		}
		cursor.close();
		return list;
	}
}
