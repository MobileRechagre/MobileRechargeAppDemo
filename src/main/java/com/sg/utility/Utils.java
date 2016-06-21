package com.sg.utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sg.constants.SPKeyConstants;
import com.sg.mobilerecharge.R;

public class Utils {
	private static String tag = "Utils";

	public static void showToast(Activity activity, String message){
		Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
	}
	public static boolean isNetworkAvailable(Context context){
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		return (networkInfo != null && networkInfo.isConnected() && networkInfo.isAvailable());
	}

	public static void saveOrUpdateValueInSharedPreferences(Context context, String key, String value){
		SharedPreferences sp = context.getSharedPreferences(SPKeyConstants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
		sp.edit().putString(key, value).commit();
	}

	public static String getValueFromSharedPreferences(Context context, String key){
		SharedPreferences sp = context.getSharedPreferences(SPKeyConstants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
		return sp.getString(key, null);
	}

	public static void showDialogWithOkButton(Context activity, String message){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
		TextView messageTextView = new TextView(activity);
		messageTextView.setTextSize(18);
		messageTextView.setText(message);
		messageTextView.setGravity(Gravity.CENTER);
		alertDialogBuilder.setView(messageTextView).setCancelable(false).setNeutralButton(
				activity.getString(R.string.OK_ButtonText), new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}

		public static void hideSoftInput(Activity activity) {
		activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE); 
		inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	}



		public static AlertDialog.Builder getAlertDialogBuilder(Activity activity, String message) {
		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(activity);
		alertBuilder.setCancelable(false);

		TextView messageTextView = new TextView(activity);
		messageTextView.setTextSize(18);
		messageTextView.setText(message);
		messageTextView.setGravity(Gravity.CENTER);

		alertBuilder.setView(messageTextView);

		return alertBuilder;
	}

	
	public static void showAlertDialog(Activity activity, String message, final EditText et){
		AlertDialog.Builder alertBuilder = Utils.getAlertDialogBuilder(activity, message);
		alertBuilder.setPositiveButton(activity.getString(R.string.OK_ButtonText), new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				et.requestFocus();
			}
		});
		alertBuilder.show();
	}

	public static void showAlertDialog(final Activity activity,String message){
		AlertDialog.Builder alertBuilder = Utils.getAlertDialogBuilder(activity, message);
		alertBuilder.setPositiveButton(activity.getString(R.string.OK_ButtonText), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				activity.finish();
			}
		});
		alertBuilder.create().show();
	}

	
}