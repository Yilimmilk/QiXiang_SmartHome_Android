package com.example.a521i;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends Activity implements OnClickListener {

	private EditText et1,et2,et3;
	private Button bt1,bt2;
	private String name,mm1,mm2;
	private SQLiteDatabase db;
	private DataHelper mdatabase;
	private Cursor cursor;
	private String sql;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);
		initviews();
		mdatabase = new DataHelper(this);
		db=mdatabase.getWritableDatabase();
	}


	private void initviews() {
		// TODO Auto-generated method stub
	
		et1 = (EditText) findViewById(R.id.up_yhm);
		et2 = (EditText) findViewById(R.id.up_mm);
		et3 = (EditText) findViewById(R.id.up_mm2);
		
		bt1 = (Button) findViewById(R.id.up_tj);
		bt1.setOnClickListener(this);
		bt2 = (Button) findViewById(R.id.up_tc);
		bt2.setOnClickListener(this);

	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.up_tc:
			startActivity(new Intent(UpdateActivity.this,LoginActivity.class));
			finish();
			break;
		case R.id.up_tj:
			name = et1.getText().toString();
			mm1 = et2.getText().toString();
			mm2 = et3.getText().toString();
			ContentValues values = new ContentValues();
			if(mm1.equals(mm2)){
				Toast.makeText(this, "?", 0).show();
			}else{
				cursor=db.query(mdatabase.TABLE_NAME, new String[]{"uname","upsw"}, "uname=? and upsw=? ", new String[]{name,mm1}, null, null, null);
				if(cursor.getCount()<1){
				new AlertDialog.Builder(this).setTitle("修改失败").setMessage("修改失败：输入的用户名不存在").setPositiveButton("ok", null);
				}else{
					if(!mm1.equals( sql = "select upsw from user_table where uname = 'name' ")){
						new AlertDialog.Builder(this).setTitle("修改失败").setMessage("旧密码错误").setPositiveButton("ok", null);
					}
			   values.put("upsw", mm2);
			   db.update(mdatabase.TABLE_NAME, values, "uname = ?", new String[] { name });
				new AlertDialog.Builder(this).setTitle("修改成功").setMessage("密码修改成功").setPositiveButton("ok", null);

			 }}
			break;
		}
	}
	

	

}
