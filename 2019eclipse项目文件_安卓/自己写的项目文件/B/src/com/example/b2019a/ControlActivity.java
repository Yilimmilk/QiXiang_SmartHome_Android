package com.example.b2019a;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.SocketClient;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ControlActivity extends Activity implements OnClickListener,OnCheckedChangeListener {
	
	private TextView roomnumCenter;
	private ToggleButton baojingdeng,fengshan,shedeng;
	private Button kongtiao,dianshiji,dvd,menjin,clkai,clguan,clting;
	
	private int select;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_control);
		
		Intent intent =getIntent();
		select=intent.getIntExtra("selectRoomNum", 0);
		Log.d("当前房号", ""+select);
		
		initView();
		
		roomnumCenter.setText("房号:"+select);
		
		ControlUtils.getData();
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {

			@Override
			public void onResult(final DeviceBean bean) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (!TextUtils.isEmpty( bean.getLamp() ) && bean.getLamp().equals( ConstantUtil.CLOSE )) {
                            shedeng.setChecked( false );
                        } else {
                            shedeng.setChecked( true );
                        }
                        if (!TextUtils.isEmpty( bean.getFan() ) && bean.getFan().equals( ConstantUtil.CLOSE )) {
                        	fengshan.setChecked( false );
                        } else {
                            fengshan.setChecked( true );
                        }
                        if (!TextUtils.isEmpty( bean.getCurtain() ) && bean.getCurtain().equals( ConstantUtil.CHANNEL_3 )) {
                        	Toast.makeText(ControlActivity.this, "窗帘开", Toast.LENGTH_SHORT).show();
                        } else if (!TextUtils.isEmpty( bean.getCurtain() ) && bean.getCurtain().equals( ConstantUtil.CHANNEL_1 )) {
                        	Toast.makeText(ControlActivity.this, "窗帘停", Toast.LENGTH_SHORT).show();
                        } else if (!TextUtils.isEmpty( bean.getCurtain() ) && bean.getCurtain().equals( ConstantUtil.CHANNEL_2 )) {
                        	Toast.makeText(ControlActivity.this, "窗帘关", Toast.LENGTH_SHORT).show();
                        }
                        if (!TextUtils.isEmpty( bean.getWarningLight() ) && bean.getWarningLight().equals( ConstantUtil.CLOSE )) {
                            baojingdeng.setChecked( false );
                        } else {
                            baojingdeng.setChecked( true );
                        }
					}
				});
			}
		});
	}
	
	public void initView(){
		roomnumCenter=(TextView)findViewById(R.id.tv_roomnum_center);
		baojingdeng=(ToggleButton)findViewById(R.id.tgb_baojingdeng);
		baojingdeng.setOnCheckedChangeListener(this);
		fengshan=(ToggleButton)findViewById(R.id.tgb_fengshan);
		fengshan.setOnCheckedChangeListener(this);
		shedeng=(ToggleButton)findViewById(R.id.tgb_shedeng);
		shedeng.setOnCheckedChangeListener(this);
		dianshiji=(Button)findViewById(R.id.bt_dianshiji);
		dianshiji.setOnClickListener(this);
		kongtiao=(Button)findViewById(R.id.bt_kongtiao);
		kongtiao.setOnClickListener(this);
		dvd=(Button)findViewById(R.id.bt_dvd);
		dvd.setOnClickListener(this);
		menjin=(Button)findViewById(R.id.bt_menjin);
		menjin.setOnClickListener(this);
		clkai=(Button)findViewById(R.id.bt_clkai);
		clkai.setOnClickListener(this);
		clguan=(Button)findViewById(R.id.bt_clguan);
		clguan.setOnClickListener(this);
		clting=(Button)findViewById(R.id.bt_clting);
		clting.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_control, menu);
		return true;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		if (!buttonView.isPressed()) return;
		switch (buttonView.getId()) {
		case R.id.tgb_shedeng:
			if (isChecked) {
				ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
			}else {
				ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
			}
			break;
			
		case R.id.tgb_fengshan:
			if (isChecked) {
				ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
			}else {
				ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
			}
			break;
			
		case R.id.tgb_baojingdeng:
			if (isChecked) {
				ControlUtils.control(ConstantUtil.WarningLight,ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
			} else {
				ControlUtils.control(ConstantUtil.WarningLight,ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
			}
			break;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_menjin:
			ControlUtils.control(ConstantUtil.RFID_Open_Door, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			break;
        case R.id.bt_clkai:
            ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN );
            break;
        case R.id.bt_clting:
            ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN );
            break;
        case R.id.bt_clguan:
            ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_2, ConstantUtil.OPEN );
            break;
        case R.id.bt_kongtiao:
        	ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
			break;
			
		case R.id.bt_dvd:
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "3", ConstantUtil.OPEN);
			break;
			
		case R.id.bt_dianshiji:
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			break;
		}
	}

}
