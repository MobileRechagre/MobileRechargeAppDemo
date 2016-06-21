package com.sg.service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.sg.helper.Net_Recharge_Constants;

public class RequsetPrcoessor {
	public String methodName;
	public String soapAction;
	public String url;
	RequsetPrcoessor(String methodName,String url){
		this.methodName=methodName;
		this.soapAction=ServiceConfiguration.NAMESPACE+methodName;
		this.url=url;
	}
	public Object processRequset(Map<String,String> requestProperties){
		SoapObject newob = null;
		Object response;
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.encodingStyle = SoapEnvelope.ENC;
		SoapObject Request = new SoapObject(ServiceConfiguration.NAMESPACE,methodName);
		if (requestProperties != null) {
			for (Map.Entry<String, String> requestProperty : requestProperties.entrySet()) {
				Request.addProperty(requestProperty.getKey(), requestProperty.getValue());
			}
		}
		envelope.dotNet = true;
		envelope.setOutputSoapObject(Request);
		AndroidHttpTransport httptransport ;
		httptransport = new AndroidHttpTransport(url);
		httptransport.debug=true;
		try {
			httptransport.call(soapAction,envelope);
			newob = (SoapObject)envelope.bodyIn;
			Log.d("response",newob+"");
		}
		catch (IOException e) {
			return "I/O Exception";
		}
		catch (XmlPullParserException e) {
			return "XmlPullParserException Exception";
		}
		catch (Exception e) {
			return "Server Exception";
		}
		return newob;
	}
}
