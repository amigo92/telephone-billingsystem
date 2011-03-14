package sg.edu.nus.iss.billsys.vo;

import sg.edu.nus.iss.billsys.constant.*;

import java.util.*;

/**
 * 
 * @author Xu Guoneng
 *
 */
public class CallHist {

	private String acctNo;
	private CallTxnType aCallTxnType;
	private String telNo;
	private Date timeOfCall;
	private long callDuration;
	
	public CallHist(String acctNo, CallTxnType aCallTxnType, String telNo, Date timeOfCall, long callDuration) {
		this.acctNo = acctNo;
		this.aCallTxnType = aCallTxnType;
		this.telNo = telNo;
		this.timeOfCall = timeOfCall;
		this.callDuration = callDuration;
	}
	
	public String getAcctNo() {
		return acctNo;
	}

	public CallTxnType getaCallTxnType() {
		return aCallTxnType;
	}

	public String getTelNo() {
		return telNo;
	}

	public Date getTimeOfCall() {
		return timeOfCall;
	}

	public long getCallDuration() {
		return callDuration;
	}
}
