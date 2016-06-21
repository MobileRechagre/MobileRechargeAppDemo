package com.sg.utility;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sg.Activity.ComplaintActivity;
import com.sg.Activity.ParentActivity;
import com.sg.mobilerecharge.R;

public  class ConfirmDialog{
	private ParentActivity activity;
	private static AlertDialog alertDialog;
	
	public ConfirmDialog(ParentActivity activity) {
		this.activity = activity;
	}



	public void showConfirmDialog(String message){
		AlertDialog.Builder alertDialogBuilder =Utils.getAlertDialogBuilder(activity, message);
		alertDialogBuilder.setCancelable(false).setNegativeButton(
				"Cancel", new ButtonListener());	
		// OK
		alertDialogBuilder.setPositiveButton(activity.getString(R.string.OK_ButtonText), new ButtonListener());
		alertDialog = alertDialogBuilder.create();
		alertDialog.show();	



	}
	public void showConfirmDialogwithHeader(String header, String message){
		AlertDialog.Builder alertDialogBuilder =Utils.getAlertDialogBuilder(activity, message);
		alertDialogBuilder.setCancelable(false).setNegativeButton(
				"Cancel", new ButtonListener());	
		TextView title = new TextView(activity);
		title.setText(header);
		title.setGravity(Gravity.CENTER);
		title.setTextColor(Color.parseColor("#ff0099cc"));
		title.setTextSize(22);
		alertDialogBuilder.setCustomTitle(title);
		// OK
		alertDialogBuilder.setPositiveButton(activity.getString(R.string.OK_ButtonText), new ButtonListener());
		alertDialog = alertDialogBuilder.create();
		alertDialog.show();	



	}
	public static void cancelConfirm(){
		if(alertDialog!=null)
			alertDialog.dismiss();
	}
	public void RenameFile(final ComplaintActivity activity) {
		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(activity);
		alertBuilder.setCancelable(false);
		//alertBuilder.setTitle("File Name");
		LayoutInflater inflater = activity.getLayoutInflater();
		View dialoglayout = inflater.inflate(R.layout.filedialog, null);
		alertBuilder.setView(dialoglayout);
		final AlertDialog fileNameDialog = alertBuilder.create();
		fileNameDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		fileNameDialog.show();
		final EditText fileNameEt=(EditText)dialoglayout.findViewById(R.id.fileNameet);
		Button mOk=(Button)dialoglayout.findViewById(R.id.ok);
		Button mCancel=(Button)dialoglayout.findViewById(R.id.cancel);
		mCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				fileNameDialog.cancel();
			}
		});
		mOk.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String fileName=fileNameEt.getText().toString();
				activity.setRemark(fileName);
			}
		});

	}

	private class ButtonListener implements DialogInterface.OnClickListener{

		@Override
		public void onClick(DialogInterface dialog, int which) {
			if(dialog.BUTTON_NEGATIVE==which){
				activity.commandNo();	
			}
			else if(dialog.BUTTON_POSITIVE==which){
				activity.commandYes();
			}
		}
	}
	
	
	
}
