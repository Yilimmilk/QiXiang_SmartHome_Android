package com.example.fffff;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.SocketClient;

public class BaseFragment extends Fragment implements View.OnClickListener, OnTouchListener{
	TextView text_tmp, text_humidy, text_gas, text_guangzhao, text_qiya,
			text_pm25, text_yanwu, text_co2, text_renti;
	ImageView view_baojingdeng, view_door, view_deng, view_fun, view_curOn,
			view_pause, view_off, view_hong1, view_hong2, view_hong3,
			view_tvimage,view_dvdimage,view_kongtiaovimage;
	MainActivity activity;
	private Handler handler = new Handler();
	private DisplayMetrics dm;
	private int lastX;
	private int lastY;
	private ImageView mLogoIv2,mLogoIv3;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_base, null);
		view.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(),SeeSqlActivity.class));
				return false;
			}
		});
		 dm =getActivity().getResources().getDisplayMetrics();    
		activity = (MainActivity) getActivity();
//		控件初始化
		init(view);
		initDatas();
		return view;
	}
	
	//数据写入数据库
    private void insertDatas(String name,String value) {
		// TODO Auto-generated method stub
		activity.helper = new Helper(activity);
		activity.db = activity.helper.getReadableDatabase();
		ContentValues values = new ContentValues();
		values.put("type", name);
		values.put("value", value);
		activity.db.insert("t_data", null, values);
	}
    
private void initDatas() {
		// TODO Auto-generated method stub
	ControlUtils.getData();
    SocketClient.getInstance().getData( new DataCallback<DeviceBean>() {
        @Override
        public void onResult(final DeviceBean bean) {
            activity.runOnUiThread( new Runnable() {
                @Override
                public void run() {
                    	text_tmp.setText(DeviceBean.getTemperature());
                    	insertDatas("温度",DeviceBean.getTemperature());
                        text_humidy.setText( DeviceBean.getHumidity() );
                        insertDatas("湿度",DeviceBean.getHumidity());
                   
                        text_yanwu.setText( DeviceBean.getSmoke() );
                        insertDatas("烟雾",DeviceBean.getSmoke());
                        
                        if(!DeviceBean.getSmoke().isEmpty()){
                        	activity.yanwu_value = Float.parseFloat(DeviceBean.getSmoke());
                        }else{
                        	activity.yanwu_value = 0;
                        }
                        
                        text_gas.setText( DeviceBean.getGas() );
                        insertDatas("燃气",DeviceBean.getGas());
                        if(!DeviceBean.getGas().isEmpty()){
                        	activity.ranqi_value = Float.parseFloat(DeviceBean.getGas());
                        }else{
                        	activity.ranqi_value = 0;
                        }
                    
                    	text_guangzhao.setText(DeviceBean.getIllumination() );
                    	insertDatas("光照",DeviceBean.getIllumination());
                    	text_qiya.setText( DeviceBean.getAirPressure() );
                    	insertDatas("气压",DeviceBean.getAirPressure());
                    	text_co2.setText( DeviceBean.getCo2() );
                    	insertDatas("Co2",DeviceBean.getCo2());
                        text_pm25.setText( DeviceBean.getPM25() );
                        insertDatas("PM2.5",DeviceBean.getPM25());
                        insertDatas("人体",DeviceBean.getStateHumanInfrared());
                        if (!DeviceBean.getStateHumanInfrared().isEmpty() && DeviceBean.getStateHumanInfrared().equals( ConstantUtil.CLOSE )){
                        	text_renti.setText( "无人" );
                        	activity.hasman= false;
                        }else{
                        	text_renti.setText( "有人" );
                        	activity.hasman= true;
                        }
                }
            } );
        }
    } );
}

