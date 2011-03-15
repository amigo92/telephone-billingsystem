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
	private int previousBalance;
	private int paymentMaded;
	private Date paymentConsolidationDate;
	
	public PaymentHist(String acctNo, int previousBalance, int paymentMaded, Date paymentConsolidationDate) {
		this.acctNo = acctNo;
		this.previousBalance = previousBalance;
		this.paymentMaded = paymentMaded;
		this.paymentConsolidationDate = paymentConsolidationDate;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public int getPreviousBalance() {
		return previousBalance;
	}

	public int getPaymentMaded() {
		return paymentMaded;
	}

	public Date getPaymentConsolidationDate() {
		return paymentConsolidationDate;
	}
	
	public boolean isCurrMonthTxn(Date billDate){
		return TimeUtils.equalsYearMonth(getPaymentConsolidationDate(), billDate);
	}
	
}
