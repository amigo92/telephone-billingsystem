package sg.edu.nus.iss.billsys.mgr;

import java.util.List;

import java.util.*;

import sg.edu.nus.iss.billsys.constant.*;
import sg.edu.nus.iss.billsys.dao.*;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.vo.*;


/**
 * 
 * @author Yu Chui Chi
 *
 */

public class ComplaintMgr {
	
	private ComplaintsDao dao;
	
	public ComplaintMgr() throws BillingSystemException
	{
		dao = new ComplaintsDao();
	}
	
	/**
	 * 
	 * @param acctNo the account number this complaints belong to
	 * @param description
	 * @return the newly created Complaint id
	 */
	public long createComplaintByAccount(String acctNo, String description)throws BillingSystemException
	{
		try
		{
			//system date for the creation date
			 Date systemDate = new Date();
	
			CustComplaint newComplaint = new CustComplaint();
			newComplaint.setAccNo(acctNo);
			newComplaint.setComplaint_Details(description);
			newComplaint.setComplaintDate(systemDate);
			newComplaint.setStatus(ComplaintStatus.PENDING);
			
			//successful
			String complaintId = dao.addComplaint(newComplaint);
			return Long.parseLong(complaintId); 
		}
		catch(BillingSystemException e)
		{
			throw e;
		}
		catch(Exception e)
		{
			//fail
			return 0;
			
		}
	}
	
	public long createComplaintByCustomerId(String custId, String description) throws BillingSystemException
	{
		String acctNo = getAcctNoByCustId(custId);
		if(acctNo=="")
			return 0; // account not found for this customer id
		else
			return this.createComplaintByAccount(acctNo,description);
	}
	
	public List<CustComplaint> getComplaintByAccount(String acctNo)
	{		
		return dao.getComplaintList(acctNo);
	}	
	
	public List<CustComplaint> getComplaintByCustomerId(String custId)
	{
		String acctNo = getAcctNoByCustId(custId);		
		return getComplaintByAccount(acctNo);
	}
	
	/***
	 * 
	 * @param complaintId
	 * @param status either "Pending" or "Completed"
	 * @return 1 for success, 0 for fail
	 * @throws BillingSystemException 
	 */
	public int updateComplaint(long complaintId, ComplaintStatus status) throws BillingSystemException
	{
		int success = 0;
		//use system date to be the update Date	
		CustComplaint complaint = dao.getComplaint(Long.toString(complaintId));
		if(complaint!=null)
		{
			complaint.setStatus(status);
			success = dao.updateComplaint(complaint);
		}	

		return success;
	}	
	
	/**
	 * 
	 * @param custId the customer id/ nric to search by
	 * @return the account number for this customer, will be empty string if not found
	 */
	private String getAcctNoByCustId(String custId)
	{
		String acctNo = "";
		
		AccountMgr accountManager = new AccountMgr();
		Customer thisCustomer = accountManager.getCustomerDetailsById(custId);
		if(thisCustomer!=null) //customer found
			acctNo = thisCustomer.getAcct().getAcctNo();
				
		return acctNo;
	}

}
