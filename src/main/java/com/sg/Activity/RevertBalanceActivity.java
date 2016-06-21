package com.sg.Activity;

import java.util.Map;

import org.ksoap2.serialization.SoapObject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sg.constants.Messages;
import com.sg.constants.SPKeyConstants;
import com.sg.helper.AlertLevel;
import com.sg.helper.ProgressController;
import com.sg.mobilerecharge.R;
import com.sg.requsetCreator.RequsetManager;
import com.sg.service.ReponseParser;
import com.sg.service.ServiceConfiguration;
import com.sg.service.WebServiceCallerAsyncTask;
import com.sg.utility.Utils;
import com.sg.validator.Validator;

public class RevertBalanceActivity extends ParentActivity{
	private boolean isValidate=true;
	private Button transfer;
	private EditText amount,monumber;
	private String enterredAmount,enterredNumber;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.revert_balance);
		setlayoutRef();
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
		//oen ids
		amount=(EditText)findViewById(R.id.amountedittext);
		monumber=(EditText)findViewById(R.id.Mobilenoedittext);
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
		enterredAmount=amount.getText().toString();
		enterredNumber=monumber.getText().toString();
		if(Utils.isNetworkAvailable(this)){
			 if(Validator.isEmptyText(enterredNumber)){
				isValidate=false;
				AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,Messages.invalidnum,this);
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
		Map<String,String> requset=RequsetManager.getIntance().revertBalanceRequset(Utils.getValueFromSharedPreferences(this,SPKeyConstants.SHARED_PREF_USERNAME), 
				enterredNumber);	
		WebServiceCallerAsyncTask servcie=new WebServiceCallerAsyncTask(this);
		servcie.startService(ServiceConfiguration.LOgin_Url,requset, ServiceConfiguration.RevertBalance_METHOD);
	}
	public void sendResponseRevertBal(Object response){
		ProgressController.dismissProgressDialog();
		if(response instanceof String){
			AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,""+response,this);
		}
		else if(response instanceof SoapObject){
			String status=new ReponseParser().revertBalanceResponse((SoapObject)response);
			AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,""+status,this);	
		}
	}
}
