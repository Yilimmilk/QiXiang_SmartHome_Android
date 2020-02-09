package com.example.demog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;


public class StatisticsFragment extends Fragment {


	private ArrayList<Integer> colorList;
	private RingView ringView;
	private float lowNumber=0,highNumber=0,centreNumber=0;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.frament_statistics, null);
		initViews(view);
		  ringView=(RingView)view.findViewById(R.id.cs_view);
	      // 添加的是颜色
        colorList = new ArrayList<Integer>();
        colorList.add(R.color.color_ff3e60);
        colorList.add(R.color.color_ffa200);
        colorList.add(R.color.color_31cc64);
        colorList.add(R.color.color_3377ff);
        initList("光照度");
		return view;
	}
	
	
	private void initList(String device) {
		// TODO Auto-generated method stub
		ArrayList<Map<String, Integer>>	list =new ArrayList<Map<String, Integer>>();
	    Cursor cursor=MainActivity.mDatabase.query(MainActivity.mHelper.data_table, new String[]{"device","time","number"}, " device=? ", new String[]{device
				}, null, null, null);
		if(cursor.getCount()>0){
			while (cursor.moveToNext()) {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("number", cursor.getInt(cursor.getColumnIndex("number")));
			list.add(map);
			}
			
		}
			cursor.close();
		
	ArrayList<String> mlist =new ArrayList<String>();
	if(device.contains("光照度")){
		mlist.add("0-300");
		mlist.add("301-699");
		mlist.add("700-1500");
	}else{
		mlist.add("0-19");
		mlist.add("20-29");
		mlist.add("30-39");
	}
	for(int i=0;i<list.size();i++){
		// sum=sum+list.get(i).get("number");
		if(list.get(i).get("number")==0){
			lowNumber++;
		}else if(list.get(i).get("number")==1){
			centreNumber++;
		}else if(list.get(i).get("number")==2){
			highNumber++;
		}
	}
	Log.e("=========", "========="+lowNumber+ "========="+centreNumber+ "========="+highNumber);
	    List<Float> rateList = new ArrayList<Float>();
	    rateList.add((float)(lowNumber/list.size())*100);	
        rateList.add((float)(centreNumber/list.size())*100);
        rateList.add((float)(highNumber/list.size())*100);
       ringView.setShow(colorList, rateList,device,mlist);
      
	      
	}
	

	private void initViews(View view) {
		// TODO Auto-generated method stub
		RadioGroup	mStatisticsRG=(RadioGroup)view.findViewById(R.id.rg_statistics);
		mStatisticsRG.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(checkedId==R.id.rb_illumination){
					 initList("光照度");
				}else if(checkedId==R.id.rb_temp){
					 initList("温度");
				}
			}
		
		});
	}
}
