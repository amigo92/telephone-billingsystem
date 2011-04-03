/**
 * 
 */
package sg.edu.nus.iss.billsys.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.billsys.logger.BillingSystemLogger;
import sg.edu.nus.iss.billsys.vo.Customer;

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
		String testNRIC = "S8481366F";
		if(custDao == null){
			fail("Exception while initialising CustomerDao , objectDataMapping have errors !!");
		}
		//System.out.println(custDao.getCustomerByNric(testNRIC).getAccountId());
		assertEquals(testNRIC,custDao.getCustomerByNric(testNRIC).getNric());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.CustomerDao#getCustomerByName(java.lang.String)}.
	 */
	@Test
	public void testGetCustomerByName() {
		String testName = "Veera";
		if(custDao == null){
			fail("Exception while initialising CustomerDao , objectDataMapping have errors !!");
		}
		//System.out.println(custDao.getCustomerByNric(testNRIC).getAccountId());
		assertEquals(testName,custDao.getCustomerByName(testName).getName());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.CustomerDao#getCustomerByAcctId(java.lang.String)}.
	 */
	@Test
	public void testGetCustomerByAcctId() {
		String testAcct = "SA-2011-03-25-8481362";
		if(custDao == null){
			fail("Exception while initialising CustomerDao , objectDataMapping have errors !!");
		}
		//System.out.println(custDao.getCustomerByNric(testNRIC).getAccountId());
		assertEquals(testAcct,custDao.getCustomerByAcctId(testAcct).getAccountId());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.CustomerDao#addCustomer(sg.edu.nus.iss.billsys.vo.Customer)}.
	 */
	@Test


	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.CustomerDao#getAllCustomers()}.
	 */

	public void testGetAllCustomers() {
		ArrayList<Customer> custList = custDao.getAllCustomers();
		if(custList == null){
			fail("Exception get all customers , getAllCustomers have errors !!");
		}
		/*for (Iterator<Customer> iter = custList.iterator(); iter.hasNext();) 
		{
			Customer element = (Customer) iter.next();
			System.out.println(element.getName() + "\n");
		}*/
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.CustomerDao#getAllActiveCustomers()}.
	 */
	@Test
	public void testGetAllActiveCustomers() {
		ArrayList<Customer> custList = custDao.getAllActiveCustomers();
		if(custList == null){
			fail("Exception get active customers , getAllActiveCustomers have errors !!");
		}
		/*for (Iterator<Customer> iter = custList.iterator(); iter.hasNext();) 
		{
			Customer element = (Customer) iter.next();
			System.out.println(element.getName() + "\n");
		}*/
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.CustomerDao#deleteCust(java.lang.String, java.util.Date)}.
	 */
	@Test
	public void testDeleteCust() {
		if(custDao == null){
			fail("Exception in dao generation , objectDataMapping have errors !!");
		}
		String testAcct = "SA-2011-03-25-8481361";
		custDao.deleteCust(testAcct, Calendar.getInstance().getTime());
		assertEquals(true,custDao.getCustomerByAcctId(testAcct).getIsDeleted());
		
	}

}
