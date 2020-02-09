package com.example.a;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SlidingDrawer;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

public class MainActivity extends Activity implements OnClickListener,OnCheckedChangeListener {
	private TextView mTempTv, mHumidityTv, mGasTv, mIlluminationTv, mPm25Tv, mPressureTv, mSmokeTv, mCo2Tv, mStateHumanInfraredTv;
    private CheckBox mLampCb, mDoorCb, mWindSpeedCb, mAlarmCb,lvmodel,wdmodel,anfangmodel,zidingyimodel;
    private EditText fazhi_et;
    private Button KT,DVD,TV,kai,guan,ting,zhuangtai;
    
    private Spinner sp1,sp2,sp3;
    private String[] sp1values = new String[]{"温度","光照"};
    private String[] sp2values = new String[]{">","<="};
    private String[] sp3values = new String[]{"风扇开","射灯开"};
	private ArrayAdapter<String> adapter1;
	private ArrayAdapter<String> adapter2;
	private ArrayAdapter<String> adapter3;
	private String sp1value,sp2value,sp3value;  //记录当前选项的值
	
	Runnable r_lvyou,r_wendu,r_anfang,r_ziding; //四个模式的线程
	Runnable r_menjin; //关闭门禁线程
	boolean warLightState,chuanState,fanState,lampState,tvState,dvdState,ktState;  //控制设备的状态
	
	
	//红外学习频道
	String DVD_chanel = "8";
	String TV_chanel = "5";
	String KT_chanel = "1";
	
	
	
	
	
