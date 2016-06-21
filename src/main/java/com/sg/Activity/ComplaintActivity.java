package com.sg.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.ksoap2.serialization.SoapObject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sg.adapter.Last10ReportLazyAdapter;
import com.sg.constants.Messages;
import com.sg.constants.SPKeyConstants;
import com.sg.helper.AlertLevel;
import com.sg.helper.ProgressController;
import com.sg.mobilerecharge.R;
import com.sg.requsetCreator.RequsetManager;
import com.sg.service.ReponseParser;
import com.sg.service.ServiceConfiguration;
import com.sg.service.WebServiceCallerAsyncTask;
import com.sg.utility.ConfirmDialog;
import com.sg.utility.Utils;
import com.sg.validator.Validator;

public class ComplaintActivity extends ParentActivity{
	private boolean isValidate=true;
	private Button transfer;
	private EditText amount,monumber;
	private String enterredAmount,enterredNumber;
	private ListView listview;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.complint);
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
		monumber=(EditText)findViewById(R.id.Mobilenoedittext);
		monumber.setHint("User Id");
		transfer=(Button)findViewById(R.id.ok);
		transfer.setText("Submit");
		transfer.setOnClickListener(new ButtonListener());
		listview=(ListView)findViewById(R.id.rechargereport_list);
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
	public void showRemarkDialog(){
		new ConfirmDialog(this).RenameFile(ComplaintActivity.this);
	}
	public void submit(){
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
		Map<String,String> requset=RequsetManager.getIntance().complaintRequset(Utils.getValueFromSharedPreferences(this,SPKeyConstants.SHARED_PREF_USERNAME), 
				enterredNumber);	
		WebServiceCallerAsyncTask servcie=new WebServiceCallerAsyncTask(this);
		servcie.startService(ServiceConfiguration.LOgin_Url,requset, ServiceConfiguration.Complaint_METHOD);
	}
	private void goServerForComplaintFile(String remark) {
		Map<String,String> requset=RequsetManager.getIntance().complaintFileRequset(Utils.getValueFromSharedPreferences(this,SPKeyConstants.SHARED_PREF_USERNAME), 
				enterredNumber,remark);	
		WebServiceCallerAsyncTask servcie=new WebServiceCallerAsyncTask(this);
		servcie.startService(ServiceConfiguration.LOgin_Url,requset, ServiceConfiguration.Complaintfile_METHOD);
	}
	public void sendResponseComplaintFile(Object response){
		ProgressController.dismissProgressDialog();
		if(response instanceof String){
			AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,""+response,this);
		}
		else if(response instanceof SoapObject){
			String status=new ReponseParser().complaintFileResponse((SoapObject)response);
			if(status!=null){
				AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,""+status,this);
			}
			else{
				AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,"Server Problem",this);
			}
		}
	}
	public void setRemark(String remark){
		goServerForComplaintFile(remark);
	}
	public void sendResponseComplaint(Object response){
		ProgressController.dismissProgressDialog();
		if(response instanceof String){
			AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,""+response,this);
		}
		else if(response instanceof SoapObject){
			ArrayList<HashMap<String,String>> reportList=new ReponseParser().complaintResponse((SoapObject)response);
			if(reportList!=null && reportList.size()>0){
				listview.setAdapter(new Last10ReportLazyAdapter(this,reportList));
			}
			else{
				AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,"Server Problem",this);
			}
		}
	}
}
