package com.example.demoB;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressActivity extends Activity {
	int i = 0;
	Handler mHandler = new Handler();
	private ProgressBar mProgress;
	private TextView mProgressTv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress);
		mProgress = (ProgressBar) findViewById(R.id.progressBar);
		mProgressTv = (TextView) findViewById(R.id.tv_progress);
		mHandler.postDelayed(thread, 50);

	}

	Thread thread = new Thread(new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			i++;
			mProgress.setProgress(i);
			mProgressTv.setText(i + "%");
			if (i == 100) {
				mHandler.removeCallbacks(thread);
				new AlertDialog.Builder(ProgressActivity.this).setTitle("登陆成功")
				.setMessage("欢迎回来")
				.setPositiveButton("Ok", new OnClickListener() {
					
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						startActivity(new Intent(ProgressActivity.this,MainActivity.class));
						finish();
					}
				}).show();
			} else {
				mHandler.postDelayed(thread, 50);
			}
		}
	});
}
