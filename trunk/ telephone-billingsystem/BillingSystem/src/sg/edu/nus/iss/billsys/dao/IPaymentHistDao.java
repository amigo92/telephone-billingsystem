/**
 * 
 */
package sg.edu.nus.iss.billsys.dao;

import java.util.ArrayList;

import sg.edu.nus.iss.billsys.vo.BillPeriod;
import sg.edu.nus.iss.billsys.vo.PaymentHist;

/**
 * @author Veera
 *
 */
public interface IPaymentHistDao {
	
	public ArrayList<PaymentHist> getPaymentHistByBillPeriodAcctNo(BillPeriod billPeriod, String acctNo);
	public ArrayList<PaymentHist> getPaymentHistByBillPeriod(BillPeriod billPeriod);

}
