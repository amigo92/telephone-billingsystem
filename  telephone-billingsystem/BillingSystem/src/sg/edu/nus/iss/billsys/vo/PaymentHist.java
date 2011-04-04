package sg.edu.nus.iss.billsys.vo;

import java.util.*;

import sg.edu.nus.iss.billsys.tools.*;

/**
 * 
 * @author Xu Guoneng
 *
 */
public class PaymentHist {

	private String acctNo;
	private int paymentAmt;
	private Date paymentDate;

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public void setPaymentAmt(int paymentAmt) {
		this.paymentAmt = paymentAmt;
	}



	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}



	public String getAcctNo() {
		return acctNo;
	}

	public int getPaymentAmt() {
		return paymentAmt;
	}



	public Date getPaymentDate() {
		return paymentDate;
	}

	
}
