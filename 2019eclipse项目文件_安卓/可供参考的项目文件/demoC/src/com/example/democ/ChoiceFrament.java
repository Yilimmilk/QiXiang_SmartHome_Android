package com.example.democ;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class ChoiceFrament extends Fragment implements OnClickListener {
	private TextView mBasicTv, mLinkageTv, mModelTv, mDrawTv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.frament_choice, null);
		initViews(view);
		return view;
	}

	private void initViews(View view) {
		// TODO Auto-generated method stub
		mBasicTv = (TextView) view.findViewById(R.id.tv_basic);
		mBasicTv.setOnClickListener(this);
		mLinkageTv = (TextView) view.findViewById(R.id.tv_linkage);
		mLinkageTv.setOnClickListener(this);
		mModelTv = (TextView) view.findViewById(R.id.tv_model);
		mModelTv.setOnClickListener(this);
		mDrawTv = (TextView) view.findViewById(R.id.tv_draw);
		mDrawTv.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_basic:
			MainActivity.ViewPager.setCurrentItem(1);
			mBasicTv.setCompoundDrawablesWithIntrinsicBounds(getActivity()
					.getResources().getDrawable(R.drawable.ic_launcher), null,
					null, null);
			mLinkageTv.setCompoundDrawables(null, null, null, null);
			mModelTv.setCompoundDrawables(null, null, null, null);
			mDrawTv.setCompoundDrawables(null, null, null, null);

			break;
		case R.id.tv_linkage:
			MainActivity.ViewPager.setCurrentItem(2);
			mLinkageTv.setCompoundDrawablesWithIntrinsicBounds(getActivity()
					.getResources().getDrawable(R.drawable.ic_launcher), null,
					null, null);
			mBasicTv.setCompoundDrawablesWithIntrinsicBounds(null, null, null,
					null);
			mModelTv.setCompoundDrawablesWithIntrinsicBounds(null, null, null,
					null);
			mDrawTv.setCompoundDrawablesWithIntrinsicBounds(null, null, null,
					null);
			break;
		case R.id.tv_model:
			MainActivity.ViewPager.setCurrentItem(3);
			mModelTv.setCompoundDrawablesWithIntrinsicBounds(getActivity()
					.getResources().getDrawable(R.drawable.ic_launcher), null,
					null, null);
			mBasicTv.setCompoundDrawablesWithIntrinsicBounds(null, null, null,
					null);
			mLinkageTv.setCompoundDrawablesWithIntrinsicBounds(null, null,
					null, null);
			mDrawTv.setCompoundDrawablesWithIntrinsicBounds(null, null, null,
					null);
			break;
		case R.id.tv_draw:
			MainActivity.ViewPager.setCurrentItem(4);
			mDrawTv.setCompoundDrawablesWithIntrinsicBounds(getActivity()
					.getResources().getDrawable(R.drawable.ic_launcher), null,
					null, null);
			mBasicTv.setCompoundDrawablesWithIntrinsicBounds(null, null, null,
					null);
			mLinkageTv.setCompoundDrawablesWithIntrinsicBounds(null, null,
					null, null);
			mModelTv.setCompoundDrawablesWithIntrinsicBounds(null, null, null,
					null);
			break;
		default:
			break;
		}
	}
}
