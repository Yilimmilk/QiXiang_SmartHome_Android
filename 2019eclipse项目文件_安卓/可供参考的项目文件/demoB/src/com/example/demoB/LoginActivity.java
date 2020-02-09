package com.example.demoB;


import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;



public class LoginActivity extends Activity {

	private EditText mIpEt;
	private Button mLoginBtn;
	private SeekBar mSeekBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initViews();
	
	
	}

	private void initViews() {
		// TODO Auto-generated method stub
		mIpEt=(EditText)findViewById(R.id.et_ip);

		 mSeekBar = (SeekBar) findViewById(R.id.seekbar);
		    mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
				
				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					if(mSeekBar.getProgress()!=100){
						mSeekBar.setProgress(0);
					}
				}
				
				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {
					// TODO Auto-generated method stub
					
				}
			});
		mLoginBtn=	(Button)findViewById(R.id.btn_login);
		mLoginBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			    if (TextUtils.isEmpty( mIpEt.getText().toString() )) {
                    Toast.makeText( LoginActivity.this, "IP不能为空!", Toast.LENGTH_SHORT ).show();
                    return;
                }
			    if(mSeekBar.getProgress()!=100){
			    	  Toast.makeText( LoginActivity.this, "请先向右滑动解锁!", Toast.LENGTH_SHORT ).show();
	                    return;
			    }
			 
                ControlUtils.setUser( "","", mIpEt.getText().toString() );
                SocketClient.getInstance().creatConnect();
                SocketClient.getInstance().login( new LoginCallback() {
                    @Override
                    public void onEvent(final String status) {
                        runOnUiThread( new Runnable() {
                            @Override
                            public void run() {
                                if (status.equals( ConstantUtil.SUCCESS )) {
                                    startActivity( new Intent( LoginActivity.this, ProgressActivity.class ) );
                                    finish();
                                } else {
                                    Toast.makeText( LoginActivity.this, "连接失败", Toast.LENGTH_SHORT ).show();
                                    startActivity( new Intent( LoginActivity.this, ProgressActivity.class ) );
                                    finish();
                                }
                            }
                        } );
                    }
                });
                }

	});
	}




}
