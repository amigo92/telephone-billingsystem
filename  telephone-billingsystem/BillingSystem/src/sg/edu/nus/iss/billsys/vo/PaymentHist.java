package sg.edu.nus.iss.billsys.vo;

import java.util.*;

/**
 * 
 * @author Xu Guoneng
 *
 */
public class PaymentHist {

	private String acctNo;
	private int previousBalance;
	private int paymentMaked;
	private Date paymentConsolidationDate;
	
	public PaymentHist(String acctNo, int previousBalance, int paymentMaked, Date paymentConsolidationDate) {
		this.acctNo = acctNo;
		this.previousBalance = previousBalance;
		this.paymentMaked = paymentMaked;
		this.paymentConsolidationDate = paymentConsolidationDate;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public int getPreviousBalance() {
		return previousBalance;
	}

	public int getPaymentMaked() {
		return paymentMaked;
	}

	public Date getPaymentConsolidationDate() {
		return paymentConsolidationDate;
	}
	
	
}
