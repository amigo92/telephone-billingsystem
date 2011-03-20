package sg.edu.nus.iss.billsys.dao;


import java.util.*;

import sg.edu.nus.iss.billsys.tools.TimeUtils;
import sg.edu.nus.iss.billsys.vo.*;

/**
 * 
 * @author Xu Guoneng , Veera
 *
 */
public class CallHistDao extends GenericDao {
	
	private List<CallHist> listCallHx=new ArrayList<CallHist>();
	
	public CallHistDao() {
		this.objectDataMapping(getCallHistoryData());
	}
	
	@Override
	protected void saveObjectData() {
		// This method will not be implemented , since there is no save use case for this data,only read operation is required on the call history
		
	}
	
	@Override
	protected boolean validateData(String[][] data) {
		// TO be Impleted later , to check the correctness of the data file.
		return false;
	}
	
	@Override
	protected void objectDataMapping(String[][] data) {
		try{
		List<CallHist> listCallHx=new ArrayList<CallHist>();
		
		for(int i=0;i<data.length;i++){
	    	
			CallHist callHx=new CallHist();
	    		
			callHx.setAcctNo(data[i][4]);
			callHx.setCallDuration(Integer.parseInt(data[i][3]));
			callHx.setCallTxnTypeCd(Integer.parseInt(data[i][0]));
			callHx.setTelNo(data[i][1]);
			callHx.setTimeOfCall(TimeUtils.parseDate(data[i][2]));
			callHx.setNumberCalled(data[i][5]);
	    	
	    	listCallHx.add(callHx);	
	    }
		
		
		this.listCallHx=listCallHx;
		}
		catch(Exception ex){
			throw new RuntimeException(ex);
		}
		
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
		
			ArrayList<CallHist> tempList = new ArrayList<CallHist>();
			
			
			for (Iterator iter = listCallHx.iterator(); iter.hasNext();) {
				CallHist element = (CallHist) iter.next();
				if(billPeriod.isInRange(element.getTimeOfCall())){
					tempList.add(element);
				}
				
			}

			return tempList;
		
	}
}
