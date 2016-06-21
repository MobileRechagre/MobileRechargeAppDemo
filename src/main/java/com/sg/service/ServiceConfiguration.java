package com.sg.service;

public interface  ServiceConfiguration {
	public final static String NAMESPACE = "http://tempuri.org/";
	//url
	public final static String LOgin_Url="http://www.b2b.pcs18.com/WSLoginSatatus.asmx";
	public final static String CheckBalance_Url="http://www.b2b.pcs18.com/WSLoginSatatus.asmx";
	public final static String Recharge_Url="http://www.b2b.pcs18.com/RechargeWebService.asmx";
	//public final static String CheckBalance_Url="http://www.b2b.pcs18.com/WSLoginSatatus.asmx";
	//method name
	public final static String LOGIN_METHOD="LoginStatus";
	public final static String BalanceEnquery_METHOD="BalanceEnquery";
	public final static String BalanceTransaction_METHOD="BalanceTransaction";
	public final static String Recharge_METHOD="RechargeProcces";
	public final static String ChangePasswod_METHOD="ChangePasswod";
	public final static String RechargeReport_METHOD="RechargeReport";
	public final static String TransactionReport_METHOD="TransactionReport";
	public final static String DTHBookingProcces_METHOD="DTHBookingProcces";
	public final static String SalesReport_METHOD="SalesReport";
	public final static String RefundReport_METHOD="RefundReport";
	public final static String RevertBalance_METHOD="RevertBalance";
	public final static String RechargeReportdir_METHOD="RechargeReportdir";
	public final static String Complaint_METHOD="Complaint";
	public final static String Complaintfile_METHOD="Complaintfile";
	// parameters name
	public final static String USERNAMW="UserName";
	public final static String Password="Password";
	public final static String LoginId="LoginId";
	public final static String Amount="Amount";
	public final static String ReciverMobileNo="ReciverMobileNo";
	public final static String MobileNo="MobileNo";
	public final static String Operator="Operator";
	public final static String RechAmount="RechAmount";
	public final static String CurrentPassword="CurrentPassword";
	public final static String NewPassword="NewPassword";
	public final static String ProductCode="ProductCode";
	public final static String PackCode="PackCode";
	public final static String CustomerName="CustomerName";
	public final static String CustomerMobileno="CustomerMobileno";
	public final static String Address="Address";
	public final static String OriginalCustomerID="OriginalCustomerID";
	public final static String TransactionNumber="TransactionNumber";
	public final static String UID="UID";
	public final static String Remark="Remark";
	public final static String Account="Account";
	//response tag
	
	public final static String LoginStatusResult="LoginStatusResult";
	public final static String BalanceEnqueryResult="BalanceEnqueryResult";
	public final static String BalanceTransactionResult="BalanceTransactionResult";
	public final static String RechargeProccesResult="RechargeProccesResult";
	public final static String ChangePasswodResult="ChangePasswodResult";
	public final static String RechargeReportResult="RechargeReportResult";
	public final static String TransactionReportResult="TransactionReportResult";
	public final static String DTHBookingProccesResult="DTHBookingProccesResult";
	public final static String SalesReportResult="SalesReportResult";
	public final static String RefundReportResult="RefundReportResult";
	public final static String RevertBalanceResult="RevertBalanceResult";
	public final static String RechargeReportdirResult="RechargeReportdirResult";
	public final static String ComplaintResult="ComplaintResult";
	public final static String ComplaintfileResult="ComplaintfileResult";
}
