package com.example.socket_json;




import lib.SocketThread;
import lib.SysApplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
	public class JoinActivity extends Activity {
		private EditText username_AutoCompleteTextView;
		private EditText ip_AutoCompleteTextView;
		private EditText port_AutoCompleteTextView;
		private EditText password_EditText;
		public String password,username;
		private Button button;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			SysApplication.getInstance().addActivity(this);                             
			setContentView(R.layout.join);
			


			username_AutoCompleteTextView = (EditText) findViewById(R.id.autoCompleteTextView_username);  
			ip_AutoCompleteTextView= (EditText) findViewById(R.id.autoCompleteTextView_ip);
			port_AutoCompleteTextView= (EditText) findViewById(R.id.autoCompleteTextView_port);
	        password_EditText=(EditText)findViewById(R.id.editText_password);
			
			button=(Button)findViewById(R.id.button1);
			button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					if(username_AutoCompleteTextView.getText().toString().equals("bizideal")&&password_EditText.getText().toString().equals("123456"))
					{
						username=username_AutoCompleteTextView.getText().toString();
						password=password_EditText.getText().toString();
					SocketThread.SocketIp = ip_AutoCompleteTextView.getText().toString();                        	
				SocketThread.Port = Integer.parseInt(port_AutoCompleteTextView.getText().toString());
						startActivity(new Intent(JoinActivity.this,MainActivity.class));	
					}
					else{
						Toast.makeText(JoinActivity.this, "用户名或密码错误，请重新输入...", Toast.LENGTH_SHORT).show();
					}
				}
			});	
	
		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.activity_main, menu);
			return true;
		}
		
	    
	}

