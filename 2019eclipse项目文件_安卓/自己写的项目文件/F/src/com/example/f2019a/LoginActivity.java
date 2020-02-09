package com.example.f2019a;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity implements android.view.View.OnClickListener{
	
	private Button btLogin,btReg,btChange,btFind;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		btReg=(Button)findViewById(R.id.bt_reg);
		btReg.setOnClickListener(this);
	}
	
	private void initDialog(){
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		View view = View.inflate(LoginActivity.this, R.layout.custom_dialog_register, null);
		builder.setView(view);
		final EditText etUser,etPass,etRepass,etSecpass;
		etUser=(EditText)view.findViewById(R.id.et_dia_user);
		etPass=(EditText)view.findViewById(R.id.et_dia_pass);
		etRepass=(EditText)view.findViewById(R.id.et_dia_repass);
		etSecpass=(EditText)view.findViewById(R.id.et_dia_secpass);
		builder.setNegativeButton("зЂВс", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		builder.create().show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_reg:
			initDialog();
			break;

		default:
			break;
		}
	}

}
