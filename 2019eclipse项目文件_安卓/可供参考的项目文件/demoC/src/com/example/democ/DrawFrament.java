package com.example.democ;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DrawFrament extends Fragment{
	private DoubleLineChatView mHistogramView;
	 private int[] mDataLeft;
	 private int[] mDataRight;
	 private String[] mDataTextX ;
	    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.frament_draw, null);
		mHistogramView=(DoubleLineChatView)view.findViewById(R.id.histogram);
		Cursor cursor=MainActivity.mDatabase.query(MainActivity.mHelper.Illumination_table, null, null, null, null, null, null);
		if(cursor.getCount()>0){
			  cursor.moveToFirst();
			mDataLeft=new int[cursor.getCount()];
			mDataRight=new int[cursor.getCount()];
			mDataTextX=new String[cursor.getCount()];
			  for (int i = 0; i < cursor.getCount(); i++) {
                 String tempe = cursor.getString(cursor.getColumnIndex("tempe"));
                 String illumination = cursor.getString(cursor.getColumnIndex("illumination"));
                 int id = cursor.getInt(cursor.getColumnIndex("id"));
                 mDataLeft[i]= (int) Double.parseDouble(tempe);
                 mDataRight[i]=(int) Double.parseDouble(illumination);
                 mDataTextX[i]=String.valueOf(id);
               //移动到下一位
                 cursor.moveToNext();
			 }
			  cursor.close();
			  mHistogramView.setData(mDataLeft, mDataRight, mDataTextX);
		}
		return view;
	}

}
