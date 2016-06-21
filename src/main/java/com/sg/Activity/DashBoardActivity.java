package com.sg.Activity;

import java.util.Map;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.sg.RechargeManger.Posts;
import com.sg.constants.SPKeyConstants;
import com.sg.helper.AlertLevel;
import com.sg.helper.ProgressController;
import com.sg.mobilerecharge.R;
import com.sg.requsetCreator.RequsetManager;
import com.sg.service.ReponseParser;
import com.sg.service.ServiceConfiguration;
import com.sg.service.WebServiceCallerAsyncTask;
import com.sg.utility.Utils;

public class DashBoardActivity extends ParentActivity {
	enum whichRecharge{
		Mobile,
		DTH,
		Postpaid,
		Utility
	}
	whichRecharge rechargeService;
	TextView balancetv;
	boolean showPopup=false;
	String posts;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		posts=Utils.getValueFromSharedPreferences(this,SPKeyConstants.SHARED_PREF_POSTS);
		if(Posts.RT.equalsIgnoreCase(posts)){
			setContentView(R.layout.dashboard);	
		}
		else{
			setContentView(R.layout.admin_dashboard);
		}

		setlayoutRef();
		setSlidingMenu(savedInstanceState);
		getBalance();
	}
	@Override
	protected void onResume() {
		super.onResume();
		showPopup=false;
	}

	private void getBalance() {
		Map<String,String> requset=RequsetManager.getIntance().balanceRequset(Utils.getValueFromSharedPreferences(this,SPKeyConstants.SHARED_PREF_USERNAME));	
		WebServiceCallerAsyncTask servcie=new WebServiceCallerAsyncTask(this);
		servcie.startService(ServiceConfiguration.CheckBalance_Url,requset, ServiceConfiguration.BalanceEnquery_METHOD);
	}
	public void sendResponseCheckBalance(Object response){
		ProgressController.dismissProgressDialog();
		if(response instanceof String){
			AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,""+response,this);
		}
		else if(response instanceof SoapObject){
			String status=new ReponseParser().chaeckBalanceResponse((SoapObject)response);
			if(status!=null){
				if(showPopup){
					AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,"Balance is Rs "+status,this);
				}
				balancetv.setText("Rs "+status);
			}
		}
	}
	private void setlayoutRef() {
		if(Posts.RT.equalsIgnoreCase(posts)){
			ImageView drawer=(ImageView)findViewById(R.id.drawer);
			drawer.setOnClickListener(new ClickListener());
			TextView mobile=(TextView)findViewById(R.id.mobileR);
			TextView dth=(TextView)findViewById(R.id.dthR);
			TextView postpaid=(TextView)findViewById(R.id.postpaidR);
			TextView utility=(TextView)findViewById(R.id.utilityR);
			balancetv=(TextView)findViewById(R.id.balancetv);
			TextView checkbalance=(TextView)findViewById(R.id.checkbalance);
			checkbalance.setOnClickListener(new ClickListener());
			dth.setOnClickListener(new ClickListener());
			TextView report=(TextView)findViewById(R.id.report);
			report.setOnClickListener(new ClickListener());
			postpaid.setOnClickListener(new ClickListener());
			utility.setOnClickListener(new ClickListener());
			mobile.setOnClickListener(new ClickListener());
			TextView dthBooking=(TextView)findViewById(R.id.dthbooking);
			dthBooking.setOnClickListener(new ClickListener());	
			TextView complaint=(TextView)findViewById(R.id.complaint);
			complaint.setOnClickListener(new ClickListener());	
		}
		else{
			ImageView drawer=(ImageView)findViewById(R.id.drawer);
			drawer.setOnClickListener(new ClickListener());
			balancetv=(TextView)findViewById(R.id.balancetv);
			TextView checkbalance=(TextView)findViewById(R.id.checkbalance);
			checkbalance.setOnClickListener(new ClickListener());
			TextView balanceTransfer=(TextView)findViewById(R.id.balanceTransfer);
			balanceTransfer.setOnClickListener(new ClickListener());
			TextView report=(TextView)findViewById(R.id.report);
			report.setOnClickListener(new ClickListener());
			TextView revertBal=(TextView)findViewById(R.id.revertBal);
			revertBal.setOnClickListener(new ClickListener());
			
		}

	}
	private class ClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.drawer :
				openMenuImageClicked();
				break;
			case R.id.mobileR :
				rechargeService=whichRecharge.Mobile;
				openRechargeActivity();
				break;

			case R.id.dthR :
				rechargeService=whichRecharge.DTH;
				openRechargeActivity();
				break;

			case R.id.utilityR :
				rechargeService=whichRecharge.Utility;
				openRechargeActivity();
				break;

			case R.id.postpaidR :
				rechargeService=whichRecharge.Postpaid;
				openRechargeActivity();
				break;
			case R.id.checkbalance:
				showPopup=true;
				getBalance();
				break;
			case R.id.balanceTransfer:
				balanceTrasferClecked();
				break;
			case R.id.report:
				reportClicked();
				break;
			case R.id.dthbooking:
				dthBookingClicked();
				break;
			case R.id.revertBal:
				revertButton();
				break;
			case R.id.complaint:
				complaintcliked();
				break;
			default:
				break;
			}			
		}

	
	}
	private void complaintcliked() {
		startNewActivity(new Intent(DashBoardActivity.this,ComplaintActivity.class));
	}
	private void revertButton() {
		startNewActivity(new Intent(DashBoardActivity.this,RevertBalanceActivity.class));		
	}

	private void dthBookingClicked() {
		startNewActivity(new Intent(DashBoardActivity.this,DthBooking.class));
	}
	private void reportClicked() {
		startNewActivity(new Intent(DashBoardActivity.this,ReportOptionsActivity.class));
	}
	private void balanceTrasferClecked() {
		startNewActivity(new Intent(DashBoardActivity.this,WalletTransferActivity.class));
	}
	private void openRechargeActivity() {
		if(rechargeService.equals(whichRecharge.Mobile)){
			MyApplication.selectedRecharge="Mobile";
			startNewActivity(new Intent(DashBoardActivity.this,OperatorListtActivity.class));
		}
		else if(rechargeService.equals(whichRecharge.DTH)){
			MyApplication.selectedRecharge="dth";
			startNewActivity(new Intent(DashBoardActivity.this,OperatorListtActivity.class));
		}

		else if(rechargeService.equals(whichRecharge.Postpaid)){
			MyApplication.selectedRecharge="postpaid";
			startNewActivity(new Intent(DashBoardActivity.this,OperatorListtActivity.class));
		}

		else if(rechargeService.equals(whichRecharge.Utility)){
			MyApplication.selectedRecharge="utility";
			startNewActivity(new Intent(DashBoardActivity.this,OperatorListtActivity.class));
		}

	}

}
