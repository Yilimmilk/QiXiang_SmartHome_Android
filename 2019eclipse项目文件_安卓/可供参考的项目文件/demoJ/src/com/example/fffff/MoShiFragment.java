package com.example.fffff;


import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

public class MoShiFragment extends Fragment {

	CheckBox box_shangban, box_xiaban, box3_shuiming;
	Runnable r_shangban, r_xiaban, r_shuimiann; // 三个模式的线程
	
	MainActivity activity;
	Runnable r_sqlThrad;
	float values[];
	private LineView myview;
	Handler handler = new Handler();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.moshi_fragment, null);
		init(view);
		activity = (MainActivity) getActivity();
		initRunable();
		initOnclicks();
		values = new float[7];
		return view;
	}

	
	void getSqlData(){
		activity.helper = new Helper(activity);
		activity.db = activity.helper.getReadableDatabase();
		Cursor cursor = activity.db.rawQuery("select top 7 value from t_data where name = ? ORDER BY id", new String[]{"烟雾"});
		int i =0;
		while(cursor.moveToNext()){
			values[i] = Float.parseFloat(cursor.getString(0));
			if(values[i]>800){
				if(!activity.warLightState){
					activity.openOrCloseWarLight(true);
				}
			}
			i++;
		}
	}
	
	
	
	
	
	private void initOnclicks() {
		// TODO Auto-generated method stub
		box_shangban.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if(box_xiaban.isChecked()||box3_shuiming.isChecked()){
						Toast.makeText(activity, "当前模式与其他模式冲突 请先关闭其他模式", 0).show();
						box_shangban.setChecked(false);
						return ;
					}
					handler.post(r_shangban);
				} else {
					handler.removeCallbacks(r_shangban);
				}
			}
		});

		box_xiaban.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if(box_shangban.isChecked()){
						Toast.makeText(activity, "当前模式与上班模式冲突 请先关闭上班模式", 0).show();
						box_xiaban.setChecked(false);
						return ;
					}
					
					handler.post(r_xiaban);
				} else {
					handler.removeCallbacks(r_xiaban);
				}
			}
		});

		box3_shuiming.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if(box_shangban.isChecked()){
						Toast.makeText(activity, "当前模式与上班模式冲突 请先关闭上班模式", 0).show();
						box3_shuiming.setChecked(false);
						return ;
					}
					
					handler.post(r_shuimiann);
				} else {
					handler.removeCallbacks(r_shuimiann);
				}
			}
		});

	}

	private void initRunable() {
		// TODO Auto-generated method stub
		r_sqlThrad = new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				handler.postDelayed(r_sqlThrad, 1000);
				activity.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						myview.initData(values);
					}
				});
			}
		};
		
		r_shangban = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				// 如果窗帘状态是关闭 则开启窗帘 同时修改状态
				if (activity.chuanState) {
					activity.openOrCloseChuangLian(3);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (activity.hasman) {
					if (!activity.chuanState) {
						activity.openOrCloseChuangLian(1);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (activity.lampState) {
						activity.openOrCloseLamp(!activity.lampState);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						activity.openOrCloseLamp(!activity.lampState);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				handler.postDelayed(r_shangban, 1000);
			}
		};

		r_xiaban = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				if (!activity.lampState) {
					activity.openOrCloseLamp(true);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (!activity.ktState) {
					activity.openOrCloseKT(true);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (activity.yanwu_value > 600) {
					if (!activity.fanState) {
						activity.openOrCloseFan(true);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else {
					if (activity.fanState) {
						activity.openOrCloseFan(false);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				handler.postDelayed(r_xiaban, 1000);
			}
		};

		r_shuimiann = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				if (activity.chuanState) {
					activity.openOrCloseChuangLian(3);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (activity.hasman) {
					if (!activity.warLightState) {
						activity.openOrCloseWarLight(true);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					if (!activity.lampState) {
						activity.openOrCloseLamp(true);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}

				handler.postDelayed(r_shuimiann, 1000);
			}
		};

	}

	private void init(View view) {
		// TODO Auto-generated method stub
		box_shangban = (CheckBox) view.findViewById(R.id.checkBox1);
		box_xiaban = (CheckBox) view.findViewById(R.id.CheckBox01);
		box3_shuiming = (CheckBox) view.findViewById(R.id.CheckBox02);
		myview = (LineView) view.findViewById(R.id.lineview);
	}

}
