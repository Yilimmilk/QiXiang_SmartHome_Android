package com.example.b2019a;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;


public class LoadActivity extends Activity {
	
	private ProgressBar mainBar;
	private TextView loadnum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load);
		
		mainBar=(ProgressBar)findViewById(R.id.pgbar_main);
		loadnum=(TextView)findViewById(R.id.tv_loadnum);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 101; i++) {
					
					Message msg=new Message();
					msg.what=i;
					mHandler.sendMessage(msg);
					
					mainBar.setProgress(i);
					
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	Handler mHandler=new Handler(){
		public void handleMessage(Message msg){
			super.handleMessage(msg);
			loadnum.setText(msg.what+"%");
			switch (msg.what) {
			case 100:
				AlertDialog.Builder dialog=new AlertDialog.Builder(LoadActivity.this);
				dialog.setTitle("登录成功");
				dialog.setMessage("欢迎回来");
				dialog.setNegativeButton("OK", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
						startActivity(new Intent(LoadActivity.this,MainActivity.class));
						finish();
					}
				});
				dialog.show();
				break;

			default:
				break;
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_load, menu);
		return true;
	}

}
