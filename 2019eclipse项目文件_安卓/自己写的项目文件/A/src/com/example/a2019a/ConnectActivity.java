package com.example.a2019a;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class ConnectActivity extends Activity {
	
	private TextView textStateWangguan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connect);
		
		textStateWangguan=(TextView)findViewById(R.id.tv_state_wangguan);
		
		SocketClient.getInstance().login(new LoginCallback() {
			
			@Override
			public void onEvent(final String status) {
				// TODO Auto-generated method stub
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (status.equals(ConstantUtil.SUCCESS)) {
							textStateWangguan.setText("网关:在线");
						}else {
							textStateWangguan.setText("网关:离线");
							Log.d("连接状态", "失败");
						}
					}
				});
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_connect, menu);
		return true;
	}

}
