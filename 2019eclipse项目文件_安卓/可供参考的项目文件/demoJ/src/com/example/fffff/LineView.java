package com.example.fffff;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
/**
 * 光照度折线图(纵线总共400 横线600,主要是纵线的计算，比方实际光照200，200/1600 占比0.125。则400*1.125会得到50的坐标差
 * ，这就好办了，500减去这50便得到了我们需要描绘的点的坐标450（意思就是将200光照的坐标点计算出来得到50）
 * 
 * @author Administrator
 *
 */
public class LineView extends View {
    Paint paint;
   // int num1,num2,num3,num4,num5,num6,num7;
    float[] nums ;

	public LineView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

	}

	public LineView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
     public void initData(float[] nums){
    	 
//    	 for (int i = 0; i < nums.length; i++) {
//			nums[i] = nums[i]*2;
//    		 
//		}
    	 
    	 this.nums = nums;
    	 invalidate();
     }
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);


		paint = new Paint();
		canvas.drawLine(100, 100, 100, 500, paint);
		canvas.drawLine(100, 500, 700, 500, paint);
		
		
//		canvas.drawLine(100, 100, 700, 100, paint);
//		canvas.drawLine(100, 200, 700, 200, paint);
//		canvas.drawLine(100, 300, 700, 300, paint);
//		canvas.drawLine(100, 400, 700, 400, paint);
//		
//		canvas.drawLine(200, 100, 200, 500, paint);
//		canvas.drawLine(300, 100, 300, 500, paint);
//		canvas.drawLine(400, 100, 400, 500, paint);
//		canvas.drawLine(500, 100, 500, 500, paint);
//		canvas.drawLine(600, 100, 600, 500, paint);
//		canvas.drawLine(700, 100, 700, 500, paint);
		
		int[] args = {200,400,600,800};
		for(int i=0;i<args.length;i++){
			canvas.drawText(String.valueOf(args[i]), 50, 410-i*100, paint);
		}
		
		//paint.setColor(Color.RED);
		//第一个点到第七个点一次 x坐标为100，200.。。。-700，Y坐标即代表点的高度
		int[] xs = {100,200,300,400,500,600,700};
		
		if(nums!=null){
			for(int i=0;i<xs.length;i++){
				canvas.drawCircle(xs[i], (float) ((int)500-((nums[i]/1600)*400)), 4, paint);
				canvas.drawCircle(xs[i], 500, 4, paint);
				canvas.drawText(nums[i]/2+"", xs[i]-5, 500+15, paint);
				if(i<6){
					canvas.drawLine(xs[i], (float) ((int)500-((nums[i]/1600)*400)), xs[i+1], (float) ((int)500-((nums[i+1]/1600)*400)), paint);
					
				}
				
			}
		}
		
		
	
		
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		//设置View大小
		setMeasuredDimension(750,600);
	}
	

}
