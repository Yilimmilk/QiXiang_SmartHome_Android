package com.example.socket_json;



import java.text.SimpleDateFormat;

import lib.Json_data;
import lib.SocketThread;
import lib.SysApplication;
import lib.Updata_activity;
import lib.json_dispose;
import lib.popwindow;

import org.json.JSONException;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private int         count;
	private String      databuff;
	private EditText 	editTemp;
	private EditText 	editHumidity;
	private EditText 	editIllumination;
	private EditText 	EditText_smoke;
	private EditText 	EditTextGAS;
	private EditText 	EditTextPM25;
	private EditText 	EditTextair;
	private EditText 	EditTextCO2;
	private EditText 	editTextman;
	private EditText 	EditTexthelp;
	private EditText 	EditText_step;
	private EditText 	EditTextnumb;
	private EditText	editText_rfid;
	private EditText	EditTextrfidcard;
	private EditText    EditTextchannel;
	private EditText	EditTextadd;
	
	private Button 		button_step_stop;
	private Button		buttonstep;
	private Button		Button_relay;
	private Button		button_numb;
	private Button		button_led;
	private Button		button_camera;
	private Button		button_rfid_r;
	private Button		button_rfid_w;
	private Button		Buttonsend;
	private Button		button_rfid_wcard;
	
	private Switch		switch_step;
	private Switch		SwitchRelaySingle;
	private Switch		switch_current;
	private Switch		Switch_buzz;	
	private Switch		Switch_curtain;
	private Switch		Switch_fan;
	private Switch		Switch_lamp;
	private Switch		Switch_warningLight;
	private Switch		Switch_door;
	
	private RadioGroup	radioGroup_curtain;
	private RadioButton radio_open;
	private RadioButton radio_close;
	private RadioButton radio_stop;
	
	private CheckBox	relay1,relay2,relay3,relay4,led1,led2,led3,led4;
	
	private boolean 	step_state,rfid_read_state;
	private int			stepnumb,relay1_state,relay2_state,relay3_state,relay4_state,led1_state,led2_state,led3_state,led4_state,rfid_data_add,rfid_data;
	private static Thread UpdataThread;
	private TextView 	adapter_text;
	private Spinner		ID_select;
	private ImageButton menu;
	private static final String[] ID_name={"admin","bizideal"};
	private popwindow popwindow1;
	private ArrayAdapter<String> adapter;
	private SimpleDateFormat df;				
	private json_dispose Js=new json_dispose();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);   
		super.onCreate(savedInstanceState);
		SysApplication.getInstance().addActivity(this);                             
		setContentView(R.layout.activity_main);
       
		editTemp=(EditText)findViewById(R.id.editTemp);
		editHumidity=(EditText)findViewById(R.id.editHumidity);
		editIllumination=(EditText)findViewById(R.id.editIllumination);
		EditText_smoke=(EditText)findViewById(R.id.EditText_smoke);
		EditTextGAS=(EditText)findViewById(R.id.EditTextGAS);
		EditTextPM25=(EditText)findViewById(R.id.EditTextPM25);
		EditTextair=(EditText)findViewById(R.id.EditTextair);
		EditTextCO2=(EditText)findViewById(R.id.EditTextCO2);
		editTextman=(EditText)findViewById(R.id.editTextman);
		EditTexthelp=(EditText)findViewById(R.id.EditTexthelp);
		EditText_step=(EditText)findViewById(R.id.EditText_step);
		EditTextnumb=(EditText)findViewById(R.id.EditTextnumb);
		editText_rfid=(EditText)findViewById(R.id.editText_rfid);
		EditTextchannel=(EditText)findViewById(R.id.EditTextchannel);
		EditTextrfidcard=(EditText)findViewById(R.id.EditTextrfidcard);
		EditTextadd=(EditText)findViewById(R.id.EditTextadd);
		
		buttonstep=(Button)findViewById(R.id.buttonstep);
		button_step_stop=(Button)findViewById(R.id.Button_step_stop);
		Button_relay=(Button)findViewById(R.id.Button_relay);
		button_numb=(Button)findViewById(R.id.button_numb);
		button_led=(Button)findViewById(R.id.button_led);
		button_camera=(Button)findViewById(R.id.button_camera);
		button_rfid_r=(Button)findViewById(R.id.button_rfid_r);
		button_rfid_w=(Button)findViewById(R.id.button_rfid_w);
		Buttonsend=(Button)findViewById(R.id.Buttonsend);
		button_rfid_wcard=(Button)findViewById(R.id.button_rfid_wcard);
		
		switch_step=(Switch)findViewById(R.id.switch_step);
		SwitchRelaySingle=(Switch)findViewById(R.id.SwitchRelaySingle);
		switch_current=(Switch)findViewById(R.id.switch_current);
		Switch_buzz=(Switch)findViewById(R.id.Switch_buzz);
		Switch_fan=(Switch)findViewById(R.id.Switch_fan);
		Switch_lamp=(Switch)findViewById(R.id.Switch_lamp);
		Switch_warningLight=(Switch)findViewById(R.id.Switch_warningLight);
		Switch_door=(Switch)findViewById(R.id.Switch_door);
		
		radioGroup_curtain=(RadioGroup)findViewById(R.id.radioGroup_curtain);
		radio_open=(RadioButton)findViewById(R.id.radio_open);
		radio_close=(RadioButton)findViewById(R.id.radio_close);
		radio_stop=(RadioButton)findViewById(R.id.radio_stop);
		
		relay1=(CheckBox)findViewById(R.id.CheckBox_relay1);
		relay2=(CheckBox)findViewById(R.id.CheckBox_relay2);
		relay3=(CheckBox)findViewById(R.id.CheckBox_relay3);
		relay4=(CheckBox)findViewById(R.id.CheckBox_relay4);
		led1=(CheckBox)findViewById(R.id.checkBox_led1);
		led2=(CheckBox)findViewById(R.id.checkBox_led2);
		led3=(CheckBox)findViewById(R.id.checkBox_led3);
		led4=(CheckBox)findViewById(R.id.checkBox_led4);
		
		editTemp.setFocusable(false);
		editHumidity.setFocusable(false);
		editIllumination.setFocusable(false);
		EditText_smoke.setFocusable(false);
		EditTextGAS.setFocusable(false);
		EditTextPM25.setFocusable(false);
		EditTextair.setFocusable(false);
		EditTextCO2.setFocusable(false);
		editTextman.setFocusable(false);
		EditTexthelp.setFocusable(false);
		EditTextrfidcard.setFocusable(false);

		count=1;
		step_state=true;										
		rfid_read_state=false;
		relay1_state=0;
		relay2_state=0;
		relay3_state=0;
		relay4_state=0;
		led1_state=0;
		led2_state=0;
		led3_state=0;
		led4_state=0;
		rfid_data_add=0;
		rfid_data=0;
		menu = (ImageButton) findViewById(R.id.bt1);
		adapter_text=(TextView)findViewById(R.id.textView1);
