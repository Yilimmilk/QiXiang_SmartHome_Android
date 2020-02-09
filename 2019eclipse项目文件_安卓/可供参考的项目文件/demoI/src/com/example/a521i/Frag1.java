package com.example.a521i;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.SocketClient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Frag1 extends Fragment {
	private TextView wd11,sd11,rq1,gz1,pm1,co1,yw1,hw1,qy1;
	
	
	
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		view = inflater.inflate(R.layout.fragment_1, null);
		initViews(view);
		initdata();
		return view;
	}
	private void initViews(View view) {
		// TODO Auto-generated method stub
		    sd11 = (TextView) view.findViewById(R.id.sd1);
			wd11 = (TextView) view.findViewById(R.id.wd1);
			qy1 = (TextView) view.findViewById(R.id.qy);
			rq1 = (TextView) view.findViewById(R.id.rq);
			yw1 = (TextView) view.findViewById(R.id.yw);
			gz1 = (TextView) view.findViewById(R.id.gz);
			pm1 = (TextView) view.findViewById(R.id.pm);
			co1 = (TextView)view. findViewById(R.id.co);
			hw1 = (TextView)view. findViewById(R.id.hw);
	}


	private void initdata() {
		// TODO Auto-generated method stub
		ControlUtils.getData();
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {

			@Override
			public void onResult(DeviceBean arg0) {
				// TODO Auto-generated method stub
				getActivity().runOnUiThread(new Runnable() {
					public void run() {
						if(!TextUtils.isEmpty(DeviceBean.getAirPressure())){
							qy1.setText(DeviceBean.getTemperature());
						}	
						if(!TextUtils.isEmpty(DeviceBean.getGas())){
							rq1.setText(DeviceBean.getGas());
						}	
						if(!TextUtils.isEmpty(DeviceBean.getIllumination())){
							gz1.setText(DeviceBean.getIllumination());
						}	
						if(!TextUtils.isEmpty(DeviceBean.getSmoke())){
							yw1.setText(DeviceBean.getSmoke());
						}	
						if(!TextUtils.isEmpty(DeviceBean.getCo2())){
							co1.setText(DeviceBean.getCo2());
						}	
						if(!TextUtils.isEmpty(DeviceBean.getPM25())){
							pm1.setText(DeviceBean.getPM25());
						}	
						if(!TextUtils.isEmpty(DeviceBean.getTemperature())){
							wd11.setText(DeviceBean.getTemperature());
						}	
						if(!TextUtils.isEmpty(DeviceBean.getHumidity())){
							sd11.setText(DeviceBean.getHumidity());
						}	
						if(!TextUtils.isEmpty(DeviceBean.getStateHumanInfrared())&&DeviceBean.getStateHumanInfrared().equals(ConstantUtil.CLOSE)){
							hw1.setText("无人");
						}else{
							hw1.setText("有人");
						}
					
					}
				});
			}
		});
	}
 
	
	

}
