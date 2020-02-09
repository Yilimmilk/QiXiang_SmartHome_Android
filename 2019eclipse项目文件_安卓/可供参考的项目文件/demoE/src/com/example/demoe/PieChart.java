package com.example.demoe;

import java.util.ArrayList;

import android.R.integer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class PieChart extends View {
	 private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
			         0xFFE6B800, 0xFF7CFC00};
	private ArrayList<Integer> mData;
	private float r;


	public PieChart(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	

@Override
protected void onDraw(Canvas canvas) {
	// TODO Auto-generated method stub
	super.onDraw(canvas);
	  if (null == mData){
	       return;
	  }
	Paint Paint=new Paint();
	Paint paint=new Paint();
	paint.setColor(Color.WHITE);
	canvas.translate(getWidth()/2, getHeight()/2);
	 r = (float) (Math.min(getWidth(), getHeight()) / 2 * 0.8);
	RectF rect = new RectF(-r, -r, r, r);  
	 float startAngle = 0;   
	 float sum = 0;  
	 for (int i = 0; i < mData.size(); i++) {
			sum = mData.get(i)+sum; 
	 }
	for (int i = 0; i < mData.size(); i++) {
		 Paint.setColor(mColors[i]);
		 float percentage =mData.get(i)/sum;
         float angle = percentage*360;
     	 canvas.drawArc(rect, startAngle,angle, true, Paint);
     	 drawPieLable(canvas,mData.get(i),startAngle + angle/2,paint);
		 startAngle=startAngle+angle;
	}
}


//画每个饼块的文字
private void drawPieLable(Canvas canvas, Integer data,float angle,Paint paint) {
	// TODO Auto-generated method stub
	  float percent = 0.85f;
	   float x,y;
	    if (angle >= 0 && angle < 90) {
	    	 x = (float) (percent * r* Math.cos(Math.toRadians(angle))) ;
	         y = (float) (percent *  r  * Math.sin(Math.toRadians(angle))) ;
	    } else if (angle >= 90 && angle <= 180) {
	    	 x = (float) (-percent * r * Math.cos(Math.toRadians(180 - angle))) ;
	         y = (float) (percent * r * Math.sin(Math.toRadians(180 - angle)));
	    } else if (angle > 180 && angle <= 270) {
	    	  x = (float) (-percent * r  * Math.cos(Math.toRadians(angle - 180)));
	          y = (float) (-percent *  r * Math.sin(Math.toRadians(angle - 180))) ;
	    } else {
	    	 x = (float) (percent *  r * Math.cos(Math.toRadians(360 - angle))) ;
	         y = (float) (-percent *  r * Math.sin(Math.toRadians(360 - angle)));
	    }
	   
	    canvas.drawText("" + data, x, y, paint);
}



public void setData(ArrayList<Integer> mData) {
        this.mData = mData;
       //  initDate(mData);
         invalidate();   // 刷新
     }
 
}
