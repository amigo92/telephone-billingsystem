package sg.edu.nus.iss.billsys.dao;

import java.util.*;

import sg.edu.nus.iss.billsys.tools.TimeUtils;
import sg.edu.nus.iss.billsys.vo.*;

/**
 * 
 * @author Xu Guoneng,Veera
 *
 */
public class PaymentHistDao extends GenericDao {
	
	private List<PaymentHist> listPaymentHx=new ArrayList<PaymentHist>();

	@Override
	protected boolean validateData(String[][] data) {
//		 To be Implemented later , to check the correctness of the data file.
		return false;
	}
	
	@Override
	protected void saveObjectData() {
//		 This method will not be implemented , since there is no save use case for this data,only read operation is required on the Payment history
		
	}
	@Override
	protected void objectDataMapping(String[][] data) {
		try{
			List<PaymentHist> listPaymentHx=new ArrayList<PaymentHist>();
			
			for(int i=0;i<data.length;i++){
		    	
				PaymentHist payHx=new PaymentHist();
		    		
				payHx.setAcctNo(data[i][0]);
				payHx.setPaymentAmt(Integer.parseInt(data[i][2]));
				payHx.setPaymentDate(TimeUtils.parseDate(data[i][1]));
		    	
				listPaymentHx.add(payHx);	
		    }
			
			
			this.listPaymentHx=listPaymentHx;
			}
			catch(Exception ex){
				throw new RuntimeException(ex);
			}
	
	}
	public PaymentHistDao(){
		this.objectDataMapping(getPaymentHistoryData());
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
