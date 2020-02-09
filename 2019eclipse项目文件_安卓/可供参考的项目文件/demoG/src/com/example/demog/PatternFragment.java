package com.example.demog;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DeviceBean;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;


public class PatternFragment extends Fragment implements OnCheckedChangeListener{
	private Handler mHandler=new Handler();
	private String state;
	private Spinner mConditionSp;
	private Switch mState;
	private EditText mValueEt;
	private CheckBox cb_lamp,cb_fan,cb_alarm,cb_door,channel_1,channel_2,channel_3,cb_curtain;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.frament_pattern, null);
		initViews(view);
		return view;
	}

	private void initViews(View view) {
		// TODO Auto-generated method stub
		CheckBox cb_security=(CheckBox)view.findViewById( R.id.cb_security);
		cb_security.setOnCheckedChangeListener(this);
		CheckBox cb_custom=(CheckBox)view.findViewById( R.id.cb_custom);
		cb_custom.setOnCheckedChangeListener(this);
		CheckBox cb_leavehome=(CheckBox)view.findViewById( R.id.cb_leavehome);
		cb_leavehome.setOnCheckedChangeListener(this);
		mConditionSp = (Spinner) view.findViewById(R.id.sp_condition);
		mState= (Switch) view.findViewById(R.id.sw_state);
		mValueEt = (EditText) view.findViewById(R.id.et_value);
		cb_lamp=(CheckBox)view.findViewById( R.id.cb_lamp);
		cb_fan=(CheckBox)view.findViewById( R.id.cb_fan);
		cb_alarm=(CheckBox)view.findViewById( R.id.cb_alarm);
		cb_door=(CheckBox)view.findViewById( R.id.cb_door);
		channel_1=(CheckBox)view.findViewById( R.id.channel_1);
		channel_2=(CheckBox)view.findViewById( R.id.channel_2);
		channel_3=(CheckBox)view.findViewById( R.id.channel_3);
		cb_curtain=(CheckBox)view.findViewById( R.id.cb_curtain);
		
	}
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		switch (buttonView.getId()) {
		
		case R.id.cb_leavehome:
			if (isChecked) {
				mHandler.postDelayed(leavehomeThread, 500);
			} else {
				mHandler.removeCallbacks(leavehomeThread);
			}
			break;
		case R.id.cb_security:// 安防模式
			if (isChecked) {
				mHandler.postDelayed(securityThread, 500);
			} else {
				mHandler.removeCallbacks(securityThread);
			}
			break;
		case R.id.cb_custom:// 自定义模式
			if (TextUtils.isEmpty(mValueEt.getText().toString())) {
				Toast.makeText(getActivity(), "条件值不能为空！", 0).show();
				return;
			}
			if (isChecked) {
				mHandler.postDelayed(customOpenThread, 500);
			} else {
				mHandler.removeCallbacks(customOpenThread);
				mState.setChecked(false);
				cb_lamp.setChecked(false);
				cb_fan.setChecked(false);
				cb_alarm.setChecked(false);
				cb_door.setChecked(false);
				channel_1.setChecked(false);
				channel_2.setChecked(false);
				channel_3.setChecked(false);
				channel_3.setChecked(false);
				cb_curtain.setChecked(false);
			}
			break;
		}
	}
	
	/**
	 * 回家
	 */
	Thread leavehomeThread = new Thread(new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if ((!TextUtils.isEmpty(DeviceBean.getStateHumanInfrared())
					&& !DeviceBean.getStateHumanInfrared().equals(
							ConstantUtil.CLOSE))||(!TextUtils.isEmpty(DeviceBean.getGas())
							&&Double.parseDouble(DeviceBean.getGas())>=800)) {
				ControlUtils.control(ConstantUtil.WarningLight,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
			}
			mHandler.postDelayed(securityThread, 500);
		}

	});
	
	
	
	
/**
 * 安防
 */
Thread securityThread = new Thread(new Runnable() {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (!TextUtils.isEmpty(DeviceBean.getStateHumanInfrared())
				&& !DeviceBean.getStateHumanInfrared().equals(
						ConstantUtil.CLOSE)) {
			ControlUtils.control(ConstantUtil.WarningLight,
					ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
		}
		mHandler.postDelayed(securityThread, 500);
	}

});

/**
 * 自定义开启
 */
Thread customOpenThread = new Thread(new Runnable() {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (mConditionSp.getSelectedItem().toString().equals(">")) {
			if(Double.parseDouble(DeviceBean.getTemperature())>Double.parseDouble(mValueEt.getText().toString())){
				initData();
				
			}
		}else if(mConditionSp.getSelectedItem().toString().equals("<")){
            if(Double.parseDouble(DeviceBean.getTemperature())<Double.parseDouble(mValueEt.getText().toString())){
				
			}
		}
		mHandler.postDelayed(securityThread, 500);
	}

	
});




private void initData() {
	// TODO Auto-generated method stub
	if(mState.isChecked()==true){
		state= ConstantUtil.OPEN;
	}else{
		state= ConstantUtil.CLOSE;
	}
	if(cb_lamp.isChecked()==true){
		ControlUtils.control(ConstantUtil.Lamp,
				ConstantUtil.CHANNEL_ALL, state);
	}
    if(cb_fan.isChecked()==true){
		ControlUtils.control(ConstantUtil.Fan,
				ConstantUtil.CHANNEL_ALL, state);
	}
    if(cb_alarm.isChecked()==true){
		ControlUtils.control(ConstantUtil.WarningLight,
				ConstantUtil.CHANNEL_ALL, state);
	}
    if(cb_door.isChecked()==true){
  		ControlUtils.control(ConstantUtil.RFID_Open_Door,
  				ConstantUtil.CHANNEL_1, state);
  	}
    if(channel_1.isChecked()==true){
  		ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
  				ConstantUtil.CHANNEL_1, state);
  	}
    if(channel_2.isChecked()==true){
  		ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
  				ConstantUtil.CHANNEL_2, state);
  	}
    if(channel_3.isChecked()==true){
  		ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
  				ConstantUtil.CHANNEL_3, state);
  	}
    if(cb_curtain.isChecked()==true){
    	if(mState.isChecked()==true){
  		   ControlUtils.control(ConstantUtil.Curtain,
  				ConstantUtil.CHANNEL_1,  ConstantUtil.OPEN);
    	}else{
    		ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3,
					ConstantUtil.OPEN);
    	}
  	}
    
}



}
