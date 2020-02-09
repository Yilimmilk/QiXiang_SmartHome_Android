package com.example.demog;


import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;
import com.bizideal.smarthome.socket.Utils.SpUtils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private EditText mAccountEt,mPasswordEt,mIpEt;
	private Button mLoginBtn;
	private CheckBox mRemember;
	private CheckBox mLoginCb;
	
  @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_login);
	initViews();
}

private void initViews() {
	// TODO Auto-generated method stub
	 mAccountEt = (EditText) findViewById( R.id.username_et );//账号
     mPasswordEt = (EditText) findViewById( R.id.password_et );//密码
     mIpEt = (EditText) findViewById( R.id.ip_et );//IP
     mRemember=(CheckBox)findViewById(R.id.cb_remember);
     mLoginCb=(CheckBox)findViewById(R.id.cb_login);
     if(!TextUtils.isEmpty(SpUtils.getValue( this, "Account", "" ))){
    	 mRemember.setChecked(true);
    	 mAccountEt.setText( SpUtils.getValue( this, "Account", "" ) );
    	 mPasswordEt.setText( SpUtils.getValue( this, "Password", "" ) );
    	 mIpEt.setText( SpUtils.getValue( this, "Ip", "" ) );
     }
     if(!TextUtils.isEmpty(SpUtils.getValue( this, "Account", "" ))&&SpUtils.getValue( this, "state", "" ).equals("1")
    		 &&!TextUtils.isEmpty(SpUtils.getValue( this, "Account", "" ))){
    	 mLoginCb.setChecked(true);
         getLogin();
    	 return;
     }
     
     
     mLoginBtn=(Button)findViewById(R.id.btn_login);
     mLoginBtn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
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
          
             if (!mAccountEt.getText().toString().equals( "bizideal" ) || !mPasswordEt.getText().toString().equals( "123456" )) {
            		 Toast.makeText( LoginActivity.this, "账号或密码错误！", Toast.LENGTH_SHORT ).show();
                 return;
             }
             getLogin();
		}
     });
     }
private void getLogin() {
	// TODO Auto-generated method stub
	 if(mRemember.isChecked()==true){
         SpUtils.putValue( LoginActivity.this, "Account", mAccountEt.getText().toString() );
         SpUtils.putValue( LoginActivity.this, "Password", mPasswordEt.getText().toString() );
         SpUtils.putValue( LoginActivity.this, "Ip", mIpEt.getText().toString() );
         }else{
             SpUtils.putValue( LoginActivity.this, "Account", "" );
             SpUtils.putValue( LoginActivity.this, "Password","" );
             SpUtils.putValue( LoginActivity.this, "Ip", "" );
         }
         if(mLoginCb.isChecked()==true){
              SpUtils.putValue( LoginActivity.this, "state", "1");
         }else {
        	   SpUtils.putValue( LoginActivity.this, "state", "0");
         }
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
                        	   startActivity( new Intent( LoginActivity.this, MainActivity.class ) );
                               finish();
                             Toast.makeText( LoginActivity.this, "失败！", Toast.LENGTH_SHORT ).show();
                         }
                     }
                 } );
             }
         } );

}


}
