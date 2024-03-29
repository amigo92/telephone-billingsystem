package sg.edu.nus.iss.billsys.dao;


import java.text.ParseException;
import java.util.*;

import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.tools.TimeUtils;
import sg.edu.nus.iss.billsys.vo.*;

/**
 * 
 * @author Xu Guoneng , Veera
 * 
 * This class is a implementation class for accessing the data layer to get the call history information
 * It extends the genericdao to inherit the functionality of accessing the physical file and to return a 
 * raw data of two dimensional string array , which will in turn mapped to the domain objects for easy 
 * manipulation , This class also provides implementation to save back the domain object to the data file.
 * 
 * This class will be inheriting the Interface class to implement the public methods which will be used by the 
 * manager classes for the data read / update / create functionalities.
 *
 */
public class CallHistDao extends GenericDao implements ICallHistDao{
	
	private final static String  CALL_HISTORY_DATA_FILE="data/CallHistory.txt";//This Constant is to specify the file path to load the Call history information
	private static final int COL_LENGTH=6; //This constant would give the number of columns expected in the file 
	
	private List<CallHist> listCallHx=new ArrayList<CallHist>();//Instance variable to hold the data from the parsed data of the file
	/*
	 * The Constructor intialisation also invokes the call to map the raw data parsed from the file to domain object.
	 */
	protected CallHistDao() throws BillingSystemException{
		this.objectDataMapping();
	}
	
	@Override
	protected final void saveObjectData() throws BillingSystemException{
		// This method will not be implemented , since there is no save use case for this data,only read operation is required on the call history
		
	}
	/*
	 * 
	 * This method will implement the logic to map the raw data of two dimensional array to the Domain objects which is then used by the public methods of the Dao. 
	 * (non-Javadoc)
	 * @see sg.edu.nus.iss.billsys.dao.GenericDao#objectDataMapping()
	 */
	@Override
	protected final void objectDataMapping() throws BillingSystemException{
		String[][] data=getDataAsArray(CALL_HISTORY_DATA_FILE);
		if(validateData(data,"Call History",COL_LENGTH)){
					
				List<CallHist> listCallHx=new ArrayList<CallHist>();
				
				for(int i=0;i<data.length;i++){
			    	
					CallHist callHx=new CallHist();
			    		
					callHx.setAcctNo(data[i][4]);
					callHx.setCallDuration(Integer.parseInt(data[i][3]));
					callHx.setCallTxnTypeCd(Integer.parseInt(data[i][0]));
					callHx.setTelNo(data[i][1]);
					
					try{
					callHx.setTimeOfCall(TimeUtils.parseDate(data[i][2]));
					}catch (ParseException e) {
						throw new BillingSystemException("Exception while pasring the 'time of call' data in Call History , Please check the data :"+data[i][2]);
					}
					callHx.setNumberCalled(data[i][5]);
			    	
			    	listCallHx.add(callHx);	
			    }
				
				
				this.listCallHx=listCallHx;
		
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
