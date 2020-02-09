package com.example.demoB;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class RoomControlActivity extends Activity implements OnCheckedChangeListener,OnClickListener{
private String mRoomName;
private TextView mRoomNameTv;
private ToggleButton mAlarmLamp,mLamp,mFan;
private Switch mAir,mTv,mDVD,mDoor;
private Button mStop,mColse,mOpen;

@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_roomcontrol);
	mRoomName=getIntent().getStringExtra("roomName");
	initViews();
}

private void initViews() {
	// TODO Auto-generated method stub
	mRoomNameTv=(TextView)findViewById(R.id.tv_room);
	mRoomNameTv.setText("房间号:"+mRoomName);
	mAlarmLamp=(ToggleButton)findViewById(R.id.tb_alarmlamp);
	mAlarmLamp.setOnCheckedChangeListener(this);
	mLamp=(ToggleButton)findViewById(R.id.tb_lamp);
	mLamp.setOnCheckedChangeListener(this);
	mFan=(ToggleButton)findViewById(R.id.tb_fan);
	mFan.setOnCheckedChangeListener(this);
	
	mAir=(Switch)findViewById(R.id.sw_air);
	mAir.setOnCheckedChangeListener(this);
	
	mTv=(Switch)findViewById(R.id.sw_tv);
	mTv.setOnCheckedChangeListener(this);
	
	mDVD=(Switch)findViewById(R.id.sw_dvd);
	mDVD.setOnCheckedChangeListener(this);
	mDoor=(Switch)findViewById(R.id.sw_door);
	mDoor.setOnCheckedChangeListener(this);
	
	mStop=(Button)findViewById(R.id.btn_stop);
	mStop.setOnClickListener(this);
	mColse=(Button)findViewById(R.id.btn_colse);
	mColse.setOnClickListener(this);
	mOpen=(Button)findViewById(R.id.btn_open);
	mOpen.setOnClickListener(this);
}


@Override
public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	// TODO Auto-generated method stub
	switch (buttonView.getId()) {
	case R.id.tb_alarmlamp:
		if(isChecked){
			ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
		}else{
			ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
		}
		break;
	case R.id.tb_lamp:
		if(isChecked){
			ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
		}else{
			ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
		}
		break;
	case R.id.tb_fan:
		if(isChecked){
			ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
		}else{
			ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
		}
		break;
	case R.id.sw_air:
		if(isChecked){
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
		}else{
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, ConstantUtil.CHANNEL_1,ConstantUtil.CLOSE);
		}
		break;
	case R.id.sw_tv:
		if(isChecked){
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
		}else{
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, ConstantUtil.CHANNEL_2,ConstantUtil.CLOSE);
		}
		break;
	case R.id.sw_dvd:
		if(isChecked){
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
		}else{
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, ConstantUtil.CHANNEL_3,ConstantUtil.CLOSE);
		}
		break;
	case R.id.sw_door:
			ControlUtils.control(ConstantUtil.RFID_Open_Door, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
		break;
	default:
		break;
	}
}

@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	switch (v.getId()) {
	case R.id.btn_stop:
		ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
		break;
	case R.id.btn_colse:
		ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
		break;
	case R.id.btn_open:
		ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
		break;

	default:
		break;
	}
}




}
