package com.sg.localBroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.sg.Activity.Dialogactivity;
import com.sg.constants.Messages;
import com.sg.utility.Utils;

public class MyLocalBroadcast extends BroadcastReceiver{
	
	
	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent != null) {
			String message = intent.getStringExtra(Messages.MSG_KEY);
			context.startActivity(new Intent(context,Dialogactivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
		}
	}
}
