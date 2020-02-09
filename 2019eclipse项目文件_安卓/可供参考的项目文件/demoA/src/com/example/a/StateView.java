package com.example.a;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class StateView extends View {

	public StateView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	

	
	
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
	
		Paint paint = new Paint();
		
		
		int startX = 400;
		int startY =240;
		
		
		List<Bitmap> list = new ArrayList<Bitmap>();
		
		list.add(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
		list.add(BitmapFactory.decodeResource(getResources(), R.drawable.a1312));
		list.add(BitmapFactory.decodeResource(getResources(), R.drawable.a1313));
		list.add(BitmapFactory.decodeResource(getResources(), R.drawable.a1314));
		list.add(BitmapFactory.decodeResource(getResources(), R.drawable.a1315));
		list.add(BitmapFactory.decodeResource(getResources(), R.drawable.a1316));
		list.add(BitmapFactory.decodeResource(getResources(), R.drawable.a1317));
		list.add(BitmapFactory.decodeResource(getResources(), R.drawable.a1318));
		list.add(BitmapFactory.decodeResource(getResources(), R.drawable.a1319));
		list.add(BitmapFactory.decodeResource(getResources(), R.drawable.a1320));
		list.add(BitmapFactory.decodeResource(getResources(), R.drawable.a1321));
		
		
		
		canvas.drawBitmap(list.get(0), startX, startY, paint);		
		
		
		
		
		canvas.drawBitmap(list.get(1), startX-240, startY-160, paint);
		canvas.drawLine(startX , startY, startX-240+list.get(1).getWidth(), startY-160+list.get(1).getHeight(), paint);
		canvas.drawBitmap(list.get(2), startX-80, startY-200, paint);
		canvas.drawLine(startX +30, startY, startX-80+list.get(2).getWidth()/2, startY-200+list.get(2).getHeight(), paint);
		canvas.drawBitmap(list.get(3), startX+80, startY-200, paint);
		canvas.drawLine(startX +30, startY, startX+80+list.get(3).getWidth()/2, startY-200+list.get(3).getHeight(), paint);
		canvas.drawBitmap(list.get(4), startX+240, startY-160, paint);
		canvas.drawLine(startX +30, startY, startX+240+list.get(4).getWidth()/2, startY-160+list.get(4).getHeight(), paint);
		canvas.drawBitmap(list.get(5), startX-300, startY, paint);
		canvas.drawLine(startX, startY+list.get(0).getHeight()/2, startX-300+list.get(4).getWidth(), startY+list.get(0).getHeight()/2, paint);
		canvas.drawBitmap(list.get(6), startX-240, startY+160, paint);
		canvas.drawLine(startX, startY+list.get(0).getHeight(), startX-240+list.get(4).getWidth(), startY+160, paint);
		canvas.drawBitmap(list.get(7), startX-80, startY+200, paint);
		canvas.drawLine(startX+30, startY+list.get(0).getHeight(), startX-80+list.get(4).getWidth(), startY+200, paint);
		canvas.drawBitmap(list.get(8), startX+80, startY+200, paint);
		canvas.drawLine(startX+30, startY+list.get(0).getHeight(), startX+80+list.get(4).getWidth(), startY+200, paint);
		canvas.drawBitmap(list.get(9), startX+300, startY+200, paint);
		canvas.drawLine(startX+30, startY+list.get(0).getHeight(), startX+200+list.get(4).getWidth(), startY+200, paint);
		canvas.drawBitmap(getBigOrSmall(list.get(10),0.5f,0.5f), startX+300, startY, paint);
	
		canvas.drawLine(startX+list.get(0).getWidth(), startY+list.get(0).getHeight()/2, startX+300+list.get(4).getWidth(), startY+list.get(0).getHeight()/2, paint);
		
		
		
		
	}
	  private Bitmap getBigOrSmall(Bitmap bmp,float xbs,float ybs){
	    	Matrix matrix = new Matrix();
	    	matrix.postScale(xbs, ybs);
	    	Bitmap dstbmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(),
					matrix, true);
			return dstbmp;
	    } 
	
	
	 
	  

}
