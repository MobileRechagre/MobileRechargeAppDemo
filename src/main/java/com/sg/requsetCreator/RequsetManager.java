package com.sg.requsetCreator;

import java.util.HashMap;
import java.util.Map;

import com.sg.service.ServiceConfiguration;


public class RequsetManager {
	static RequsetManager requsetManger;
	private RequsetManager(){

	}
	public static RequsetManager getIntance(){
		if(requsetManger==null){
			requsetManger=new RequsetManager();
		}
		return requsetManger;
	}
	public Map<String,String> loginRequset(String userName,String pass){
		Map<String,String> requset=new HashMap<String, String>();
		requset.put(ServiceConfiguration.USERNAMW, userName);
		requset.put(ServiceConfiguration.Password, pass);
		return requset;
	}
	
	public Map<String,String> balanceRequset(String userName){
		Map<String,String> requset=new HashMap<String, String>();
		requset.put(ServiceConfiguration.USERNAMW, userName);
		return requset;
	}
	public Map<String,String> balanceTransferRequset(String userName,String num,String amount){
		Map<String,String> requset=new HashMap<String, String>();
		requset.put(ServiceConfiguration.ReciverMobileNo, num);
		requset.put(ServiceConfiguration.LoginId, userName);
		requset.put(ServiceConfiguration.Amount, amount);
		return requset;
	}
	public Map<String,String> complaintRequset(String userName,String uid){
		Map<String,String> requset=new HashMap<String, String>();
		requset.put(ServiceConfiguration.UID, uid);
		requset.put(ServiceConfiguration.LoginId, userName);
		return requset;
	}
	public Map<String,String> complaintFileRequset(String userName,String uid,String remark){
		Map<String,String> requset=new HashMap<String, String>();
		requset.put(ServiceConfiguration.UID, uid);
		requset.put(ServiceConfiguration.Remark, remark);
		requset.put(ServiceConfiguration.LoginId, userName);
		return requset;
	}
	public Map<String,String> revertBalanceRequset(String userName,String num){
		Map<String,String> requset=new HashMap<String, String>();
		requset.put(ServiceConfiguration.TransactionNumber, num);
		requset.put(ServiceConfiguration.LoginId, userName);
		return requset;
	}
	public Map<String,String> mobileRecharegRequset(String userName,String num,String amount,String opertor,String account){
		Map<String,String> requset=new HashMap<String, String>();
		requset.put(ServiceConfiguration.MobileNo, num);
		requset.put(ServiceConfiguration.Operator, opertor);
		requset.put(ServiceConfiguration.LoginId, userName);
		requset.put(ServiceConfiguration.RechAmount, amount);
		requset.put(ServiceConfiguration.Account, account);
		return requset;
	}
	public Map<String,String> changePassRequset(String userName,String newpass,String oldpass){
		Map<String,String> requset=new HashMap<String, String>();
		requset.put(ServiceConfiguration.NewPassword, newpass);
		requset.put(ServiceConfiguration.LoginId, userName);
		requset.put(ServiceConfiguration.CurrentPassword, oldpass);
		return requset;
	}
	public Map<String,String> rechargeReportRequset(String userName,String mobileno){
		Map<String,String> requset=new HashMap<String, String>();
		requset.put(ServiceConfiguration.LoginId, userName);
		requset.put(ServiceConfiguration.MobileNo, mobileno);
		return requset;
	}
	public Map<String,String> saleReportRequset(String userName){
		Map<String,String> requset=new HashMap<String, String>();
		requset.put(ServiceConfiguration.LoginId, userName);
		return requset;
	}
	public Map<String,String> dthBookingRequset(String userName,String proCode,String packCode,String address
			,String cus_id,String cus_name,String mobileno){
		Map<String,String> requset=new HashMap<String, String>();
		requset.put(ServiceConfiguration.LoginId, userName);
		requset.put(ServiceConfiguration.ProductCode, proCode);
		requset.put(ServiceConfiguration.PackCode, packCode);
		requset.put(ServiceConfiguration.Address, address);
		requset.put(ServiceConfiguration.CustomerMobileno, mobileno);
		requset.put(ServiceConfiguration.OriginalCustomerID, cus_id);
		requset.put(ServiceConfiguration.CustomerName, cus_name);
		return requset;
	}
}
