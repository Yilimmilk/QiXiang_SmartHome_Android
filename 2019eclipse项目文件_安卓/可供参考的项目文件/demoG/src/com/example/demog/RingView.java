package com.example.demog;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

public class RingView extends View {
    private Context mContext;
    private Paint mPaint;
    private int mPaintWidth = 0;      
    private int topMargin = 30;      
    private int leftMargin = 20;      
    private Resources mRes;
    private DisplayMetrics dm;

    private int circleCenterX = 96;    
    private int circleCenterY = 96;    

    private int ringOuterRidus = 96;   
    private int ringInnerRidus = 50;   
    private int ringPointRidus = 70;   

    private RectF rectF;              
    private RectF rectFPoint;        

    private List<Integer> colorList;
    private List<Float> rateList;
	private String device;
	private ArrayList<String> mlist;

    public RingView(Context context) {
        super(context, null);
    }

    public RingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public void setShow(List<Integer> colorList, List<Float> rateList, String device, ArrayList<String> mlist) {
        this.colorList = colorList;
        this.rateList = rateList;
        this.device=device;
        this.mlist=mlist;
        invalidate();
    }

    private void initView() {
        this.mRes = mContext.getResources();
        this.mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        int screenWidth = wm.getDefaultDisplay().getWidth();
        leftMargin = (px2dip(screenWidth) - (2 * circleCenterX)) / 2;
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(dip2px(mPaintWidth));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        rectF = new RectF(dip2px(mPaintWidth + leftMargin),
                dip2px(mPaintWidth + topMargin),
                dip2px(circleCenterX + ringOuterRidus + mPaintWidth * 2 + leftMargin),
                dip2px(circleCenterY + ringOuterRidus + mPaintWidth * 2 + topMargin));

        rectFPoint = new RectF(dip2px(mPaintWidth + leftMargin + (ringOuterRidus - ringPointRidus)),
                dip2px(mPaintWidth + topMargin + (ringOuterRidus - ringPointRidus)),
                dip2px(circleCenterX + ringPointRidus + mPaintWidth * 2 + leftMargin),
                dip2px(circleCenterY + ringPointRidus + mPaintWidth * 2 + topMargin));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        pointList.clear();
        canvas.restore();
        if (colorList != null && rateList != null) {
            for (int i = 0; i < rateList.size(); i++) {
                mPaint.setColor(mRes.getColor(colorList.get(i)));
                mPaint.setStyle(Paint.Style.FILL);
                canvas.drawRect(new RectF(dip2px(((circleCenterX + mPaintWidth * 2 + leftMargin) + ringInnerRidus) + 50), dip2px(circleCenterY + mPaintWidth * 2 + ((i * 30))), dip2px(((circleCenterX + mPaintWidth * 2 + leftMargin) + ringInnerRidus) + (50 + 20)), dip2px((circleCenterY + mPaintWidth * 2 + ((i * 30)) + 20))), mPaint);
                drawOuter(canvas, i);
            }
        }
        mPaint.setStyle(Paint.Style.FILL);
        drawInner(canvas);

    }

    private void drawInner(Canvas canvas) {
        mPaint.setColor(Color.WHITE);
        canvas.drawCircle(dip2px(circleCenterX + mPaintWidth * 2 + leftMargin), dip2px(circleCenterY + mPaintWidth * 2 + topMargin), dip2px(ringInnerRidus), mPaint);
        if(device.contains("温度")){
        	  mPaint.setColor(Color.BLACK);
              mPaint.setTextSize(dip2px(20));
             canvas.drawText("温度一小时统计图 ", getWidth() / 2, topMargin, mPaint);
             mPaint.setColor(Color.RED);
             canvas.drawText("单位 ℃", dip2px(((circleCenterX + mPaintWidth * 2 + leftMargin) + ringInnerRidus) + 50), dip2px((circleCenterY + (mPaintWidth * 2)) + 110), mPaint); 
           }else{
        	  mPaint.setColor(Color.BLACK);
              mPaint.setTextSize(dip2px(20));
        	 canvas.drawText("光照度一小时统计图 ", getWidth() / 2, topMargin, mPaint);
        	  mPaint.setColor(Color.RED);
        	  canvas.drawText("单位 LX", dip2px(((circleCenterX + mPaintWidth * 2 + leftMargin) + ringInnerRidus) + 50), dip2px((circleCenterY + (mPaintWidth * 2)) + 110), mPaint); 
              
          }
        for(int i=0;i<mlist.size();i++){
        	 canvas.drawText(mlist.get(i).toString(), dip2px(((circleCenterX + mPaintWidth * 2 + leftMargin) + ringInnerRidus) + 80), dip2px((circleCenterY + (mPaintWidth * 2)) +i*30+ 20), mPaint);
             
      }
    
         mPaint.setColor(Color.RED);
        canvas.drawText("图列", dip2px(((circleCenterX + mPaintWidth * 2 + leftMargin) + ringInnerRidus) + 50), dip2px((circleCenterY + mPaintWidth * 2)-10), mPaint);


    }

    private float preRate;

    private void drawArcCenterPoint(Canvas canvas, int position) {
    
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.TRANSPARENT);
        mPaint.setStrokeWidth(dip2px(1));
        canvas.drawArc(rectFPoint, preAngle, (endAngle) / 2, true, mPaint);
        dealPoint(rectFPoint, preAngle, (endAngle) / 2, pointArcCenterList);
        Point point = pointArcCenterList.get(position);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
      //  canvas.drawText(rateList.get(position) + "%", preAngle,(endAngle) / 2, mPaint);
        preRate = rateList.get(position);


    }

    List<Point> pointList = new ArrayList<Point>();
    List<Point> pointArcCenterList = new ArrayList<Point>();

    private void dealPoint(RectF rectF, float startAngle, float endAngle, List<Point> pointList) {
        Path orbit = new Path();
        orbit.addArc(rectF, startAngle, endAngle);
        PathMeasure measure = new PathMeasure(orbit, false);
        float[] coords = new float[]{0f, 0f};
        int divisor = 1;
        measure.getPosTan(measure.getLength() / divisor, coords, null);
        float x = coords[0];
        float y = coords[1];
        Point point = new Point(Math.round(x), Math.round(y));
        pointList.add(point);
    }

    private void drawOuter(Canvas canvas, int position) {
        if (rateList != null) {
            endAngle = getAngle(rateList.get(position));
        }
        canvas.drawArc(rectF, preAngle, endAngle, true, mPaint);
        drawArcCenterPoint(canvas, position);
        preAngle = preAngle + endAngle;

    }

    private float preAngle = -90;
    private float endAngle = -90;


    private float getAngle(float percent) {
        float a = 360f / 100f * percent;
        return a;
    }

 
    public int dip2px(float dpValue) {
        return (int) (dpValue * dm.density + 0.5f);
    }


    public int px2dip(float pxValue) {
        return (int) (pxValue / dm.density + 0.5f);
    }

}
