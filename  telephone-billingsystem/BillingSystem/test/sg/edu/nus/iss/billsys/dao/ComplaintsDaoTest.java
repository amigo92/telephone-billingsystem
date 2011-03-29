/**
 * 
 */
package sg.edu.nus.iss.billsys.dao;

import static org.junit.Assert.*;

import java.util.logging.Level;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.billsys.logger.BillingSystemLogger;

/**
 * @author Veera
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
	 */
	@Test
	public void testGetComplaintList() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.ComplaintsDao#getComplaint(java.lang.String)}.
	 */
	@Test
	public void testGetComplaint() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.ComplaintsDao#addComplaint(sg.edu.nus.iss.billsys.vo.CustComplaint)}.
	 */
	@Test
	public void testAddComplaint() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.ComplaintsDao#updateComplaint(sg.edu.nus.iss.billsys.vo.CustComplaint)}.
	 */
	@Test
	public void testUpdateComplaint() {
		fail("Not yet implemented");
	}

}
