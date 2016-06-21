package com.sg.Activity;

import java.util.Map;

import org.ksoap2.serialization.SoapObject;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sg.constants.Messages;
import com.sg.constants.SPKeyConstants;
import com.sg.helper.AlertLevel;
import com.sg.helper.Net_Recharge_Constants;
import com.sg.helper.ProgressController;
import com.sg.mobilerecharge.R;
import com.sg.operator.GetListMobileOperator;
import com.sg.requsetCreator.RequsetManager;
import com.sg.service.ReponseParser;
import com.sg.service.ServiceConfiguration;
import com.sg.service.WebServiceCallerAsyncTask;
import com.sg.utility.Utils;
import com.sg.validator.Validator;

public class DthBooking extends ParentActivity{
	private boolean isValidate=true;
	private Button transfer;
	private EditText amount,monumber;
	private String enterredAmount,enterredNumber,opname,opcode,URL;
	private TextView productCode,packCode,address,name,number,custo_id;
	private String mproductCode,mpackCode,maddress,mname,mnumber,mcusto_id;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dth_booking);
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
		productCode=(TextView)findViewById(R.id.productCode);
		packCode=(TextView)findViewById(R.id.packCode);
		address=(TextView)findViewById(R.id.address);
		name=(TextView)findViewById(R.id.name);
		number=(TextView)findViewById(R.id.number);
		custo_id=(TextView)findViewById(R.id.custo_id);
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
		mproductCode=productCode.getText().toString();
		mpackCode=packCode.getText().toString();
		maddress=address.getText().toString();
		mname=name.getText().toString();
		mnumber=number.getText().toString();
		mcusto_id=custo_id.getText().toString();

		if(Utils.isNetworkAvailable(this)){
			if(Validator.isEmptyText(mproductCode)){
				isValidate=false;
				AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,Messages.invalidFields,this);
			}
			else if(Validator.isEmptyText(mpackCode)){
				isValidate=false;
				AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,Messages.invalidFields,this);
			}
			else if(Validator.isEmptyText(maddress)){
				isValidate=false;
				AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,Messages.invalidFields,this);
			}
			else if(Validator.isEmptyText(mname)){
				isValidate=false;
				AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,Messages.invalidFields,this);
			}
			else if(Validator.isEmptyText(mnumber)){
				isValidate=false;
				AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,Messages.invalidFields,this);
			}
			else if(Validator.isEmptyText(mcusto_id)){
				isValidate=false;
				AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,Messages.invalidFields,this);
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
		Map<String,String> requset=RequsetManager.getIntance().dthBookingRequset(Utils.getValueFromSharedPreferences(this,SPKeyConstants.SHARED_PREF_USERNAME),
				mproductCode, mpackCode, maddress, mcusto_id, mname, mnumber);	
		WebServiceCallerAsyncTask servcie=new WebServiceCallerAsyncTask(this);
		servcie.startService(ServiceConfiguration.Recharge_Url,requset, ServiceConfiguration.DTHBookingProcces_METHOD);
	}
	public void sendResponseDthBooking(Object response){
		ProgressController.dismissProgressDialog();
		if(response instanceof String){
			AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,""+response,this);
		}
		else if(response instanceof SoapObject){
			String status=new ReponseParser().busbookingResponse((SoapObject)response);
			if(status!=null){
				AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,""+status,this);
			}
			else{
				AlertLevel.getInstance().fireAlert(AlertLevel.alertType.SimpleAlert,"Server Problem",this);
			}
		}
	}
}
