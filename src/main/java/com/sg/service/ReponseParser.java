package com.sg.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.ksoap2.serialization.SoapObject;

import android.util.Log;

public class ReponseParser {

	public String loginResponse(SoapObject response){
		String status=null;
		if(response!=null){
			status=response.getProperty(ServiceConfiguration.LoginStatusResult).toString();
		}
		return status;
	}
	public String chaeckBalanceResponse(SoapObject response){
		String status=null;
		if(response!=null){
			status=response.getProperty(ServiceConfiguration.BalanceEnqueryResult).toString();
		}
		return status;
	}
	public String balanceTransResponse(SoapObject response){
		String status=null;
		if(response!=null){
			status=response.getProperty(ServiceConfiguration.BalanceTransactionResult).toString();
		}
		return status;
	}
	public String revertBalanceResponse(SoapObject response){
		String status=null;
		if(response!=null){
			status=response.getProperty(ServiceConfiguration.RevertBalanceResult).toString();
		}
		return status;
	}
	public String rechargeResponse(SoapObject response){
		String status=null;
		if(response!=null){
			status=response.getProperty(ServiceConfiguration.RechargeProccesResult).toString();
		}
		return status;
	}
	public String busbookingResponse(SoapObject response){
		String status=null;
		if(response!=null){
			status=response.getProperty(ServiceConfiguration.DTHBookingProccesResult).toString();
		}
		return status;
	}
	public String changePassResponse(SoapObject response){
		String status=null;
		if(response!=null){
			status=response.getProperty(ServiceConfiguration.ChangePasswodResult).toString();
		}
		return status;
	}
	public ArrayList<HashMap<String,String>> rechargeReportResponse(SoapObject response){
		ArrayList<HashMap<String,String>> reportList=new ArrayList<HashMap<String,String>>();
		if(response!=null){
			try{
				SoapObject diettype_listResult=(SoapObject)response.getProperty(ServiceConfiguration.RechargeReportResult);
				SoapObject diffgram = (SoapObject) diettype_listResult.getProperty("diffgram");
				SoapObject NewDataSet1 = (SoapObject) diffgram.getProperty("RechargeReport") ;
				int i=0;
				for(i=0;i<NewDataSet1.getPropertyCount();i++)
				{	
					SoapObject NewDataSet=(SoapObject)NewDataSet1.getProperty(i);
					HashMap<String, String> map=new HashMap<String, String>();
					map.put("Id","Balance :"+NewDataSet.getProperty("Balance").toString());
					map.put("OperatorName","Operator :"+NewDataSet.getProperty("Operator").toString());
					map.put("Number","Mobile Number :"+ NewDataSet.getProperty("Device").toString());
					map.put("Amount", "Amount :"+NewDataSet.getProperty("Amount").toString());
					map.put("Date", "Date :"+NewDataSet.getProperty("Date").toString());
					map.put("Status", "Status :"+NewDataSet.getProperty("Status").toString());
					//map.put("ON","Operator :"+NewDataSet.getProperty("Operator").toString());
					reportList.add(map);
				}	
			}
			catch (Exception e) {
				HashMap<String, String> map=new HashMap<String, String>();
				//map.put(S_NO, "1");
				map.put("Id","No Value");
				map.put("OperatorName", "No Value");
				map.put("Number","No Value");
				map.put("Amount","No Value");
				map.put("Date","No Value");
				map.put("Status", "No Value");
				Log.d("Exception",e.toString());
				reportList.add(map);
			} 
		}
		return reportList;
	}
	public ArrayList<HashMap<String,String>> rechargeReportDireResponse(SoapObject response){
		ArrayList<HashMap<String,String>> reportList=new ArrayList<HashMap<String,String>>();
		if(response!=null){
			try{
				SoapObject diettype_listResult=(SoapObject)response.getProperty(ServiceConfiguration.RechargeReportdirResult);
				SoapObject diffgram = (SoapObject) diettype_listResult.getProperty("diffgram");
				SoapObject NewDataSet1 = (SoapObject) diffgram.getProperty("RechargeReport") ;
				int i=0;
				for(i=0;i<NewDataSet1.getPropertyCount();i++)
				{	
					SoapObject NewDataSet=(SoapObject)NewDataSet1.getProperty(i);
					HashMap<String, String> map=new HashMap<String, String>();
					map.put("Id","Balance :"+NewDataSet.getProperty("Balance").toString());
					map.put("OperatorName","Operator :"+NewDataSet.getProperty("Operator").toString());
					map.put("Number","Mobile Number :"+ NewDataSet.getProperty("Device").toString());
					map.put("Amount", "Amount :"+NewDataSet.getProperty("Amount").toString());
					map.put("Date", "Date :"+NewDataSet.getProperty("Date").toString());
					map.put("Status", "Status :"+NewDataSet.getProperty("Status").toString());
					//map.put("ON","Operator :"+NewDataSet.getProperty("Operator").toString());
					reportList.add(map);
				}	
			}
			catch (Exception e) {
				HashMap<String, String> map=new HashMap<String, String>();
				//map.put(S_NO, "1");
				map.put("Id","No Value");
				map.put("OperatorName", "No Value");
				map.put("Number","No Value");
				map.put("Amount","No Value");
				map.put("Date","No Value");
				map.put("Status", "No Value");
				Log.d("Exception",e.toString());
				reportList.add(map);
			} 
		}
		return reportList;
	}
	public ArrayList<HashMap<String,String>> transactionReportResponse(SoapObject response){
		ArrayList<HashMap<String,String>> reportList=new ArrayList<HashMap<String,String>>();
		if(response!=null){
			try{
				SoapObject diettype_listResult=(SoapObject)response.getProperty(ServiceConfiguration.TransactionReportResult);
				SoapObject diffgram = (SoapObject) diettype_listResult.getProperty("diffgram");
				SoapObject NewDataSet1 = (SoapObject) diffgram.getProperty("ROReport") ;
				int i=0;
				for(i=0;i<NewDataSet1.getPropertyCount();i++)
				{	
					SoapObject NewDataSet=(SoapObject)NewDataSet1.getProperty(i);
					HashMap<String, String> map=new HashMap<String, String>();
					map.put("Id","Current Balance :"+NewDataSet.getProperty("CurrentBalance").toString());
					map.put("OperatorName","Receiver :"+NewDataSet.getProperty("Receiver").toString());
					map.put("Number","TransactionNumber :"+ NewDataSet.getProperty("TransactionNumber").toString());
					map.put("Amount", "Amount :"+NewDataSet.getProperty("Amount").toString());
					map.put("Date", "Date :"+NewDataSet.getProperty("Date").toString());
					map.put("Status", "Description :"+NewDataSet.getProperty("Description").toString());
					//map.put("ON","Operator :"+NewDataSet.getProperty("Operator").toString());
					reportList.add(map);
				}	
			}
			catch (Exception e) {
				HashMap<String, String> map=new HashMap<String, String>();
				//map.put(S_NO, "1");
				map.put("Id","No Value");
				map.put("OperatorName", "No Value");
				map.put("Number","No Value");
				map.put("Amount","No Value");
				map.put("Date","No Value");
				map.put("Status", "No Value");
				Log.d("Exception",e.toString());
				reportList.add(map);
			} 
		}
		return reportList;
	}
	public ArrayList<HashMap<String,String>> saleReportResponse(SoapObject response){
		ArrayList<HashMap<String,String>> reportList=new ArrayList<HashMap<String,String>>();
		if(response!=null){
			try{
				SoapObject diettype_listResult=(SoapObject)response.getProperty(ServiceConfiguration.SalesReportResult);
				SoapObject diffgram = (SoapObject) diettype_listResult.getProperty("diffgram");
				SoapObject NewDataSet1 = (SoapObject) diffgram.getProperty("SalesReport") ;
				int i=0;
				for(i=0;i<NewDataSet1.getPropertyCount();i++)
				{	
					SoapObject NewDataSet=(SoapObject)NewDataSet1.getProperty(i);
					HashMap<String, String> map=new HashMap<String, String>();
					map.put("Id","Balance :"+NewDataSet.getProperty("Balance").toString());
					map.put("OperatorName","PackName :"+NewDataSet.getProperty("PackName").toString()+
							"ProductName :"+NewDataSet.getProperty("ProductName").toString()
							);
					map.put("Number","Customer Name :"+ NewDataSet.getProperty("CustomerName").toString());
					map.put("Amount", "PackPrice :"+NewDataSet.getProperty("PackPrice").toString());
					map.put("Date", "Date :"+NewDataSet.getProperty("Date").toString());
					map.put("Status", "Status :"+NewDataSet.getProperty("Status").toString());
					//map.put("ON","Operator :"+NewDataSet.getProperty("Operator").toString());
					reportList.add(map);
				}	
			}
			catch (Exception e) {
				HashMap<String, String> map=new HashMap<String, String>();
				//map.put(S_NO, "1");
				map.put("Id","No Value");
				map.put("OperatorName", "No Value");
				map.put("Number","No Value");
				map.put("Amount","No Value");
				map.put("Date","No Value");
				map.put("Status", "No Value");
				Log.d("Exception",e.toString());
				reportList.add(map);
			} 
		}
		return reportList;
	}
	public ArrayList<HashMap<String,String>> refundReportResponse(SoapObject response){
		ArrayList<HashMap<String,String>> reportList=new ArrayList<HashMap<String,String>>();
		if(response!=null){
			try{
				SoapObject diettype_listResult=(SoapObject)response.getProperty(ServiceConfiguration.RefundReportResult);
				SoapObject diffgram = (SoapObject) diettype_listResult.getProperty("diffgram");
				SoapObject NewDataSet1 = (SoapObject) diffgram.getProperty("RechargeReport") ;
				int i=0;
				for(i=0;i<NewDataSet1.getPropertyCount();i++)
				{	
					SoapObject NewDataSet=(SoapObject)NewDataSet1.getProperty(i);
					HashMap<String, String> map=new HashMap<String, String>();
					map.put("Id","RemainingBalance :"+NewDataSet.getProperty("RemainingBalance").toString());
					map.put("OperatorName","Operator :"+NewDataSet.getProperty("Operator").toString());
					map.put("Number","Mobile Number :"+ NewDataSet.getProperty("Device").toString());
					map.put("Amount", "Amount :"+NewDataSet.getProperty("Amount").toString());
					map.put("Date", "Date :"+NewDataSet.getProperty("Date").toString());
					map.put("Status", "Status :"+NewDataSet.getProperty("Status").toString());
					//map.put("ON","Operator :"+NewDataSet.getProperty("Operator").toString());
					reportList.add(map);
				}	
			}
			catch (Exception e) {
				HashMap<String, String> map=new HashMap<String, String>();
				//map.put(S_NO, "1");
				map.put("Id","No Value");
				map.put("OperatorName", "No Value");
				map.put("Number","No Value");
				map.put("Amount","No Value");
				map.put("Date","No Value");
				map.put("Status", "No Value");
				Log.d("Exception",e.toString());
				reportList.add(map);
			} 
		}
		return reportList;
	}
	public String complaintFileResponse(SoapObject response){
		String status=null;
		if(response!=null){
			status=response.getProperty(ServiceConfiguration.ComplaintfileResult).toString();
		}
		return status;
	}
	public ArrayList<HashMap<String,String>> complaintResponse(SoapObject response){
		ArrayList<HashMap<String,String>> reportList=new ArrayList<HashMap<String,String>>();
		if(response!=null){
			try{
				SoapObject diettype_listResult=(SoapObject)response.getProperty(ServiceConfiguration.ComplaintResult);
				SoapObject diffgram = (SoapObject) diettype_listResult.getProperty("diffgram");
				SoapObject NewDataSet1 = (SoapObject) diffgram.getProperty("RechargeReport") ;
				int i=0;
				for(i=0;i<NewDataSet1.getPropertyCount();i++)
				{	
					SoapObject NewDataSet=(SoapObject)NewDataSet1.getProperty(i);
					HashMap<String, String> map=new HashMap<String, String>();
					map.put("Id","Balance :"+NewDataSet.getProperty("Balance").toString());
					map.put("OperatorName","Operator :"+NewDataSet.getProperty("Operator").toString());
					map.put("Number","Mobile Number :"+ NewDataSet.getProperty("Device").toString());
					map.put("Amount", "Amount :"+NewDataSet.getProperty("Amount").toString());
					map.put("Date", "Date :"+NewDataSet.getProperty("Date").toString());
					map.put("Status", "Status :"+NewDataSet.getProperty("Status").toString());
					//map.put("ON","Operator :"+NewDataSet.getProperty("Operator").toString());
					reportList.add(map);
				}	
			}
			catch (Exception e) {
				HashMap<String, String> map=new HashMap<String, String>();
				//map.put(S_NO, "1");
				map.put("Id","No Value");
				map.put("OperatorName", "No Value");
				map.put("Number","No Value");
				map.put("Amount","No Value");
				map.put("Date","No Value");
				map.put("Status", "No Value");
				Log.d("Exception",e.toString());
				reportList.add(map);
			} 
		}
		return reportList;
	}
}
