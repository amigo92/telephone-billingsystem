package sg.edu.nus.iss.billsys.dao;

import java.io.*;
import java.text.ParseException;
import java.util.*;

import sg.edu.nus.iss.billsys.tools.TimeUtils;
import sg.edu.nus.iss.billsys.vo.*;

/**
 * 
 * @author Xu Guoneng
 *
 */
public class CallHistDao extends GenericDao {
	
	public CallHistDao(){
		super("C:/callhist.txt");
	}

	/**
	 * 
	 * @param billDate
	 * @param acctNo
	 * @return a list of call history within the given bill month period and account No.
	 */
	public ArrayList<CallHist> getCallHistByBillDateAcctNo(Date billDate, String acctNo){
		ArrayList<CallHist> list = new ArrayList<CallHist>();
		for(CallHist ch : getCallHistByBillDate(billDate)){
			if(ch.getAcctNo().equals(acctNo)){
				list.add(ch);
			}
		}
		
		return list;
	}
	
	/**
	 * 
	 * @param billDate
	 * @return a list of call history within the given bill month period
	 */
	public ArrayList<CallHist> getCallHistByBillDate(Date billDate){
		try{
			ArrayList<CallHist> list = new ArrayList<CallHist>();
			
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
	 * @param callHist e.g. 'SA-2010-02-10, 66178954, 0, 2010-02-18 18:10:01, 1800'
	 * @return
	 * @throws ParseException
	 */
	private CallHist convert(String callHist) throws ParseException{
		StringTokenizer st = new StringTokenizer(callHist, ", ");
		
		CallHist hist = new CallHist();
		hist.setAcctNo(st.nextToken()); 
		hist.setTelNo(st.nextToken()); 
		hist.setCallTxnTypeCd(Integer.parseInt(st.nextToken()));
		hist.setTimeOfCall(TimeUtils.parseDate(st.nextToken()));
		hist.setCallDuration(Long.parseLong(st.nextToken()));
		
	    return hist; 
	}
}
