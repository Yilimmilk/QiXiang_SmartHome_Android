package com.example.a521i;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DeviceBean;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract.Contacts.Data;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

public class Frag3 extends Fragment implements OnCheckedChangeListener{
	private RadioGroup mRg;
	private Spinner sp1,sp2;
	private CheckBox cb1;
	private View view;
	private EditText value;
	private String device;
	private Handler mHandler=new Handler();
	private String sj;
	private String dx;
	private String mValueEt;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_3, null);
		initViews(view);
		return view;
	}

	private void initViews(View view) {
		// TODO Auto-generated method stub
		sp2 = (Spinner)view. findViewById(R.id.Spinner01);
		sp1 = (Spinner) view.findViewById(R.id.spinner1);
		value = (EditText) view.findViewById(R.id.shuzhi);
		cb1 = (CheckBox)  view.findViewById(R.id.checkBox1);
		cb1.setOnCheckedChangeListener(this);
		Button mBtn=(Button)view.findViewById(R.id.btn);
		mBtn.setOnClickListener(new OnClickListener() {
			
			

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final AlertDialog Dialog	=new AlertDialog.Builder(getActivity()).create();
				Dialog.show();
				Dialog.setContentView(R.layout.dialog);
				mRg=(RadioGroup)Dialog.getWindow().findViewById(R.id.rg);
				Dialog.getWindow().findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
					
				

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						  RadioButton radioButton = (RadioButton)Dialog.getWindow().findViewById(mRg.getCheckedRadioButtonId());
					       device=radioButton.getText().toString();
					       Dialog.dismiss();
					}
				});
				
			}
		});
	}

	Runnable mRunnable=new Runnable() {
		public void run() {
		
			if(sj.equals("温度")){
				if(dx.equals("大于")){
					if (!TextUtils.isEmpty(DeviceBean.getTemperature())
							&& Double.parseDouble(DeviceBean.getTemperature()) > Integer
									.parseInt(mValueEt)) {// 判断是否符合条件
						Data();
					}
				}else{
					if (!TextUtils.isEmpty(DeviceBean.getTemperature())
							&& Double.parseDouble(DeviceBean.getTemperature()) < Integer
									.parseInt(mValueEt)) {// 判断是否符合条件
						Data();
					}
				}
			}else if(sj.equals("湿度")){
				if(dx.equals("大于")){
					if (!TextUtils.isEmpty(DeviceBean.getHumidity())
							&& Double.parseDouble(DeviceBean.getHumidity()) > Integer
									.parseInt(mValueEt)) {// 判断是否符合条件
						Data();
					}
				}else{
					if (!TextUtils.isEmpty(DeviceBean.getHumidity())
							&& Double.parseDouble(DeviceBean.getHumidity()) < Integer
									.parseInt(mValueEt)) {// 判断是否符合条件
						Data();
					}
				}
			}else if(sj.equals("光照")){
				if(dx.equals("大于")){
					if (!TextUtils.isEmpty(DeviceBean.getIllumination())
							&& Double.parseDouble(DeviceBean.getIllumination()) > Integer
									.parseInt(mValueEt)) {// 判断是否符合条件
						Data();
					}
				}else{
					if (!TextUtils.isEmpty(DeviceBean.getIllumination())
							&& Double.parseDouble(DeviceBean.getIllumination()) < Integer
									.parseInt(mValueEt)) {// 判断是否符合条件
						Data();
					}
				}
			}else if(sj.equals("气压")){
				if(dx.equals("大于")){
					if (!TextUtils.isEmpty(DeviceBean.getAirPressure())
							&& Double.parseDouble(DeviceBean.getAirPressure()) > Integer
									.parseInt(mValueEt)) {// 判断是否符合条件
						Data();
					}
				}else{
					if (!TextUtils.isEmpty(DeviceBean.getAirPressure())
							&& Double.parseDouble(DeviceBean.getAirPressure()) < Integer
									.parseInt(mValueEt)) {// 判断是否符合条件
						Data();
					}
				}
			}
			mHandler.postDelayed(mRunnable, 1000);
		}
	};
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		switch (buttonView.getId()) {
		case R.id.checkBox1:
			sj = sp1.getSelectedItem().toString();
			dx = sp2.getSelectedItem().toString();
			mValueEt = value.getText().toString();
		if(TextUtils.isEmpty(mValueEt)){
			Toast.makeText(getActivity(), "条件指不能为空", 0).show();
			return;
		  }
			if(isChecked){
				mHandler.postDelayed(mRunnable, 500);
				if(sj.equals("温度")){
					if(dx.equals("大于")){
						if (!TextUtils.isEmpty(DeviceBean.getTemperature())
								&& Double.parseDouble(DeviceBean.getTemperature()) > Integer
										.parseInt(mValueEt)) {// 判断是否符合条件
							Data();
						}
					}else{
						if (!TextUtils.isEmpty(DeviceBean.getTemperature())
								&& Double.parseDouble(DeviceBean.getTemperature()) < Integer
										.parseInt(mValueEt)) {// 判断是否符合条件
							Data();
						}
					}
				}else if(sj.equals("湿度")){
					if(dx.equals("大于")){
						if (!TextUtils.isEmpty(DeviceBean.getHumidity())
								&& Double.parseDouble(DeviceBean.getHumidity()) > Integer
										.parseInt(mValueEt)) {// 判断是否符合条件
							Data();
						}
					}else{
						if (!TextUtils.isEmpty(DeviceBean.getHumidity())
								&& Double.parseDouble(DeviceBean.getHumidity()) < Integer
										.parseInt(mValueEt)) {// 判断是否符合条件
							Data();
						}
					}
				}else if(sj.equals("光照")){
					if(dx.equals("大于")){
						if (!TextUtils.isEmpty(DeviceBean.getIllumination())
								&& Double.parseDouble(DeviceBean.getIllumination()) > Integer
										.parseInt(mValueEt)) {// 判断是否符合条件
							Data();
						}
					}else{
						if (!TextUtils.isEmpty(DeviceBean.getIllumination())
								&& Double.parseDouble(DeviceBean.getIllumination()) < Integer
										.parseInt(mValueEt)) {// 判断是否符合条件
							Data();
						}
					}
				}else if(sj.equals("气压")){
					if(dx.equals("大于")){
						if (!TextUtils.isEmpty(DeviceBean.getAirPressure())
								&& Double.parseDouble(DeviceBean.getAirPressure()) > Integer
										.parseInt(mValueEt)) {// 判断是否符合条件
							Data();
						}
					}else{
						if (!TextUtils.isEmpty(DeviceBean.getAirPressure())
								&& Double.parseDouble(DeviceBean.getAirPressure()) < Integer
										.parseInt(mValueEt)) {// 判断是否符合条件
							Data();
						}
					}
				}
			}
			break;

		default:
			break;
		}
	
	}

	private void Data() {
		// TODO Auto-generated method stub
		if (device.contains("风扇")){
			ControlUtils
					.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL,
							ConstantUtil.OPEN);
		}else if (device.contains("射灯")){
			ControlUtils
					.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL,
							ConstantUtil.OPEN);
		}else if(device.contains("窗帘")){
			ControlUtils
			.control(ConstantUtil.Curtain,
					ConstantUtil.CHANNEL_1,
					ConstantUtil.OPEN);
		}else if(device.contains("报警灯")){
			ControlUtils
			.control(ConstantUtil.WarningLight,
					ConstantUtil.CHANNEL_ALL,
					ConstantUtil.OPEN);
		}
	}
	
	

}
