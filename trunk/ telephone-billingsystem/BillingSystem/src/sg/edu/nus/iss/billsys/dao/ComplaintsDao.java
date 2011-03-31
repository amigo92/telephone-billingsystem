package sg.edu.nus.iss.billsys.dao;


import java.text.ParseException;
import java.util.*;

import sg.edu.nus.iss.billsys.constant.ComplaintStatus;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.tools.TimeUtils;
import sg.edu.nus.iss.billsys.vo.*;

/**
 * 
 * @author Veera, Yu Chui Chi
 *
 */
public class ComplaintsDao extends GenericDao implements IComplaintsDao{
	
	private static final int COL_LENGTH=5;
	private List<CustComplaint> listComplaints=new ArrayList<CustComplaint>();
	
	protected ComplaintsDao() throws BillingSystemException{
		this.objectDataMapping(getComplaintsData());
	}
	
	@Override
	public final void saveObjectData() throws BillingSystemException{
		int cnt=0;
		
		String data[][]=new String[listComplaints.size()][COL_LENGTH];

		for (Iterator<CustComplaint> iter = listComplaints.iterator(); iter.hasNext();) {
		
			CustComplaint element = (CustComplaint) iter.next();			
				
				data[cnt][0]=element.getAccNo();
				data[cnt][1]=element.getComplaint_id();
				data[cnt][2]=element.getComplaint_Details();				
				data[cnt][3]=String.valueOf(element.getStatus().ordinal());
				data[cnt][4]=TimeUtils.dateToString(element.getComplaintDate());
				
				cnt++;				
			}
		if(validateData(data,"Complaints",COL_LENGTH)){
			if(!saveComplaintsData(data))throw new BillingSystemException("Saving to File Operation Failed for ComplaintsData");
		}
		
	}
	
	private ComplaintStatus getComplaintStatusByCode(String code){
			
		ComplaintStatus[] temp=ComplaintStatus.values();
		ComplaintStatus status=null;
			
			for (int i = 0; i < temp.length; i++) {
				if(String.valueOf(temp[i].ordinal()).equals(code))
					status=temp[i];
					
			}
			return status;
		
		
	}
	
	@Override
	protected final void objectDataMapping(String[][] data) throws BillingSystemException{
				
		if(validateData(data,"Complaints",COL_LENGTH)){
		List<CustComplaint> listComplaints=new ArrayList<CustComplaint>();
		
		for(int i=0;i<data.length;i++){
	    	
			CustComplaint comp=new CustComplaint();
			
			comp.setAccNo(data[i][0]);
			comp.setComplaint_Details(data[i][2]);
			comp.setComplaint_id(data[i][1]);
			try{
			comp.setComplaintDate(TimeUtils.parseDate(data[i][4]));
			}catch (ParseException e) {
				throw new BillingSystemException("Exception while pasring the 'complaint' data in Complaints , Please check the data :"+data[i][4]);
			}
			comp.setStatus(getComplaintStatusByCode(data[i][3]));
	    		    	
			listComplaints.add(comp);	
	    }
		
		
		this.listComplaints=listComplaints;
		
		}
	}
	
	/**
	 * 
	 * @param accNo
	 * @return the list of complaints for this account
	 */
	public List<CustComplaint> getComplaintList(String accNo)
	{			
		List<CustComplaint> returnList = new ArrayList<CustComplaint>();		
		for (Iterator<CustComplaint> iter = listComplaints.iterator(); iter.hasNext();) 
		{
			CustComplaint element = (CustComplaint) iter.next();
						
			if(element.getAccNo().equals(accNo)){
				returnList.add(element);
			}
		}
		
		return returnList;
	}	
	
	/**
	 * 
	 * @param complaintId the complaint Id to search for
	 * @return the complaint
	 */
	public CustComplaint getComplaint(String complaintId)
	{
		for (Iterator<CustComplaint> iter = listComplaints.iterator(); iter.hasNext();) 
		{
			CustComplaint element = (CustComplaint) iter.next();
						
			if(element.getComplaint_id().equals(complaintId)){
				return element;
			}
		}
		
		return null; // complaint not found	
	}
	
	/**
	 * 
	 * @param obj the Customer Complaints to be added
	 * @return the newly created Complaint id
	 */
	public String addComplaint(CustComplaint obj) throws BillingSystemException
	{
		String newId = Integer.toString(listComplaints.size()+1);
		obj.setComplaint_id(newId);
		listComplaints.add(obj);
		this.saveObjectData();
		return newId;
	}
	
	/**
	 * 
	 * @param obj the customer Complaint object to be updated
	 * @return true for success, false for fail
	 */
	public int updateComplaint(CustComplaint obj)  throws BillingSystemException
	{
		for (Iterator<CustComplaint> iter = listComplaints.iterator(); iter.hasNext();) 
		{
			CustComplaint element = (CustComplaint) iter.next();
						
			if(element.getComplaint_id().equals(obj.getComplaint_id())){
				element = obj;
				this.saveObjectData();
				return 1; //success
			}
		}
		
		return 0; // fail		
	}
}
