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

	public PaymentHist(String acctNo, int paymentAmt, Date paymentDate) {
		this.acctNo = acctNo;
		this.paymentAmt = paymentAmt;
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

	public boolean isCurrMonthTxn(Date billDate){
		return TimeUtils.equalsYearMonth(getPaymentDate(), billDate);
	}
	
}
