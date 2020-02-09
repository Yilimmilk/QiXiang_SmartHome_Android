package com.example.a2019a;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CustomLine extends View{
	
	private boolean wangguanStatus=false;

	public CustomLine(Context context, AttributeSet attrs) {
		super(context, attrs);
		SocketClient.getInstance().login(new LoginCallback() {
			
			@Override
			public void onEvent(final String status) {
				// TODO Auto-generated method stub
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (status.equals(ConstantUtil.SUCCESS)) {
							wangguanStatus=true;
						}else {
							wangguanStatus=false;
							Log.d("Á¬½Ó×´Ì¬", "Ê§°Ü");
						}
					}
				});
			}
		});
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onDraw(Canvas canvas){
		Paint paint=new Paint();
		paint.setColor(Color.BLACK);
		paint.setStrokeWidth(2);
		
		canvas.drawLine(110, 70, 260, 150, paint);
		canvas.drawLine(220, 70, 270, 150, paint);
		canvas.drawLine(320, 70, 280, 150, paint);
		canvas.drawLine(420, 70, 290, 150, paint);
		
		canvas.drawLine(120, 180, 250, 180, paint);
		canvas.drawLine(300, 180, 370, 180, paint);
		
		canvas.drawLine(110, 280, 260, 200, paint);
		canvas.drawLine(220, 280, 270, 200, paint);
		canvas.drawLine(320, 280, 280, 200, paint);
		canvas.drawLine(420, 280, 290, 200, paint);
		
		if (wangguanStatus==true) {
			paint.setColor(Color.RED);
			canvas.drawLine(310, 175, 315, 185, paint);
			canvas.drawLine(310, 185, 315, 175, paint);
		}
	}

}
