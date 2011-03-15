package sg.edu.nus.iss.billsys.dao;

import java.util.*;
import sg.edu.nus.iss.billsys.vo.*;

public class PaymentHistDao extends GenericDao {

	/**
	 * 
	 * @param billDate
	 * @return a list of payment history within the given bill month period
	 */
	public ArrayList<PaymentHist> getPaymentHistByBillDate(Date billDate){
		ArrayList<PaymentHist> list = new ArrayList<PaymentHist>();
		
		//TODO
		
		return list;
	}
}
