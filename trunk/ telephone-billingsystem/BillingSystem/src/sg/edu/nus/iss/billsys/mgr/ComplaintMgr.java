package sg.edu.nus.iss.billsys.mgr;

import java.util.*;

import sg.edu.nus.iss.billsys.constant.*;
import sg.edu.nus.iss.billsys.tools.*;
import sg.edu.nus.iss.billsys.dao.*;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.vo.*;


/**
 * 
 * @author Yu Chui Chi
 *
 */

public class ComplaintMgr {
	
	
	public long createComplaintByAccount(String acctNo, String description)
	{
		//system date for the creation date
		
		//successful
		//TODO: return complaint id 
		
		//fail
		return 0;
	}
	
	public long createComplaintByCustomerId(String custId, String description)
	{
		//system date for the creation date
		
		//successful
		//TODO: return complaint id 
		
		//fail
		return 0;
	}
	
	public List<CustComplaint> getComplaintByAccount(String acctNo)
	{
		List<CustComplaint> complaintList = new ArrayList<CustComplaint>();
		return complaintList;
	}
	
	public List<CustComplaint> getComplaintByCustomerId(String custId)
	{
		List<CustComplaint> complaintList = new ArrayList<CustComplaint>();
		return complaintList;
	}
	
	public int updateComplaint(long complaintId, String status)
	{
		//use system date to be the update Date
		
		//successful
		//TODO: return 1;
		
		//fail
		return 0;
	}	

}
