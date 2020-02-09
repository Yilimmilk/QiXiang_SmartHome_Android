package com.example.a521i;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DeviceBean;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class Frag2 extends Fragment implements OnCheckedChangeListener{

	private ToggleButton tg1,tg2,tg3,tg4,tg5,tg6,tg7,tg8;
	
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		view = inflater.inflate(R.layout.fragment_2, null);
		initViews(view);
		return view;
	}

	private void initViews(View view) {
		// TODO Auto-generated method stub
		tg1 = (ToggleButton) view.findViewById(R.id.cl);
		tg1.setOnCheckedChangeListener(this);
		tg2 = (ToggleButton)view. findViewById(R.id.bjd);
		tg2.setOnCheckedChangeListener(this);
		tg3 = (ToggleButton)view. findViewById(R.id.sdn);
		tg3.setOnCheckedChangeListener(this);
		tg4 = (ToggleButton) view.findViewById(R.id.hqs);
		tg4.setOnCheckedChangeListener(this);
		tg5 = (ToggleButton)view. findViewById(R.id.kt);
		tg5.setOnCheckedChangeListener(this);
		tg6 = (ToggleButton)view. findViewById(R.id.dvd);
		tg6.setOnCheckedChangeListener(this);
		tg7 = (ToggleButton)view. findViewById(R.id.dsj);
		tg7.setOnCheckedChangeListener(this);
		tg8 = (ToggleButton)view. findViewById(R.id.mj);
		tg8.setOnCheckedChangeListener(this);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		if(!buttonView.isPressed())return;
		switch(buttonView.getId()){
		case R.id.cl:
			if(isChecked){
				ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}else{
				ControlUtils.control(ConstantUtil.Curtain,ConstantUtil.CHANNEL_1,ConstantUtil.CLOSE);
			}
			break;
		case R.id.bjd:
			if(isChecked){
				ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
			}else{
				ControlUtils.control(ConstantUtil.WarningLight,ConstantUtil.CHANNEL_ALL,ConstantUtil.CLOSE);
			}
			break;
		case R.id.sdn:
			if(isChecked){
				ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
			}else{
				ControlUtils.control(ConstantUtil.Lamp,ConstantUtil.CHANNEL_ALL,ConstantUtil.CLOSE);
			}
			break;
		case R.id.kt:
			if(isChecked){
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}else{
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,ConstantUtil.CHANNEL_1,ConstantUtil.CLOSE);
			}
			break;
		case R.id.dvd:
			if(isChecked){
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
			}else{
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,ConstantUtil.CHANNEL_2,ConstantUtil.CLOSE);
			}
			break;
		case R.id.dsj:
			if(isChecked){
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
			}else{
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,ConstantUtil.CHANNEL_3,ConstantUtil.CLOSE);
			}
			break;
		case R.id.hqs:
			if(isChecked){
				ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
			}else{
				ControlUtils.control(ConstantUtil.Fan,ConstantUtil.CHANNEL_ALL,ConstantUtil.CLOSE);
			}
			break;
		case R.id.mj:
				ControlUtils.control(ConstantUtil.RFID_Open_Door, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			tg8.setChecked(false);
			break;
		
		}
	}

}
