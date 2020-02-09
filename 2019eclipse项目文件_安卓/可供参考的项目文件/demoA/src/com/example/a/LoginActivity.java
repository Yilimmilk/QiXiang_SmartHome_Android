package com.example.a;

import com.bizideal.smarthome.socket.ControlUtils;
import com.example.a.MyView.OnUnlockListener;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class LoginActivity extends Activity {

	private MyView myview;
	private EditText ip_et;
	private Button bt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		ip_et = (EditText) findViewById(R.id.ip);
		
		
		myview = (MyView) findViewById(R.id.myview);
		bt = (Button) findViewById(R.id.login);
		
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String ip = ip_et.getText().toString();
				
				if(ip.isEmpty()){
					Toast.makeText(LoginActivity.this, "请输入正确的ip", 0).show();
					return ;
				}
				ControlUtils.setUser("bizideal", "123456", ip);
				startActivity(new Intent(LoginActivity.this, MainActivity.class));
			}
		});
		
		myview.setOnUnlockListener(new OnUnlockListener() {
			
			@Override
			public void setUnlock(boolean unlock) {
				// TODO Auto-generated method stub
				if(unlock){
					String ip = ip_et.getText().toString();
					if(ip.isEmpty()){
						Toast.makeText(LoginActivity.this, "请输入正确的ip", 0).show();
						return ;
					}
					ControlUtils.setUser("bizideal", "123456", ip);
					startActivity(new Intent(LoginActivity.this, MainActivity.class));
				}
			}
		});
	}

	

}
