package com.example.a521i;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashActivity extends Activity {

	private ProgressBar jdt1;
	private TextView jdtwz;
	private Handler mHandler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
    jdt1 = (ProgressBar) findViewById(R.id.jdt);
		 
		jdtwz = (TextView) findViewById(R.id.jdtwz);
		mHandler.postDelayed(r, 50);
	}

Thread r = new Thread(new Runnable() {
	private int i=0;

	public void run() {
		i++;
		jdt1.setProgress(i);
		if(i==1){
			jdtwz.setText("正在初始化");
		}else if(i==20){
			jdtwz.setText("正在载入系统");
		}else if(i==51){
			jdtwz.setText("正在连接服务器");
		}else if(i==99){
			startActivity(new Intent(SplashActivity.this,LoginActivity.class));
			mHandler.removeCallbacks(r);
			finish();
			
		}
		mHandler.postDelayed(r, 50);
	}
});



}
