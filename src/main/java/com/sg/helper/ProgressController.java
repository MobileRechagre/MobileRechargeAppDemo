package com.sg.helper;

import com.sg.mobilerecharge.R;

import android.app.Activity;
import android.app.ProgressDialog;

public class ProgressController {

	private static ProgressDialog progressDialog;
	
	public static void showProgressDialog(Activity activity){
		progressDialog = new ProgressDialog(activity);
		progressDialog.setMessage(activity.getResources().getString(R.string.PleaseWait));
		progressDialog.setCancelable(false);
		progressDialog.setIndeterminate(true);
		if (!progressDialog.isShowing()){
			progressDialog.show();	
		}
	}
	
	public static void dismissProgressDialog(){
		if (progressDialog != null && progressDialog.isShowing()){
			progressDialog.dismiss();
		}
	}
}
