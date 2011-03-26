package sg.edu.nus.iss.billsys.dao;


import java.util.*;

import sg.edu.nus.iss.billsys.constant.ComplaintStatus;
import sg.edu.nus.iss.billsys.tools.TimeUtils;
import sg.edu.nus.iss.billsys.vo.*;

/**
 * 
 * @author Veera, Yu Chui Chi
 *
 */
public class ComplaintsDao extends GenericDao {
	
	private List<CustComplaint> listComplaints=new ArrayList<CustComplaint>();
	
	public ComplaintsDao() {
		this.objectDataMapping(getComplaintsData());
	}
	
	@Override
	protected void saveObjectData() {
		int cnt=0;
		
		String data[][]=new String[listComplaints.size()][5];
		String temp;
		ComplaintStatus status;
		
		for (Iterator<CustComplaint> iter = listComplaints.iterator(); iter.hasNext();) {
		
			CustComplaint element = (CustComplaint) iter.next();			
				
				data[cnt][0]=element.getAccNo();
				data[cnt][1]=element.getComplaint_id();
				data[cnt][2]=element.getComplaint_Details();				
				data[cnt][3]= element.getStatus();
				data[cnt][4]=TimeUtils.dateToString(element.getComplaintDate());
				
				cnt++;				
			}
			saveComplaintsData(data);
		
	}
	
	@Override
	protected boolean validateData(String[][] data) {
		// TO be Implemented later , to check the correctness of the data file.
		return false;
	}
	
	@Override
	protected void objectDataMapping(String[][] data) {
		try{
		
			List<CustComplaint> listComplaints=new ArrayList<CustComplaint>();
		
		for(int i=0;i<data.length;i++){
	    	
			CustComplaint comp=new CustComplaint();
			
			comp.setAccNo(data[i][0]);
			comp.setComplaint_Details(data[i][2]);
			comp.setComplaint_id(data[i][1]);
			comp.setComplaintDate(TimeUtils.parseDate(data[i][4]));
			comp.setStatus(data[i][3]);
	    		    	
			listComplaints.add(comp);	
	    }
		
		
		this.listComplaints=listComplaints;
		}
		catch(Exception ex){
			throw new RuntimeException(ex);
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
	public String addComplaint(CustComplaint obj)
	{
		String newId = Integer.toString(listComplaints.size());
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
	public int updateComplaint(CustComplaint obj) 
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
