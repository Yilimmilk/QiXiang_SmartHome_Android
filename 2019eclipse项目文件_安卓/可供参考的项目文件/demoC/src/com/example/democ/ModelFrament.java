package com.example.democ;

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
import android.widget.RadioGroup;
import android.widget.ToggleButton;


public class ModelFrament extends Fragment {
	private int index=0;
	private RadioGroup radioGroup;
	private Handler mHandler=new Handler();
	
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	View view=inflater.inflate(R.layout.frament_model, null);
	initViews(view);
	return view;
}

private void initViews(View view) {
	// TODO Auto-generated method stub
		radioGroup=(RadioGroup)view.findViewById(R.id.rg_model);
/*	radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
		
			if(checkedId==R.id.radioButton1){
				index=0;
			}else if(checkedId==R.id.radioButton2){
				index=1;
			}else if(checkedId==R.id.radioButton3){   
				index=2;
			}else if(checkedId==R.id.radioButton4){   
				index=3;
			}
		}
	});
	*/
	ToggleButton toggleButton=(ToggleButton)view.findViewById(R.id.toggleButton);
	toggleButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub

			if(radioGroup.getCheckedRadioButtonId()==R.id.radioButton1){
				index=0;
			}else if(radioGroup.getCheckedRadioButtonId()==R.id.radioButton2){
				index=1;
			}else if(radioGroup.getCheckedRadioButtonId()==R.id.radioButton3){   
				index=2;
			}else if(radioGroup.getCheckedRadioButtonId()==R.id.radioButton4){   
				index=3;
			}
			if(isChecked){
				if(index==0){
				   mHandler.postDelayed(Daythread, 500);
				}else if(index==1){
					 mHandler.postDelayed(thread, 500);
				}else if(index==2){
					 mHandler.postDelayed(thread2, 500);
				}
				else if(index==3){
					 mHandler.postDelayed(thread3, 500);
				}
			}else{
				if(Daythread!=null){
					mHandler.removeCallbacks(Daythread);
				}
				if(thread!=null){
					mHandler.removeCallbacks(thread);
				}
				if(thread2!=null){
					mHandler.removeCallbacks(thread2);
				}
				if(thread3!=null){
					mHandler.removeCallbacks(thread3);
				}
			}
		}
	});;
}



/**
 * 白天模式
 */
Thread Daythread = new Thread(new Runnable() {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (!TextUtils.isEmpty(DeviceBean.getLamp())
				&& !DeviceBean.getLamp().equals(ConstantUtil.CLOSE)) {
			ControlUtils.control(ConstantUtil.Lamp,
					ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
		}
		if (!TextUtils.isEmpty(DeviceBean.getCurtain())
				&& DeviceBean.getCurtain().equals("3")) {
			ControlUtils.control(ConstantUtil.Curtain,
					ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
		}
		if (!TextUtils.isEmpty(DeviceBean.getSmoke())
				&& Integer.parseInt(DeviceBean.getSmoke()) > 400) {
			ControlUtils.control(ConstantUtil.Fan,
					ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
		}
		mHandler.postDelayed(Daythread, 500);
	}

});


/**
 * 夜晚模式
 */
Thread thread = new Thread(new Runnable() {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (!TextUtils.isEmpty(DeviceBean.getLamp())
				&& !DeviceBean.getLamp().equals(ConstantUtil.CLOSE)) {
			ControlUtils.control(ConstantUtil.Lamp,
					ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
		}
		if (!TextUtils.isEmpty(DeviceBean.getCurtain())
				&& DeviceBean.getCurtain().equals("1")) {
			ControlUtils.control(ConstantUtil.Curtain,
					ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
		}
		if(!TextUtils.isEmpty(DeviceBean.getIllumination())
				&& Integer.parseInt(DeviceBean.getSmoke()) > 500){
			ControlUtils.control(ConstantUtil.Lamp,
					ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
		}else if (!TextUtils.isEmpty(DeviceBean.getIllumination())
				&& Integer.parseInt(DeviceBean.getSmoke()) < 200) {
			ControlUtils.control(ConstantUtil.Lamp,
					ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
		}
		mHandler.postDelayed(thread, 500);
	}

});
/**
 * 防盗模式
 */
Thread thread2 = new Thread(new Runnable() {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		 ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
					ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			if (!TextUtils.isEmpty(DeviceBean.getLamp())
					&& !DeviceBean.getLamp().equals(ConstantUtil.OPEN)) {
			   ControlUtils.control(ConstantUtil.Lamp,
					ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
			}else{
				  ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
			}
	
		mHandler.postDelayed(thread2, 2000);
	}

});

/**
 * 防盗模式
 */
Thread thread3 = new Thread(new Runnable() {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (!TextUtils.isEmpty(DeviceBean.getStateHumanInfrared())
				&& !DeviceBean.getStateHumanInfrared().equals(ConstantUtil.OPEN)) {
			if (!TextUtils.isEmpty(DeviceBean.getLamp())
					&& !DeviceBean.getLamp().equals(ConstantUtil.OPEN)) {
			  ControlUtils.control(ConstantUtil.Lamp,
					ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
			}
			if (!TextUtils.isEmpty(DeviceBean.getWarningLight())
					&& !DeviceBean.getWarningLight().equals(ConstantUtil.OPEN)) {
			  ControlUtils.control(ConstantUtil.WarningLight,
					ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
			}
		}
		
		mHandler.postDelayed(thread3, 500);
	}

});
}
