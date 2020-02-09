package com.example.demog;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.database.sqlite.SQLiteDatabase;

public class MainActivity extends FragmentActivity {

	private RadioGroup mRG;
	public static DatabaseHelper mHelper;
	public static SQLiteDatabase mDatabase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mHelper=new DatabaseHelper(this);
		mDatabase=mHelper.getWritableDatabase();
		
		
		getFragmentManager().beginTransaction().replace(R.id.fragment, new DeviceFragment()).commit();
		mRG=(RadioGroup)findViewById(R.id.rg);
		mRG.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(checkedId==R.id.rb_device){
					getFragmentManager().beginTransaction().replace(R.id.fragment, new DeviceFragment()).commit();
				}else if(checkedId==R.id.rb_pattern){
					getFragmentManager().beginTransaction().replace(R.id.fragment, new PatternFragment()).commit();
					
				}else if(checkedId==R.id.rb_statistics){
					getFragmentManager().beginTransaction().replace(R.id.fragment, new StatisticsFragment()).commit();
					
				}else if(checkedId==R.id.rb_set){
					getFragmentManager().beginTransaction().replace(R.id.fragment, new SetFragment()).commit();
					
				}
			}
		});
	}

	/**
	 * 获取当前时间
	 * @return
	 */
	public static String getTime(){
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
	Date date = new Date(System.currentTimeMillis());
	return simpleDateFormat.format(date);
	}

}
