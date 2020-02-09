package com.example.democ;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPageAdater extends FragmentPagerAdapter {


	private ArrayList<Fragment> list;

	public ViewPageAdater(FragmentManager fragmentManager, ArrayList<Fragment> list) {
		// TODO Auto-generated constructor stub
		 super(fragmentManager);
		 this.list=list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}



	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

}
