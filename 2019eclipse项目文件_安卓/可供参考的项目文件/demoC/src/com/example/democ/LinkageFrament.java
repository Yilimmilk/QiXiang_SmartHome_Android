package com.example.democ;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DeviceBean;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;


public class LinkageFrament extends Fragment {
private Button mLinkage;
private boolean linkage=false;
private Spinner mSensorSp, mConditionSp;
private EditText mValueEt;
private Switch mAlarm,mFan,mLamp,mDoor;
private EditText mTimeEt;
private Handler mHandler=new Handler();
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	View view=inflater.inflate(R.layout.frament_linkage, null);
	initViews(view);
	return view;
}

private void initViews(View view) {
	// TODO Auto-generated method stub
	mValueEt = (EditText)view. findViewById(R.id.et_value);
	mTimeEt = (EditText)view. findViewById(R.id.tv_time);
	mSensorSp = (Spinner) view.findViewById(R.id.sp_sensor);
	mAlarm = (Switch) view.findViewById(R.id.alarm);
	mFan = (Switch) view.findViewById(R.id.fan);
	mLamp = (Switch) view.findViewById(R.id.lamp);
	mDoor = (Switch) view.findViewById(R.id.door);
	mConditionSp = (Spinner) view.findViewById(R.id.sp_condition);
	mLinkage=(Button)view.findViewById(R.id.btn_linkage);
	mLinkage.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(linkage==false){
				if(TextUtils.isEmpty(mTimeEt.getText().toString())){
					Toast.makeText(getActivity(), "执行时间不能为空！", 0).show();
					return;
				}
				linkage=true;
			    mLinkage.setText("停止联动");
			    mHandler.postDelayed(Linkage, 500);
			}else{
				linkage=false;
				mLinkage.setText("开启联动");
			}
		}
	private	Runnable Linkage=new Runnable() {
	public void run() {
		if (mConditionSp.getSelectedItem().toString().equals(">")) {
			if (mSensorSp.getSelectedItem().toString().equals("温度")) {// 选择的spinner选项是温度
				// 选择的spinner选项是大于
				if (!TextUtils.isEmpty(DeviceBean.getTemperature())
						&& Double.parseDouble(DeviceBean.getTemperature()) > Integer
								.parseInt(mValueEt.getText().toString())) {// 判断是否符合条件
					Control();
				
			   }
			
			} else if (mSensorSp.getSelectedItem().toString().equals("光照")) {
				if (!TextUtils.isEmpty(DeviceBean.getIllumination())
						&& Double.parseDouble(DeviceBean.getIllumination()) > Integer
								.parseInt(mValueEt.getText().toString())) {// 判断是否符合条件
					Control();
				}
			}else if (mSensorSp.getSelectedItem().toString().equals("湿度")) {
				if (!TextUtils.isEmpty(DeviceBean.getHumidity())
						&& Double.parseDouble(DeviceBean.getHumidity()) > Integer
								.parseInt(mValueEt.getText().toString())) {// 判断是否符合条件
					Control();
				}
			}

		} else if (mConditionSp.getSelectedItem().toString().equals("<=")) {
			if (mSensorSp.getSelectedItem().toString().equals("温度")) {// 选择的spinner选项是温度
				// 选择的spinner选项是小于
				if (!TextUtils.isEmpty(DeviceBean.getTemperature())
						&& Double.parseDouble(DeviceBean.getTemperature()) <=Integer
								.parseInt(mValueEt.getText().toString())) {// 判断是否符合条件
					Control();
				}
			} else if (mSensorSp.getSelectedItem().toString().equals("光照")) {
				if (!TextUtils.isEmpty(DeviceBean.getIllumination())
						&& Double.parseDouble(DeviceBean.getIllumination()) < Integer
								.parseInt(mValueEt.getText().toString())) {// 判断是否符合条件
					Control();
				}
			}else if (mSensorSp.getSelectedItem().toString().equals("湿度")) {
				if (!TextUtils.isEmpty(DeviceBean.getHumidity())
						&& Double.parseDouble(DeviceBean.getHumidity()) > Integer
								.parseInt(mValueEt.getText().toString())) {// 判断是否符合条件
					Control();
				}
			}
		}
		mHandler.postDelayed(Linkage, Integer.parseInt(mTimeEt.getText().toString())*60*1000);
	}
};
		

		private void Control() {
			// TODO Auto-generated method stub
			if (mAlarm.isChecked()==true){
				ControlUtils
				.control(ConstantUtil.WarningLight,
						ConstantUtil.CHANNEL_ALL,
						ConstantUtil.OPEN);
	        }else{
	        	ControlUtils
				.control(ConstantUtil.WarningLight,
						ConstantUtil.CHANNEL_ALL,
						ConstantUtil.CLOSE);
	         }
			
			if (mFan.isChecked()==true){
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
			if (mDoor.isChecked()==true){
				ControlUtils
				.control(ConstantUtil.RFID_Open_Door,
						ConstantUtil.CHANNEL_1,
						ConstantUtil.OPEN);
	        }else{
	        	ControlUtils
				.control(ConstantUtil.RFID_Open_Door,
						ConstantUtil.CHANNEL_1,
						ConstantUtil.CLOSE);
	         }
			if (mLamp.isChecked()==true){
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
	});
}
}
