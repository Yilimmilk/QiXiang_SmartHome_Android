package com.example.fffff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SeeSqlActivity extends Activity {
	ListView listView;
	Spinner spinner;
	Button btn;
	Helper helper;
	SQLiteDatabase db;
	
	List<Map<String,String>> list;
	private SimpleAdapter simadapter;
	private JiuGongGeLock lockview;
	int errCount=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_see_sql);
		init();
		list = new ArrayList<Map<String,String>>();
	}


	private void init() {
		// TODO Auto-generated method stub
		 listView = (ListView) findViewById(R.id.listView);
		    spinner = (Spinner) findViewById(R.id.spinner);
		    btn = (Button) findViewById(R.id.button);
		    btn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					sqlQuery(spinner.getSelectedItem().toString());
				}
			});
		    btn.setEnabled(false);
		    lockview = (JiuGongGeLock) findViewById(R.id.lockview);
		    lockview.setOnDrawFinishedListener(new OnDrawFinishedListener() {
				
				@Override
				public void onSuccess(int count) {
					// TODO Auto-generated method stub
					if(count==1){
						startActivity(new Intent(SeeSqlActivity.this,MainActivity.class));
						finish();
					}else{
						btn.setEnabled(true);
					}
					Toast.makeText(SeeSqlActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
				}
				
				@Override
				public void onFailure(int errCount) {
					// TODO Auto-generated method stub
					 Toast.makeText(SeeSqlActivity.this, "验证失败", Toast.LENGTH_SHORT).show();
					  if(errCount>2){
						  finish();
					  }
				}
			});
	}

	
	private void sqlQuery(String name){
		helper = new Helper(SeeSqlActivity.this);
		db = helper.getReadableDatabase();
		Cursor cursor = null ;
		if(name.equals("全部")){
			cursor = db.rawQuery("select * from t_data ", null);
		}else{
			cursor = db.rawQuery("select * from t_data where type = ? ", new String[]{name});
		}
		
		if(list.size()>0){
			list.clear();
		}
		while(cursor.moveToNext()){
			Map map = new HashMap<String, String>();
			map.put("id", String.valueOf(cursor.getInt(0)));
			map.put("type", cursor.getString(1));
			map.put("value", cursor.getString(2));
			list.add(map);
		}
		cursor.close();
		simadapter = new SimpleAdapter(SeeSqlActivity.this,list,R.layout.lsititem , new String[]{"id","type","value"}, new int[]{R.id.id,R.id.type,R.id.value});
		listView.setAdapter(simadapter);
	}
	

}
