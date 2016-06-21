package com.sg.helper;

import android.content.Context;
import android.content.Intent;

import com.sg.constants.Messages;
import com.sg.localBroadcast.MyLocalBroadcast;
import com.sg.utility.Utils;

public class AlertLevel {
	public static enum alertType{
		Broadcast,
		SimpleAlert
	}
	 alertType mAlertType;
	 static AlertLevel alert;
	 private AlertLevel(){
		 
	 }
	 public static AlertLevel getInstance(){
		 if(alert==null){
			alert=new AlertLevel(); 
		 }
		 return alert;
	 }
	public void fireAlert(alertType alert,String message,Context ctx){
		if(alertType.Broadcast==alert){
			sendBrodcast(ctx, message);
		}
		else if(alertType.SimpleAlert==alert){
			Utils.showDialogWithOkButton(ctx, message);
		}
	}
	public  void sendBrodcast(Context ctx,String message){
		Intent intent = new Intent(ctx, MyLocalBroadcast.class);
		intent.setAction("com.sg.localBroadcast.MyLocalBroadcast");
		intent.putExtra(Messages.MSG_KEY, message);
		ctx.sendBroadcast(intent); 

	}

}
