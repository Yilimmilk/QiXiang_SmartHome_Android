package com.example.democ;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends FragmentActivity {

	public static ViewPager ViewPager;
	private ArrayList<Fragment> lists;
	private RadioGroup radioGroup;
	public static DatabaseHelper mHelper;
	public static SQLiteDatabase mDatabase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
		mHelper=new DatabaseHelper(this);
		mDatabase=mHelper.getWritableDatabase();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		lists=new ArrayList<Fragment>();
		lists.add(new ChoiceFrament());
		lists.add(new BasicFrament());
		lists.add(new LinkageFrament());
		lists.add(new ModelFrament());
		lists.add(new DrawFrament());
		ViewPager=(ViewPager)findViewById(R.id.viewpager);
		ViewPageAdater Adater=new ViewPageAdater(getSupportFragmentManager(),lists);
		ViewPager.setAdapter(Adater);
	    radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(checkedId==R.id.rb_choice){
					ViewPager.setCurrentItem(0);
				}else if(checkedId==R.id.rb_basic){
					ViewPager.setCurrentItem(1);
				}else if(checkedId==R.id.rb_linkage){   
					ViewPager.setCurrentItem(2);
				}else if(checkedId==R.id.rb_model){   
					ViewPager.setCurrentItem(3);
				}else if(checkedId==R.id.rb_draw){
					ViewPager.setCurrentItem(4);
				}
				
			}
		});
		ViewPager.setOnPageChangeListener(new OnPageChangeListener() {
		     
		    @Override
		    public void onPageSelected(int arg0) {
		    	if(arg0==0){
		    		radioGroup.check(R.id.rb_choice);
		    	}else if(arg0==1){
		    		radioGroup.check(R.id.rb_basic);
		    	}else if(arg0==2){
		    		radioGroup.check(R.id.rb_linkage);
		    	}else if(arg0==3){
		    		radioGroup.check(R.id.rb_model);
		    	}else if(arg0==4){
		    		radioGroup.check(R.id.rb_draw);
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
