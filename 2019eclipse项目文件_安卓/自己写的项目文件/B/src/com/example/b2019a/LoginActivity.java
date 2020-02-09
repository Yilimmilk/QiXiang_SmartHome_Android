package com.example.b2019a;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;
import com.bizideal.smarthome.socket.Utils.SpUtils;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	private SeekBar mainBar;
	private EditText ip;
	private TextView login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		mainBar=(SeekBar)findViewById(R.id.sb_main);
		ip=(EditText)findViewById(R.id.et_ip);
		login=(TextView)findViewById(R.id.tv_login);
		
		mainBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				if (mainBar.getProgress()==100) {
					if (TextUtils.isEmpty(ip.getText().toString())) {
						Toast.makeText(LoginActivity.this, "IP非法", Toast.LENGTH_SHORT).show();
						mainBar.setProgress(0);
						return;
					}
					
					SpUtils.putValue(LoginActivity.this, "Account", "bizideal");
					SpUtils.putValue(LoginActivity.this, "Password", "123456");
					SpUtils.putValue(LoginActivity.this, "Ip", ip.getText().toString());
					
					ControlUtils.setUser("bizideal", "123456", ip.getText().toString());
					
					SocketClient.getInstance().creatConnect();
					SocketClient.getInstance().login(new LoginCallback() {
						
						@Override
						public void onEvent(final String arg0) {
							// TODO Auto-generated method stub
							runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									if (arg0.equals(ConstantUtil.SUCCESS)) {
										startActivity(new Intent(LoginActivity.this,LoadActivity.class));
										finish();
									}else {
										Toast.makeText(LoginActivity.this, "组网失败", Toast.LENGTH_SHORT).show();
									}
								}
							});
						}
					});
				}else {
					mainBar.setProgress(0);
					Log.d("滑动条", "当前进度"+mainBar.getProgress());
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
		
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (TextUtils.isEmpty(ip.getText().toString())) {
					Toast.makeText(LoginActivity.this, "IP非法", Toast.LENGTH_SHORT).show();
				}
				
				SpUtils.putValue(LoginActivity.this, "Account", "bizideal");
				SpUtils.putValue(LoginActivity.this, "Password", "123456");
				SpUtils.putValue(LoginActivity.this, "Ip", ip.getText().toString());
				
				ControlUtils.setUser("bizideal", "123456", ip.getText().toString());
				
				SocketClient.getInstance().creatConnect();
				SocketClient.getInstance().login(new LoginCallback() {
					
					@Override
					public void onEvent(final String arg0) {
						// TODO Auto-generated method stub
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								if (arg0.equals(ConstantUtil.SUCCESS)) {
									startActivity(new Intent(LoginActivity.this,LoadActivity.class));
									finish();
								}else {
									Toast.makeText(LoginActivity.this, "组网失败", Toast.LENGTH_SHORT).show();
								}
							}
						});
					}
				});
				
			}
		});
		
		login.setLongClickable(true);
		login.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(LoginActivity.this, "强制进入主界面，当前为调试模式", Toast.LENGTH_SHORT).show();
				startActivity(new Intent(LoginActivity.this,LoadActivity.class));
				finish();
				return false;
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
