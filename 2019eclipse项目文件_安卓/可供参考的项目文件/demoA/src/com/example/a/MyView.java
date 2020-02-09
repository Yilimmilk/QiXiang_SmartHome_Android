package com.example.a;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MyView extends View {
	private Bitmap jiesuo_bg; // 背景图片
    private Bitmap jiesuo_button; //滑块图片
    private int bg_width;
    private int bg_height;
    private int block_width; //锁屏背景宽度
    private int measuredWidth;
    private int measuredHeight;
    private float downX;
    private float downY;
    private float currentX;
    private float currentY;
    private boolean isOnBlock;
    private int left;
    private int right;
    private OnUnlockListener onUnlockListener;
    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        jiesuo_bg = BitmapFactory.decodeResource(getResources(), R.drawable.b2303);
        jiesuo_button = BitmapFactory.decodeResource(getResources(), R.drawable.b2302);        
        jiesuo_bg = getBigOrSmall(jiesuo_bg, 1.5f, 1.2f);        
        bg_width = jiesuo_bg.getWidth();
        bg_height = jiesuo_bg.getHeight();
        block_width = jiesuo_button.getWidth();

    }    
    private Bitmap getBigOrSmall(Bitmap bmp,float xbs,float ybs){
    	Matrix matrix = new Matrix();
    	matrix.postScale(xbs, ybs);
    	Bitmap dstbmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(),
				matrix, true);
		return dstbmp;
    } 
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(jiesuo_bg, measuredWidth / 2 - bg_width / 2, measuredHeight / 2 - bg_height / 2, null);
        //控制边界
        if (currentX < left) {
            currentX = left;
        } else if (currentX > right) {
            currentX = right;
        }
        canvas.drawBitmap(jiesuo_button, currentX, currentY+bg_height/2-jiesuo_button.getHeight()/2, null);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measuredWidth = getMeasuredWidth();
        measuredHeight = getMeasuredHeight();
        
        //
        float xbs = measuredWidth/jiesuo_bg.getWidth();
        float ybs = measuredHeight/jiesuo_bg.getHeight();
        jiesuo_bg = getBigOrSmall(jiesuo_bg, xbs, ybs);
        
        //获取一开始的位置
        currentX = measuredWidth / 2 - bg_width / 2;
        currentY = measuredHeight / 2 - bg_height / 2;
        left = measuredWidth / 2 - bg_width / 2;
        right = measuredWidth / 2 + bg_width / 2 - block_width;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //判断手指是否按在了小球上
                downX = event.getX();
                downY = event.getY();
                isOnBlock = isOnBlock(downX, downY);
                if (isOnBlock) {
                    Toast.makeText(getContext(), "按到了", Toast.LENGTH_SHORT).show();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isOnBlock) {
                    //获取最新的位置
                    float moveX = event.getX();
                    currentX = moveX - block_width / 2;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                isOnBlock = false;
                if (currentX < right - 5) {
                    //应该弹回去
                    currentX = left;
                } else {
                    if (onUnlockListener != null) {
                        Toast.makeText(getContext(), "解锁", Toast.LENGTH_SHORT).show();
                        onUnlockListener.setUnlock(true);
                    }

                }
                invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }
    /**
     * @param downX
     * @param downY
     * @return
     */
    private boolean isOnBlock(float downX, float downY) {
        //先计算圆心点
        float rx = currentX + block_width / 2;
        float ry = currentY + block_width / 2;

        double distance = Math.sqrt((downX - rx) * (downX - rx) + (downY - ry) * (downY - ry));
        if (distance < block_width / 2) {
            return true;
        }
        return false;
    }

    public void setOnUnlockListener(OnUnlockListener onUnlockListener) {
        this.onUnlockListener = onUnlockListener;
    }

    public interface OnUnlockListener {
        void setUnlock(boolean unlock);
    }

}
