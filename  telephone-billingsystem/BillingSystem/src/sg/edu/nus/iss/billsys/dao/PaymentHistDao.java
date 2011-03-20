package sg.edu.nus.iss.billsys.dao;

import java.util.*;

import sg.edu.nus.iss.billsys.tools.TimeUtils;
import sg.edu.nus.iss.billsys.vo.*;

/**
 * 
 * @author Xu Guoneng
 *
 */
public class PaymentHistDao extends GenericDao {

	@Override
	protected void saveObjectData() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void objectDataMapping(String[][] data) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 * @param billPeriod
	 * @param acctNo
	 * @return a list of payment history within the given bill month period and account No.
	 */
	public ArrayList<PaymentHist> getPaymentHistByBillPeriodAcctNo(BillPeriod billPeriod, String acctNo){
		ArrayList<PaymentHist> list = new ArrayList<PaymentHist>();
		for(PaymentHist ph : getPaymentHistByBillPeriod(billPeriod)){
			if(ph.getAcctNo().equals(acctNo)){
				list.add(ph);
			}
		}
		return list;
	}
	
	/**
	 * 
	 * @param billPeriod
	 * @return a list of payment history within the given bill month period
	 */
	public ArrayList<PaymentHist> getPaymentHistByBillPeriod(BillPeriod billPeriod){
		try{
			ArrayList<PaymentHist> list = new ArrayList<PaymentHist>();
			
			String[][] matrix = getPaymentHistoryData();
			for(String[] row : matrix){
				PaymentHist hist = new PaymentHist();
				hist.setPaymentDate(TimeUtils.parseDate(row[1]));
				hist.setPaymentAmt(Integer.parseInt(row[2])); 
				hist.setAcctNo(row[3]); 
				
				if(billPeriod.isInRange(hist.getPaymentDate())){
					list.add(hist);
				}
			}
			
			return list;
		}
		catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}
	@Override
	protected boolean validateData(String[][] data) {
		// TODO Auto-generated method stub
		return false;
	}
}
