package com.example.fffff;

import android.R.integer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class JiuGongGeLock extends View {
	private int width = 200;// 控件宽度
	private int height = 300;// 控件高度
	private float radius;// 半径
	private float dis;// 间隔
	private JiuGongPoint points[] = new JiuGongPoint[9];// 9个点的位置信息
	private StringBuffer sPoints = new StringBuffer("");// 选中的点的索引位置
	private String mPass = "012345";// 正确密令
	private String mPass1 = "678";
	private float currentX, currentY;// 记录当前接触点位置
	private JiuGongPoint curPoint;// 记录当前选中点
	private boolean checkFlag = false;// 记录密码判定结果
	private boolean moveFlag = false;// 移动状态
	private int errorCount;// 错误次数
	private OnDrawFinishedListener onDrawFinishedListener;// 解锁状态变化接口事件

	// 系统要求的构造函数
	public JiuGongGeLock(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public JiuGongGeLock(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		// 设置View大小
		setMeasuredDimension(200, 200);
	}

	/**
	 * 初始化 计算初始九点的位置
	 */
	private void init() {
		radius = (Math.min(width, height) / 6.0f) - 10;
		dis = 2 * radius + 5;
		float x0, y0;
		x0 = dis / 2;
		y0 = x0;
		for (int i = 0; i < 9; i++) {
			points[i] = new JiuGongPoint(x0 + (i % 3) * dis, y0 + (i / 3) * dis);
		}
		errorCount = 0;// 错误次数清零
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO 自动生成的方法存根
		Paint paint = new Paint();
		// 绘制九个点
		for (JiuGongPoint p : points) {
			canvas.drawCircle(p.x0, p.y0, 8f, paint);
		}
		// 绘制选中的点与线
		if (sPoints.length() > 0) {
			if (checkFlag) {// 如果密码正确输出蓝色圆圈及路径
				paint.setColor(Color.BLUE);
			} else
				paint.setColor(Color.RED);// 否则使用红色画笔绘制
			paint.setStyle(Paint.Style.STROKE);// 设置圆圈为空心
			JiuGongPoint prePoint = getPointByIndex(sPoints.charAt(0));// 获取第一个选中的点
			canvas.drawCircle(prePoint.x0, prePoint.y0, radius, paint);
			for (int i = 1; i < sPoints.length(); i++) {
				JiuGongPoint nowPoint = getPointByIndex(sPoints.charAt(i));
				canvas.drawCircle(nowPoint.x0, nowPoint.y0, radius, paint); // 绘制当前节点的空心圆
				canvas.drawLine(prePoint.x0, prePoint.y0, nowPoint.x0,
						nowPoint.y0, paint);// 绘制当前节点到上一个选中点的路径
				prePoint = nowPoint;
			}
			// 手势未结束，则画出当前手势位置到上一点选中节点的路径
			if (moveFlag) {
				canvas.drawLine(curPoint.x0, curPoint.y0, currentX, currentY,
						paint);
			}
		}
		
	}

	// 根据索引的字符获取点对象
	private JiuGongPoint getPointByIndex(char index) {
		int i = Integer.parseInt("" + index);
		if (i >= 0 && i < 9)
			return points[i];
		return null;
	}

	/**
	 * 滑屏手势处理
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (checkFlag)
			return false;// 验证通过不再判定
		currentX = event.getX();
		currentY = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:// 手势开始，初始化
			sPoints.delete(0, sPoints.length());// 选中列表清空
			curPoint = null;// 当前无选中节点
			checkFlag = false;// 重新检测，密码初始为false
			moveFlag = true;// 设置移动状态为移动，直到手离开屏幕
			for (JiuGongPoint p : points)
				p.isSelected = false;// 选中状态清空
			checkInPoint();// 判定当前位置是否选中节点
			break;
		case MotionEvent.ACTION_MOVE:// 手势移动，将选中点放入选择列表
			checkInPoint();
			break;
		case MotionEvent.ACTION_UP:// 手势结束，判定是否正确
			moveFlag = false;// 结束移动
			checkPass();
			break;
		}
		invalidate();// 更新界面
		return true;
	}

	// 判定当前位置是否存在可选中节点,在范围内未选中则填入选择表中
	private void checkInPoint() {
		for (int i = 0; i < points.length; i++) {
			JiuGongPoint p = points[i];
			if (!p.isSelected) {// 当前节点未选中，则判定距离是否小于指定半径，是则选中当前节点
				if (Math.pow((p.x0 - currentX), 2)
						+ Math.pow((p.y0 - currentY), 2) <= Math.pow(radius, 2)) {
					p.isSelected = true;// 修改节点状态为选中
					sPoints.append(i);// 并加入到选中列表中
					curPoint = p;// 更新当前选中节点
					break;
				}
			}
		}
	}

	/**
	 * 检测输入的密码是否正确
	 */
	private void checkPass() {
		if (mPass1.equals(sPoints.toString())) {
			checkFlag = true;
			if (null !=  onDrawFinishedListener) {
				onDrawFinishedListener.onSuccess(1);
			}
		} else if (mPass.equals(sPoints.toString())) {
			checkFlag = true;
			if (null != onDrawFinishedListener) {
				onDrawFinishedListener.onSuccess(0);
			}
		} else {
			errorCount++;
			if (null != onDrawFinishedListener) {
				onDrawFinishedListener.onFailure(errorCount);
			}

		}
	}

	public void setOnDrawFinishedListener(
			OnDrawFinishedListener l) {// 设置监听事件
		this.onDrawFinishedListener = l;
	}
}

// 九宫格解锁状态变化是触发事件
interface OnDrawFinishedListener {
	// 当解锁成功时回调的方法
	void onSuccess(int count);

	// 当解锁失败时回调的方法
	void onFailure(int errCount);
}

// 九宫格点对象
class JiuGongPoint {
	public float x0;
	public float y0;
	public boolean isSelected;

	public JiuGongPoint(float x0, float y0) {
		this.x0 = x0;
		this.y0 = y0;
		this.isSelected = false;
	}

}