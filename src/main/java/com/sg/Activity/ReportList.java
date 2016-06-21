package com.sg.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.ksoap2.serialization.SoapObject;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sg.Activity.ReportOptionsActivity.whichReport;
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
import com.sg.utility.Utils;
import com.sg.validator.Validator;

public class ReportList extends ParentActivity{
	private ListView listview;
	private EditText mobileno;
	private boolean isValidate=true;
	private String enterredNumber;
	whichReport requset;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userreportlistview);
		requset=(whichReport)getIntent().getSerializableExtra("ServiceName");
		setlayoutRef();
		if(requset==whichReport.sale||requset==whichReport.Statement||
				requset==whichReport.DRecharge||requset==whichReport.refund){
			goServer();
		}
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
		listview=(ListView)findViewById(R.id.rechargereport_list);
		mobileno=(EditText)findViewById(R.id.mobileno);
		ImageView submit=(ImageView)findViewById(R.id.ok);
		submit.setOnClickListener(new ButtonListener());
		if(requset==whichReport.sale||requset==whichReport.Statement||requset==whichReport.refund){
			mobileno.setVisibility(View.GONE);
			submit.setVisibility(View.GONE);
		}
	}
	private class ButtonListener implements View.OnClickListener{
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.back:
				backClicked();
				break;
			case R.id.ok:
				okClicked();
				break;
			default:
				break;
			}			
		}

	}
	private void okClicked() {
		enterredNumber=mobileno.getText().toString();
		requset=whichReport.Recharge;
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
		if(requset==whichReport.Recharge){
			Map<String,String> requset=RequsetManager.getIntance().rechargeReportRequset(Utils.getValueFromSharedPreferences(this,SPKeyConstants.SHARED_PREF_USERNAME), 
					enterredNumber);	
			WebServiceCallerAsyncTask servcie=new WebServiceCallerAsyncTask(this);
			servcie.startService(ServiceConfiguration.LOgin_Url,requset, ServiceConfiguration.RechargeReport_METHOD);

		}
		else if(requset==whichReport.DRecharge){
			Map<String,String> requset=RequsetManager.getIntance().saleReportRequset(Utils.getValueFromSharedPreferences(this,SPKeyConstants.SHARED_PREF_USERNAME));	
			WebServiceCallerAsyncTask servcie=new WebServiceCallerAsyncTask(this);
			servcie.startService(ServiceConfiguration.LOgin_Url,requset, ServiceConfiguration.RechargeReportdir_METHOD);
		}
		else if(requset==whichReport.Statement){
			Map<String,String> requset=RequsetManager.getIntance().saleReportRequset(Utils.getValueFromSharedPreferences(this,SPKeyConstants.SHARED_PREF_USERNAME));	
			WebServiceCallerAsyncTask servcie=new WebServiceCallerAsyncTask(this);
			servcie.startService(ServiceConfiguration.LOgin_Url,requset, ServiceConfiguration.TransactionReport_METHOD);
		}
		else if(requset==whichReport.sale){
			Map<String,String> requset=RequsetManager.getIntance().saleReportRequset(Utils.getValueFromSharedPreferences(this,SPKeyConstants.SHARED_PREF_USERNAME));	
			WebServiceCallerAsyncTask servcie=new WebServiceCallerAsyncTask(this);
			servcie.startService(ServiceConfiguration.LOgin_Url,requset, ServiceConfiguration.SalesReport_METHOD);
		}
		else if(requset==whichReport.refund){
			Map<String,String> requset=RequsetManager.getIntance().saleReportRequset(Utils.getValueFromSharedPreferences(this,SPKeyConstants.SHARED_PREF_USERNAME));	
			WebServiceCallerAsyncTask servcie=new WebServiceCallerAsyncTask(this);
			servcie.startService(ServiceConfiguration.LOgin_Url,requset, ServiceConfiguration.RefundReport_METHOD);
		}
	}
	public void sendResponseRefundReport(Object response){
		ProgressController.dismissProgressDialog();
		if(response instanceof String){
			AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,""+response,this);
		}
		else if(response instanceof SoapObject){
			ArrayList<HashMap<String,String>> reportList=new ReponseParser().refundReportResponse((SoapObject)response);
			if(reportList!=null && reportList.size()>0){
				listview.setAdapter(new Last10ReportLazyAdapter(this,reportList));
			}
			else{
				AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,"Server Problem",this);
			}
		}
	}
	public void sendResponseRechargeReport(Object response){
		ProgressController.dismissProgressDialog();
		if(response instanceof String){
			AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,""+response,this);
		}
		else if(response instanceof SoapObject){
			ArrayList<HashMap<String,String>> reportList=new ReponseParser().rechargeReportResponse((SoapObject)response);
			if(reportList!=null && reportList.size()>0){
				listview.setAdapter(new Last10ReportLazyAdapter(this,reportList));
			}
			else{
				AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,"Server Problem",this);
			}
		}
	}
	public void sendResponseRechargeDIerReport(Object response){
		ProgressController.dismissProgressDialog();
		if(response instanceof String){
			AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,""+response,this);
		}
		else if(response instanceof SoapObject){
			ArrayList<HashMap<String,String>> reportList=new ReponseParser().rechargeReportDireResponse((SoapObject)response);
			if(reportList!=null && reportList.size()>0){
				listview.setAdapter(new Last10ReportLazyAdapter(this,reportList));
			}
			else{
				AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,"Server Problem",this);
			}
		}
	}
	public void sendResponseSaleReport(Object response){
		ProgressController.dismissProgressDialog();
		if(response instanceof String){
			AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,""+response,this);
		}
		else if(response instanceof SoapObject){
			ArrayList<HashMap<String,String>> reportList=new ReponseParser().saleReportResponse((SoapObject)response);
			if(reportList!=null && reportList.size()>0){
				listview.setAdapter(new Last10ReportLazyAdapter(this,reportList));
			}
			else{
				AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,"Server Problem",this);
			}
		}
	}
	public void sendResponseTransactionReport(Object response){
		ProgressController.dismissProgressDialog();
		if(response instanceof String){
			AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,""+response,this);
		}
		else if(response instanceof SoapObject){
			ArrayList<HashMap<String,String>> reportList=new ReponseParser().transactionReportResponse((SoapObject)response);
			if(reportList!=null && reportList.size()>0){
				listview.setAdapter(new Last10ReportLazyAdapter(this,reportList));
			}
			else{
				AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,"Server Problem",this);
			}
		}
	}
}
