/**
 * 
 */
package sg.edu.nus.iss.billsys.dao;

import java.util.ArrayList;

import sg.edu.nus.iss.billsys.vo.BillPeriod;
import sg.edu.nus.iss.billsys.vo.CallHist;

/**
 * @author Veera
 *
 */
public interface ICallHistDao {
	
	public ArrayList<CallHist> getCallHistByBillDateAcctNo(BillPeriod billPeriod, String acctNo);
	public ArrayList<CallHist> getCallHistByBillDate(BillPeriod billPeriod);

}
