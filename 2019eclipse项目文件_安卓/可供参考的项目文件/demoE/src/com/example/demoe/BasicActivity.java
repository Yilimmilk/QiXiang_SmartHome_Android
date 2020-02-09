package com.example.demoe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DeviceBean;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class BasicActivity extends Activity {
	
private PieChart pie;
private LinearLayout mLL;
private Switch mLink;
private Switch deposit;
private Handler mHandler=new Handler();
private EditText mDevice;
private EditText mState,mValueEt,mNumEt;
private Switch mLinkage;
private ListView mLv;
private Spinner mSensorSp, mConditionSp, mStateSp;

@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_basic);
	 initViews();
	 initData();
	 initList();
  }

private void initList() {
	// TODO Auto-generated method stub
	ArrayList<Map<String, Object>>	list =new ArrayList<Map<String, Object>>();
	Cursor run=MainActivity.mDatabase.query(MainActivity.mHelper.run_table, null, null, null, null, null, null);
	if(run.getCount()>0){
		while (run.moveToNext()) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", run.getInt(run.getColumnIndex("id")));
		map.put("device", run.getString(run.getColumnIndex("device")));
		map.put("state", run.getString(run.getColumnIndex("state")));
		map.put("time", run.getString(run.getColumnIndex("time")));
		list.add(map);
		}
		run.close();
		
		SimpleAdapter mAdapter=new SimpleAdapter(BasicActivity.this,list, R.layout.item_list, new String[] {"id", "device","state","time"}, new int[] {R.id.tv_id,R.id.tv_device,R.id.tv_state,R.id.tv_time});
		mLv.setAdapter(mAdapter);
	}else{
		run.close();
	}
}

private void initData() {
	// TODO Auto-generated method stub
  Cursor cursor=MainActivity.mDatabase.query(MainActivity.mHelper.illumination_table, null, null, null, null, null, null);
  if(cursor.getCount()>0){
	  final ArrayList list=new ArrayList<Integer>();
	  if(!(list.size()>3)){
	    while (cursor.moveToNext()) {
	   	list.add( (int)Double.parseDouble(cursor.getString(cursor.getColumnIndex("value"))));
	    }
	  }
	pie.setData(list);
}
  cursor.close();
}



private void initViews() {
	// TODO Auto-generated method stub
	mSensorSp = (Spinner) findViewById(R.id.sp_sensor);//
	mConditionSp = (Spinner) findViewById(R.id.sp_condition);
	mStateSp = (Spinner) findViewById(R.id.sp_state);
	mValueEt = (EditText) findViewById(R.id.et_value);
	mNumEt = (EditText) findViewById(R.id.et_num);
	pie=(PieChart)findViewById(R.id.pie);
	mDevice = (EditText) findViewById(R.id.et_device);
	mState = (EditText) findViewById(R.id.et_state);
	mLinkage = (Switch) findViewById(R.id.s_linkage);
	RadioGroup mRG=(RadioGroup) findViewById(R.id.rg);
	mRG.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		private String DEVICE;
		private String State;

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			if(checkedId==R.id.pattern){
				if(mLinkage.isChecked()==true){
					if(leavehomeThread!=null){
						   mHandler.removeCallbacks(leavehomeThread);
						}
					if(customOpenThread!=null){
						 mHandler.removeCallbacks(customOpenThread);
					}
					mHandler.postDelayed(linkageThread, 500);
				}else{
					mHandler.postDelayed(leavehomeThread, 500);
				}
			}else if(checkedId==R.id.rb_link){
				if(leavehomeThread!=null){
					   mHandler.removeCallbacks(leavehomeThread);
					}
				mHandler.postDelayed(customOpenThread, 500);
			}else if(checkedId==R.id.rb_instruct){
				if(customOpenThread!=null){
					   mHandler.removeCallbacks(customOpenThread);
					}
				if(leavehomeThread!=null){
					   mHandler.removeCallbacks(leavehomeThread);
					}
				if(TextUtils.isEmpty(mDevice.getText().toString())||TextUtils.isEmpty(mState.getText().toString())){
					Toast.makeText(BasicActivity.this, "请先取数据", 0).show();
					return;
				}
				if(mDevice.getText().toString().equals("射灯")){
					DEVICE=ConstantUtil.Lamp;
				}else if(mDevice.getText().toString().equals("报警灯")){
					DEVICE=ConstantUtil.WarningLight;
				}else if(mDevice.getText().toString().equals("风扇")){
					DEVICE=ConstantUtil.Fan;
				}
			
				if(mState.getText().toString().equals("开")){
					State=ConstantUtil.OPEN;
				}else{
					State=ConstantUtil.CLOSE;
				}
				ControlUtils.control(DEVICE,
						ConstantUtil.CHANNEL_1, State);
			}
		}
	});
	
	
	mLv=(ListView)findViewById(R.id.lv);
	
	TextView mIllumination = (TextView) findViewById(R.id.tv_illumination);
	mIllumination.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			initData();
		}
	});
	Button mGet= (Button) findViewById(R.id.btn_get);
	mGet.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(TextUtils.isEmpty(mNumEt.getText().toString())){
				Toast.makeText(BasicActivity.this, "编号为空", 0).show();
				return;
			}
			Cursor cursor=MainActivity.mDatabase.query(MainActivity.mHelper.device_table,new String []{"deviceId","state","device"}, "deviceId=? ",
					  new String[]{mNumEt.getText().toString()}, null, null, null);
			  if(cursor.getCount()>0){
				    while (cursor.moveToNext()) {
					mDevice.setText(cursor.getString(cursor.getColumnIndex("device")));
					mState.setText(cursor.getString(cursor.getColumnIndex("state")));
				    }
			  }
			  cursor.close();
		}
	});
	Button mDeposit= (Button) findViewById(R.id.btn_deposit);
	mDeposit.setOnClickListener(new OnClickListener() {
		
		private int id;

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(TextUtils.isEmpty(mDevice.getText().toString())){
				Toast.makeText(BasicActivity.this, "设备不能为空", 0).show();
				return;
			}
if(TextUtils.isEmpty(mState.getText().toString())){
	Toast.makeText(BasicActivity.this, "状态不能为空", 0).show();
				return;
			}
   ContentValues values= new ContentValues();
  values.put("device", mDevice.getText().toString());
  values.put("state",  mState.getText().toString());
  MainActivity.mDatabase.insert(MainActivity.mHelper.device_table, null, values);
  Cursor cursor=MainActivity.mDatabase.query(MainActivity.mHelper.device_table,new String []{"deviceId","state","device"}, "device=? and state=?",
		  new String[]{mDevice.getText().toString(),mState.getText().toString()}, null, null, null);
  if(cursor.getCount()>0){
	  while (cursor.moveToNext()) {
	     id=cursor.getInt(cursor.getColumnIndex("deviceId"));
	  }
  }
  cursor.close();
  new AlertDialog.Builder(BasicActivity.this)
	.setTitle("编号")
	.setMessage("编号:"+id)
	.setPositiveButton("确定",null).show();
  
		}
	});

	mLL = (LinearLayout) findViewById(R.id.ll);
	mLL.setOnLongClickListener(new OnLongClickListener() {

		@Override
		public boolean onLongClick(View v) {
			// TODO Auto-generated method stub
			startActivity(new Intent(BasicActivity.this, MainActivity.class));
			finish();
			return false;
		}
	});
	
}

