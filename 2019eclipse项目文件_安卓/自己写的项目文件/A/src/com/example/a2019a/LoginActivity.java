package com.example.a2019a;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;
import com.bizideal.smarthome.socket.Utils.SpUtils;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class LoginActivity extends Activity {
	
	private SeekBar mainBar;
	private EditText ip;
	private Button loginBt;
	
	private String ipString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		ip=(EditText)findViewById(R.id.et_ip);
		mainBar=(SeekBar)findViewById(R.id.sb_yanzheng);
		loginBt=(Button)findViewById(R.id.bt_login);
		
		mainBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				Log.d("滑动条", "当前进度"+mainBar.getProgress());
				if (mainBar.getProgress()==100) {
					//Toast.makeText(LoginActivity.this, "验证通过", Toast.LENGTH_SHORT).show();
					initStart();
				}
				if (!(mainBar.getProgress()==100)) {
					mainBar.setProgress(0);
				}
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				
			}
		});
		
		loginBt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				initStart();
				
			}
		});
		
		loginBt.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(LoginActivity.this,MainActivity.class);
				startActivity(intent);
				return false;
			}
		});
		
	}

	private void initStart(){
		ipString=ip.getText().toString();
		
		if (TextUtils.isEmpty(ipString)) {
			Toast.makeText(LoginActivity.this, "ip不能为空", Toast.LENGTH_SHORT).show();
		}
		
		SpUtils.putValue(LoginActivity.this, "Account", "bizideal");
		SpUtils.putValue(LoginActivity.this, "Password", "123456");
		SpUtils.putValue(LoginActivity.this, "Ip", ipString);
		
		ControlUtils.setUser("bizideal", "123456", ipString);
		
		SocketClient.getInstance().creatConnect();
		SocketClient.getInstance().login(new LoginCallback() {
			
			@Override
			public void onEvent(final String status) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (status.equals(ConstantUtil.SUCCESS)) {
							Intent intent=new Intent(LoginActivity.this,MainActivity.class);
							startActivity(intent);
						}else {
							Toast.makeText(LoginActivity.this, "连接服务器失败", Toast.LENGTH_SHORT).show();
						}
					}
				});
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

}
