/**
 * 
 */
package sg.edu.nus.iss.billsys.dao;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.billsys.constant.ComplaintStatus;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.logger.BillingSystemLogger;
import sg.edu.nus.iss.billsys.tools.TimeUtils;
import sg.edu.nus.iss.billsys.vo.CustComplaint;

/**
 * @author Veera, Yu Chui Chi
 *
 */
public class ComplaintsDaoTest {
	private ComplaintsDao complaintDao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		try{
			complaintDao=new ComplaintsDao();
		}catch (Exception e) {
			fail("Exception while initialising ComplaintsDao !!");
			BillingSystemLogger.log(Level.SEVERE, e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		complaintDao=null;
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.ComplaintsDao#objectDataMapping(java.lang.String[][])}.
	 */
	@Test
	public void testObjectDataMapping() {
		if(complaintDao==null){
			fail("Exception while initialising ComplaintsDao , objectDataMapping have errors !!");
		}
	}
	
	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.ComplaintsDao#saveObjectData()}.
	 */
	@Test
	public void testSaveObjectData() {
		
		try{
			complaintDao.saveObjectData();
		}catch (Exception e) {
			fail("Exception while Saving the Object to File ComplaintsDao.saveObjectData !! , Saving Failed.");
			BillingSystemLogger.log(Level.SEVERE, e);
			e.printStackTrace();
		}
		
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.ComplaintsDao#getComplaintList(java.lang.String)}.
	 * @throws  
	 */
	@Test
	public void testGetComplaintList() {
		try
		{
			List<CustComplaint> list = complaintDao.getComplaintList("SA-2011-03-25-8481366");
			if(list==null)
				fail("Unable to get Complaint list");
			assert(!list.isEmpty());
			
			CustComplaint obj = list.get(0);
			if(obj==null)
				fail("Unable to get Complaint Object");
			assertEquals("1",obj.getComplaint_id());
			assertEquals("SA-2011-03-25-8481366",obj.getAccNo());
			assertEquals("Calls are getting Disconnected Often",obj.getComplaint_Details());
			assertEquals(TimeUtils.parseDate("2010-02-17 18:10:01"),obj.getComplaintDate());			
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.ComplaintsDao#getComplaint(java.lang.String)}.
	 */
	@Test
	public void testGetComplaint() {
		try
		{
			CustComplaint obj = complaintDao.getComplaint("1");
			if(obj==null)
				fail("Unable to get Complaint Object");
			
			assertEquals("1",obj.getComplaint_id());
			assertEquals("SA-2011-03-25-8481366",obj.getAccNo());
			assertEquals("Calls are getting Disconnected Often",obj.getComplaint_Details());
			assertEquals(TimeUtils.parseDate("2010-02-17 18:10:01"),obj.getComplaintDate());			
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.ComplaintsDao#addComplaint(sg.edu.nus.iss.billsys.vo.CustComplaint)}.
	 */
	@Test
	public void testAddComplaint() {
		try
		{
			//system date for the creation date
			 Date systemDate = new Date();
			 String acctNo = "SA-2011-03-25-8481364"; 
			 String description = "The bill is way too expensive!";
	
			CustComplaint newComplaint = new CustComplaint();
			newComplaint.setAccNo(acctNo);
			newComplaint.setComplaint_Details(description);
			newComplaint.setComplaintDate(systemDate);
			newComplaint.setStatus(ComplaintStatus.PENDING);		
			
			//successful
			String complaintId = complaintDao.addComplaint(newComplaint);
			assert(!complaintId.isEmpty());
			 
		}		
		catch(Exception e)
		{
			fail(e.getMessage());			
		}
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.ComplaintsDao#updateComplaint(sg.edu.nus.iss.billsys.vo.CustComplaint)}.
	 */
	@Test
	public void testUpdateComplaint() {
		
		String complaintId = "2";
		int success = 0;
		
		try
		{
			//use system date to be the update Date	
			CustComplaint complaint = complaintDao.getComplaint(complaintId);
			if(complaint!=null)
			{
				complaint.setStatus(ComplaintStatus.COMPLETED);
				success = complaintDao.updateComplaint(complaint);
			}
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
		
		assert(success!=0);
	}

}
