package com.example.demoB;

import java.util.ArrayList;
import java.util.HashMap;
import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;


public class MainActivity extends Activity  {
	public static DatabaseHelper mHelper;
	public static SQLiteDatabase mDataBase;
	private RoomAdater adapter;
	private int index=1;
	int checkIn=1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mHelper=new DatabaseHelper(this);
	    mDataBase= mHelper.getWritableDatabase();
		Cursor cursor=mDataBase.query(mHelper.room_table, null, null, null, null, null, null);
		//写入房间号
		if(cursor.getCount()<1){
	    ContentValues values= new ContentValues();
        values.put("roomName", "8101");
        values.put("checkIn", "0");
        mDataBase.insert(mHelper.room_table, null, values);
        
        values.clear();
        values.put("roomName", "8102");
        values.put("checkIn", "0");
        mDataBase.insert(mHelper.room_table, null, values);
        
        values.clear();
        values.put("roomName", "8103");
        values.put("checkIn", "0");
        mDataBase.insert(mHelper.room_table, null, values);
        
        values.clear();
        values.put("roomName", "8104");
        values.put("checkIn", "0");
        mDataBase.insert(mHelper.room_table, null, values);
        
       
        values.clear();
        values.put("roomName", "8201");
        values.put("checkIn", "0");
        mDataBase.insert(mHelper.room_table, null, values);
        
        values.clear();
        values.put("roomName", "8202");
        values.put("checkIn", "0");
        mDataBase.insert(mHelper.room_table, null, values);
        
        values.clear();
        values.put("roomName", "8203");
        values.put("checkIn", "0");
        mDataBase.insert(mHelper.room_table, null, values);
        
        values.clear();
        values.put("roomName", "8204");
        values.put("checkIn", "0");
        mDataBase.insert(mHelper.room_table, null, values);
        
        
        values.clear();
        values.put("roomName", "8301");
        values.put("checkIn", "0");
        mDataBase.insert(mHelper.room_table, null, values);
        
        values.clear();
        values.put("roomName", "8302");
        values.put("checkIn", "0");
        mDataBase.insert(mHelper.room_table, null, values);
        
        values.clear();
        values.put("roomName", "8303");
        values.put("checkIn", "0");
        mDataBase.insert(mHelper.room_table, null, values);
        
        values.clear();
        values.put("roomName", "8304");
        values.put("checkIn", "0");
        mDataBase.insert(mHelper.room_table, null, values);
        
        
        values.clear();
        values.put("roomName", "8401");
        values.put("checkIn", "0");
        mDataBase.insert(mHelper.room_table, null, values);
        
        values.clear();
        values.put("roomName", "8402");
        values.put("checkIn", "0");
        mDataBase.insert(mHelper.room_table, null, values);
        
        values.clear();
        values.put("roomName", "8403");
        values.put("checkIn", "0");
        mDataBase.insert(mHelper.room_table, null, values);
        
        values.clear();
        values.put("roomName", "8404");
        values.put("checkIn", "0");
        mDataBase.insert(mHelper.room_table, null, values);
		}
		initViews();
		initData();
		   SocketClient.getInstance().login( new LoginCallback() {
               @Override
               public void onEvent(final String status) {
                   runOnUiThread( new Runnable() {
                       @Override
                       public void run() {
                           if (status.equals( ConstantUtil.SUCCESS )) {
                        	   Toast.makeText( MainActivity.this, "组网成功", 0 ).show();
                           } else {
                              
                           }
                       }
                   } );
               }
           });
	}

	private void initData() {
		// TODO Auto-generated method stub
		ControlUtils.getData();
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {

			@Override
			public void onResult(final DeviceBean bean) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
					 	    ContentValues 	values=new  ContentValues();
					        values.put("value",bean.getTemperature());
					        mDataBase.insert(mHelper.temp_table, null, values);
					}
				});
			}
		});
	}

	private ArrayList<HashMap<String, Object>> getData() {
		// TODO Auto-generated method stub
		Cursor cursor=mDataBase.query(mHelper.room_table, null, null, null, null, null, null);
		ArrayList<HashMap<String, Object>>	list=new ArrayList<HashMap<String, Object>>();
		Log.e("=================", "=============="+cursor.getCount());
		if(cursor.getCount()>0){
		 while (cursor.moveToNext()) {
			 HashMap<String, Object> map=new HashMap<String, Object>();
			 map.put("roomName", cursor.getString(cursor.getColumnIndex("roomName")));
			 map.put("checkIn", cursor.getInt(cursor.getColumnIndex("checkIn")));
			 list.add(map);
		  }
		}
		cursor.close();
		return list;
	}

	private void initViews() {
		// TODO Auto-generated method stub
		final View	mView1=(View)findViewById(R.id.view1);
		final View	mView2=(View)findViewById(R.id.view2);
		final View	mView3=(View)findViewById(R.id.view3);
		final View	mView4=(View)findViewById(R.id.view4);
		RadioGroup	mRG=(RadioGroup)findViewById(R.id.rg);
		mRG.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(checkedId==R.id.rb1){
					mView1.setBackgroundColor(Color.parseColor("#1eaee3"));
					mView2.setBackgroundColor(Color.parseColor("#00000000"));
					mView3.setBackgroundColor(Color.parseColor("#00000000"));
					mView4.setBackgroundColor(Color.parseColor("#00000000"));
					index=1;
				}else if(checkedId==R.id.rb2){
					mView2.setBackgroundColor(Color.parseColor("#1eaee3"));
					mView1.setBackgroundColor(Color.parseColor("#00000000"));
					mView3.setBackgroundColor(Color.parseColor("#00000000"));
					mView4.setBackgroundColor(Color.parseColor("#00000000"));
					index=2;
				}else if(checkedId==R.id.rb3){
					mView3.setBackgroundColor(Color.parseColor("#1eaee3"));
					mView1.setBackgroundColor(Color.parseColor("#00000000"));
					mView2.setBackgroundColor(Color.parseColor("#00000000"));
					mView4.setBackgroundColor(Color.parseColor("#00000000"));
					index=3;
				}else if(checkedId==R.id.rb4){
					mView4.setBackgroundColor(Color.parseColor("#1eaee3"));
					mView1.setBackgroundColor(Color.parseColor("#00000000"));
					mView2.setBackgroundColor(Color.parseColor("#00000000"));
					mView3.setBackgroundColor(Color.parseColor("#00000000"));
					index=4;
				}
			}
		});
		adapter=new RoomAdater(this);
		adapter.setData(getData());
		GridView mGv=(GridView)findViewById(R.id.gv);
		mGv.setAdapter(adapter);
		mGv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				// TODO Auto-generated method stub
				if(index==1){
					alertDialog(getData().get(arg2).get("roomName").toString());
				}else if(index==2){
				   startActivity(new Intent(MainActivity.this,RoomControlActivity.class).putExtra("roomName", getData().get(arg2).get("roomName").toString()));
				}else if(index==3){
					 startActivity(new Intent(MainActivity.this,LinkageActivity.class).putExtra("roomName", getData().get(arg2).get("roomName").toString()));
				}else if(index==4){
					 startActivity(new Intent(MainActivity.this,TempActivity.class).putExtra("roomName", getData().get(arg2).get("roomName").toString()));
				}
			}

			
		});
	}
	
	private void alertDialog(final String string) {
		// TODO Auto-generated method stub
		 final AlertDialog mDialog = new AlertDialog.Builder( this).create();
		 mDialog.show();
		 mDialog.getWindow().setContentView(R.layout.dialog_room);
		final TextView	mRoomTv = (TextView)mDialog.getWindow(). findViewById(R.id.tv_room);
		mRoomTv.setText("房间号:"+string);
		final TextView	mTempTv = (TextView)mDialog.getWindow(). findViewById(R.id.temp_tv);// 温度
		final TextView	mHumidityTv = (TextView)mDialog.getWindow(). findViewById(R.id.humidity_tv);// 湿度
		final TextView mGasTv = (TextView)mDialog.getWindow().  findViewById(R.id.gas_tv);// 燃气
		final TextView mIlluminationTv = (TextView)mDialog.getWindow().  findViewById(R.id.illumination_tv);// 光照度
		final TextView mPm25Tv = (TextView) mDialog.getWindow(). findViewById(R.id.pm25_tv);// Pm2.5
		final 	TextView	mPressureTv = (TextView) mDialog.getWindow(). findViewById(R.id.pressure_tv);// 气压
		final 	TextView	mSmokeTv = (TextView)mDialog.getWindow().  findViewById(R.id.smoke_tv);// 烟雾
		final 	TextView	mCo2Tv = (TextView)mDialog.getWindow().  findViewById(R.id.co2_tv);// C02
		final 	TextView mStateHumanInfraredTv = (TextView) mDialog.getWindow().findViewById(R.id.infrared_tv);// 人体红外
		final 	TextView	mColseTv = (TextView)mDialog.getWindow().  findViewById(R.id.colse_tv);
		mColseTv.setOnClickListener( new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                ContentValues  values = new ContentValues();
	                values.put("checkIn",checkIn);
	                mDataBase.update(mHelper.room_table, values, "roomName=?",new String[]{string});
	            	adapter.setData(getData());
	            	mDialog.dismiss();
	            }
	        } );
		RadioGroup	mStateRG=(RadioGroup)mDialog.getWindow().findViewById(R.id.state_rg);
		mStateRG.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(checkedId==R.id.rb1){
					checkIn=1;
				}else if(checkedId==R.id.rb2){
					checkIn=2;
				}else if(checkedId==R.id.rb3){
					checkIn=0;
				}
			}
		});
		if (!TextUtils.isEmpty(DeviceBean.getTemperature()))
			mTempTv.setText("温度:"+DeviceBean.getTemperature());
		if (!TextUtils.isEmpty(DeviceBean.getHumidity()))
			mHumidityTv.setText("湿度:"+DeviceBean.getHumidity());
		if (!TextUtils.isEmpty(DeviceBean.getGas()))
			mGasTv.setText("燃气:"+DeviceBean.getGas());
		if (!TextUtils.isEmpty(DeviceBean.getIllumination()))
			mIlluminationTv.setText("光照度:"+DeviceBean.getIllumination());
		if (!TextUtils.isEmpty(DeviceBean.getPM25()))
			mPm25Tv.setText("PM2.5:"+DeviceBean.getPM25());
		if (!TextUtils.isEmpty(DeviceBean.getSmoke()))
			mSmokeTv.setText("烟雾:"+DeviceBean.getSmoke());
		if (!TextUtils.isEmpty(DeviceBean.getCo2()))
			mCo2Tv.setText("Co2:"+DeviceBean.getCo2());
		if (!TextUtils.isEmpty(DeviceBean.getAirPressure()))
			mPressureTv.setText("气压:"+DeviceBean.getAirPressure());
		if (!TextUtils.isEmpty(DeviceBean.getStateHumanInfrared())
				&& DeviceBean.getStateHumanInfrared().equals(
						ConstantUtil.CLOSE))
			mStateHumanInfraredTv.setText("人体红外:"+"无人");
		else
			mStateHumanInfraredTv.setText("人体红外:"+"有人");
	}
}
