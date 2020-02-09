package com.example.b2019a;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View{
	
	private int Xstart=70;
    private int Ystart=20;//起始坐标
    private int Xlength=600;
    private int Ylength=300;//XY轴长度
    public static List<Integer> data=new ArrayList<Integer>();//数据

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            mHandler.postDelayed(new Runnable() {
                public void run() {
                    mHandler.sendMessage(mHandler.obtainMessage());
                    CustomView.this.invalidate();//重绘
                }
            }, 1000);//一秒刷新一次
        };
    };

	
	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		 mHandler.sendMessage(mHandler.obtainMessage());
	}
	
	protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画笔
        Paint paint=new Paint();
        //画笔预设，抗锯齿
        paint.setAntiAlias(true);
        //画Y轴
        canvas.drawLine(Xstart, Ystart-10, Xstart, Ystart+Ylength, paint);
        //画X轴
        canvas.drawLine(Xstart, Ystart+Ylength, Xstart+Xlength+10, Ystart+Ylength, paint);
        //定义刻度
        //String[] str={"30m/s","15m/s","0"};
        //画刻度
//        for (int i = 0; i < 3; i++) {
//            canvas.drawLine(Xstart, Ystart+i*150, Xstart-5, Ystart+i*150, paint);
//            canvas.drawText(str[i], Xstart-65, Ystart+i*150+3, paint);
//        }
//        for (int i = 0; i < 7; i++) {
//            canvas.drawLine(Xstart+i*100, Ystart+Ylength, Xstart+i*100, Ystart+Ylength-5, paint);
//        }
        //画小箭头
        canvas.drawLine(Xstart, Ystart-10, Xstart+3, Ystart-6, paint);
        canvas.drawLine(Xstart, Ystart-10, Xstart-3, Ystart-6, paint);
        System.out.println("Data.size = " + data.size()); 
        //画折线
        if(data.size()>1){
            for (int i = 0; i < data.size()-1; i++) {
                canvas.drawLine(Xstart+i*100, Ystart+Ylength-data.get(i)*10, Xstart+(i+1)*100, Ystart+Ylength-data.get(i+1)*10, paint);
            }
        }
	}
}
