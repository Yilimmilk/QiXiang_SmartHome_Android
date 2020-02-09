package com.example.demog;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;




public class DeviceFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.frament_device, null);
		initViews(view);
		return view;
	}

	private void initViews(View view) {
		// TODO Auto-generated method stub
		getFragmentManager().beginTransaction().replace(R.id.fragment_device, new SensorFragment()).commit();
		RadioGroup	mDeviceRG=(RadioGroup)view.findViewById(R.id.rg_device);
		mDeviceRG.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(checkedId==R.id.rb_sensor){
					getFragmentManager().beginTransaction().replace(R.id.fragment_device, new SensorFragment()).commit();
				}else if(checkedId==R.id.rb_control){
					getFragmentManager().beginTransaction().replace(R.id.fragment_device, new ControlFragment()).commit();
					
				}
			}
		});
	}
	
	
}
