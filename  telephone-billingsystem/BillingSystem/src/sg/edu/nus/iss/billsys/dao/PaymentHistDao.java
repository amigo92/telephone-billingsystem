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
 * This class is a implementation class for accessing the data layer to get the payment history information
 * It extends the genericdao to inherit the functionality of accessing the physical file and to return a 
 * raw data of two dimensional string array , which will in turn mapped to the domain objects for easy 
 * manipulation , This class also provides implementation to save back the domain object to the data file.
 * 
 * This class will be inheriting the Interface class to implement the public methods which will be used by the 
 * manager classes for the data read / update / create functionalities.
 *
 */
public class PaymentHistDao extends GenericDao implements IPaymentHistDao{
	private final static String  PAYMENT_HISTORY_DATA_FILE="data/PaymentHistory.txt";//This Constant is to specify the file path to load the Payment history information
	private static final int COL_LENGTH=3;//This constant would give the number of columns expected in the file 
	private List<PaymentHist> listPaymentHx=new ArrayList<PaymentHist>();//Instance variable to hold the data from the parsed data of the file

	@Override
	protected final void saveObjectData() throws BillingSystemException{
//		 This method will not be implemented , since there is no save use case for this data,only read operation is required on the Payment history
		
	}
	
	/*
	 * This method will implement the logic to map the raw data of two dimensional array to the Domain objects which is then used by the public methods of the Dao.
	 * (non-Javadoc)
	 * @see sg.edu.nus.iss.billsys.dao.GenericDao#objectDataMapping()
	 */
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
	/*
	 * The Constructor intialisation also invokes the call to map the raw data parsed from the file to domain object.
	 */
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
