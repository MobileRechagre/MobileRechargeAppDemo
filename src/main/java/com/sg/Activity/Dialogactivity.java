package com.sg.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.sg.constants.Messages;
import com.sg.mobilerecharge.R;

public class Dialogactivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog);
		TextView mesage=(TextView)findViewById(R.id.textItem);
		Button ok=(Button)findViewById(R.id.ok);
		if(getIntent().getStringExtra(Messages.MSG_KEY)!=null){
			mesage.setText(getIntent().getStringExtra(Messages.MSG_KEY));
		}
		ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();				
			}
		});
	}
}
