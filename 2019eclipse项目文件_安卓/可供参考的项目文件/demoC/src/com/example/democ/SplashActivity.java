package com.example.democ;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashActivity extends Activity{
	private ProgressBar mProgress;
	private TextView mProgressTv;
	int i=0;
    Handler mHandler=new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
    	mProgress =(ProgressBar)findViewById(R.id.progress);
    	mProgressTv=(TextView)findViewById(R.id.tv_progress);
    	mHandler.postDelayed(thread, 50);
	}
	
	
	Thread thread=new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			i++;
			mProgress.setProgress(i);
			if(i==10){
				mProgressTv.setText("正在加载串口配置...........");
			}else if(i==10){
				mProgressTv.setText("串口配置加载完成...........");
			}else if(i==30){
				mProgressTv.setText("正在加载界面配置...........");
			}else if(i==50){
				mProgressTv.setText("界面配置加载完成...........");
			}else if(i==60){
				mProgressTv.setText("正在初始化界面..........");
			}else if(i==80){
				mProgressTv.setText("界面初始化完成..........");
			}else if(i==100){
				mProgressTv.setText("进入系统中...........");
				startActivity(new Intent(SplashActivity.this,LoginActivity.class));
				mHandler.removeCallbacks(thread);
				finish();
			}
			mHandler.postDelayed(thread, 50);
		}
		
	});
	
}
