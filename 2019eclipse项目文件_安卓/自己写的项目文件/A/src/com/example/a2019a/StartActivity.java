package com.example.a2019a;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

public class StartActivity extends Activity {
	
	private ProgressBar pgmain;
	private TextView tvmain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		pgmain=(ProgressBar)findViewById(R.id.pg_main);
		tvmain=(TextView)findViewById(R.id.tv_main);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 101; i++) {
					if (i==10) {
						Message msg=new Message();
						msg.what=10;
						mHandler.sendMessage(msg);
					}
					if (i==20) {
						Message msg=new Message();
						msg.what=20;
						mHandler.sendMessage(msg);
					}
					if (i==30) {
						Message msg=new Message();
						msg.what=30;
						mHandler.sendMessage(msg);
					}
					if (i==50) {
						Message msg=new Message();
						msg.what=50;
						mHandler.sendMessage(msg);
					}
					if (i==60) {
						Message msg=new Message();
						msg.what=60;
						mHandler.sendMessage(msg);
					}
					if (i==80) {
						Message msg=new Message();
						msg.what=80;
						mHandler.sendMessage(msg);
					}
					if (i==100) {
						Message msg=new Message();
						msg.what=100;
						mHandler.sendMessage(msg);
					}
					pgmain.setProgress(i);
					try {
						Thread.sleep(10);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		}).start();
	}
	
	private Handler mHandler = new Handler() { 
		
		public void handleMessage(Message msg){
			switch (msg.what) {
			case 10:
				if (msg.what==10) {
					tvmain.setText("正在加载串口配置...........");
				}
				break;
			case 20:
				if (msg.what==20) {
					tvmain.setText("串口配置加载完成...........");
				}
				break;
			case 30:
				if (msg.what==30) {
					tvmain.setText("正在加载界面配置...........");
				}
				break;
			case 50:
				if (msg.what==50) {
					tvmain.setText("界面配置加载完成...........");
				}
				break;
			case 60:
				if (msg.what==60) {
					tvmain.setText("正在初始化界面..........");
				}
				break;
			case 80:
				if (msg.what==80) {
					tvmain.setText("界面初始化完成..........");
				}
				break;
			case 100:
				if (msg.what==100) {
					tvmain.setText("进入系统中...........");
					Intent intent=new Intent(StartActivity.this,LoginActivity.class);
					startActivity(intent);
					finish();
				}
				break;

			default:
				break;
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_start, menu);
		return true;
	}

}
