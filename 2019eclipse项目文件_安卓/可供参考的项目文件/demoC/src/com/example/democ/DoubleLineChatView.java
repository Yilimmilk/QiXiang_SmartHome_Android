package com.example.democ;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


public class DoubleLineChatView extends View {

    private static final String TAG = "DoubleLineChatView";

    private Context mContext;
  
    private Paint mPaintLeft;

    private Paint mPaintRight;
    
    private Paint mPaintTextLeft;
 
    private Paint mPaintTextRight;

    private Paint mPaintTextXY;

   
    private int[] mDataLeft = {0, 0, 0, 0};
 
    private int[] mDataRight = {0, 0, 0, 0};
 
    private String[] mDataTextX = {"", "", "", ""};


    private float mYDistance;
 
    private float mXDistance;

    private float mLineWidth;

    private int mLeftLineBackGroundColor = Color.RED;
    private int mRightLineBackGroundColor = Color.BLUE;
    private int mLeftLineTextColor = Color.RED;
    private int mRightLineTextColor = Color.BLUE;
    private float mLineTextSize;
    private int mLineXYColor = Color.GRAY;
    private float mLineXYSize;
    private boolean mIsShowArrowYInterval = true;
 
    private int mAnimationDuration = 1000;

    private int mViewWidth;

    private int mViewHeight;
 
    private int mMaxData;

    private float mBigDistance;
 
    private float mSmallDistance;

    public DoubleLineChatView(Context context) {
        this(context, null);
    }

