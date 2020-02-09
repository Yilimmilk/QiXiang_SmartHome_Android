package com.example.democ;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.SocketClient;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class BasicFrament extends Fragment implements OnCheckedChangeListener{
	
	private TextView mTempTv, mHumidityTv, mGasTv, mIlluminationTv, mPm25Tv,
	mPressureTv, mSmokeTv, mCo2Tv, mStateHumanInfraredTv;
	private ToggleButton mFanTb,mLampTb,mLamp2Tb,mCurtainTb,mAlarmTb,mDoorTb;
	private Button mSendBtn;
	private EditText mEt;
	
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	View view=inflater.inflate(R.layout.frament_basic, null);
	initViews(view);
	initData();
	return view;
}

private void initViews(View view) {
	// TODO Auto-generated method stub
	mTempTv = (TextView) view.findViewById(R.id.temp_tv);// 温度
	mHumidityTv = (TextView)  view.findViewById(R.id.humidity_tv);// 湿度
	mGasTv = (TextView)  view.findViewById(R.id.gas_tv);// 燃气
	mIlluminationTv = (TextView)  view.findViewById(R.id.illumination_tv);// 光照度
	mPm25Tv = (TextView)  view.findViewById(R.id.pm25_tv);// Pm2.5
	mPressureTv = (TextView) view. findViewById(R.id.pressure_tv);// 气压
	mSmokeTv = (TextView)  view.findViewById(R.id.smoke_tv);// 烟雾
	mCo2Tv = (TextView)  view.findViewById(R.id.co2_tv);// C02
	mStateHumanInfraredTv = (TextView)  view.findViewById(R.id.stateHumanInfrared_tv);// 人体红外
	
	mEt = (EditText)  view.findViewById(R.id.et);
	
	mSendBtn = (Button)  view.findViewById(R.id.btn_send);
	mSendBtn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(TextUtils.isEmpty(mEt.getText().toString())){
				Toast.makeText(getActivity(), "通道号不能为空！", 0).show();
				return;
			}
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, mEt.getText().toString(), ConstantUtil.OPEN);
		}
	});
	
	mCurtainTb = (ToggleButton)  view.findViewById(R.id.tb_curtain);
	mCurtainTb.setOnCheckedChangeListener(this);
	mAlarmTb = (ToggleButton)  view.findViewById(R.id.tb_alarm);
	mAlarmTb.setOnCheckedChangeListener(this);
	mDoorTb = (ToggleButton)  view.findViewById(R.id.tb_door);
	mDoorTb.setOnCheckedChangeListener(this);
	
	mFanTb = (ToggleButton)  view.findViewById(R.id.tb_fan);
	mFanTb.setOnCheckedChangeListener(this);
	mLampTb = (ToggleButton)  view.findViewById(R.id.tb_lamp);
	mLampTb.setOnCheckedChangeListener(this);
	mLamp2Tb = (ToggleButton)  view.findViewById(R.id.tb_lamp2);
	mLamp2Tb.setOnCheckedChangeListener(this);
	
}

private void initData() {
		// TODO Auto-generated method stub
		ControlUtils.getData();
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {
			@Override
			public void onResult(final DeviceBean bean) {
				getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						ContentValues	values=new ContentValues();
						values.put("tempe", DeviceBean.getTemperature());
						values.put("illumination", DeviceBean.getIllumination());
						MainActivity.mDatabase.insert(MainActivity.mHelper.Illumination_table, null, values);
						if (!TextUtils.isEmpty(bean.getTemperature())){
							mTempTv.setText(bean.getTemperature());
						}
						if (!TextUtils.isEmpty(bean.getHumidity())){
							mHumidityTv.setText(bean.getHumidity());
						}
						if (!TextUtils.isEmpty(bean.getGas())){
							mGasTv.setText(bean.getGas());
						}
						if (!TextUtils.isEmpty(bean.getIllumination())){
							mIlluminationTv.setText(bean.getIllumination());
						}
						if (!TextUtils.isEmpty(bean.getPM25())){
							mPm25Tv.setText(bean.getPM25());
						}
						if (!TextUtils.isEmpty(bean.getSmoke())){
							mSmokeTv.setText(bean.getSmoke());
						}
						if (!TextUtils.isEmpty(bean.getCo2())){
							mCo2Tv.setText(bean.getCo2());
						}
						if (!TextUtils.isEmpty(bean.getAirPressure())){
							mPressureTv.setText(bean.getAirPressure());
						}
						if (!TextUtils.isEmpty(bean.getStateHumanInfrared())
								&& bean.getStateHumanInfrared().equals(
										ConstantUtil.CLOSE))
							mStateHumanInfraredTv.setText("无人");
						else{
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
	switch (buttonView.getId()) {
	case R.id.tb_lamp:// 射灯
		if (isChecked) {
			ControlUtils.control(ConstantUtil.Lamp,
					ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
		} else {
			ControlUtils.control(ConstantUtil.Lamp,
					ConstantUtil.CHANNEL_1, ConstantUtil.CLOSE);
		}
		break;
	case R.id.tb_lamp2:// 射灯
		if (isChecked) {
			ControlUtils.control(ConstantUtil.Lamp,
					ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
		} else {
			ControlUtils.control(ConstantUtil.Lamp,
					ConstantUtil.CHANNEL_2, ConstantUtil.CLOSE);
		}
		break;
	case R.id.tb_door:// 门禁
		ControlUtils.control(ConstantUtil.RFID_Open_Door,
				ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
		break;
	case R.id.tb_fan:// 风扇
		if (isChecked)
			ControlUtils.control(ConstantUtil.Fan,
					ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
		else
			ControlUtils.control(ConstantUtil.Fan,
					ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
		break;
	case R.id.tb_alarm:// 报警灯
		if (isChecked)
			ControlUtils.control(ConstantUtil.WarningLight,
					ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
		else
			ControlUtils.control(ConstantUtil.WarningLight,
					ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
		break;
	}
}



}
