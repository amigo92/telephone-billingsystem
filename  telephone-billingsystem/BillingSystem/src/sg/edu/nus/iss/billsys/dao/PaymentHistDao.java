package sg.edu.nus.iss.billsys.dao;

import java.text.ParseException;
import java.util.*;

import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.tools.TimeUtils;
import sg.edu.nus.iss.billsys.vo.*;

/**
 * 
 * @author Xu Guoneng,Veera
 *
 */
public class PaymentHistDao extends GenericDao implements IPaymentHistDao{
	private final static String  PAYMENT_HISTORY_DATA_FILE="data/PaymentHistory.txt";
	private static final int COL_LENGTH=3;
	private List<PaymentHist> listPaymentHx=new ArrayList<PaymentHist>();

	@Override
	protected final void saveObjectData() throws BillingSystemException{
//		 This method will not be implemented , since there is no save use case for this data,only read operation is required on the Payment history
		
	}
	@Override
	protected final void objectDataMapping() throws BillingSystemException{
		String[][] data=getDataAsArray(PAYMENT_HISTORY_DATA_FILE);
		if(validateData(data,"Payment History",COL_LENGTH)){
			List<PaymentHist> listPaymentHx=new ArrayList<PaymentHist>();
			
			for(int i=0;i<data.length;i++){
		    	
				PaymentHist payHx=new PaymentHist();
		    		
				payHx.setAcctNo(data[i][0]);
				payHx.setPaymentAmt(Integer.parseInt(data[i][2]));
			try{
				payHx.setPaymentDate(TimeUtils.parseDate(data[i][1]));
			}catch (ParseException e) {
				throw new BillingSystemException("Exception while pasring the 'payment' data in Payment History , Please check the data :"+data[i][1]);
			}
		    	
				listPaymentHx.add(payHx);	
		    }
			
			
			this.listPaymentHx=listPaymentHx;
		}
			
	
	}
	protected PaymentHistDao() throws BillingSystemException{
		this.objectDataMapping();
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
		
			ArrayList<PaymentHist> list = new ArrayList<PaymentHist>();
			
		
			for (Iterator iter = listPaymentHx.iterator(); iter.hasNext();) {
				PaymentHist element = (PaymentHist) iter.next();
				if(billPeriod.isInRange(element.getPaymentDate())){
					list.add(element);
				}
				
			}
			
			return list;
		
		
	}
	
}
