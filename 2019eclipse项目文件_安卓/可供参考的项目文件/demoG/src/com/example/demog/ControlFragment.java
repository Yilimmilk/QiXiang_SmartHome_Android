package com.example.demog;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;


public class ControlFragment extends Fragment implements OnCheckedChangeListener,OnClickListener{
	
	private Switch mFanTb,mLampTb,mAlarmTb,mDoorTb;
	private Button mSendBtn;
	private EditText mEt;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.frament_control, null);
		initViews(view);
		return view;
	}

	private void initViews(View view) {
		// TODO Auto-generated method stub
		mEt = (EditText)  view.findViewById(R.id.et);
		
		mSendBtn = (Button)  view.findViewById(R.id.btn_send);
		mSendBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(TextUtils.isEmpty(mEt.getText().toString())){
					Toast.makeText(getActivity(), "Í¨µÀºÅ²»ÄÜÎª¿Õ£¡", 0).show();
					return;
				}
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, mEt.getText().toString(), ConstantUtil.OPEN);
			}
		});
		
		TextView	mCurtainOpenTv = (TextView) view.findViewById(R.id.curtain_open);// ´°Á±¿ª
		mCurtainOpenTv.setOnClickListener(this);
		TextView mCurtainStopTv = (TextView)view. findViewById(R.id.curtain_stop);// ´°Á±Í£
		mCurtainStopTv.setOnClickListener(this);
		TextView mCurtainCloseTv = (TextView)view. findViewById(R.id.curtain_close);// ´°Á±¹Ø
		mCurtainCloseTv.setOnClickListener(this);
		mAlarmTb = (Switch)  view.findViewById(R.id.tb_alarm);
		mAlarmTb.setOnCheckedChangeListener(this);
		mDoorTb = (Switch)  view.findViewById(R.id.tb_door);
		mDoorTb.setOnCheckedChangeListener(this);
		
		mFanTb = (Switch)  view.findViewById(R.id.tb_fan);
		mFanTb.setOnCheckedChangeListener(this);
		mLampTb = (Switch)  view.findViewById(R.id.tb_lamp);
		mLampTb.setOnCheckedChangeListener(this);
	}
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		switch (buttonView.getId()) {
		case R.id.tb_lamp:// ÉäµÆ
			if (isChecked) {
				ControlUtils.control(ConstantUtil.Lamp,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			} else {
				ControlUtils.control(ConstantUtil.Lamp,
						ConstantUtil.CHANNEL_1, ConstantUtil.CLOSE);
			}
			break;
	
		case R.id.tb_door:// ÃÅ½û
			ControlUtils.control(ConstantUtil.RFID_Open_Door,
					ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			break;
		case R.id.tb_fan:// ·çÉÈ
			if (isChecked)
				ControlUtils.control(ConstantUtil.Fan,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
			else
				ControlUtils.control(ConstantUtil.Fan,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
			break;
		case R.id.tb_alarm:// ±¨¾¯µÆ
			if (isChecked)
				ControlUtils.control(ConstantUtil.WarningLight,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
			else
				ControlUtils.control(ConstantUtil.WarningLight,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
			break;
		}
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.curtain_open:// ´°Á±¿ª
			ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1,
					ConstantUtil.OPEN);
			break;
		case R.id.curtain_stop:// ´°Á±Í£
			ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_2,
					ConstantUtil.OPEN);
			break;
		case R.id.curtain_close:// ´°Á±¹Ø
			// ¹Ø
			ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3,
					ConstantUtil.OPEN);
			break;
		}}
}
