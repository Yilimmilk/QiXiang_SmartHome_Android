package com.example.demog;

import java.util.HashMap;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.SocketClient;
import android.app.Fragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class SensorFragment extends Fragment {
	private TextView mTempTv, mHumidityTv, mGasTv, mIlluminationTv, mPm25Tv,
	mPressureTv, mSmokeTv, mCo2Tv, mStateHumanInfraredTv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.frament_sensor, null);
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
	}
	
	
	private void initData() {
		// TODO Auto-generated method stub
		ControlUtils.getData();
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {
			@Override
			public void onResult(final DeviceBean bean) {
				getActivity().runOnUiThread(new Runnable() {
					private int tempNumber;
					private int illuminationNumber;

					@Override
					public void run() {
				
						if (!TextUtils.isEmpty(bean.getTemperature())){
							mTempTv.setText(bean.getTemperature());
							try {
								if(Double.parseDouble(bean.getTemperature())<=19&&Double.parseDouble(bean.getTemperature())>0){
									tempNumber=0;
								}else if(Double.parseDouble(bean.getTemperature())<=30&&Double.parseDouble(bean.getTemperature())>20){
									tempNumber=1;
								}else if(Double.parseDouble(bean.getTemperature())<=39&&Double.parseDouble(bean.getTemperature())>30){
									tempNumber=2;
								}
								  ContentValues	Values =new ContentValues();
								  Values.put("device","温度");
								  Values.put("time",MainActivity.getTime());
								  Values.put("number",tempNumber);
								  MainActivity.mDatabase.insert(MainActivity.mHelper.data_table, null, Values);
							} catch (Exception e) {
								// TODO: handle exception
							}	
						}
						if (!TextUtils.isEmpty(bean.getHumidity())){
							mHumidityTv.setText(bean.getHumidity());
						}
						if (!TextUtils.isEmpty(bean.getGas())){
							mGasTv.setText(bean.getGas());
						}
						if (!TextUtils.isEmpty(bean.getIllumination())){
							mIlluminationTv.setText(bean.getIllumination());
							try {
							
								if(Double.parseDouble(bean.getIllumination())<=300&&Double.parseDouble(bean.getIllumination())>0){
									illuminationNumber=0;
								}else if(Double.parseDouble(bean.getIllumination())<=699&&Double.parseDouble(bean.getIllumination())>301){
									illuminationNumber=1;
								}else if(Double.parseDouble(bean.getIllumination())<=1500&&Double.parseDouble(bean.getIllumination())>700){
									illuminationNumber=2;
								}
								  ContentValues	Values =new ContentValues();
								  Values.put("device","光照度");
								  Values.put("time",MainActivity.getTime());
								  Values.put("number",illuminationNumber);
								  MainActivity.mDatabase.insert(MainActivity.mHelper.data_table, null, Values);
							} catch (Exception e) {
								// TODO: handle exception
							}	
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
	
}
