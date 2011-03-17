package sg.edu.nus.iss.billsys.dao;

import java.io.BufferedReader;
import java.text.ParseException;
import java.util.*;

import sg.edu.nus.iss.billsys.tools.TimeUtils;
import sg.edu.nus.iss.billsys.vo.*;

/**
 * 
 * @author Xu Guoneng
 *
 */
public class PaymentHistDao extends GenericDao {

	public PaymentHistDao(){
		super("C:/payments.txt");
	}
	
	/**
	 * 
	 * @param billDate
	 * @param acctNo
	 * @return a list of payment history within the given bill month period and account No.
	 */
	public ArrayList<PaymentHist> getPaymentHistByBillDateAcctNo(Date billDate, String acctNo){
		ArrayList<PaymentHist> list = new ArrayList<PaymentHist>();
		for(PaymentHist ph : getPaymentHistByBillDate(billDate)){
			if(ph.getAcctNo().equals(acctNo)){
				list.add(ph);
			}
		}
		return list;
	}
	
	/**
	 * 
	 * @param billDate
	 * @return a list of payment history within the given bill month period
	 */
	public ArrayList<PaymentHist> getPaymentHistByBillDate(Date billDate){
		try{
			ArrayList<PaymentHist> list = new ArrayList<PaymentHist>();
			
			String res = null;
			BufferedReader br = getCurrReader();
			while((res = br.readLine()) != null){
				list.add(convert(res));
			}
			br.close();
			
			return list;
		}
		catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}
	
	/**
	 * 
	 * @param callHist e.g. 'SA-2010-02-10, 20080, 2010-02-18 18:10:01'
	 * @return
	 * @throws ParseException
	 */
	private PaymentHist convert(String paymentHist) throws ParseException{
		StringTokenizer st = new StringTokenizer(paymentHist, ", ");
		
		PaymentHist hist = new PaymentHist();
		hist.setAcctNo(st.nextToken()); 
		hist.setPaymentAmt(Integer.parseInt(st.nextToken())); 
		hist.setPaymentDate(TimeUtils.parseDate(st.nextToken()));
		
	    return hist; 
	}
}
