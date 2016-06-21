package com.sg.Activity;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.sg.adapter.OperatorLazyAdapter;
import com.sg.mobilerecharge.R;
import com.sg.operator.GetListMobileOperator;

public class OperatorListtActivity extends ParentActivity{
	static  ArrayList<HashMap<String, String>> operator_list;
	GridView list;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.operator);
		setlayoutRef();
		GetListMobileOperator gmo=new GetListMobileOperator(this);
		operator_list = new ArrayList<HashMap<String, String>>();
		if(MyApplication.selectedRecharge.equalsIgnoreCase("Mobile")){
			operator_list=gmo.getListMobileOperator("mobile_operators.xml");
		}
		else if(MyApplication.selectedRecharge.equalsIgnoreCase("dth")){
			operator_list=gmo.getListMobileOperator("dth_operators.xml");
		}
		else if(MyApplication.selectedRecharge.equalsIgnoreCase("postpaid")){
			operator_list=gmo.getListMobileOperator("postpaid_operators.xml");
		}
		else if(MyApplication.selectedRecharge.equalsIgnoreCase("utility")){
			operator_list=gmo.getListMobileOperator("utility_operators.xml");
		}
		list=(GridView)findViewById(R.id.list);
		OperatorLazyAdapter	adapter1=new OperatorLazyAdapter(this, operator_list);
		list.setAdapter(adapter1);
		list.setOnItemClickListener(new ItemClickListener());
	}
	public void setlayoutRef(){
		//set header
		TextView back=(TextView)findViewById(R.id.back);
		back.setVisibility(View.VISIBLE);
		back.setOnClickListener(new ButtonListener());
		ImageView drw=(ImageView)findViewById(R.id.drawer);
		drw.setVisibility(View.GONE);
		TextView label=(TextView)findViewById(R.id.label);
		label.setVisibility(View.GONE);
		TextView balancetv=(TextView)findViewById(R.id.balancetv);
		balancetv.setVisibility(View.GONE);
		ImageView logo=(ImageView)findViewById(R.id.logo);
		logo.setVisibility(View.GONE);
	}
	private class ButtonListener implements View.OnClickListener{
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.back:
				backClicked();
				break;
			default:
				break;
			}			
		}

	}
	private class ItemClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Intent intent=new Intent(OperatorListtActivity.this,MobileRecharge.class);
			intent.putExtra("OP_Name", operator_list.get(arg2).get(GetListMobileOperator.KEY_TITLE));
			intent.putExtra("OP_CODE", operator_list.get(arg2).get(GetListMobileOperator.opcode));
			intent.putExtra("URL", operator_list.get(arg2).get(GetListMobileOperator.KEY_THUMB_URL));
			startActivity(intent);
		}

	}
}
