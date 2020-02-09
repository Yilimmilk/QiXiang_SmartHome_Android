package com.example.demog;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends Activity{
	private ImageView mImg;
	private TextView time;
	  Handler mHandler=new Handler();
	  int i=5;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		initViews();
		initData();
	}

	private void initViews() {
		// TODO Auto-generated method stub
	 mImg=(ImageView)findViewById(R.id.img);
	 time=(TextView)findViewById(R.id.time);
	}

	private void initData() {
		// TODO Auto-generated method stub
		AnimationSet animationSet=new AnimationSet(true);
		ScaleAnimation scaleAnimation = new ScaleAnimation(
		       0.5f, 2.15f, 0.5f, 2.15f,
		        Animation.RELATIVE_TO_SELF, 0.5f,
		        Animation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimation.setDuration(1000);//动画持续时间
		animationSet.addAnimation(scaleAnimation);//保存动画效果到。
		animationSet.setFillAfter(true);//结束后保存状态
		mImg.startAnimation(animationSet);//设置给控件
		mHandler.postDelayed(thread, 1000);
	}

	Thread thread=new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			time.setVisibility(View.VISIBLE);
			i--;
			time.setText(i+"秒后进入登录界面。。。");
			if(i==0){
				mHandler.removeCallbacks(thread);
				startActivity(new Intent(SplashActivity.this,LoginActivity.class));
				finish();
			}
			
			mHandler.postDelayed(thread, 1000);
		}
	});
}
