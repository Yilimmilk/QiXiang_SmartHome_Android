package com.example.demoe;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;
import com.bizideal.smarthome.socket.Utils.SpUtils;
import com.example.demoe.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
private Button mLoginBtn;
private EditText mIpEt;
private EditText mPortEt;

@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_login);
	initViews();
}

private void initViews() {
	// TODO Auto-generated method stub
	  mIpEt = (EditText) findViewById( R.id.ip_et );//IP
      mPortEt = (EditText) findViewById( R.id.port_et );//密码
	  mLoginBtn=(Button)findViewById(R.id.login_btn);
      mLoginBtn.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
       if (TextUtils.isEmpty( mIpEt.getText().toString() )) {
           Toast.makeText( LoginActivity.this, "Ip不能为空！", Toast.LENGTH_SHORT ).show();
           return;
       }
       if (TextUtils.isEmpty( mPortEt.getText().toString() )) {
        	
           Toast.makeText( LoginActivity.this, "端口不能为空！", Toast.LENGTH_SHORT ).show();
           return;
       }
       SpUtils.putValue( LoginActivity.this, "Ip", mIpEt.getText().toString() );
       SpUtils.putValue( LoginActivity.this, "Port", mPortEt.getText().toString() );
       ControlUtils.setUser("", "", mIpEt.getText().toString() );
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
                          Toast.makeText( LoginActivity.this, "网络连接成功！", Toast.LENGTH_SHORT ).show();
                           startActivity( new Intent( LoginActivity.this, MainActivity.class ) );
                           finish();
                       } else {
                    		new AlertDialog.Builder(LoginActivity.this)
                      		.setTitle("登陆失败")
                      		.setMessage("网络连接失败，是否返回登录界面！")
                      		.setPositiveButton("OK", null).show();
                        
                       }
                   }
               } );
           }
       } );
       
	}
});
}
}
