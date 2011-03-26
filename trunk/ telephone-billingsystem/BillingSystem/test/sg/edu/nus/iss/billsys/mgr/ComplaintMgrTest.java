/**	
 * 
 */
package sg.edu.nus.iss.billsys.mgr;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.billsys.constant.ComplaintStatus;
import sg.edu.nus.iss.billsys.vo.CustComplaint;

/**
 * @author Yu Chui Chi
 *
 */
public class ComplaintMgrTest {

	String acctNo;
	String nric;
	long complaintId;
	String description;
	ComplaintStatus status;
	ComplaintMgr manager;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.mgr.ComplaintMgr#ComplaintMgr()}.
	 */
	@Test
	public void testComplaintMgr() {
		manager = new ComplaintMgr();
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.mgr.ComplaintMgr#createComplaintByAccount(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testCreateComplaintByAccount() {
		manager = new ComplaintMgr();
		acctNo = "SA-2011-03-25-8481364";
		description = "Unable to see the tv channel that I have subscribed!";
		complaintId = manager.createComplaintByAccount(acctNo, description);
		
		if(complaintId==0)
			fail("Unable to create complaint by account");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.mgr.ComplaintMgr#createComplaintByCustomerId(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testCreateComplaintByCustomerId() {
		manager = new ComplaintMgr();
		nric = "S8481361F";
		description = "I am being over-charged!!";
		complaintId = manager.createComplaintByCustomerId(nric, description);
		if(complaintId==0)
			fail("Unable to create complaint by customer id");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.mgr.ComplaintMgr#getComplaintByAccount(java.lang.String)}.
	 */
	@Test
	public void testGetComplaintByAccount() {
		manager = new ComplaintMgr();
		acctNo = "SA-2011-03-25-8481364";
		List<CustComplaint> complaints = manager.getComplaintByAccount(acctNo);
		System.out.println("Get Complaints for account:" + acctNo);
		System.out.println("############################################");
		for(CustComplaint complaint : complaints)
		{
			System.out.println("Complaint Id: " + complaint.getComplaint_id());
			System.out.println("Account No: " + complaint.getAccNo());
			System.out.println("Complaint Date: " + complaint.getComplaintDate());
			System.out.println("Complaint Details: " + complaint.getComplaint_Details());
			System.out.println("Complaint Status: " + complaint.getStatus().toString());
			System.out.println("############################################");
		}
		
		if(complaints.isEmpty())
			fail("Unable to get list of complaints by account number: " + acctNo);
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.mgr.ComplaintMgr#getComplaintByCustomerId(java.lang.String)}.
	 */
	@Test
	public void testGetComplaintByCustomerId() {
		manager = new ComplaintMgr();
		nric = "S8481362F";
		List<CustComplaint> complaints = manager.getComplaintByCustomerId(nric);
		System.out.println("Get Complaints for customer id:" + nric);
		System.out.println("############################################");
		for(CustComplaint complaint : complaints)
		{
			System.out.println("Complaint Id: " + complaint.getComplaint_id());
			System.out.println("Account No: " + complaint.getAccNo());
			System.out.println("Complaint Date: " + complaint.getComplaintDate());
			System.out.println("Complaint Details: " + complaint.getComplaint_Details());
			System.out.println("Complaint Status: " + complaint.getStatus().toString());
			System.out.println("############################################");		
		}
		
		if(complaints.isEmpty())
			fail("Unable to get list of complaints by customer id:" + nric);
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.mgr.ComplaintMgr#updateComplaint(long, sg.edu.nus.iss.billsys.constant.ComplaintStatus)}.
	 */
	@Test
	public void testUpdateComplaint() {
		manager = new ComplaintMgr();
		
		acctNo = "SA-2011-03-25-8481364";
		List<CustComplaint> complaints;
		CustComplaint complaint;
		
		// get the complaint to update
		complaints = manager.getComplaintByAccount(acctNo);
		complaint = complaints.get(0);
				
		System.out.println("Update Status to Pending");
		System.out.println("**********************************************");
		manager.updateComplaint(Long.parseLong(complaint.getComplaint_id()), ComplaintStatus.PENDING);
		
		complaints = manager.getComplaintByAccount(acctNo);
		complaint = complaints.get(0);
		System.out.println("Complaint Id: " + complaint.getComplaint_id());
		System.out.println("Complaint Status: " + complaint.getStatus().toString());
		if(complaint.getStatus() != ComplaintStatus.PENDING)
			fail("Complaint Status not updated to Pending");
		
		System.out.println("**********************************************");
		System.out.println("Update Status to Completed");
		System.out.println("**********************************************");
		manager.updateComplaint(Long.parseLong(complaint.getComplaint_id()), ComplaintStatus.COMPLETED);
		
		complaints = manager.getComplaintByAccount(acctNo);
		complaint = complaints.get(0);
		System.out.println("Complaint Id: " + complaint.getComplaint_id());
		System.out.println("Complaint Status: " + complaint.getStatus().toString());
		if(complaint.getStatus() != ComplaintStatus.COMPLETED)
			fail("Complaint Status not updated to Completed");		
	}

}
