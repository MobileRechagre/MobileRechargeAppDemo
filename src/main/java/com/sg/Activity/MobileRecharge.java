package com.sg.Activity;

import java.util.Map;

import org.ksoap2.serialization.SoapObject;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sg.constants.Messages;
import com.sg.constants.SPKeyConstants;
import com.sg.helper.AlertLevel;
import com.sg.helper.Net_Recharge_Constants;
import com.sg.helper.ProgressController;
import com.sg.mobilerecharge.R;
import com.sg.requsetCreator.RequsetManager;
import com.sg.service.ReponseParser;
import com.sg.service.ServiceConfiguration;
import com.sg.service.WebServiceCallerAsyncTask;
import com.sg.utility.Utils;
import com.sg.validator.Validator;

public class MobileRecharge extends ParentActivity{
	private boolean isValidate=true;
	private Button transfer;
	private EditText amount,monumber,account;
	private String enterredAmount,enterredNumber,opname,opcode,URL,enterredAccount;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mobilerecharge);
		opname=getIntent().getStringExtra("OP_Name");
		opcode=getIntent().getStringExtra("OP_CODE");
		URL=getIntent().getStringExtra("URL");
		setlayoutRef();
		setImage();
	}

	private void setImage() {
		ImageView logo=(ImageView)findViewById(R.id.relogo);
		Uri uri = Uri.parse("android.resource://"+Net_Recharge_Constants.project_activitys+"/drawable/"+URL);
		logo.setImageURI(uri);
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
		TextView opnametv=(TextView)findViewById(R.id.opname);
		opnametv.setText(opname);
		amount=(EditText)findViewById(R.id.rs);
		monumber=(EditText)findViewById(R.id.mobileno);
		transfer=(Button)findViewById(R.id.ok);
		transfer.setOnClickListener(new ButtonListener());
		account=(EditText)findViewById(R.id.account);
		if(MyApplication.selectedRecharge.equalsIgnoreCase("utility")){
			//4 fields
			if(opcode.equalsIgnoreCase("re")){
				account.setVisibility(View.VISIBLE);
				monumber.setHint("Phone Number(With STD Code)");
				account.setHint("Customer No");
			}
			else if(opcode.equalsIgnoreCase("ml")){
				account.setVisibility(View.VISIBLE);
				monumber.setHint("Phone Number(With STD Code)");
				account.setHint("Customer Account No(10digits)");
			}
			else{
				account.setVisibility(View.GONE);
				if(opcode.equalsIgnoreCase("ab")||opcode.equalsIgnoreCase("al")||
						opcode.equalsIgnoreCase("tb")){
					monumber.setHint("Phone Number(With STD Code");
				}
				else if(opcode.equalsIgnoreCase("br")||opcode.equalsIgnoreCase("by")){
					monumber.setHint("Customer Account No(9-10 digits)");
				}
				else if(opcode.equalsIgnoreCase("mg")){
					monumber.setHint("Customer Account No");
				}
				else if(opcode.equalsIgnoreCase("nd")){
					monumber.setHint("Customer Account No(11-12 digits)");
				}
			}

		}

	}
	private class ButtonListener implements View.OnClickListener{
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.ok:
				submit();
				break;
			case R.id.back:
				backClicked();
				break;
			default:
				break;
			}			
		}

	}
	public void submit(){
		if(account.getVisibility()==View.VISIBLE){
			enterredAmount=amount.getText().toString();
			enterredNumber=monumber.getText().toString();
			enterredAccount=account.getText().toString();
			if(Utils.isNetworkAvailable(this)){
				if(Validator.isEmptyText(enterredNumber)){
					isValidate=false;
					AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,Messages.invalidnum,this);
				}
				else if(Validator.isEmptyText(enterredAmount)){
					isValidate=false;
					AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,Messages.invalidamount,this);
				}
				else{
					isValidate=true;
				}
				if(isValidate){
					goServer();
				}
			}
			else{
				AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,Messages.networkproblem,this);
			}
		}
		else{
			enterredAmount=amount.getText().toString();
			enterredNumber=monumber.getText().toString();
			if(Utils.isNetworkAvailable(this)){
				if(Validator.isEmptyText(enterredNumber)){
					isValidate=false;
					AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,Messages.invalidnum,this);
				}
				else if(Validator.isEmptyText(enterredAmount)){
					isValidate=false;
					AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,Messages.invalidamount,this);
				}
				else{
					isValidate=true;
				}
				if(isValidate){
					goServer();
				}
			}
			else{
				AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,Messages.networkproblem,this);
			}
		}
	}
	private void goServer() {
		if(account.getVisibility()==View.GONE){
			Map<String,String> requset=RequsetManager.getIntance().mobileRecharegRequset(Utils.getValueFromSharedPreferences(this,SPKeyConstants.SHARED_PREF_USERNAME), 
					enterredNumber, enterredAmount,opcode,"");	
			WebServiceCallerAsyncTask servcie=new WebServiceCallerAsyncTask(this);
			servcie.startService(ServiceConfiguration.Recharge_Url,requset, ServiceConfiguration.Recharge_METHOD);
		}
		else{
			Map<String,String> requset=RequsetManager.getIntance().mobileRecharegRequset(Utils.getValueFromSharedPreferences(this,SPKeyConstants.SHARED_PREF_USERNAME), 
					enterredNumber, enterredAmount,opcode,enterredAccount);	
			WebServiceCallerAsyncTask servcie=new WebServiceCallerAsyncTask(this);
			servcie.startService(ServiceConfiguration.Recharge_Url,requset, ServiceConfiguration.Recharge_METHOD);

		}
	}
	public void sendResponseRecharge(Object response){
		ProgressController.dismissProgressDialog();
		if(response instanceof String){
			AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,""+response,this);
		}
		else if(response instanceof SoapObject){
			String status=new ReponseParser().rechargeResponse((SoapObject)response);
			if(status!=null){
				AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,""+status,this);
			}
			else{
				AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,"Server Problem",this);
			}
		}
	}
}
