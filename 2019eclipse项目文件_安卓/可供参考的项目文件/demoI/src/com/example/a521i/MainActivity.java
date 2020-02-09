package com.example.a521i;

import java.util.ArrayList;
import java.util.List;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

public class MainActivity extends FragmentActivity  {
	ViewPager vp;
	private ActionBar bar;
	private Tab tab1;
	private Tab tab2;
	private Tab tab3;
	private Tab tab4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		vp = (ViewPager) findViewById(R.id.vp);
		bar = getActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		tab1 = bar.newTab();
		tab1.setText("传感器数据");
		tab1.setTabListener(new TabListener() {

			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				vp.setCurrentItem(0);
			}

			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub

			}
		});

		tab2 = bar.newTab();
		tab2.setText("电器控制");

		tab2.setTabListener(new TabListener() {

			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				vp.setCurrentItem(1);
			}

			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub

			}
		});

		tab3 = bar.newTab();

		tab3.setText("联动控制");

		tab3.setTabListener(new TabListener() {

			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				vp.setCurrentItem(2);
			}

			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub

			}
		});
		tab4 = bar.newTab();
		tab4.setText("模式控制");
		tab4.setTabListener(new TabListener() {

			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				vp.setCurrentItem(3);
			}

			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub

			}
		});
		
		bar.addTab(tab1);
		bar.addTab(tab2);
		bar.addTab(tab3);
		bar.addTab(tab4);
		
		

		List<Fragment> list = new ArrayList<Fragment>();
		list.add(new Frag1());
		list.add(new Frag2());
		list.add(new Frag3());
		list.add(new Frag4());

		MyAdapter adapter = new MyAdapter(getSupportFragmentManager(), list);

		vp.setAdapter(adapter);
		
		
		vp.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				if(arg0==0){
					bar.selectTab(tab1);
				}else if(arg0 == 1){
					bar.selectTab(tab2);
				}else if(arg0 == 2){
					bar.selectTab(tab3);
				}else{
					bar.selectTab(tab4);
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	
	}


}
