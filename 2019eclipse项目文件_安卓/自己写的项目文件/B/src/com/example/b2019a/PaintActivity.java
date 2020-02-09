package com.example.b2019a;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import java.util.List;

import com.bizideal.smarthome.socket.DeviceBean;

public class PaintActivity extends Activity{
	
	private int select;
	private String wenduString="25";
	private int wenduInt;
	private int[] DataInt=new int[9];
	private List<String> DataString;
	private CustomView paintgraph;
	
	private TextView fanghao;
	
	public CustomView custom;
	public Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_paint);
		
		Intent intent =getIntent();
		select=intent.getIntExtra("selectRoomNum", 0);
		Log.d("当前房号", ""+select);
		
		fanghao=(TextView)findViewById(R.id.tv_fanghao);
		fanghao.setText("房号:"+select);
		
		new Thread(){
            public void run() {
                while(true){
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(CustomView.data.size()>6){
                    	CustomView.data.remove(0);
                    }
                    CustomView.data.add(Double.valueOf(DeviceBean.getTemperature()).intValue());
                }
            };
        }.start();
	}
}
