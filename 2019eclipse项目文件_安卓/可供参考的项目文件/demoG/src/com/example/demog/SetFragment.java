package com.example.demog;


import com.bizideal.smarthome.socket.Utils.SpUtils;
import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;



public class SetFragment extends Fragment {


	private EditText mOlderPasswordEt;
	private EditText mPasswordEt;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.frament_set, null);
		initViews(view);
		return view;
	}

	private void initViews(View view) {
		// TODO Auto-generated method stub
		 mOlderPasswordEt = (EditText) view.findViewById( R.id.oldepassword_et );
		 mPasswordEt = (EditText) view.findViewById( R.id.password_et );
		ImageView	out=(ImageView)view.findViewById(R.id.iv_out);
		out.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 SpUtils.putValue(getActivity(), "state", "0");
				getActivity().finish();
			}
		});
		Button mUpBtn=(Button)view.findViewById(R.id.btn_up);
		 mUpBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 if (TextUtils.isEmpty( mOlderPasswordEt.getText().toString() )) {
	                 Toast.makeText( getActivity(), "旧密码不能为空！", Toast.LENGTH_SHORT ).show();
	                 return;
	             }
	             if (TextUtils.isEmpty( mPasswordEt.getText().toString() )) {
	                 Toast.makeText( getActivity(), "新密码不能为空！", Toast.LENGTH_SHORT ).show();
	                 return;
	             }
	           
	             if (!mOlderPasswordEt.getText().toString().equals( SpUtils.getValue(getActivity() ,"Password","" ))) {
	            		 Toast.makeText(getActivity(), "旧密码不正确", Toast.LENGTH_SHORT ).show();
	                 return;
	             }
	             Toast.makeText(getActivity(), "密码修改成功", Toast.LENGTH_SHORT ).show();
			}
		});
	}
}
