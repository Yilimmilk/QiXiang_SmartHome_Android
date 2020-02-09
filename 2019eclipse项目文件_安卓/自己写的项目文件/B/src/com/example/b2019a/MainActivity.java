package com.example.b2019a;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	
	private View cursor1,cursor2,cursor3,cursor4;
	private TextView guangzhao,shidu,wendu,yanwu,qiya,co2,pm25,ranqi,renti;
	private TextView tv8101,tv8102,tv8103,tv8104,tv8201,tv8202,tv8203,tv8204,tv8301,tv8302,tv8303,tv8304,tv8401,tv8402,tv8403,tv8404;
	private Button btManage,btControl,btLink,btPaint,btYes,btWait,btNo;
	
	private int select;
	public int status8101,status8102,status8103;
	
	public SharedPreferences roomStatus;
	public Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
		
		//默认光标状态
		select=1;
		cursor1.setVisibility(View.VISIBLE);
		cursor2.setVisibility(View.INVISIBLE);
		cursor3.setVisibility(View.INVISIBLE);
		cursor4.setVisibility(View.INVISIBLE);
		
		//初始化
		roomStatus = getSharedPreferences("status", 0);
//		Editor editor=roomStatus.edit();
//		editor.putInt("8101", 1);
//		editor.putInt("8102", 1);
//		editor.putInt("8103", 1);
//		editor.putInt("8104", 1);
//		editor.putInt("8201", 1);
//		editor.putInt("8202", 1);
//		editor.putInt("8203", 1);
//		editor.putInt("8204", 1);
//		editor.putInt("8301", 1);
//		editor.putInt("8302", 1);
//		editor.putInt("8303", 1);
//		editor.putInt("8304", 1);
//		editor.putInt("8401", 1);
//		editor.putInt("8402", 1);
//		editor.putInt("8403", 1);
//		editor.putInt("8404", 1);
//		editor.commit();
		
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				handler=new Handler();
				handler.post(checkedStatus);
			}
		});
		
		SocketClient.getInstance().login( new LoginCallback() {
            @Override
            public void onEvent(final String status) {
                runOnUiThread( new Runnable() {
                    @Override
                    public void run() {
                        if (status.equals( ConstantUtil.SUCCESS  )) {
                            Toast.makeText( MainActivity.this, "组网成功！", Toast.LENGTH_SHORT ).show();
                        } else if (status.equals( ConstantUtil.FAILURE  )) {
                            Toast.makeText( MainActivity.this, "组网失败！", Toast.LENGTH_SHORT ).show();
                        } else {
                            Toast.makeText( MainActivity.this, "组网中！", Toast.LENGTH_SHORT ).show();
                        }
                    }
                } );
            }
        } );
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		
		case R.id.bt_manage:
			select=1;
			cursor1.setVisibility(View.VISIBLE);
			cursor2.setVisibility(View.INVISIBLE);
			cursor3.setVisibility(View.INVISIBLE);
			cursor4.setVisibility(View.INVISIBLE);
			break;
			
		case R.id.bt_control:
			select=2;
			cursor1.setVisibility(View.INVISIBLE);
			cursor2.setVisibility(View.VISIBLE);
			cursor3.setVisibility(View.INVISIBLE);
			cursor4.setVisibility(View.INVISIBLE);
			break;
			
		case R.id.bt_link:
			select=3;
			cursor1.setVisibility(View.INVISIBLE);
			cursor2.setVisibility(View.INVISIBLE);
			cursor3.setVisibility(View.VISIBLE);
			cursor4.setVisibility(View.INVISIBLE);
			break;
			
		case R.id.bt_paint:
			select=4;
			cursor1.setVisibility(View.INVISIBLE);
			cursor2.setVisibility(View.INVISIBLE);
			cursor3.setVisibility(View.INVISIBLE);
			cursor4.setVisibility(View.VISIBLE);
			break;
			
		
		
		
		
		case R.id.tv_8101:
			if (select==1) {
				initDialog(8101);
			}else if (select==2) {
				intentStartControl(8101);
			}else if (select==3) {
				intentStartLink(8101);
			}else if (select==4) {
				intentStartPaint(8101);
			}
			break;
			
		case R.id.tv_8102:
			if (select==1) {
				initDialog(8102);
			}else if (select==2) {
				intentStartControl(8102);
			}else if (select==3) {
				intentStartLink(8102);
			}else if (select==4) {
				intentStartPaint(8102);
			}
			break;
			
		case R.id.tv_8103:
			if (select==1) {
				initDialog(8103);
			}else if (select==2) {
				intentStartControl(8103);
			}else if (select==3) {
				intentStartLink(8103);
			}else if (select==4) {
				intentStartPaint(8103);
			}
			break;
			
		case R.id.tv_8104:
			if (select==1) {
				initDialog(8104);
			}else if (select==2) {
				intentStartControl(8104);
			}else if (select==3) {
				intentStartLink(8104);
			}else if (select==4) {
				intentStartPaint(8104);
			}
			break;
			
		case R.id.tv_8201:
			if (select==1) {
				initDialog(8201);
			}else if (select==2) {
				intentStartControl(8201);
			}else if (select==3) {
				intentStartLink(8201);
			}else if (select==4) {
				intentStartPaint(8201);
			}
			break;
			
		case R.id.tv_8202:
			if (select==1) {
				initDialog(8202);
			}else if (select==2) {
				intentStartControl(8202);
			}else if (select==3) {
				intentStartLink(8202);
			}else if (select==4) {
				intentStartPaint(8202);
			}
			break;
			
		case R.id.tv_8203:
			if (select==1) {
				initDialog(8203);
			}else if (select==2) {
				intentStartControl(8203);
			}else if (select==3) {
				intentStartLink(8203);
			}else if (select==4) {
				intentStartPaint(8203);
			}
			break;
			
		case R.id.tv_8204:
			if (select==1) {
				initDialog(8204);
			}else if (select==2) {
				intentStartControl(8204);
			}else if (select==3) {
				intentStartLink(8204);
			}else if (select==4) {
				intentStartPaint(8204);
			}
			break;
			
		case R.id.tv_8301:
			if (select==1) {
				initDialog(8301);
			}else if (select==2) {
				intentStartControl(8301);
			}else if (select==3) {
				intentStartLink(8301);
			}else if (select==4) {
				intentStartPaint(8301);
			}
			break;
			
		case R.id.tv_8302:
			if (select==1) {
				initDialog(8302);
			}else if (select==2) {
				intentStartControl(8302);
			}else if (select==3) {
				intentStartLink(8302);
			}else if (select==4) {
				intentStartPaint(8302);
			}
			break;
			
		case R.id.tv_8303:
			if (select==1) {
				initDialog(8303);
			}else if (select==2) {
				intentStartControl(8303);
			}else if (select==3) {
				intentStartLink(8303);
			}else if (select==4) {
				intentStartPaint(8303);
			}
			break;
			
		case R.id.tv_8304:
			if (select==1) {
				initDialog(8304);
			}else if (select==2) {
				intentStartControl(8304);
			}else if (select==3) {
				intentStartLink(8304);
			}else if (select==4) {
				intentStartPaint(8304);
			}
			break;
			
		case R.id.tv_8401:
			if (select==1) {
				initDialog(8401);
			}else if (select==2) {
				intentStartControl(8401);
			}else if (select==3) {
				intentStartLink(8401);
			}else if (select==4) {
				intentStartPaint(8401);
			}
			break;
			
		case R.id.tv_8402:
			if (select==1) {
				initDialog(8402);
			}else if (select==2) {
				intentStartControl(8402);
			}else if (select==3) {
				intentStartLink(8402);
			}else if (select==4) {
				intentStartPaint(8402);
			}
			break;
			
		case R.id.tv_8403:
			if (select==1) {
				initDialog(8403);
			}else if (select==2) {
				intentStartControl(8403);
			}else if (select==3) {
				intentStartLink(8403);
			}else if (select==4) {
				intentStartPaint(8403);
			}
			break;
			
		case R.id.tv_8404:
			if (select==1) {
				initDialog(8404);
			}else if (select==2) {
				intentStartControl(8404);
			}else if (select==3) {
				intentStartLink(8404);
			}else if (select==4) {
				intentStartPaint(8404);
			}
			break;

		default:
			break;
		}
	}
	
	private void initDialog(final int fanghaoInt){
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		View view = View.inflate(MainActivity.this, R.layout.dialog_custom, null);
		builder.setTitle("房间管理");
		builder.setView(view);
		builder.setCancelable(true);
		builder.setNegativeButton("关闭", null);
		final String roomnumString= Integer.toString(fanghaoInt);
		
		TextView fanghao= (TextView) view.findViewById(R.id.tv_dialog_fanghao);
		fanghao.setText(roomnumString);
		guangzhao= (TextView) view.findViewById(R.id.tv_guangzhao);
		shidu= (TextView) view.findViewById(R.id.tv_shidu);
		wendu= (TextView) view.findViewById(R.id.tv_wendu);
		yanwu= (TextView) view.findViewById(R.id.tv_yanwu);
		qiya= (TextView) view.findViewById(R.id.tv_qiya);
		co2= (TextView) view.findViewById(R.id.tv_co2);
		pm25= (TextView) view.findViewById(R.id.tv_pm25);
		ranqi= (TextView) view.findViewById(R.id.tv_ranqi);
		renti= (TextView) view.findViewById(R.id.tv_renti);
		
		btYes=(Button)view.findViewById(R.id.bt_yes);
		btWait=(Button)view.findViewById(R.id.bt_wait);
		btNo=(Button)view.findViewById(R.id.bt_no);
		
		ControlUtils.getData();
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {

			@Override
			public void onResult(DeviceBean bean) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (!TextUtils.isEmpty(DeviceBean.getTemperature())) {
							wendu.setText(DeviceBean.getTemperature());
						}
						if (!TextUtils.isEmpty(DeviceBean.getHumidity())) {
							shidu.setText(DeviceBean.getHumidity());
						}
						if (!TextUtils.isEmpty(DeviceBean.getGas())) {
							ranqi.setText(DeviceBean.getGas());
						}
						if (!TextUtils.isEmpty(DeviceBean.getIllumination())) {
							guangzhao.setText(DeviceBean.getIllumination());
						}
						if (!TextUtils.isEmpty(DeviceBean.getPM25())) {
							pm25.setText(DeviceBean.getPM25());
						}
						if (!TextUtils.isEmpty(DeviceBean.getAirPressure())) {
							qiya.setText(DeviceBean.getAirPressure());
						}
						if (!TextUtils.isEmpty(DeviceBean.getSmoke())) {
							yanwu.setText(DeviceBean.getSmoke());
						}
						if (!TextUtils.isEmpty(DeviceBean.getCo2())) {
							co2.setText(DeviceBean.getCo2());
						}
						if (!TextUtils.isEmpty( DeviceBean.getStateHumanInfrared() ) && DeviceBean.getStateHumanInfrared().equals( ConstantUtil.CLOSE )){
                            renti.setText( "无人" );
                        }else{
                            renti.setText( "有人" );
                        }
					}
				});
			}
		});
		
		btYes.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Editor editor = roomStatus.edit();
				editor.putInt(roomnumString, 1);
				editor.commit();
			}
		});

		btWait.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Editor editor = roomStatus.edit();
				editor.putInt(roomnumString, 2);
				editor.commit();
			}
		});

		btNo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Editor editor = roomStatus.edit();
				editor.putInt(roomnumString, 3);
				editor.commit();
			}
		});
		
		builder.create().show();
	}

	private void initView(){
		
		btManage=(Button)findViewById(R.id.bt_manage);
		btControl=(Button)findViewById(R.id.bt_control);
		btLink=(Button)findViewById(R.id.bt_link);
		btPaint=(Button)findViewById(R.id.bt_paint);
		
		cursor1=(View)findViewById(R.id.cursor_manage);
		cursor2=(View)findViewById(R.id.cursor_control);
		cursor3=(View)findViewById(R.id.cursor_link);
		cursor4=(View)findViewById(R.id.cursor_paint);
		
		tv8101=(TextView)findViewById(R.id.tv_8101);
		tv8102=(TextView)findViewById(R.id.tv_8102);
		tv8103=(TextView)findViewById(R.id.tv_8103);
		tv8104=(TextView)findViewById(R.id.tv_8104);
		tv8201=(TextView)findViewById(R.id.tv_8201);
		tv8202=(TextView)findViewById(R.id.tv_8202);
		tv8203=(TextView)findViewById(R.id.tv_8203);
		tv8204=(TextView)findViewById(R.id.tv_8204);
		tv8301=(TextView)findViewById(R.id.tv_8301);
		tv8302=(TextView)findViewById(R.id.tv_8302);
		tv8303=(TextView)findViewById(R.id.tv_8303);
		tv8304=(TextView)findViewById(R.id.tv_8304);
		tv8401=(TextView)findViewById(R.id.tv_8401);
		tv8402=(TextView)findViewById(R.id.tv_8402);
		tv8403=(TextView)findViewById(R.id.tv_8403);
		tv8404=(TextView)findViewById(R.id.tv_8404);
		
		btManage.setOnClickListener(this);
		btControl.setOnClickListener(this);
		btLink.setOnClickListener(this);
		btPaint.setOnClickListener(this);
		
		tv8101.setOnClickListener(this);
		tv8102.setOnClickListener(this);
		tv8103.setOnClickListener(this);
		tv8104.setOnClickListener(this);
		tv8201.setOnClickListener(this);
		tv8202.setOnClickListener(this);
		tv8203.setOnClickListener(this);
		tv8204.setOnClickListener(this);
		tv8301.setOnClickListener(this);
		tv8302.setOnClickListener(this);
		tv8303.setOnClickListener(this);
		tv8304.setOnClickListener(this);
		tv8401.setOnClickListener(this);
		tv8402.setOnClickListener(this);
		tv8403.setOnClickListener(this);
		tv8404.setOnClickListener(this);
	}
	
	Runnable checkedStatus = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			if (roomStatus.getInt("8101", 0)==1) {
				tv8101.setBackgroundColor(Color.parseColor("#00ff00"));
				Log.d("可入住", "8101");
			}
			if (roomStatus.getInt("8102", 0)==1) {
				tv8102.setBackgroundColor(Color.parseColor("#00ff00"));
				Log.d("可入住", "8102");
			}
			if (roomStatus.getInt("8103", 0)==1) {
				tv8103.setBackgroundColor(Color.parseColor("#00ff00"));
				Log.d("可入住", "8103");
			}
			if (roomStatus.getInt("8104", 0)==1) {
				tv8104.setBackgroundColor(Color.parseColor("#00ff00"));
				Log.d("可入住", "8104");
			}
			
			if (roomStatus.getInt("8201", 0)==1) {
				tv8201.setBackgroundColor(Color.parseColor("#00ff00"));
				Log.d("可入住", "8201");
			}
			if (roomStatus.getInt("8202", 0)==1) {
				tv8202.setBackgroundColor(Color.parseColor("#00ff00"));
				Log.d("可入住", "8202");
			}
			if (roomStatus.getInt("8203", 0)==1) {
				tv8203.setBackgroundColor(Color.parseColor("#00ff00"));
				Log.d("可入住", "8203");
			}
			if (roomStatus.getInt("8204", 0)==1) {
				tv8204.setBackgroundColor(Color.parseColor("#00ff00"));
				Log.d("可入住", "8204");
			}
			
			if (roomStatus.getInt("8301", 0)==1) {
				tv8301.setBackgroundColor(Color.parseColor("#00ff00"));
				Log.d("可入住", "8301");
			}
			if (roomStatus.getInt("8302", 0)==1) {
				tv8302.setBackgroundColor(Color.parseColor("#00ff00"));
				Log.d("可入住", "8302");
			}
			if (roomStatus.getInt("8303", 0)==1) {
				tv8303.setBackgroundColor(Color.parseColor("#00ff00"));
				Log.d("可入住", "8303");
			}
			if (roomStatus.getInt("8304", 0)==1) {
				tv8304.setBackgroundColor(Color.parseColor("#00ff00"));
				Log.d("可入住", "8304");
			}
			
			if (roomStatus.getInt("8401", 0)==1) {
				tv8401.setBackgroundColor(Color.parseColor("#00ff00"));
				Log.d("可入住", "8401");
			}
			if (roomStatus.getInt("8402", 0)==1) {
				tv8402.setBackgroundColor(Color.parseColor("#00ff00"));
				Log.d("可入住", "8402");
			}
			if (roomStatus.getInt("8403", 0)==1) {
				tv8403.setBackgroundColor(Color.parseColor("#00ff00"));
				Log.d("可入住", "8403");
			}
			if (roomStatus.getInt("8404", 0)==1) {
				tv8404.setBackgroundColor(Color.parseColor("#00ff00"));
				Log.d("可入住", "8404");
			}
			
			
			
			
			if (roomStatus.getInt("8101", 0)==2) {
				tv8101.setBackgroundColor(Color.parseColor("#808080"));
			}
			if (roomStatus.getInt("8102", 0)==2) {
				tv8102.setBackgroundColor(Color.parseColor("#808080"));
			}
			if (roomStatus.getInt("8103", 0)==2) {
				tv8103.setBackgroundColor(Color.parseColor("#808080"));
			}
			if (roomStatus.getInt("8104", 0)==2) {
				tv8104.setBackgroundColor(Color.parseColor("#808080"));
			}
			
			if (roomStatus.getInt("8201", 0)==2) {
				tv8201.setBackgroundColor(Color.parseColor("#808080"));
			}
			if (roomStatus.getInt("8202", 0)==2) {
				tv8202.setBackgroundColor(Color.parseColor("#808080"));
			}
			if (roomStatus.getInt("8203", 0)==2) {
				tv8203.setBackgroundColor(Color.parseColor("#808080"));
			}
			if (roomStatus.getInt("8204", 0)==2) {
				tv8204.setBackgroundColor(Color.parseColor("#808080"));
			}
			
			if (roomStatus.getInt("8301", 0)==2) {
				tv8301.setBackgroundColor(Color.parseColor("#808080"));
			}
			if (roomStatus.getInt("8302", 0)==2) {
				tv8302.setBackgroundColor(Color.parseColor("#808080"));
			}
			if (roomStatus.getInt("8303", 0)==2) {
				tv8303.setBackgroundColor(Color.parseColor("#808080"));
			}
			if (roomStatus.getInt("8304", 0)==2) {
				tv8304.setBackgroundColor(Color.parseColor("#808080"));
			}
			
			if (roomStatus.getInt("8401", 0)==2) {
				tv8401.setBackgroundColor(Color.parseColor("#808080"));
			}
			if (roomStatus.getInt("8402", 0)==2) {
				tv8402.setBackgroundColor(Color.parseColor("#808080"));
			}
			if (roomStatus.getInt("8403", 0)==2) {
				tv8403.setBackgroundColor(Color.parseColor("#808080"));
			}
			if (roomStatus.getInt("8404", 0)==2) {
				tv8404.setBackgroundColor(Color.parseColor("#808080"));
			}
			
			
			
			
			if (roomStatus.getInt("8101", 0)==3) {
				tv8101.setBackgroundColor(Color.parseColor("#ff0000"));
			}
			if (roomStatus.getInt("8102", 0)==3) {
				tv8102.setBackgroundColor(Color.parseColor("#ff0000"));
			}
			if (roomStatus.getInt("8103", 0)==3) {
				tv8103.setBackgroundColor(Color.parseColor("#ff0000"));
			}
			if (roomStatus.getInt("8104", 0)==3) {
				tv8104.setBackgroundColor(Color.parseColor("#ff0000"));
			}
			
			if (roomStatus.getInt("8201", 0)==3) {
				tv8201.setBackgroundColor(Color.parseColor("#ff0000"));
			}
			if (roomStatus.getInt("8202", 0)==3) {
				tv8202.setBackgroundColor(Color.parseColor("#ff0000"));
			}
			if (roomStatus.getInt("8203", 0)==3) {
				tv8203.setBackgroundColor(Color.parseColor("#ff0000"));
			}
			if (roomStatus.getInt("8204", 0)==3) {
				tv8204.setBackgroundColor(Color.parseColor("#ff0000"));
			}
			
			if (roomStatus.getInt("8301", 0)==3) {
				tv8301.setBackgroundColor(Color.parseColor("#ff0000"));
			}
			if (roomStatus.getInt("8302", 0)==3) {
				tv8302.setBackgroundColor(Color.parseColor("#ff0000"));
			}
			if (roomStatus.getInt("8303", 0)==3) {
				tv8303.setBackgroundColor(Color.parseColor("#ff0000"));
			}
			if (roomStatus.getInt("8304", 0)==3) {
				tv8304.setBackgroundColor(Color.parseColor("#ff0000"));
			}
			
			if (roomStatus.getInt("8401", 0)==3) {
				tv8401.setBackgroundColor(Color.parseColor("#ff0000"));
			}
			if (roomStatus.getInt("8402", 0)==3) {
				tv8402.setBackgroundColor(Color.parseColor("#ff0000"));
			}
			if (roomStatus.getInt("8403", 0)==3) {
				tv8403.setBackgroundColor(Color.parseColor("#ff0000"));
			}
			if (roomStatus.getInt("8404", 0)==3) {
				tv8404.setBackgroundColor(Color.parseColor("#ff0000"));
			}
			handler.postDelayed(this, 1000);
            Log.d("检测状态Handler结束", "延迟两秒开始执行");
		}
	};
	
	private void intentStartControl(int roomNum){
		Intent intent=new Intent(MainActivity.this,ControlActivity.class);
		intent.putExtra("selectRoomNum", roomNum);
		startActivity(intent);
	}
	
	private void intentStartLink(int roomNum){
		Intent intent=new Intent(MainActivity.this,LinkActivity.class);
		intent.putExtra("selectRoomNum", roomNum);
		startActivity(intent);
	}
	
	private void intentStartPaint(int roomNum){
		Intent intent=new Intent(MainActivity.this,PaintActivity.class);
		intent.putExtra("selectRoomNum", roomNum);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacks( checkedStatus );
        }
    }
}
