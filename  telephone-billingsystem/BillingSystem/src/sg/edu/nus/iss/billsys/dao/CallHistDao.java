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
	@Override
	protected void saveObjectData() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected boolean validateData(String[][] data) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected void objectDataMapping(String[][] data) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 * @param billDate
	 * @param acctNo
	 * @return a list of call history within the given bill month period and account No.
	 */
	public ArrayList<CallHist> getCallHistByBillDateAcctNo(BillPeriod billPeriod, String acctNo){
		ArrayList<CallHist> list = new ArrayList<CallHist>();
		for(CallHist ch : getCallHistByBillDate(billPeriod)){
			if(ch.getAcctNo().equals(acctNo)){
				list.add(ch);
			}
		}
		
		return list;
	}
	
	/**
	 * 
	 * @param billPeriod
	 * @return a list of call history within the given bill month period
	 */
	public ArrayList<CallHist> getCallHistByBillDate(BillPeriod billPeriod){
		try{
			ArrayList<CallHist> list = new ArrayList<CallHist>();
			
			String[][] matrix = getCallHistoryData();
			for(String[] row : matrix){
				CallHist hist = new CallHist();
				hist.setCallTxnTypeCd(Integer.parseInt(row[1]));
				hist.setTelNo(row[3]); 
				hist.setTimeOfCall(TimeUtils.parseDate(row[4]));
				hist.setCallDuration(Integer.parseInt(row[5])); 
				hist.setAcctNo(row[6]); 

				if(billPeriod.isInRange(hist.getTimeOfCall())){
					list.add(hist);
				}
			}

			return list;
		}
		catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}
}
