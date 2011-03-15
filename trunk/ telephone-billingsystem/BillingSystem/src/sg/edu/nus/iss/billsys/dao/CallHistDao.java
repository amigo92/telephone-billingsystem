package sg.edu.nus.iss.billsys.dao;

import java.util.*;

import sg.edu.nus.iss.billsys.vo.*;

public class CallHistDao extends GenericDao {

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
		ArrayList<CallHist> list = new ArrayList<CallHist>();
		
		//TODO
		
		return list;
	}
}
