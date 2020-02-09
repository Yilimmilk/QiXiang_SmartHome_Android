package com.example.demoe;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.SocketClient;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainActivity extends Activity implements OnCheckedChangeListener,
		OnClickListener {

	private LinearLayout mLL;
	private EditText mTempTv, mHumidityTv, mGasTv, mIlluminationTv, mPm25Tv,
			mPressureTv, mSmokeTv, mCo2Tv, mStateHumanInfraredTv;
	private CheckBox mLampCb, mDoorCb, mWindSpeedCb, mAlarmCb,mCurtainCb,mFanCb;
	private String mState;
	private String mDevice;
	public static DatabaseHelper mHelper;
	public static SQLiteDatabase mDatabase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 mHelper=new DatabaseHelper(this);
		 mDatabase= mHelper.getWritableDatabase();
		initView();
		initData();
		
	}

	private void initView() {
		// TODO Auto-generated method stub
		mTempTv = (EditText) findViewById(R.id.temp_et);// 温度
		mHumidityTv = (EditText) findViewById(R.id.humidity_et);// 湿度
		mGasTv = (EditText) findViewById(R.id.gas_et);// 燃气
		mIlluminationTv = (EditText) findViewById(R.id.illumination_et);// 光照度
		mPm25Tv = (EditText) findViewById(R.id.pm25_et);// Pm2.5
		mPressureTv = (EditText) findViewById(R.id.pressure_et);// 气压
		mSmokeTv = (EditText) findViewById(R.id.smoke_et);// 烟雾
		mCo2Tv = (EditText) findViewById(R.id.co2_et);// C02
		mStateHumanInfraredTv = (EditText) findViewById(R.id.stateHumanInfrared_et);// 人体红外
		mLampCb = (CheckBox) findViewById(R.id.lamp_cb);// 射灯
		mLampCb.setOnCheckedChangeListener(this);
		mDoorCb = (CheckBox) findViewById(R.id.door_cb);// 门禁
		mDoorCb.setOnCheckedChangeListener(this);
		mCurtainCb = (CheckBox) findViewById(R.id.curtain_cb);
		mCurtainCb.setOnCheckedChangeListener(this);
		mFanCb = (CheckBox) findViewById(R.id.wind_speed_cb);
		mFanCb.setOnCheckedChangeListener(this);
		mCurtainCb.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_2,
						ConstantUtil.OPEN);
				return false;
			}
		});
		mWindSpeedCb = (CheckBox) findViewById(R.id.wind_speed_cb);// 风扇
		mWindSpeedCb.setOnCheckedChangeListener(this);
		mAlarmCb = (CheckBox) findViewById(R.id.alarm_cb);// 报警灯
		mAlarmCb.setOnCheckedChangeListener(this);
		Button mChannel1Btn = (Button) findViewById(R.id.channe1_btn);
		mChannel1Btn.setOnClickListener(this);
		Button mChannel2Btn = (Button) findViewById(R.id.channe2_btn);
		mChannel2Btn.setOnClickListener(this);
		Button mChannel3Btn = (Button) findViewById(R.id.channe3_btn);
		mChannel3Btn.setOnClickListener(this);
		mLL = (LinearLayout) findViewById(R.id.ll);
		mLL.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this, BasicActivity.class));
		
				return false;
			}
		});
	}

	private void initData() {
		// TODO Auto-generated method stub
		ControlUtils.getData();
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {
			@Override
			public void onResult(final DeviceBean bean) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						if (!TextUtils.isEmpty(bean.getTemperature())) {
							mTempTv.setText(bean.getTemperature());
						}
						if (!TextUtils.isEmpty(bean.getHumidity())) {
							mHumidityTv.setText(bean.getHumidity());
						}
						if (!TextUtils.isEmpty(bean.getGas())) {
							mGasTv.setText(bean.getGas());
						}
						if (!TextUtils.isEmpty(bean.getIllumination())) {
							mIlluminationTv.setText(bean.getIllumination());
							 ContentValues values= new ContentValues();
							   values.put("device", "光照度");
							   values.put("value", bean.getIllumination());
							   mDatabase.insert(mHelper.illumination_table, null, values);
						}
						if (!TextUtils.isEmpty(bean.getPM25())) {
							mPm25Tv.setText(bean.getPM25());
						}
						if (!TextUtils.isEmpty(bean.getSmoke())) {
							mSmokeTv.setText(bean.getSmoke());
						}
						if (!TextUtils.isEmpty(bean.getCo2())) {
							mCo2Tv.setText(bean.getCo2());
						}
						if (!TextUtils.isEmpty(bean.getAirPressure())) {
							mPressureTv.setText(bean.getAirPressure());
						}
						if (!TextUtils.isEmpty(bean.getStateHumanInfrared())
								&& bean.getStateHumanInfrared().equals(
										ConstantUtil.CLOSE))
							mStateHumanInfraredTv.setText("无人");
						else {
							mStateHumanInfraredTv.setText("有人");
						}
						
					}
				});
			}
		});
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		if (!buttonView.isPressed())
			return;
		switch (buttonView.getId()) {
		case R.id.lamp_cb:// 射灯
			if (isChecked) {
				mLampCb.setButtonDrawable(R.drawable.ic_5204);
				Control(ConstantUtil.Lamp,ConstantUtil.CHANNEL_ALL,ConstantUtil.OPEN);
			} else {
				mLampCb.setButtonDrawable(R.drawable.ic_5203);
				Control(ConstantUtil.Lamp,ConstantUtil.CHANNEL_ALL,ConstantUtil.CLOSE);
			}
			break;
		case R.id.door_cb:// 门禁
			ControlUtils.control(ConstantUtil.RFID_Open_Door,
					ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			break;
		case R.id.wind_speed_cb:// 风扇
			if (isChecked){
				mFanCb.setButtonDrawable(R.drawable.ic_5207);
				Control(ConstantUtil.Fan,ConstantUtil.CHANNEL_ALL,ConstantUtil.OPEN);
			}else{
				
				mFanCb.setButtonDrawable(R.drawable.ic_5208);
				Control(ConstantUtil.Fan,ConstantUtil.CHANNEL_ALL,ConstantUtil.CLOSE);
			}
			break;
		case R.id.alarm_cb:// 报警灯
			if (isChecked){
				mAlarmCb.setButtonDrawable(R.drawable.ic_5210);
				Control(ConstantUtil.WarningLight,ConstantUtil.CHANNEL_ALL,ConstantUtil.OPEN);
			}else{
				mAlarmCb.setButtonDrawable(R.drawable.ic_5209);
				Control(ConstantUtil.WarningLight,ConstantUtil.CHANNEL_ALL,ConstantUtil.CLOSE);
			}
			break;
		case R.id.curtain_cb:
			if(isChecked){
				mCurtainCb.setButtonDrawable(R.drawable.ic_5205);
				Control(ConstantUtil.Curtain,ConstantUtil.CHANNEL_1,ConstantUtil.OPEN);
		
			}else{
				mCurtainCb.setButtonDrawable(R.drawable.ic_5206);
				Control(ConstantUtil.Curtain,ConstantUtil.CHANNEL_3,ConstantUtil.OPEN);
			}
			break;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.channe1_btn:
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
					ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			break;
		case R.id.channe2_btn:
			
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
					ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
			break;
		case R.id.channe3_btn:
			Control(ConstantUtil.INFRARED_1_SERVE,ConstantUtil.CHANNEL_3,ConstantUtil.OPEN);
			break;

		default:
			break;
		}
	}

	
	
private void Control(String device, String  channel, String state) {
		// TODO Auto-generated method stub
	   ControlUtils.control(device,channel, state);
	   if(device.equals(ConstantUtil.Lamp)){
		   mDevice="射灯";
	   }else if(device.equals(ConstantUtil.Fan)){
		   mDevice="风扇";
	   }else if(device.equals(ConstantUtil.WarningLight)){
		   mDevice="报警灯";
	   }else if(device.equals(ConstantUtil.RFID_Open_Door)){
		   mDevice="门禁";
	   }else if(device.equals(ConstantUtil.Curtain)){
		   mDevice="窗帘";
	   }
	   if(state.contains("1")){
		   mState="开";
	   }else if(state.contains("2")){
		   mState="停";
	   }else {
		   mState="关";
	    }
	  
	   ContentValues values= new ContentValues();
	   values.put("device", mDevice);
	   values.put("state", mState);
	   values.put("time", getTimes());
	   mDatabase.insert(mHelper.run_table, null, values);
	   
	}

/**
 * 获取当前时间
 * @return
 */
private String getTimes(){
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");// HH:mm
	Date date = new Date(System.currentTimeMillis());
	return simpleDateFormat.format(date);
}
}