    public DoubleLineChatView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DoubleLineChatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mLineWidth =  dip2px(mContext, 15);
        mLineTextSize = sp2px(mContext, 14);
        mLineXYSize =  sp2px(mContext, 14);
        mBigDistance = dip2px(mContext, 20);
        mSmallDistance =dip2px(mContext, 5);
        mYDistance =  dip2px(mContext, 40);
        mXDistance = dip2px(mContext, 40);
        initView();
    }

   
    private void initView() {
         
    	mPaintLeft = new Paint();
        mPaintLeft.setColor(mLeftLineBackGroundColor);
        mPaintLeft.setStrokeWidth(mLineWidth);
        mPaintLeft.setAntiAlias(true);

        mPaintRight = new Paint();
        mPaintRight.setColor(mRightLineBackGroundColor);
        mPaintRight.setStrokeWidth(mLineWidth);
        mPaintRight.setAntiAlias(true);

         mPaintTextLeft = new Paint();
        mPaintTextLeft.setColor(mLeftLineTextColor);
        mPaintTextLeft.setTextSize(mLineTextSize);
        mPaintTextLeft.setAntiAlias(true);
     
        mPaintTextRight = new Paint();
        mPaintTextRight.setColor(mRightLineTextColor);
        mPaintTextRight.setTextSize(mLineTextSize);
        mPaintTextRight.setAntiAlias(true);

       
        mPaintTextXY = new Paint();
        mPaintTextXY.setStrokeWidth(1);
        mPaintTextXY.setColor(mLineXYColor);
        mPaintTextXY.setTextSize(mLineXYSize);
        mPaintTextXY.setAntiAlias(true);
    }

 
    public void setData(int[] dataLeft, int[] dataRight, String[] dataTextX) {
        this.mDataLeft = dataLeft;
        this.mDataRight = dataRight;
        this.mDataTextX = dataTextX;
        int maxLeft = getMax(mDataLeft);
        int maxRight = getMax(mDataRight);
        mMaxData = maxLeft > maxRight ? maxLeft : maxRight;
        Log.i(TAG, "mMaxCount=" + mMaxData);
        while (mMaxData % 5 != 0) {
            mMaxData++;
        }
        Log.i(TAG, "mMaxCount=" + mMaxData);
    }


    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setDimension(heightMeasureSpec);
    }

  
    private void setDimension(int heightMeasureSpec) {
        mViewWidth = (int) (mYDistance + mBigDistance + (mDataLeft.length * (mLineWidth * 2 + mBigDistance + mSmallDistance)));
        mViewHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(mViewWidth, mViewHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
     
        drawLineData(canvas);
   
        drawLineXY(canvas);
    
        drawLineX(canvas);
   
        drawLineY(canvas);
    }


    private void drawLineX(Canvas canvas) {
        for (int i = 0; i < mDataTextX.length; i++) {
            String text = mDataTextX[i];
            float textWidth = mPaintTextXY.measureText(text, 0, text.length());
            float dx = (mYDistance + mBigDistance + mSmallDistance / 2 + mLineWidth + (i * (mBigDistance + mSmallDistance + 2 * mLineWidth))) - textWidth / 2;
            Paint.FontMetricsInt fontMetricsInt = mPaintTextXY.getFontMetricsInt();
            float dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
            float baseLine = mViewHeight - mXDistance / 2 + dy;
            canvas.drawText(text, dx, baseLine, mPaintTextXY);
        }
    }

   
    private void drawLineY(Canvas canvas) {
        for (int i = 0; i < 6; i++) {
            String text = (mMaxData / 5 * i) + "";
            float textWidth = mPaintTextXY.measureText(text, 0, text.length());
            float dx = mYDistance / 2 - textWidth / 2;
            Paint.FontMetricsInt fontMetricsInt = mPaintTextXY.getFontMetricsInt();
            float dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
            float baseLine = (mViewHeight - mXDistance) - (i * (mViewHeight - 2 * mXDistance) / 5) + dy;
            canvas.drawText(text, dx, baseLine, mPaintTextXY);
            if (mIsShowArrowYInterval)
            	canvas.drawLine(mYDistance, (mViewHeight - mXDistance)
                            - (i * (mViewHeight - 2 * mXDistance) / 5), mYDistance +dip2px(mContext, 500),
                    (mViewHeight - mXDistance) - (i * (mViewHeight - 2 * mXDistance) / 5), mPaintTextXY);
        }
    }


    private void drawLineXY(Canvas canvas) {
        canvas.drawLine(mYDistance, mViewHeight - mXDistance, mYDistance, 15, mPaintTextXY);
        canvas.drawLine(mYDistance, mViewHeight - mXDistance, mViewWidth - 15, mViewHeight - mXDistance, mPaintTextXY);
    }


    private void drawLineData(Canvas canvas) {
        for (int i = 0; i < mDataLeft.length; i++) {
            float startX = mYDistance + mBigDistance + mLineWidth / 2 + (i * (mBigDistance + mSmallDistance + 2 * mLineWidth));
            float endY = (mViewHeight - mXDistance) - (mDataLeft[i] * (mViewHeight - 2 * mXDistance)) / mMaxData;
            canvas.drawLine(startX, mViewHeight - mXDistance, startX, endY, mPaintLeft);
            String text = mDataLeft[i] + "";
            float textWidth = mPaintTextLeft.measureText(text, 0, text.length());
            canvas.drawText(text, startX - textWidth / 2, endY - 15, mPaintTextLeft);
        }

        for (int i = 0; i < mDataRight.length; i++) {
            float startX = mYDistance + mBigDistance + mSmallDistance + mLineWidth + mLineWidth / 2 + (i * (mBigDistance + mSmallDistance + 2 * mLineWidth));
            float endY = ((mViewHeight - mXDistance)) - (mDataRight[i] * (mViewHeight - 2 * mXDistance)) / mMaxData;
            canvas.drawLine(startX, mViewHeight - mXDistance, startX, endY, mPaintRight);
            String text = mDataRight[i] + "";
            float textWidth = mPaintTextRight.measureText(text, 0, text.length());
            canvas.drawText(text, startX - textWidth / 2, endY - 15, mPaintTextRight);
        }
    }

   

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

 
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
