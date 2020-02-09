package com.example.democ;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;
import com.bizideal.smarthome.socket.Utils.SpUtils;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends Activity {
private Button mLoginBtn;
private EditText mAccountEt;
private EditText mPasswordEt;
private EditText mIpEt;
private EditText mPortEt;
private boolean isTime=false;
private TextView mTimeTv,mTv;

@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_login);
	initViews();
	  new TimeThread().start();
	
}

private void initViews() {
	// TODO Auto-generated method stub
	       mTv= (TextView) findViewById( R.id.tv);
	    
	        mTimeTv= (TextView) findViewById( R.id.time );
	        mAccountEt = (EditText) findViewById( R.id.username_et );//账号
	        mPasswordEt = (EditText) findViewById( R.id.password_et );//密码
	        mIpEt = (EditText) findViewById( R.id.ip_et );//IP
	        mPortEt = (EditText) findViewById( R.id.port_et );//密码
	        mAccountEt.setText( SpUtils.getValue( this, "Account", "" ) );
	        mPasswordEt.setText( SpUtils.getValue( this, "Password", "" ) );
	        mIpEt.setText( SpUtils.getValue( this, "Ip", "" ) );
	        mPortEt.setText( SpUtils.getValue( this, "Port", "" ) );
	       mLoginBtn=(Button)findViewById(R.id.btn_login);
	       mLoginBtn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
		 
			 if (TextUtils.isEmpty( mAccountEt.getText().toString() )) {
                 Toast.makeText( LoginActivity.this, "账号不能为空！", Toast.LENGTH_SHORT ).show();
                 return;
             }
             if (TextUtils.isEmpty( mPasswordEt.getText().toString() )) {
                 Toast.makeText( LoginActivity.this, "密码不能为空！", Toast.LENGTH_SHORT ).show();
                 return;
             }
             if (TextUtils.isEmpty( mIpEt.getText().toString() )) {
             	
                 Toast.makeText( LoginActivity.this, "Ip不能为空！", Toast.LENGTH_SHORT ).show();
                 return;
             }
             if (TextUtils.isEmpty( mPortEt.getText().toString() )) {
              	
                 Toast.makeText( LoginActivity.this, "端口不能为空！", Toast.LENGTH_SHORT ).show();
                 return;
             }
             if (!mAccountEt.getText().toString().equals( "bizideal" ) || !mPasswordEt.getText().toString().equals( "123456" )) {
            		new AlertDialog.Builder(LoginActivity.this)
            		.setTitle("登陆失败")
            		.setMessage("账号或密码错误！")
            		.setPositiveButton("OK", new  DialogInterface.OnClickListener() {
            			
            			public void onClick(DialogInterface dialog, int which) {
            				// TODO Auto-generated method stub
            				
            			}
            		}).show();
                 return;
             }
             SpUtils.putValue( LoginActivity.this, "Account", mAccountEt.getText().toString() );
             SpUtils.putValue( LoginActivity.this, "Password", mPasswordEt.getText().toString() );
             SpUtils.putValue( LoginActivity.this, "Ip", mIpEt.getText().toString() );
             SpUtils.putValue( LoginActivity.this, "Port", mPortEt.getText().toString() );
             ControlUtils.setUser( mAccountEt.getText().toString(), mPasswordEt.getText().toString(), mIpEt.getText().toString() );
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
                                 startActivity( new Intent( LoginActivity.this, MainActivity.class ) );
                                 finish();
                             } else {
                                 Toast.makeText( LoginActivity.this, "失败！", Toast.LENGTH_SHORT ).show();
                             }
                         }
                     } );
                 }
             } );
             startActivity( new Intent( LoginActivity.this, MainActivity.class ) );
             finish();
         
		}
	});
}


class TimeThread extends Thread {
     @Override
     public void run() {
         do {
             try {
                 Thread.sleep(1000);
                 Message msg = new Message();
                 msg.what = 1;  //消息(一个整型值)
                 mHandler.sendMessage(msg);// 每隔1秒发送一个msg给mHandler
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         } while (true);
     }
 }

 //在主线程里面处理消息并更新UI界面
 private Handler mHandler = new Handler(){
     @Override
     public void handleMessage(Message msg) {
         super.handleMessage(msg);
         switch (msg.what) {
             case 1:
                 long sysTime = System.currentTimeMillis();//获取系统时间
                 CharSequence sysTimeStr = DateFormat.format("yyyy-MM-dd hh:mm:ss", sysTime);//时间显示格式
                 mTimeTv.setText(sysTimeStr); //更新时间
                 mTv.setText("加载完毕，请登录。。。。");
              
                 if(isTime==false){
                	   mTv.setTextSize(20);
                	   isTime=true;
                 }else{
                	  isTime=false;
                	 mTv.setTextSize(15);
                 }
             default:
                 break;

         }
     }
 };
}
