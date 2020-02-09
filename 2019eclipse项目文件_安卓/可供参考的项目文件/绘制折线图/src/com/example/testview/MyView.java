package com.example.testview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	// 默认边距
	private int Margin = 40;
	// 原点坐标
	private int Xpoint;
	private int Ypoint;
	// X,Y轴的单位长度
	private int Xscale = 20;
	private int Yscale = 20;
	// X,Y轴上面的显示文字
	private String[] Xlabel = { "0", "1", "2", "3", "4", "5", "6", "7", "8" };
	private String[] Ylabel = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
	// 标题文本
	private String Title;
	// 曲线数据
	private int[] Data = { 1,2,3,4,5,6,7,8,9 };

	public MyView(Context context, String[] xlabel, String[] ylabel,
			String title, int[] data) {
		super(context);
		this.Xlabel = xlabel;
		this.Ylabel = ylabel;
		this.Title = title;
		this.Data = data;
	}

	public MyView(Context context) {
		super(context);
	}

	// 初始化数据值
	public void init() {
		Xpoint = this.Margin;
		Ypoint = this.getHeight() - this.Margin;
		Xscale = (this.getWidth() - 2 * this.Margin) / (this.Xlabel.length - 1);
		Yscale = (this.getHeight() - 2 * this.Margin)
				/ (this.Ylabel.length - 1);
	}

	public int getMargin() {
		return Margin;
	}

	public void setMargin(int margin) {
		Margin = margin;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		Paint p1 = new Paint();
		p1.setStyle(Paint.Style.STROKE);
		p1.setAntiAlias(true);
		p1.setColor(Color.BLACK);
		p1.setStrokeWidth(2);
		init();
		this.drawXLine(canvas, p1);
		this.drawYLine(canvas, p1);
		// this.drawTable(canvas);
		this.drawData(canvas);
	}

	// // 画表格
	// private void drawTable(Canvas canvas) {
	// Paint paint = new Paint();
	// paint.setStyle(Paint.Style.STROKE);
	// paint.setColor(Color.GRAY);
	// Path path = new Path();
	// PathEffect effects = new DashPathEffect(new float[] { 5, 5, 5, 5 }, 1);
	// paint.setPathEffect(effects);
	// // 纵向线
	// for (int i = 1; i * Xscale <= (this.getWidth() - this.Margin); i++) {
	// int startX = Xpoint + i * Xscale;
	// int startY = Ypoint;
	// int stopY = Ypoint - (this.Ylabel.length - 1) * Yscale;
	// path.moveTo(startX, startY);
	// path.lineTo(startX, stopY);
	// canvas.drawPath(path, paint);
	// }
	// // 横向线
	// for (int i = 1; (Ypoint - i * Yscale) >= this.Margin; i++) {
	// int startX = Xpoint;
	// int startY = Ypoint - i * Yscale;
	// int stopX = Xpoint + (this.Xlabel.length - 1) * Xscale;
	// path.moveTo(startX, startY);
	// path.lineTo(stopX, startY);
	// paint.setColor(Color.DKGRAY);
	// canvas.drawPath(path, paint);
	// paint.setColor(Color.WHITE);
	// paint.setTextSize(this.Margin / 2);
	// canvas.drawText(this.Ylabel[i], this.Margin / 4, startY
	// + this.Margin / 4, paint);
	// }
	// }

	// 画横纵轴
	private void drawXLine(Canvas canvas, Paint p) {
		canvas.drawLine(Xpoint, Ypoint, this.Margin, this.Margin, p);
		//canvas.drawLine(Xpoint, this.Margin, Xpoint - Xpoint / 3, this.Margin+ this.Margin / 3, p);
		//canvas.drawLine(Xpoint, this.Margin, Xpoint + Xpoint / 3, this.Margin+ this.Margin / 3, p);
	}

	private void drawYLine(Canvas canvas, Paint p) {
		canvas.drawLine(Xpoint, Ypoint, this.getWidth() - this.Margin, Ypoint,p);
		//canvas.drawLine(this.getWidth() - this.Margin, Ypoint, this.getWidth()- this.Margin - this.Margin / 3, Ypoint - this.Margin / 3, p);
		//canvas.drawLine(this.getWidth() - this.Margin, Ypoint, this.getWidth()- this.Margin - this.Margin / 3, Ypoint + this.Margin / 3, p);
	}

	// 画数据
	private void drawData(Canvas canvas) {
		Paint p = new Paint();
		p.setAntiAlias(true);
		p.setColor(Color.BLACK);
		p.setTextSize(this.Margin / 2);
		// 纵向线
		for (int i = 1; i * Xscale <= (this.getWidth() - this.Margin); i++) {
			int startX = Xpoint + i * Xscale;
			canvas.drawText(this.Xlabel[i], startX - this.Margin / 4,
					this.getHeight() - this.Margin / 4, p);
			canvas.drawCircle(startX, calY(Data[i]), 4, p);
			canvas.drawLine(Xpoint + (i - 1) * Xscale, calY(Data[i - 1]),
					startX, calY(Data[i]), p);
		}
	}

	/**
	 * 
	 * @param y
	 * @return
	 */
	private int calY(int y) {
		int y0 = 0;
		int y1 = 0;
		// Log.i("zzzz", "y:"+y);
		try {
			y0 = Integer.parseInt(Ylabel[0]);
			// Log.i("zzzz", "y0"+y0);
			y1 = Integer.parseInt(Ylabel[1]);
			// Log.i("zzzz","y1"+y1);
		} catch (Exception e) {
			// Log.i("zzzz", "string changed is err");
			return 0;
		}
		try {
			// Log.i("zzzz", "返回数据"+(Ypoint-(y-y0)*Yscale/(y1-y0)) );
			return Ypoint - ((y - y0) * Yscale / (y1 - y0));
		} catch (Exception e) {
			// Log.i("zzzz", "return is err");
			return 0;
		}
	}

}