//		ID_select=(Spinner)findViewById(R.id.spinner1);
		
//>>>>>>>>>>>>有改动<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		//创建popwindow
	/*	popwindow=new IpPopWindow(MainActivity.this);
		LayoutInflater inflater = getLayoutInflater();//获取布局加载器
		View view = inflater.inflate(R.layout.set_ip, null);//加载布局
		EditText ip_  = (EditText) view.findViewById(R.id.editip);//绑定控件
		EditText port_ =  (EditText) view.findViewById(R.id.editport);
		Button bt =  (Button) view.findViewById(R.id.set);
		popwindow.setContentView(view);//给pop设置布局
		popwindow.setEditTextIp_Prot(ip_, port_, bt);//给pop布局设置内部事件
		popwindow.setPopFocusable(true);//设置pop可以获取焦点
		popwindow.setPopOutTouchAble(true);//设置点击pop外让其消失
*/		
		
		popwindow1=new popwindow(MainActivity.this);
		//SocketThread.SocketIp = "10.1.1.0";                        	
		//SocketThread.Port = 6003;
		popwindow1.setEdit_IP(SocketThread.SocketIp);
		popwindow1.setEdit_port(SocketThread.Port);
/******************************************************************************
 * 步进电机
 *****************************************************************************/
		switch_step.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					step_state=false;
				}
				else {
					step_state=true;
				}
			}
		});
		button_step_stop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Js.control(Json_data.StepMotor,0,0);

			}
		});
		buttonstep.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					stepnumb=Integer.parseInt(EditText_step.getText().toString());
				} catch (Exception e) {
					// TODO: handle exception
					stepnumb=0;
				}
				if(step_state==true)
				{
					Js.control(Json_data.StepMotor, 0, stepnumb);
				}
				else
				{
					Js.control(Json_data.StepMotor, 0, stepnumb*-1);
				}
			}
		});
		/******************************************************************************
		 * 单路继电器
		 *****************************************************************************/
		SwitchRelaySingle.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					Js.control(Json_data.RelaySingle, 0, 1);
				}
				else {
					Js.control(Json_data.RelaySingle, 0, 0);
				}
			}
		});
		/******************************************************************************
		 * 四路继电器
		 *****************************************************************************/
		relay1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					relay1_state=1;
				}
				else {
					relay1_state=0;
				}
			}
		});
		relay2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					relay2_state=1;
				}
				else {
					relay2_state=0;
				}
			}
		});
		relay3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					relay3_state=1;
				}
				else {
					relay3_state=0;
				}
			}
		});
		relay4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					relay4_state=1;
				}
				else {
					relay4_state=0;
				}
			}
		});
		Button_relay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Js.control(Json_data.Relay1, 0, relay1_state);
				Js.control(Json_data.Relay2, 0, relay2_state);
				Js.control(Json_data.Relay3, 0, relay3_state);
				Js.control(Json_data.Relay4, 0, relay4_state);
			}
		});
		/******************************************************************************
		 * 数码管
		 *****************************************************************************/
		button_numb.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					stepnumb=Integer.parseInt(EditTextnumb.getText().toString());
				} catch (Exception e) {
					// TODO: handle exception
					stepnumb=0;
				}
				Js.control(Json_data.Digital, 0, stepnumb);
			}
		});
		/******************************************************************************
		 * 直流电机
		 *****************************************************************************/
		switch_current.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					Js.control(Json_data.DCMotor, 0, 1);
				}
				else {
					Js.control(Json_data.DCMotor, 0, 0);
				}
			}
		});
		/******************************************************************************
		 * 4路led
		 *****************************************************************************/
		led1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					led1_state=1;
				}
				else {
					led1_state=0;
				}
			}
		});
		led2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					led2_state=1;
				}
				else {
					led2_state=0;
				}
			}
		});
		led3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					led3_state=1;
				}
				else {
					led3_state=0;
				}
			}
		});
		led4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					led4_state=1;
				}
				else {
					led4_state=0;
				}
			}
		});
		button_led.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Js.control(Json_data.Led1, 0, led1_state);
				Js.control(Json_data.Led2, 0, led2_state);
				Js.control(Json_data.Led3, 0, led3_state);
				Js.control(Json_data.Led4, 0, led4_state);
			}
		});
		/******************************************************************************
		 * 摄像头
		 *****************************************************************************/
		button_camera.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(); 
				intent.setAction("android.intent.action.VIEW"); 
				Uri content_url = Uri.parse("http://192.168.1.187");
				intent.setData(content_url);
				startActivity(intent);
			}
		});
		/******************************************************************************
		 * 蜂鸣器
		 *****************************************************************************/
		Switch_buzz.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					Js.control(Json_data.Buzz, 0, 1);
				}
				else {
					Js.control(Json_data.Buzz, 0, 0);
				}
			}
		});
		/******************************************************************************
		 * 窗帘
		 *****************************************************************************/
		radioGroup_curtain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {		
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(radio_open.getId() == checkedId)
					Js.control(Json_data.Curtain, 0, 1);  
				else if(radio_close.getId() == checkedId)
					Js.control(Json_data.Curtain, 0, 2);  
				else if(radio_stop.getId() == checkedId)
					Js.control(Json_data.Curtain, 0, 3); 
			}
		}); 
		/******************************************************************************
		 * 风扇
		 *****************************************************************************/
		Switch_fan.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					Js.control(Json_data.Fan, 0, 1);
				}
				else {
					Js.control(Json_data.Fan, 0, 0);
				}
			}
		});
		/******************************************************************************
		 * 射灯
		 *****************************************************************************/
		Switch_lamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					Js.control(Json_data.Lamp, 0, 1);
				}
				else {
					Js.control(Json_data.Lamp, 0, 0);
				}
			}
		});
		/******************************************************************************
		 * 报警灯
		 *****************************************************************************/
		Switch_warningLight.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					Js.control(Json_data.WarningLight, 0, 1);
				}
				else {
					Js.control(Json_data.WarningLight, 0, 0);
				}
			}
		});
		/******************************************************************************
		 * 门禁
		 *****************************************************************************/
		Switch_door.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					Js.control(Json_data.RFID_Open_Door, 0, 1);
				}
				else {
					Js.control(Json_data.RFID_Open_Door, 0, 0);
				}
			}
		});
		/******************************************************************************
		 * 红外发射
		 *****************************************************************************/
		Buttonsend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					stepnumb=Integer.parseInt(EditTextchannel.getText().toString());
				} catch (Exception e) {
					// TODO: handle exception
					stepnumb=0;
				}
				if(stepnumb>=50)
				{
					stepnumb=0;
					EditTextchannel.setText(0);
				}
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>有改动<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<				
//				>>>> 红外发射有改动(InfraredLaunch 变成  InfraredEmit)<<<
				Js.control(Json_data.InfraredLaunch, 0, stepnumb);//
			}
		});
		/******************************************************************************
		 * RFID
		 *****************************************************************************/
		button_rfid_r.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					rfid_data_add=Integer.parseInt(EditTextadd.getText().toString());
				} catch (Exception e) {
					// TODO: handle exception
					rfid_data_add=0;
				}
				if(rfid_data_add>=16)
				{
					rfid_data_add=0;
					EditTextadd.setText(0);
				}
				Js.control(Json_data.RFID_Read_Data, rfid_data_add, 1);
				rfid_read_state=true;
			}
		});
		button_rfid_w.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					rfid_data_add=Integer.parseInt(EditTextadd.getText().toString());
				} catch (Exception e) {
					// TODO: handle exception
					rfid_data_add=0;
				}
				if(rfid_data_add>=16)
				{
					rfid_data_add=0;
					EditTextadd.setText(0);
				}
				try {
					rfid_data=Integer.parseInt(editText_rfid.getText().toString());
				} catch (Exception e) {
					// TODO: handle exception
					rfid_data=0;
				}
				Js.control(Json_data.RFID_Write_Data, rfid_data_add, rfid_data);
			}
		});
		button_rfid_wcard.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Js.control(Json_data.RFID_Read_Tag, 0, 1);
			}
		});
		
		UpdataThread=new Thread(new Updata_activity());				
		UpdataThread.start();											
		
		Updata_activity.updatahandler = new Handler(){
				public void handleMessage(Message msg) {
				
					super.handleMessage(msg);
					try {
						Js.receive();
						editTemp.setText(Js.receive_data.get(Json_data.Temp).toString());
						editHumidity.setText(Js.receive_data.get(Json_data.Humidity).toString());
						editIllumination.setText(Js.receive_data.get(Json_data.Illumination).toString());
						EditText_smoke.setText(Js.receive_data.get(Json_data.Smoke).toString());
						EditTextGAS.setText(Js.receive_data.get(Json_data.Gas).toString());
						EditTextPM25.setText(Js.receive_data.get(Json_data.PM25).toString());
						EditTextair.setText(Js.receive_data.get(Json_data.AirPressure).toString());
						EditTextCO2.setText(Js.receive_data.get(Json_data.Co2).toString());
						editTextman.setText(Js.receive_data.get(Json_data.StateHumanInfrared).toString());
						EditTexthelp.setText(Js.receive_data.get(Json_data.StateHelpButton).toString());
						EditTextrfidcard.setText(Js.receive_data.get(Json_data.RFIDTag).toString());
						if(rfid_read_state==true)
						{
							editText_rfid.setText(Js.receive_data.get(Json_data.RFIDData).toString());
							rfid_read_state=false;
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		};
		
		SocketThread.mHandlerSocketState = new Handler()
				{
					public void handleMessage(Message msg)
					{
						super.handleMessage(msg);
						Bundle b = msg.getData();
						if(count == 1)
						{
							if("error".equals(b.getString("SocketThread_State")))
							{
								Toast.makeText(MainActivity.this, "网络连接失败,请检查...", Toast.LENGTH_SHORT).show();
								
							}	
							else
							{
								Toast.makeText(MainActivity.this, "网络连接成功,请操作...", Toast.LENGTH_SHORT).show();
								//new Thread(new MyThread()).start();
								count = 0;				
							}
								
						}
					}
				};
				menu.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						popwindow1.showAsDropDown(v);	
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void onBackPressed() {

				
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				
				builder.setTitle("提示框");
				
				builder.setMessage("确定退出吗？");
			
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						SysApplication.exit();
						
					}
				});
			
				builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				});
			
				builder.show();

				return;
			}
	protected void onDestroy() {
		super.onDestroy();
		Updata_activity.mode=false;
	}
}
