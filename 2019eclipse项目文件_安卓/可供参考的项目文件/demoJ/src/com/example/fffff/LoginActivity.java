package com.example.fffff;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;
import com.bizideal.smarthome.socket.Utils.SpUtils;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	EditText mIpEt;
	Button btnLogin,btnSeeSql;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		mIpEt = (EditText) findViewById(R.id.et_ip);
		btnLogin = (Button) findViewById(R.id.btn_login);
		btnSeeSql = (Button) findViewById(R.id.btn_sql);
		
		btnSeeSql.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				  startActivity(new Intent(LoginActivity.this,SeeSqlActivity.class));
			}
		});
		
		btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
	             if (TextUtils.isEmpty( mIpEt.getText().toString() )) {
	                 Toast.makeText( LoginActivity.this, "Ip不能为空！", Toast.LENGTH_SHORT ).show();
	                 return;
	             }
	             SpUtils.putValue( LoginActivity.this, "Ip", mIpEt.getText().toString() );
	             ControlUtils.setUser( "","", mIpEt.getText().toString() );
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
	                            	  Toast.makeText( LoginActivity.this, "连接成功！", Toast.LENGTH_SHORT ).show();
	                                 startActivity( new Intent( LoginActivity.this, MainActivity.class ) );
	                                 finish();
	                             } else {
	                            		new AlertDialog.Builder(LoginActivity.this)
	            	            		.setTitle("登陆失败")
	            	            		.setMessage("网络连接失败，是否返回登录界面！")
	            	            		.setPositiveButton("确定", new  DialogInterface.OnClickListener() {
	            	            			
	            	            			public void onClick(DialogInterface dialog, int which) {
	            	            				// TODO Auto-generated method stub
	            	            				dialog.dismiss();
	            	            			}
	            	            		}).setNegativeButton("取消", new  DialogInterface.OnClickListener() {
	            	            			
	            	            			public void onClick(DialogInterface dialog, int which) {
	            	            				// TODO Auto-generated method stub
	            	            				finish();
	            	            			}
	            	            		}).show();
	                               
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

	

}
