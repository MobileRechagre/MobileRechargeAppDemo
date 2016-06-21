package com.sg.Activity;

import java.util.Map;

import org.ksoap2.serialization.SoapObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sg.RechargeManger.Posts;
import com.sg.constants.Messages;
import com.sg.constants.SPKeyConstants;
import com.sg.helper.AlertLevel;
import com.sg.helper.ProgressController;
import com.sg.localBroadcast.MyLocalBroadcast;
import com.sg.mobilerecharge.R;
import com.sg.requsetCreator.RequsetManager;
import com.sg.service.ReponseParser;
import com.sg.service.ServiceConfiguration;
import com.sg.service.WebServiceCallerAsyncTask;
import com.sg.utility.Utils;
import com.sg.validator.Validator;

public class LoginActivity extends ParentActivity{
	private boolean isValidate=true;
	private Button submit;
	private EditText userid,pass;
	private String enterredPass,enterredUserId;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		setlayoutRef();
	}
	public void setlayoutRef(){
		submit=(Button)findViewById(R.id.ok);
		userid=(EditText)findViewById(R.id.useridET);
		pass=(EditText)findViewById(R.id.passET);
		submit.setOnClickListener(new ButtonListener());
	}
	private class ButtonListener implements View.OnClickListener{
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.ok:
				submit();
				break;

			default:
				break;
			}			
		}

	}
	public void submit(){
		enterredUserId=userid.getText().toString();
		enterredPass=pass.getText().toString();
		if(Utils.isNetworkAvailable(this)){
			if(Validator.isEmptyText(enterredUserId)){
				isValidate=false;
				AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,Messages.invalidUser,this);
			}
			else if(Validator.isEmptyText(enterredPass)){
				isValidate=false;
				AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,Messages.invalidpass,this);
			}
			else{
				isValidate=true;
			}
			if(isValidate){
				validateFromServer();
			}
		}
		else{
			AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,Messages.networkproblem,this);
		}
	}
	private void validateFromServer() {
		Map<String,String> loginrequset=RequsetManager.getIntance().loginRequset(enterredUserId, enterredPass);	
		WebServiceCallerAsyncTask servcie=new WebServiceCallerAsyncTask(this);
		servcie.startService(ServiceConfiguration.LOgin_Url, loginrequset, ServiceConfiguration.LOGIN_METHOD);
	}
	public void sendResponseLogin(Object response){
		ProgressController.dismissProgressDialog();
		if(response instanceof String){
			AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,""+response,this);
		}
		else if(response instanceof SoapObject){
			String status = null,uName = null;
			String mstatus=new ReponseParser().loginResponse((SoapObject)response);
			if(mstatus!=null){
				if(mstatus!=null && mstatus.contains(",")){
					String username[]=mstatus.split(",");
					 status=username[0];
					 uName=username[1];
				}
				storeLocally(status,uName);
				if(Posts.RT.equalsIgnoreCase(status)||
						Posts.DI.equalsIgnoreCase(status)||
						Posts.FOS.equalsIgnoreCase(status)||
						Posts.SR.equalsIgnoreCase(status)||
						Posts.Admin.equalsIgnoreCase(status)){
					openDashBoard();
				}
				else if(Posts.SUBADMIN.equalsIgnoreCase(status)){
					//locked
				}
				else{
					AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,""+mstatus,this);	
				}
			}
		}
	}
	private void openDashBoard() {
		startActivity(new Intent(this,DashBoardActivity.class));		
	}
	private void storeLocally(String status,String name) {
		Utils.saveOrUpdateValueInSharedPreferences(this, SPKeyConstants.SHARED_PREF_USERNAME, enterredUserId);		
		Utils.saveOrUpdateValueInSharedPreferences(this, SPKeyConstants.SHARED_PREF_POSTS, status);		
		Utils.saveOrUpdateValueInSharedPreferences(this, SPKeyConstants.SHARED_PREF_UNAME, name);		
		
	}
}