	Handler handler = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		 getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,  
                 WindowManager.LayoutParams. FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		
		//控件初始化
		initViews();
		
//		变量初始化
		initVar();
		
		
		
		
	}

	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//进行socket连接
        SocketClient.getInstance().creatConnect();
        //连接回调
        SocketClient.getInstance().login( new LoginCallback() {
            @Override
            public void onEvent(final String status) {
                runOnUiThread( new Runnable() {
                    @Override
                    public void run() {
                        if (status.equals( ConstantUtil.SUCCESS )) {
                        	 Toast.makeText( MainActivity.this, "成功！", Toast.LENGTH_SHORT ).show();
                        } else {
                            Toast.makeText( MainActivity.this, "失败！", Toast.LENGTH_SHORT ).show();
                        }
                    }
                } );
            }
        } );
		
        initData();
	}
	private void initVar() {
		// TODO Auto-generated method stub
		warLightState = false;
		fanState =false;
		lampState =false;
		chuanState =false;
		tvState = false;
		dvdState = false;
		ktState = false;
		
		r_lvyou = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
//				判断射灯状态 如果开启状态则关闭  同时修改状态
				if(lampState){
					 openOrCloseLamp(true);
					 try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
//				如果窗帘状态是关闭 则开启窗帘  同时修改状态
				if(!chuanState){
					openOrCloseChuangLian(true);
					 try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
				float ff =0;
				try {
					ff= Float.parseFloat(mPm25Tv.getText().toString());
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					ff =0;
				}				
				
				//判断pm是否大于75 如果大于 开启风扇
				if(ff>75){
					 openOrCloseFan(true);
				}
				
				handler.postDelayed(r_lvyou, 1000);
			}
		};
		
		r_wendu = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
//				打开电视				
				if(!tvState){
					openOrCloseTV(!tvState);
				}
				
				 try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				 打开DVD
				 if(!dvdState){
					 openOrCloseDVD(!dvdState);
				 }
	             
	             try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	             
//	           	  打开空调
	             if(!ktState){
	            	openOrCloseKT(!ktState);
	             }
	        	
//				开报警灯
	        	 if(!warLightState){
	        		 openOrCloseWarLight(true);
	        		 try {
	 					Thread.sleep(1000);
	 				} catch (InterruptedException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				}
	        	 }
//	        	 开风扇
	        	 if(!fanState){
	        		 openOrCloseFan(true);
	        		 try {
	 					Thread.sleep(1000);
	 				} catch (InterruptedException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				}
	        	 }
//	        	 开射灯
	        	 if(!lampState){
	        		 openOrCloseLamp(true);
	        		 try {
	 					Thread.sleep(1000);
	 				} catch (InterruptedException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				}
	        	 }
			}
		};
		
		
		r_anfang = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//如果界面显示有人  
				if(mStateHumanInfraredTv.getText().equals("有人")){
//					判断报警灯状态
					if(!warLightState){
						openOrCloseWarLight(true);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					if(!lampState){
						openOrCloseLamp(true);
						
					}
					
				}
				
				handler.postDelayed(r_anfang, 1000);
			}
		};
		
		
		
		r_ziding = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				String value = fazhi_et.getText().toString();
				if(value.isEmpty()){
					Toast.makeText(MainActivity.this, "请输入正确的阈值", 0).show();
					zidingyimodel.setChecked(false);
					return;
					
					
				}
				
				float fazhi = Float.parseFloat(value);
				float dangqianzhi = 0;
				
				
				
				
				if(sp1value.equals("温度")){ //第一个选项框 为温度
					String ss = mTempTv.getText().toString();
					if(ss.isEmpty()){
						dangqianzhi = 0;
					}else{
						dangqianzhi = Float.parseFloat(ss);
					}
					if(sp2value.equals("<")){ //第二个下拉框为 <
						
						if(sp3value.equals(sp3values[0])){ //风扇开
							if(dangqianzhi<fazhi){
								if(!fanState){
									//开风扇
									openOrCloseFan(true);
								}
							}
						}else{ //射灯开
							if(dangqianzhi<fazhi){
								if(!fanState){
									//开射灯
									openOrCloseLamp(true);
								}
							}
						}
						
						
					}else{//第二个下拉框为 >
						if(sp3value.equals(sp3values[0])){ //风扇开
							if(dangqianzhi>=fazhi){
								if(!fanState){
									//开风扇
									openOrCloseFan(true);
								}
							}
						}else{ //射灯开
							if(dangqianzhi>=fazhi){
								if(!fanState){
									//开射灯
									openOrCloseLamp(true);
								}
							}
						}
					}
					
					
				}else { //第一个选项框 为光照
					
					String ss = mIlluminationTv.getText().toString();
					if(ss.isEmpty()){
						dangqianzhi = 0;
					}else{
						dangqianzhi = Float.parseFloat(ss);
					}
					if(sp2value.equals("<")){ //第二个下拉框为 <
						
						if(sp3value.equals(sp3values[0])){ //风扇开
							if(dangqianzhi<fazhi){
								if(!fanState){
									//开风扇
									openOrCloseFan(true);
								}
							}
						}else{ //射灯开
							if(dangqianzhi<fazhi){
								if(!fanState){
									//开射灯
									openOrCloseLamp(true);
								}
							}
						}
						
						
					}else{//第二个下拉框为 >
						if(sp3value.equals(sp3values[0])){ //风扇开
							if(dangqianzhi>=fazhi){
								if(!fanState){
									//开风扇
									openOrCloseFan(true);
								}
							}
						}else{ //射灯开
							if(dangqianzhi>=fazhi){
								if(!fanState){
									//开射灯
									openOrCloseLamp(true);
								}
							}
						}
					}	
				
				}	
				handler.postDelayed(r_ziding, 1000);
			}
			
		};
		
		
		
		r_menjin = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				mDoorCb.setBackgroundResource(R.drawable.mejin);
			}
		};
		
		
	}

	private void initViews() {
		// TODO Auto-generated method stub
		  	mTempTv = (TextView) findViewById( R.id.wendu );//温度
	        mHumidityTv = (TextView) findViewById( R.id.shidu );//湿度
	        mGasTv = (TextView) findViewById( R.id.ranqi );//燃气
	        mIlluminationTv = (TextView) findViewById( R.id.guangzhao );//光照度
	        mPm25Tv = (TextView) findViewById( R.id.PM2_5 );//Pm2.5
	        mPressureTv = (TextView) findViewById( R.id.qiya );//气压
	        mSmokeTv = (TextView) findViewById( R.id.yanwu );//烟雾
	        mCo2Tv = (TextView) findViewById( R.id.co2 );//C02
	        mStateHumanInfraredTv = (TextView) findViewById( R.id.renti);//人体红外
	        
	        
	        KT = (Button) findViewById(R.id.kongtiao);
	        KT.setOnClickListener(this);
	        DVD = (Button) findViewById(R.id.DVD);
	        DVD.setOnClickListener(this);
	        TV = (Button) findViewById(R.id.dianshi);
	        TV.setOnClickListener(this);
	        kai = (Button) findViewById(R.id.kai);
	        kai.setOnClickListener(this);
	        guan = (Button) findViewById(R.id.guan);
	        guan.setOnClickListener(this);
	        ting = (Button) findViewById(R.id.ting);
	        ting.setOnClickListener(this);
	        zhuangtai = (Button) findViewById(R.id.shebeizhuangtai);
	        zhuangtai.setOnClickListener(this);
	        fazhi_et = (EditText) findViewById(R.id.fazhi);
	        mLampCb = (CheckBox) findViewById( R.id.shedeng );//射灯
	        mLampCb.setOnCheckedChangeListener( this );
	        mDoorCb = (CheckBox) findViewById( R.id.menjin );//门禁
	        mDoorCb.setOnCheckedChangeListener( this );
	        mWindSpeedCb = (CheckBox) findViewById( R.id.fengshan );//风扇
	        mWindSpeedCb.setOnCheckedChangeListener( this );
	        mAlarmCb = (CheckBox) findViewById( R.id.baojingdeng );//报警灯
	        mAlarmCb.setOnCheckedChangeListener( this );
	        
	        lvmodel= (CheckBox) findViewById( R.id.lvyoumodel );  //旅游模式
	        lvmodel.setOnCheckedChangeListener( this );
	        wdmodel = (CheckBox) findViewById( R.id.wendumodel ); //温度模式
	        wdmodel.setOnCheckedChangeListener( this ); 
	        anfangmodel = (CheckBox) findViewById( R.id.anfangmodel ); //安放模式
	        anfangmodel.setOnCheckedChangeListener( this );
	        zidingyimodel = (CheckBox) findViewById( R.id.zidingyimodel ); //自定义模式
	        zidingyimodel.setOnCheckedChangeListener( this );
	        
	        sp1 = (Spinner) findViewById(R.id.spiner1);
	        sp2 = (Spinner) findViewById(R.id.spiner2);
	        sp3 = (Spinner) findViewById(R.id.spiner3);
	        adapter1 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item,sp1values);
	        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        sp1.setAdapter(adapter1);
	        adapter2 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item,sp2values);
	        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        sp2.setAdapter(adapter2);
	        adapter3 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item,sp3values);
	        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        sp3.setAdapter(adapter3);
	        
	        
	        
	        sp1.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					sp1value =sp1values[arg2];
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
			});
	        sp2.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					sp2value =sp2values[arg2];
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
			});
	        sp3.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					sp3value =sp3values[arg2];
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
			});
	        
	}

	 private void initData() {
	        ControlUtils.getData();
	        SocketClient.getInstance().getData( new DataCallback<DeviceBean>() {
	            @Override
	            public void onResult(final DeviceBean bean) {
	                runOnUiThread( new Runnable() {
	                    @Override
	                    public void run() {
	                        if (!bean.getTemperature().isEmpty()){
	                        	mTempTv.setText(bean.getTemperature());
	                        }
	                        if (!TextUtils.isEmpty( bean.getHumidity() )){
	                            mHumidityTv.setText( bean.getHumidity() );
	                        }
	                        if (!TextUtils.isEmpty( bean.getGas() )){
	                            mGasTv.setText( bean.getGas() );
	                        }
	                        if ( !bean.getIllumination().isEmpty()){
	                        	mIlluminationTv.setText( bean.getIllumination() );
	                        }
	                        if (!TextUtils.isEmpty( bean.getPM25() )){
	                            mPm25Tv.setText( bean.getPM25() );
	                        }
	                        if (!TextUtils.isEmpty( bean.getSmoke() )){
	                            mSmokeTv.setText( bean.getSmoke() );
	                        }
	                        if (!TextUtils.isEmpty( bean.getCo2() )){
	                            mCo2Tv.setText( bean.getCo2() );
	                        }
	                        if (!TextUtils.isEmpty( bean.getAirPressure() )){
	                            mPressureTv.setText( bean.getAirPressure() );
	                        }
	                        if (!TextUtils.isEmpty( bean.getStateHumanInfrared() ) && bean.getStateHumanInfrared().equals( ConstantUtil.CLOSE )){
	                            mStateHumanInfraredTv.setText( "无人" );
	                        }else{
	                            mStateHumanInfraredTv.setText( "有人" );
	                        }
	                     
	                    }
	                } );
	            }
	        } );
	    }

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		switch (buttonView.getId()) {
		case R.id.shedeng://射灯
            openOrCloseLamp(isChecked);
            break;
        case R.id.menjin://门禁
            ControlUtils.control( ConstantUtil.RFID_Open_Door, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN );
            mDoorCb.setBackgroundResource(R.drawable.menjinkai);
            handler.postDelayed(r_menjin, 1500);
            break;
        case R.id.fengshan://风扇
          
              openOrCloseFan(isChecked);
            
        break;
        case R.id.baojingdeng://报警灯
   
              openOrCloseWarLight(isChecked);
           
            break;
        case R.id.lvyoumodel:
        	if(isChecked){        		
        		//判断其他模式是否开启 若开启则提示 
        		
        		if(anfangmodel.isChecked()||wdmodel.isChecked()||zidingyimodel.isChecked()){
        			Toast.makeText(MainActivity.this, "请先关闭其他模式", 0).show();
        			lvmodel.setChecked(false);
        			return ;
        		}
        		
        		handler.post(r_lvyou);
        		
        	}else{
        		handler.removeCallbacks(r_lvyou);
        	}
        	break;
        case R.id.wendumodel:
        	if(isChecked){        		
        		//判断其他模式是否开启 若开启则提示 
        		
        		if(lvmodel.isChecked()||anfangmodel.isChecked()||zidingyimodel.isChecked()){
        			Toast.makeText(MainActivity.this, "请先关闭其他模式", 0).show();
        			wdmodel.setChecked(false);
        			return ;
        		}
        		
        		handler.post(r_wendu);
        		
        	}else{
        		handler.removeCallbacks(r_wendu);
        	}
        	break;
        case R.id.anfangmodel:
        	if(isChecked){        		
        		//判断其他模式是否开启 若开启则提示 
        		
        		if(lvmodel.isChecked()||wdmodel.isChecked()||zidingyimodel.isChecked()){
        			Toast.makeText(MainActivity.this, "请先关闭其他模式", 0).show();
        			anfangmodel.setChecked(false);
        			return ;
        		}
        		
        		handler.post(r_anfang);
        		
        	}else{
        		handler.removeCallbacks(r_anfang);
        	}
        	break;
        case R.id.zidingyimodel:
        	if(isChecked){        		
        		//判断其他模式是否开启 若开启则提示 
        		
        		if(lvmodel.isChecked()||wdmodel.isChecked()||anfangmodel.isChecked()){
        			Toast.makeText(MainActivity.this, "请先关闭其他模式", 0).show();
        			zidingyimodel.setChecked(false);
        			return ;
        		}
        		
        		handler.post(r_ziding);
        		
        	}else{
        		handler.removeCallbacks(r_ziding);
        	}
        	break;
		default:
			break;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		 case R.id.kai://窗帘开
			 openOrCloseChuangLian(true);
             break;
         case R.id.ting://窗帘停
             ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_2, ConstantUtil.OPEN );
             break;
         case R.id.guan://窗帘关
             //关
             openOrCloseChuangLian(false);
             break;
         case R.id.kongtiao://空调
        	 openOrCloseKT(!ktState);
             break;
         case R.id.DVD:// dvd
        	 openOrCloseDVD(!dvdState);
             break;
         case R.id.dianshi: //电视
        	 openOrCloseTV(!tvState);
         case R.id.shebeizhuangtai: //设备状态
        	 startActivity(new Intent(MainActivity.this, StateActivity.class));
             break;
         
		}
	}
	
