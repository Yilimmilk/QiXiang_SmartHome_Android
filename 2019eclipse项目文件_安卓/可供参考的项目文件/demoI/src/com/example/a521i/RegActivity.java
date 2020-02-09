package com.example.a521i;

import android.R.anim;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegActivity extends Activity implements OnClickListener {

	private EditText et1, et2, et3;
	private Button zc1, tc1;
	private String name,psw,psw2;
	
	private DataHelper mdatahelper;
	private SQLiteDatabase db;
	private Cursor cursor;
	private ContentValues Values=new ContentValues();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		mdatahelper = new DataHelper(this);
		db=mdatahelper.getWritableDatabase();
		initViews();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		et1 = (EditText) findViewById(R.id.zc_yhm);
		et2 = (EditText) findViewById(R.id.zc_mm);
		et3 = (EditText) findViewById(R.id.zc_mm2);
		
		zc1 = (Button) findViewById(R.id.zc_zc);
		zc1.setOnClickListener(this);
		tc1 = (Button) findViewById(R.id.zc_tc);
		tc1.setOnClickListener(this);
		

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.zc_tc:
			startActivity(new Intent(RegActivity.this,LoginActivity.class));
			finish();
			break;
		case R.id.zc_zc:
			name = et1.getText().toString();
			psw = et2.getText().toString();
			psw2 = et3.getText().toString();
			
			if(!psw.equals(psw2)){
			  new AlertDialog.Builder(this).setTitle("注册失败").setMessage("重复密码不一致").setPositiveButton("ok", null).show();
			}else{
				cursor=db.query(mdatahelper.TABLE_NAME, new String[]{"uname","upsw"}, "uname=? and upsw=? ", new String[]{name,psw}, null, null, null);
				if(cursor.getCount()>0){
					new AlertDialog.Builder(this).setTitle("注册失败").setMessage("用户名已经存在，请重新注册").setPositiveButton("ok", null).show();
				}else{
					Values.put("uname",name);
					Values.put("upsw",psw);
					db.insert(mdatahelper.TABLE_NAME, null, Values);
					new AlertDialog.Builder(this).setTitle("注册成功").setMessage("用户名注册成功")
					.setPositiveButton("ok",new  DialogInterface.OnClickListener() {
            			
            			public void onClick(DialogInterface dialog, int which) {
            				// TODO Auto-generated method stub
            				startActivity(new Intent(RegActivity.this,LoginActivity.class));
            				finish();
            			}
            		} ).show();

				}
			}
			break;
			}
	}

}