//	控件初始化
	private void init(View view) {
		// TODO Auto-generated method stub
		ImageView mLogoIv=(ImageView)view.findViewById(R.id.iv_logo);
		mLogoIv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mLogoIv2.setVisibility(View.VISIBLE);
				mLogoIv3.setVisibility(View.VISIBLE);
				text_tmp.layout(0, 62, 180,242);
				text_humidy.layout(180, 62, 360, 211);
				text_gas.layout(360, 62, 540, 211);
				text_guangzhao.layout(0, 242, 180, 391);
				text_qiya.layout(180, 242, 360, 391);
				text_pm25.layout(360, 242, 540, 391);
				text_yanwu.layout(0,391, 180, 540);
				text_co2.layout(180,391, 360, 540);
				text_renti.layout(360,391, 540, 540);
				view_baojingdeng.layout(680,62, 800, 182);
				view_door.layout(830,62, 950, 182);
				view_deng.layout(980,62, 1100, 182);
				view_fun.layout(1130,62, 1250, 182);
				view_curOn.layout(740,256, 830, 346);
				view_pause.layout(950,256, 1040, 346);
				view_off.layout(1160,256, 1250, 346);
				view_hong1.layout(665,434, 845, 494);
				view_hong2.layout(875,434, 1055, 494);
				view_hong3.layout(1085,434, 1250, 494);
				view_tvimage.layout(680,517, 860, 592);
				view_dvdimage.layout(890,517, 1070, 592);
				view_kongtiaovimage.layout(1100,517, 1280, 592);
			}
		});
		text_tmp = (TextView) view.findViewById(R.id.TextView01);
		text_humidy = (TextView) view.findViewById(R.id.TextView02);
		text_gas = (TextView) view.findViewById(R.id.TextView03);
		text_guangzhao = (TextView) view.findViewById(R.id.TextView04);
		text_qiya = (TextView) view.findViewById(R.id.TextView05);
		text_pm25 = (TextView) view.findViewById(R.id.TextView06);
		text_yanwu = (TextView) view.findViewById(R.id.TextView07);
		text_co2 = (TextView) view.findViewById(R.id.TextView08);
		text_renti = (TextView) view.findViewById(R.id.TextView09);
		view_baojingdeng = (ImageView) view.findViewById(R.id.ImageView001);
		view_deng = (ImageView) view.findViewById(R.id.ImageView003);
		view_door = (ImageView) view.findViewById(R.id.ImageView002);
		view_fun = (ImageView) view.findViewById(R.id.ImageView004);
		view_curOn = (ImageView) view.findViewById(R.id.ImageView005);
		view_pause = (ImageView) view.findViewById(R.id.ImageView006);
		view_off = (ImageView) view.findViewById(R.id.ImageView007);
		view_hong1 = (ImageView) view.findViewById(R.id.ImageView008);
		view_hong2 = (ImageView) view.findViewById(R.id.ImageView009);
		view_hong3 = (ImageView) view.findViewById(R.id.ImageView0010);
		view_tvimage = (ImageView) view.findViewById(R.id.ImageView0011);
		view_dvdimage = (ImageView) view.findViewById(R.id.ImageView0012);
		view_kongtiaovimage = (ImageView) view.findViewById(R.id.ImageView0013);
		
		view_baojingdeng.setOnClickListener(this);
		view_door.setOnClickListener(this);
		view_deng.setOnClickListener(this);
		view_fun.setOnClickListener(this);
		view_curOn.setOnClickListener(this);
		view_pause.setOnClickListener(this);
		view_off.setOnClickListener(this);
		view_hong1.setOnClickListener(this);
		view_hong2.setOnClickListener(this);
		view_hong3.setOnClickListener(this);
		
		
		
		
		text_tmp .setOnTouchListener(this);
		text_humidy.setOnTouchListener(this);
		text_gas .setOnTouchListener(this);
		text_guangzhao.setOnTouchListener(this);
		text_qiya.setOnTouchListener(this);
		text_pm25.setOnTouchListener(this);
		text_yanwu.setOnTouchListener(this);
		text_co2.setOnTouchListener(this);
		text_renti .setOnTouchListener(this);
		view_baojingdeng.setOnTouchListener(this);
		view_deng.setOnTouchListener(this);
		view_door.setOnTouchListener(this);
		view_fun .setOnTouchListener(this);
		view_curOn.setOnTouchListener(this);
		view_pause.setOnTouchListener(this);
		view_off .setOnTouchListener(this);
		view_hong1 .setOnTouchListener(this);
		view_hong2.setOnTouchListener(this);
		view_hong3.setOnTouchListener(this);
		view_tvimage .setOnTouchListener(this);
		view_dvdimage .setOnTouchListener(this);
		view_kongtiaovimage.setOnTouchListener(this);
		    
		mLogoIv2=(ImageView)view.findViewById(R.id.imageView2);
		mLogoIv3=(ImageView)view.findViewById(R.id.imageView3);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ImageView001://报警灯
			activity.openOrCloseWarLight(!activity.warLightState);
			 if(activity.warLightState){
				 view_baojingdeng.setImageResource(R.drawable.warnon); 
			 }else{
				 view_baojingdeng.setImageResource(R.drawable.warnoff); 
			 }

			break;
		case R.id.ImageView002://门禁
			ControlUtils.control( ConstantUtil.RFID_Open_Door, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN );
			view_door.setImageResource(R.drawable.dooron);
            handler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					view_door.setImageResource(R.drawable.dooroff);
				}
			}, 1500);
			break;
		case R.id.ImageView003://射灯
			activity.openOrCloseLamp(!activity.lampState);
			 if(activity.lampState){
				 view_deng.setImageResource(R.drawable.dengon);
			 }else{
				 view_deng.setImageResource(R.drawable.dengoff);
			 }
			break;
		case R.id.ImageView004://风扇
			activity.openOrCloseFan(!activity.fanState);
			 if (activity.fanState) {
				 view_fun.setImageResource(R.drawable.funon);
			} else {
				view_fun.setImageResource(R.drawable.funon);
			}
			break;
		case R.id.ImageView005://窗帘开
			activity.openOrCloseChuangLian(1);
			ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN );
			break;
		case R.id.ImageView006://窗帘停
			activity.openOrCloseChuangLian(2);
			ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_2, ConstantUtil.OPEN );
			break;
		case R.id.ImageView007://窗帘关
			activity.openOrCloseChuangLian(3);
			ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN );
			break;
		case R.id.ImageView008://红外1 电视机   需要切换的图片为 
			activity.openOrCloseTV(!activity.tvState);
			 if(activity.tvState){
				 view_tvimage.setImageResource(R.drawable.tv);
			 }else{
				 view_tvimage.setImageResource(R.drawable.tv2);
			 }
			break;
		case R.id.ImageView009://红外2 dvd
			activity.openOrCloseDVD(!activity.dvdState);
			 if(activity.dvdState){
				 view_dvdimage.setImageResource(R.drawable.dvd);
			 }else{
				 view_dvdimage.setImageResource(R.drawable.dvd2);
			 }
			break;
		case R.id.ImageView0010://红外3 空调
			activity.openOrCloseKT(!activity.ktState);
			 if(activity.ktState){
				 view_kongtiaovimage.setImageResource(R.drawable.kongtiao);
			 }else{
				 view_kongtiaovimage.setImageResource(R.drawable.kongtiao2);
			 }
			break;

		default:
			break;
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		int ea = event.getAction();     
        final int screenWidth = dm.widthPixels;     
        final int screenHeight = dm.heightPixels; 
        switch(ea){
            case MotionEvent.ACTION_DOWN:  
            	Log.e("=====", v.getLeft()+"========="+v.getTop()+"========="+v.getRight()+"========="+v.getBottom());
                lastX = (int) event.getRawX();// 获取触摸事件触摸位置的原始X坐标     
                lastY = (int) event.getRawY();    
                break;     
            case MotionEvent.ACTION_MOVE:     
               int dx = (int) event.getRawX() - lastX;     
                int dy = (int) event.getRawY() - lastY;     
               int l = v.getLeft() + dx;     
               int b = v.getBottom() + dy;     
                int r = v.getRight() + dx;     
                int t = v.getTop() + dy;     
               // 下面判断移动是否超出屏幕     
                if (l < 0) {     
                    l = 0;     
                   r = l + v.getWidth();     
                }     
                if (t < 0) {     
                  t = 0;     
                    b = t + v.getHeight();     
                }     
                if (r > screenWidth) {     
                    r = screenWidth;     
                    l = r - v.getWidth();     
               }     
                if (b > screenHeight) {     
                    b = screenHeight;     
                   t = b - v.getHeight();     
               }     
                v.layout(l, t, r, b);     
                lastX = (int) event.getRawX();     
                lastY = (int) event.getRawY();     
                v.postInvalidate();    
                switch(v.getId()){
                case R.id.ImageView005:
                	mLogoIv2.setVisibility(View.INVISIBLE);
                    break;
                case R.id.ImageView006:
                	mLogoIv2.setVisibility(View.INVISIBLE);
                    break;
                case R.id.ImageView007:
                	mLogoIv2.setVisibility(View.INVISIBLE);
                    break;
                case R.id.ImageView008:
                	mLogoIv3.setVisibility(View.INVISIBLE);
                    break;
                case R.id.ImageView009:
                	mLogoIv3.setVisibility(View.INVISIBLE);
                    break;
                case R.id.ImageView0010:
                	mLogoIv3.setVisibility(View.INVISIBLE);
                    break;
                }
                break;                    
                case MotionEvent.ACTION_UP:     
              break; 
            default:
                break;
            }
        return true;
	}

}