//==========  设备开启及关闭方法==================
//	开关射灯  同时修改状态
	void openOrCloseLamp(boolean is){
		 ControlUtils.control( ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,is?ConstantUtil.OPEN:ConstantUtil.CLOSE );
		 lampState = is;		 
		 mLampCb.setChecked(is);
		 if(is){
			 mLampCb.setBackgroundResource(R.drawable.shedengkai); 
		 }else{
			 mLampCb.setBackgroundResource(R.drawable.shedengkai); 
		 }
		 
	}
//	开关报警灯 同时修改状态
	void openOrCloseWarLight(boolean is){
		 ControlUtils.control( ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL,is?ConstantUtil.OPEN:ConstantUtil.CLOSE );
		 warLightState = is;		 
		 mAlarmCb.setChecked(is);
		 if(is){
			 mAlarmCb.setBackgroundResource(R.drawable.baojingdengkai); 
		 }else{
			 mAlarmCb.setBackgroundResource(R.drawable.baojingdeng); 
		 }
		 
	}
//	开关风扇  同时修改状态
	void openOrCloseFan(boolean is){
		 ControlUtils.control( ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,is?ConstantUtil.OPEN:ConstantUtil.CLOSE );
		 fanState = is;		 
		 mWindSpeedCb.setChecked(is);
		 if (is) {
			 mWindSpeedCb.setBackgroundResource(R.drawable.fengshankai);
		} else {
			mWindSpeedCb.setBackgroundResource(R.drawable.fengshan);
		}
		
		 
		 
		 
	}
//	开关窗帘
	void openOrCloseChuangLian(boolean is){
		 ControlUtils.control( ConstantUtil.WarningLight, is?ConstantUtil.CHANNEL_1:ConstantUtil.CHANNEL_3,ConstantUtil.OPEN );
		 chuanState = is;		 
		
		 
	}
//	开关电视
	void openOrCloseTV(boolean is){
		ControlUtils.control( ConstantUtil.INFRARED_1_SERVE, TV_chanel, ConstantUtil.OPEN );
		 tvState = is;		 
		 
		 
	}
//	开关dvd
	void openOrCloseDVD(boolean is){
		ControlUtils.control( ConstantUtil.INFRARED_1_SERVE, DVD_chanel, ConstantUtil.OPEN );
		 dvdState = is;		 
		
		 
	}
//	开关空调
	void openOrCloseKT(boolean is){
		 ControlUtils.control( ConstantUtil.WarningLight, KT_chanel,is?ConstantUtil.OPEN:ConstantUtil.CLOSE );
		 ktState = is;	 
		 
	}
	
	
}


