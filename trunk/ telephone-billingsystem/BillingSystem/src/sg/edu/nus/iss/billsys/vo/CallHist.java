package sg.edu.nus.iss.billsys.vo;

import java.util.*;

/**
 * 
 * @author Xu Guoneng
 *
 */
public class CallHist {

	private String acctNo;
	private int callTxnTypeCd;
	private String telNo;
	private String numberCalled;
	private Date timeOfCall;
	private int callDuration;
	
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public int getCallTxnTypeCd() {
		return callTxnTypeCd;
	}


	public void setCallTxnTypeCd(int callTxnTypeCd) {
		this.callTxnTypeCd = callTxnTypeCd;
	}


	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public void setTimeOfCall(Date timeOfCall) {
		this.timeOfCall = timeOfCall;
	}

	public void setCallDuration(int callDuration) {
		this.callDuration = callDuration;
	}

	public String getAcctNo() {
		return acctNo;
	}
	
	public String getTelNo() {
		return telNo;
	}

	public Date getTimeOfCall() {
		return timeOfCall;
	}

	public int getCallDuration() {
		return callDuration;
	}

	public String getNumberCalled() {
		return numberCalled;
	}

	public void setNumberCalled(String numberCalled) {
		this.numberCalled = numberCalled;
	}

}
