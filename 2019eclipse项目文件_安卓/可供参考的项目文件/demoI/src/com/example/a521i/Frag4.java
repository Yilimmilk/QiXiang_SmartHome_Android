package com.example.a521i;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DeviceBean;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.ToggleButton;

public class Frag4 extends Fragment{
	private View view;
	private RadioButton rb1;
	private RadioButton rb2;
	private RadioButton rb3;
private Handler mHandler=new Handler();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_4, null);
		rb1 = (RadioButton) view.findViewById(R.id.btms);
		rb2 = (RadioButton) view.findViewById(R.id.ywms);
		rb3 = (RadioButton) view.findViewById(R.id.fdms);
		ToggleButton ms=(ToggleButton)view.findViewById(R.id.ms);
		ms.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					mHandler.postDelayed(runnable, 500);
				
			}else{
				mHandler.removeCallbacks(runnable);
			}}
		});
		return view;
	}

private Runnable	runnable= new Runnable() {
		public void run() {
		
			if(rb1.isChecked()){
				ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,ConstantUtil.CLOSE);
				if(!TextUtils.isEmpty(DeviceBean.getIllumination())&&Double.parseDouble(DeviceBean.getIllumination())>150){
					ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1,ConstantUtil.OPEN);
				}else{
					ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3,ConstantUtil.OPEN);
				}
			}else if(rb2.isChecked()){
				ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,ConstantUtil.OPEN);
				if(!TextUtils.isEmpty(DeviceBean.getIllumination())&&Double.parseDouble(DeviceBean.getIllumination())<50){
					ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,ConstantUtil.OPEN);
				}else{
					ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,ConstantUtil.CLOSE);
				}
			}else if(rb3.isChecked()){
				if(!TextUtils.isEmpty(DeviceBean.getStateHumanInfrared())&&DeviceBean.getStateHumanInfrared().equals(ConstantUtil.CLOSE)){
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,ConstantUtil.OPEN);
					ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL,ConstantUtil.OPEN);
				}else{
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,ConstantUtil.CLOSE);
					ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL,ConstantUtil.CLOSE);
					}
			}
		   mHandler.postDelayed(runnable, 1000);
		}
	};
	

}
