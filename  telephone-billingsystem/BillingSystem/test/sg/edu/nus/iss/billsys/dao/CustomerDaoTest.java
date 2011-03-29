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
public class CustomerDaoTest {
	private CustomerDao custDao;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		try{
			custDao=new CustomerDao();
		}catch (Exception e) {
			fail("Exception while initialising CustomerDao !!");
			BillingSystemLogger.log(Level.SEVERE, e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		custDao=null;
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.CustomerDao#objectDataMapping(java.lang.String[][])}.
	 */
	@Test
	public void testObjectDataMapping() {
		if(custDao==null){
			fail("Exception while initialising CustomerDao , objectDataMapping have errors !!");
		}
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.CustomerDao#saveObjectData()}.
	 */
	@Test
	public void testSaveObjectData() {
		try{
			custDao.saveObjectData();
		}catch (Exception e) {
			fail("Exception while Saving the Object to File CustomerDao.saveObjectData !! , Saving Failed.");
			BillingSystemLogger.log(Level.SEVERE, e);
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.CustomerDao#getCustomerByNric(java.lang.String)}.
	 */
	@Test
	public void testGetCustomerByNric() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.CustomerDao#getCustomerByName(java.lang.String)}.
	 */
	@Test
	public void testGetCustomerByName() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.CustomerDao#getCustomerByAcctId(java.lang.String)}.
	 */
	@Test
	public void testGetCustomerByAcctId() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.CustomerDao#addCustomer(sg.edu.nus.iss.billsys.vo.Customer)}.
	 */
	@Test
	public void testAddCustomer() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.CustomerDao#getAllCustomers()}.
	 */
	@Test
	public void testGetAllCustomers() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.CustomerDao#getAllActiveCustomers()}.
	 */
	@Test
	public void testGetAllActiveCustomers() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.CustomerDao#deleteCust(java.lang.String, java.util.Date)}.
	 */
	@Test
	public void testDeleteCust() {
		fail("Not yet implemented");
	}

}
