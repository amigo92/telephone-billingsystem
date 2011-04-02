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
public class MobileNumbersDaoTest {
	private MobileNumbersDao mobiDao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		try{
			mobiDao=new MobileNumbersDao();
		}catch (Exception e) {
			fail("Exception while initialising MobileNumbersDao !!");
			BillingSystemLogger.log(Level.SEVERE, e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		mobiDao=null;
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.MobileNumbersDao#objectDataMapping()}.
	 */
	@Test
	public void testObjectDataMapping() {
		if(mobiDao==null){
			fail("Exception while initialising MobileNumbersDao , objectDataMapping have errors !!");
		}
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.MobileNumbersDao#saveObjectData()}.
	 */
	@Test
	public void testSaveObjectData() {
		try{
			mobiDao.saveObjectData();
		}catch (Exception e) {
			fail("Exception while Saving the Object to File MobileNumbersDao.saveObjectData !! , Saving Failed.");
			BillingSystemLogger.log(Level.SEVERE, e);
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.MobileNumbersDao#MobileNumbersDao()}.
	 */
	@Test
	public void testMobileNumbersDao() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.MobileNumbersDao#getPhoneNumbers()}.
	 */
	@Test
	public void testGetPhoneNumbers() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.MobileNumbersDao#removePhoneNumber(java.lang.String)}.
	 */
	@Test
	public void testRemovePhoneNumber() {
		fail("Not yet implemented");
	}

}
