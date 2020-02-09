package com.example.demoB;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RoomAdater extends BaseAdapter {
	
	private Context mContext;
	private ArrayList<HashMap<String, Object>> mData;

	public RoomAdater(Context context){
		mContext=context;
	
}
	
	public void setData( ArrayList<HashMap<String, Object>>	list){
		mData=list;	
		notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView=LayoutInflater.from(mContext).inflate(R.layout.item_room, parent,false);
		TextView mRoom=(TextView)convertView.findViewById(R.id.tv_room);
		mRoom.setText(mData.get(position).get("roomName").toString());
		if(mData.get(position).get("checkIn").toString().equals("0")){
			mRoom.setBackgroundColor(Color.GREEN);
		}else if(mData.get(position).get("checkIn").toString().equals("1")){
			mRoom.setBackgroundColor(Color.RED);
		}else if(mData.get(position).get("checkIn").toString().equals("3")){
			mRoom.setBackgroundColor(Color.parseColor("#66000000"));
		}
		return convertView;
	}

}
