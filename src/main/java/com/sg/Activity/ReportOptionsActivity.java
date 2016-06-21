package com.sg.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sg.Activity.DashBoardActivity.whichRecharge;
import com.sg.RechargeManger.Posts;
import com.sg.constants.SPKeyConstants;
import com.sg.mobilerecharge.R;
import com.sg.utility.Utils;

public class ReportOptionsActivity extends ParentActivity {
	enum whichReport{
		Recharge,
		DRecharge,
		Statement,
		sale,
		refund
	}
	whichReport mWhichReport;
	String posts;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reportoptions);
		posts=Utils.getValueFromSharedPreferences(this, SPKeyConstants.SHARED_PREF_POSTS);
		setlayoutRef();
	}

	private void setlayoutRef() {
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
		//own layout id
		Button rechargeReport=(Button)findViewById(R.id.rechargeReport);
		Button transactionReport=(Button)findViewById(R.id.transactionReport);
		Button salesReport=(Button)findViewById(R.id.salesReport);
		Button refundReport=(Button)findViewById(R.id.refundReport);
		rechargeReport.setOnClickListener(new ButtonListener());
		transactionReport.setOnClickListener(new ButtonListener());
		salesReport.setOnClickListener(new ButtonListener());
		refundReport.setOnClickListener(new ButtonListener());
		if(!Posts.RT.equalsIgnoreCase(posts)){
			rechargeReport.setVisibility(View.GONE);
			transactionReport.setVisibility(View.VISIBLE);
			salesReport.setVisibility(View.GONE);
			refundReport.setVisibility(View.GONE);
		}
	}

private class ButtonListener implements View.OnClickListener{
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rechargeReport:
			mWhichReport=whichReport.DRecharge;
			reportClicked();
			break;
		case R.id.transactionReport:
			mWhichReport=whichReport.Statement;
			reportClicked();
			break;
		case R.id.salesReport:
			mWhichReport=whichReport.sale;
			reportClicked();
			break;
		case R.id.back:
			backClicked();
			break;
		case R.id.refundReport:
			mWhichReport=whichReport.refund;
			reportClicked();
			break;
		default:
			break;
		}			
	}

	private void reportClicked() {
		if(mWhichReport==whichReport.DRecharge){
			Intent intent=new Intent(ReportOptionsActivity.this,ReportList.class);
			intent.putExtra("ServiceName", whichReport.DRecharge);
			startNewActivity(intent);
		}
		else if(mWhichReport==whichReport.Statement){
			Intent intent=new Intent(ReportOptionsActivity.this,ReportList.class);
			intent.putExtra("ServiceName", whichReport.Statement);
			startNewActivity(intent);
		}
		else if(mWhichReport==whichReport.sale){
			Intent intent=new Intent(ReportOptionsActivity.this,ReportList.class);
			intent.putExtra("ServiceName", whichReport.sale);
			startNewActivity(intent);
		}
		else if(mWhichReport==whichReport.refund){
			Intent intent=new Intent(ReportOptionsActivity.this,ReportList.class);
			intent.putExtra("ServiceName", whichReport.refund);
			startNewActivity(intent);
		}
	}

}
}
