package com.sg.Activity;

import java.util.Map;

import org.ksoap2.serialization.SoapObject;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sg.constants.Messages;
import com.sg.constants.SPKeyConstants;
import com.sg.helper.AlertLevel;
import com.sg.helper.Net_Recharge_Constants;
import com.sg.helper.ProgressController;
import com.sg.mobilerecharge.R;
import com.sg.operator.GetListMobileOperator;
import com.sg.requsetCreator.RequsetManager;
import com.sg.service.ReponseParser;
import com.sg.service.ServiceConfiguration;
import com.sg.service.WebServiceCallerAsyncTask;
import com.sg.utility.Utils;
import com.sg.validator.Validator;

public class ChangePassword extends ParentActivity{
	private boolean isValidate=true;
	private Button transfer;
	private EditText oldpassword,newpassword,confirmpassword;
	private String moldpassword,mnewpassword,mconfirmpassword;;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.changepassword);
		setlayoutRef();
	}

	

	public void setlayoutRef(){
		//set header
		TextView back=(TextView)findViewById(R.id.back);
		back.setVisibility(View.VISIBLE);
		back.setOnClickListener(new ButtonListener());
		ImageView drw=(ImageView)findViewById(R.id.drawer);
		drw.setVisibility(View.GONE);
		ImageView logo=(ImageView)findViewById(R.id.logo);
		logo.setVisibility(View.GONE);
		TextView label=(TextView)findViewById(R.id.label);
		label.setVisibility(View.GONE);
		oldpassword=(EditText)findViewById(R.id.oldpassword);
		newpassword=(EditText)findViewById(R.id.newpassword);
		confirmpassword=(EditText)findViewById(R.id.confirmpassword);
		transfer=(Button)findViewById(R.id.ok);
		transfer.setOnClickListener(new ButtonListener());

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
		moldpassword=oldpassword.getText().toString();
		mnewpassword=newpassword.getText().toString();
		mconfirmpassword=confirmpassword.getText().toString();
		if(Utils.isNetworkAvailable(this)){
			if(Validator.isEmptyText(moldpassword)){
				isValidate=false;
				AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,Messages.invalidOldPassword,this);
			}
			else if(Validator.isEmptyText(mnewpassword)){
				isValidate=false;
				AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,Messages.invalidnewpassword,this);
			}
			else if(Validator.isEmptyText(mconfirmpassword)){
				isValidate=false;
				AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,Messages.invalidConfirmPassword,this);
			}
			else if(!newpassword.getText().toString().equals(confirmpassword.getText().toString())){
				isValidate=false;
				Toast.makeText(this,"New password and old password is mismatch",Toast.LENGTH_SHORT).show();	
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
	private void goServer() {
		Map<String,String> requset=RequsetManager.getIntance().changePassRequset(Utils.getValueFromSharedPreferences(this,SPKeyConstants.SHARED_PREF_USERNAME), 
				mnewpassword,moldpassword);	
		WebServiceCallerAsyncTask servcie=new WebServiceCallerAsyncTask(this);
		servcie.startService(ServiceConfiguration.LOgin_Url,requset, ServiceConfiguration.ChangePasswod_METHOD);
	}
	public void sendResponseChangPass(Object response){
		ProgressController.dismissProgressDialog();
		if(response instanceof String){
			AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,""+response,this);
		}
		else if(response instanceof SoapObject){
			String status=new ReponseParser().changePassResponse((SoapObject)response);
			if(status!=null){
				AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,""+status,this);
			}
			else{
				AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,"Server Problem",this);
			}
		}
	}
}
