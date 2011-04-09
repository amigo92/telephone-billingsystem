package sg.edu.nus.iss.billsys.mgr;

import java.util.List;

import java.util.*;

import sg.edu.nus.iss.billsys.constant.*;
import sg.edu.nus.iss.billsys.dao.*;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.vo.*;


/**
 *  
 * To create, update and retrieval of complaints.
 * It make use of the ComplaintsDao to access the data file 
 * and provide the functions to the GUI
 * 
 * @author Yu Chui Chi
 */

public class ComplaintMgr {
	
	private IComplaintsDao dao;
	
	protected ComplaintMgr() throws BillingSystemException
	{
		dao = DaoFactory.getInstanceOfComplaintsDao();
	}
	
	/**
	 * To create a complaint by account number
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
	
	/***
	 * to create a complaint by customer id
	 * @param custId the customer id (NRIC)
	 * @param description the complaint description
	 * @return the newly created complaint id
	 * @throws BillingSystemException
	 */
	public long createComplaintByCustomerId(String custId, String description) throws BillingSystemException
	{
		String acctNo = getAcctNoByCustId(custId);
		if(acctNo=="")
			return 0; // account not found for this customer id
		else
			return this.createComplaintByAccount(acctNo,description);
	}
	
	/***
	 * Get list of complaints belonging to this account
	 * @param acctNo the account number to search for
	 * @return a list of complaints belong to this account, return empty list if there is no complaints
	 */
	public List<CustComplaint> getComplaintByAccount(String acctNo)
	{		
		return dao.getComplaintList(acctNo);
	}	
	
	/**
	 * Get list of complaints belonging to this customer id
	 * @param custId the customer id to search for
	 * @return a list of complaints belong to this customer, return empty list if there is no complaints
	 */
	public List<CustComplaint> getComplaintByCustomerId(String custId)
	{
		String acctNo = getAcctNoByCustId(custId);		
		return getComplaintByAccount(acctNo);
	}
	
	/***
	 * Update the complaint status
	 * @param complaintId the complaint id to update for
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
	 * to get the account number base on the customer id
	 * @param custId the customer id/ nric to search by
	 * @return the account number for this customer, will be empty string if not found
	 */
	private String getAcctNoByCustId(String custId)
	{
		String acctNo = "";
		try{
		AccountMgr accountManager = new AccountMgr();
		Customer thisCustomer = accountManager.getCustomerDetailsById(custId);
		if(thisCustomer!=null) //customer found
			acctNo = thisCustomer.getAcct().getAcctNo();
		}catch (Exception e) {
			// TODO: handle exception
		}
				
		return acctNo;
	}

}
