package com.example.a521i;

import java.net.Socket;
import java.util.ResourceBundle.Control;

import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;
import com.bizideal.smarthome.socket.Utils.SpUtils;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {

	private Button dl1,zc1,xgmm1,tc1;
	private EditText et1,et2,et3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initViews();

	}




	private void initViews() {
		// TODO Auto-generated method stub
		et1 = (EditText) findViewById(R.id.ip);et1.setText(SpUtils.getValue(this, "3", ""));
		et2 = (EditText) findViewById(R.id.yhm);et2.setText(SpUtils.getValue(this, "1", ""));
		et3 = (EditText) findViewById(R.id.MM);et3.setText(SpUtils.getValue(this, "2", ""));
		dl1 = (Button) findViewById(R.id.dl);dl1.setOnClickListener(this);
		zc1 = (Button) findViewById(R.id.zc);zc1.setOnClickListener(this);
		xgmm1= (Button) findViewById(R.id.xgmm);xgmm1.setOnClickListener(this);
		tc1= (Button) findViewById(R.id.tc);tc1.setOnClickListener(this);

		
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.dl:
			 String name,ip,mm;
			ip = et1.getText().toString();
			name = et2.getText().toString();
			mm = et3.getText().toString();
			SpUtils.putValue(this,"1", name);
			SpUtils.putValue(this,"2", mm);
			SpUtils.putValue(this,"3", ip);
			if(!name.equals("bizideal")&&!mm.equals("123456")){
				new AlertDialog.Builder(this)
	            .setTitle("登陆失败")
	            .setMessage("密码或用户名错误")
	            .setPositiveButton("ok", null)
	            .show();
				
			}else{
				ControlUtils.setUser("", "", et1.getText().toString());
				SocketClient.getInstance().creatConnect();
				SocketClient.getInstance().login(new LoginCallback() {
					
					@Override
					public void onEvent(final String arg0) {
						// TODO Auto-generated method stub
						runOnUiThread(new Runnable() {
							public void run() {
							if(arg0.equals("Success")){
								startActivity(new Intent(LoginActivity.this,MainActivity.class));
								finish();
							}else{
								startActivity(new Intent(LoginActivity.this,MainActivity.class));
								finish();
							}	
							}
						});
					}
				});
			}
			
			break;
		case R.id.tc:
			android.os.Process.killProcess(android.os.Process.myPid()) ;  //获取PID 
      	  System.exit(0);   //常规java、c#的标准退出法，返回值为0代表正常退出
			break;
		case R.id.zc:
			startActivity(new Intent(LoginActivity.this,RegActivity.class));
			finish();
			break;
		case R.id.xgmm:
			startActivity(new Intent(LoginActivity.this,UpdateActivity.class));
			finish();
			break;
		}
	}

}