/**
 * 在家模式
 */
Thread linkageThread = new Thread(new Runnable() {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (!TextUtils.isEmpty(DeviceBean.getLamp())
				&& !DeviceBean.getLamp().equals(ConstantUtil.CLOSE)) {
			ControlUtils.control(ConstantUtil.Lamp,
					ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
		}
		if (!TextUtils.isEmpty(DeviceBean.getCurtain())
				&& DeviceBean.getCurtain().equals("2")
				|| DeviceBean.getCurtain().equals("3")) {
			ControlUtils.control(ConstantUtil.Curtain,
					ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
		}
		
		ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
				ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
	}

});
/**
 * 离家模式
 */
Thread leavehomeThread = new Thread(new Runnable() {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (!TextUtils.isEmpty(DeviceBean.getPM25())
				&& Integer.parseInt(DeviceBean.getPM25()) > 75) {
			ControlUtils.control(ConstantUtil.Fan,
					ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
		}
		mHandler.postDelayed(leavehomeThread, 500);
	}

});

/**
 * 自定义情景模式开启时
 */
Thread customOpenThread = new Thread(new Runnable() {

	public void run() {
		if (mConditionSp.getSelectedItem().toString().equals(">=")) {
			if (mSensorSp.getSelectedItem().toString().equals("温度")) {// 选择的spinner选项是温度
				// 选择的spinner选项是大于
				if (!TextUtils.isEmpty(DeviceBean.getTemperature())
						&& Double.parseDouble(DeviceBean.getTemperature()) >= Integer
								.parseInt(mValueEt.getText().toString())) {// 判断是否符合条件
					if (mStateSp.getSelectedItem().toString()
							.contains("开")){
						ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
					}else{
						ControlUtils
								.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
					}
				}
			} else if (mSensorSp.getSelectedItem().toString().equals("光照")) {
				if (!TextUtils.isEmpty(DeviceBean.getIllumination())
						&& Double.parseDouble(DeviceBean.getIllumination()) >= Integer
								.parseInt(mValueEt.getText().toString())) {// 判断是否符合条件
					if (mStateSp.getSelectedItem().toString()
							.contains("开")){
						ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
					}else{
						ControlUtils
								.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
					}
				}
			}

		} else if (mConditionSp.getSelectedItem().toString().equals("<")) {
			if (mSensorSp.getSelectedItem().toString().equals("温度")) {// 选择的spinner选项是温度
				// 选择的spinner选项是小于
				if (!TextUtils.isEmpty(DeviceBean.getTemperature())
						&& Double.parseDouble(DeviceBean.getTemperature()) <Integer
								.parseInt(mValueEt.getText().toString())) {// 判断是否符合条件
					if (mStateSp.getSelectedItem().toString()
							.contains("开")){
						ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
					}else{
						ControlUtils
								.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
					}
				}
			} else if (mSensorSp.getSelectedItem().toString().equals("光照")) {
				if (!TextUtils.isEmpty(DeviceBean.getIllumination())
						&& Double.parseDouble(DeviceBean.getIllumination()) < Integer
								.parseInt(mValueEt.getText().toString())) {// 判断是否符合条件
					if (mStateSp.getSelectedItem().toString()
							.contains("开")){
						ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
					}else{
						ControlUtils
								.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
					}
				}
			}
		}
		mHandler.postDelayed(customOpenThread, 1000);
	}

});


}
