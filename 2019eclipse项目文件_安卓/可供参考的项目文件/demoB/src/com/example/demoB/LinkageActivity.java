package com.example.demoB;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DeviceBean;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class LinkageActivity extends Activity {
             private TextView mRoomNameTv;
			private String mRoomName;
			private Spinner mSensorSp,mConditionSp,mDeviceSp,mStateSp;
			private EditText mValueEt;
			private Switch state;
	        private Handler mHandler=new Handler();
			private Switch mLinkage;
		

			@Override
            protected void onCreate(Bundle savedInstanceState) {
            	// TODO Auto-generated method stub
            	super.onCreate(savedInstanceState);
            	setContentView(R.layout.activity_linkage);
            	mRoomName=getIntent().getStringExtra("roomName");
            	initViews();
            }

			private void initViews() {
				// TODO Auto-generated method stub
				mRoomNameTv=(TextView)findViewById(R.id.tv_room);
				mRoomNameTv.setText("房间号:"+mRoomName);
				mSensorSp = (Spinner) findViewById(R.id.sp_sensor);//
				mConditionSp = (Spinner) findViewById(R.id.sp_condition);
				mDeviceSp = (Spinner) findViewById(R.id.sp_device);
				mValueEt = (EditText) findViewById(R.id.et_value);
				mStateSp = (Spinner) findViewById(R.id.sp_state);
				
				mLinkage=(Switch)findViewById(R.id.sw_linkage);
				mLinkage.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						// TODO Auto-generated method stub
						if(isChecked){
							if(TextUtils.isEmpty(mValueEt.getText().toString())){
								Toast.makeText(LinkageActivity.this, "数值不能为空", 0).show();
								return;
							}
							mHandler.postDelayed(customOpenThread,100);
						}else{
							mHandler.removeCallbacks(customOpenThread);
						}
					}
				});
				
			}
			
			
			
			/**
			 * 自定义情景模式开启时
			 */
			Thread customOpenThread = new Thread(new Runnable() {

				public void run() {
					if (mConditionSp.getSelectedItem().toString().equals(">")) {
						if (mSensorSp.getSelectedItem().toString().equals("温度")) {// 选择的spinner选项是温度
							// 选择的spinner选项是大于
							if (!TextUtils.isEmpty(DeviceBean.getTemperature())
									&& Double.parseDouble(DeviceBean.getTemperature()) > Integer
											.parseInt(mValueEt.getText().toString())) {// 判断是否符合条件
								if (mDeviceSp.getSelectedItem().toString()
										.contains("风扇")){
									if (mStateSp.getSelectedItem().toString()
											.contains("开")){
									ControlUtils
											.control(ConstantUtil.Fan,
													ConstantUtil.CHANNEL_ALL,
													ConstantUtil.OPEN);
									}else{
										ControlUtils
										.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}else if (mDeviceSp.getSelectedItem().toString()
										.contains("射灯")){
									if (mStateSp.getSelectedItem().toString()
											.contains("开")){
									ControlUtils
											.control(ConstantUtil.Lamp,
													ConstantUtil.CHANNEL_ALL,
													ConstantUtil.OPEN);
									}else{
										ControlUtils
										.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
							 }
							}
						} else if (mSensorSp.getSelectedItem().toString().equals("光照")) {
							if (!TextUtils.isEmpty(DeviceBean.getIllumination())
									&& Double.parseDouble(DeviceBean.getIllumination()) > Integer
											.parseInt(mValueEt.getText().toString())) {// 判断是否符合条件
								if (mDeviceSp.getSelectedItem().toString()
										.contains("风扇")){
									if (mStateSp.getSelectedItem().toString()
											.contains("开")){
									ControlUtils
											.control(ConstantUtil.Fan,
													ConstantUtil.CHANNEL_ALL,
													ConstantUtil.OPEN);
									}else{
										ControlUtils
										.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}else if (mDeviceSp.getSelectedItem().toString()
										.contains("射灯")){
									if (mStateSp.getSelectedItem().toString()
											.contains("开")){
									ControlUtils
											.control(ConstantUtil.Lamp,
													ConstantUtil.CHANNEL_ALL,
													ConstantUtil.OPEN);
									}else{
										ControlUtils
										.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
							}
						}

					} else if (mConditionSp.getSelectedItem().toString().equals("<=")) {
						if (mSensorSp.getSelectedItem().toString().equals("温度")) {// 选择的spinner选项是温度
							// 选择的spinner选项是小于
							if (!TextUtils.isEmpty(DeviceBean.getTemperature())
									&& Double.parseDouble(DeviceBean.getTemperature()) <=Integer
											.parseInt(mValueEt.getText().toString())) {// 判断是否符合条件
								if (mDeviceSp.getSelectedItem().toString()
										.contains("风扇")){
									if (mStateSp.getSelectedItem().toString()
											.contains("开")){
									ControlUtils
											.control(ConstantUtil.Fan,
													ConstantUtil.CHANNEL_ALL,
													ConstantUtil.OPEN);
									}else{
										ControlUtils
										.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}else if (mDeviceSp.getSelectedItem().toString()
										.contains("射灯")){
									if (mStateSp.getSelectedItem().toString()
											.contains("开")){
									ControlUtils
											.control(ConstantUtil.Lamp,
													ConstantUtil.CHANNEL_ALL,
													ConstantUtil.OPEN);
									}else{
										ControlUtils
										.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
							}
						} else if (mSensorSp.getSelectedItem().toString().equals("光照")) {
							if (!TextUtils.isEmpty(DeviceBean.getIllumination())
									&& Double.parseDouble(DeviceBean.getIllumination()) < Integer
											.parseInt(mValueEt.getText().toString())) {// 判断是否符合条件
								if (mDeviceSp.getSelectedItem().toString()
										.contains("风扇")){
									if (mStateSp.getSelectedItem().toString()
											.contains("开")){
									ControlUtils
											.control(ConstantUtil.Fan,
													ConstantUtil.CHANNEL_ALL,
													ConstantUtil.OPEN);
									}else{
										ControlUtils
										.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}else if (mDeviceSp.getSelectedItem().toString()
										.contains("射灯")){
									if (mStateSp.getSelectedItem().toString()
											.contains("开")){
									ControlUtils
											.control(ConstantUtil.Lamp,
													ConstantUtil.CHANNEL_ALL,
													ConstantUtil.OPEN);
									}else{
										ControlUtils
										.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
							}
						}
					}
					mHandler.postDelayed(customOpenThread, 1000);
				}

			});
}
