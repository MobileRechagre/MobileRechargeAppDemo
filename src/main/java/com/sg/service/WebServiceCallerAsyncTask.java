package com.sg.service;

import java.util.Map;

import org.json.JSONObject;

import android.os.AsyncTask;

import com.sg.Activity.ParentActivity;
import com.sg.helper.ProgressController;


public class WebServiceCallerAsyncTask extends AsyncTask<Void, Void, Object>{


	private String tag = "WebServiceCallerAsyncTask";
	private ParentActivity activity;
	private String url;
	private String methodName; 
	private Map<String, String> request;
	public WebServiceCallerAsyncTask(ParentActivity activity){
		this.activity = activity;
	}

	@Override
	protected Object doInBackground(Void... params) {
		return new RequsetPrcoessor(methodName, url).processRequset(request);
	}

	@Override
	protected void onPostExecute(Object result) {
		super.onPostExecute(result);
		if (methodName != null && methodName.equals(ServiceConfiguration.LOGIN_METHOD)){
			activity.sendResponseLogin(result);
		}
		else if (methodName != null && methodName.equals(ServiceConfiguration.BalanceEnquery_METHOD)){
			activity.sendResponseCheckBalance(result);
		}
		else if (methodName != null && methodName.equals(ServiceConfiguration.BalanceTransaction_METHOD)){
			activity.sendResponseBalanceTransaction(result);
		}
		else if (methodName != null && methodName.equals(ServiceConfiguration.Recharge_METHOD)){
			activity.sendResponseRecharge(result);
		}
		else if (methodName != null && methodName.equals(ServiceConfiguration.ChangePasswod_METHOD)){
			activity.sendResponseChangPass(result);
		}
		else if (methodName != null && methodName.equals(ServiceConfiguration.RechargeReport_METHOD)){
			activity.sendResponseRechargeReport(result);
		}
		else if (methodName != null && methodName.equals(ServiceConfiguration.TransactionReport_METHOD)){
			activity.sendResponseTransactionReport(result);
		}
		else if (methodName != null && methodName.equals(ServiceConfiguration.DTHBookingProcces_METHOD)){
			activity.sendResponseDthBooking(result);
		}
		else if (methodName != null && methodName.equals(ServiceConfiguration.SalesReport_METHOD)){
			activity.sendResponseSaleReport(result);
		}
		else if (methodName != null && methodName.equals(ServiceConfiguration.RefundReport_METHOD)){
			activity.sendResponseRefundReport(result);
		}
		else if (methodName != null && methodName.equals(ServiceConfiguration.RevertBalance_METHOD)){
			activity.sendResponseRevertBal(result);
		}
		else if (methodName != null && methodName.equals(ServiceConfiguration.RechargeReportdir_METHOD)){
			activity.sendResponseRechargeDIerReport(result);
		}
		else if (methodName != null && methodName.equals(ServiceConfiguration.Complaint_METHOD)){
			activity.sendResponseComplaint(result);
		}
		else if (methodName != null && methodName.equals(ServiceConfiguration.Complaintfile_METHOD)){
			activity.sendResponseComplaintFile(result);
		}
	}
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		ProgressController.showProgressDialog(activity);
	}


	public void startService(String url, Map<String, String> request, String methodName){
		this.url = url;
		this.request = request;
		this.methodName = methodName;
		execute();
	}
}
