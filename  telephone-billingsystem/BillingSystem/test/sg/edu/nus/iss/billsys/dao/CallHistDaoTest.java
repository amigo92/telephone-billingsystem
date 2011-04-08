/**
 * 
 */
package sg.edu.nus.iss.billsys.dao;

import static org.junit.Assert.*;

import java.util.List;
import java.util.logging.Level;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.billsys.logger.BillingSystemLogger;
import sg.edu.nus.iss.billsys.vo.BillPeriod;
import sg.edu.nus.iss.billsys.vo.CallHist;

/**
 * @author Veera, Xu Guoneng
 *
 */
public class CallHistDaoTest {
	
	private CallHistDao callHxDao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		try{
			callHxDao=new CallHistDao();
		}catch (Exception e) {
			fail("Exception while initialising CallHistDao !!");
			BillingSystemLogger.log(Level.SEVERE, e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		callHxDao=null;
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.CallHistDao#objectDataMapping(java.lang.String[][])}.
	 */
	@Test
	public void testObjectDataMapping() {
		assertNotNull(callHxDao);
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.CallHistDao#getCallHistByBillDateAcctNo(sg.edu.nus.iss.billsys.vo.BillPeriod, java.lang.String)}.
	 */
	@Test
	public void testGetCallHistByBillDateAcctNo() {
		List<CallHist> list = callHxDao.getCallHistByBillDateAcctNo(new BillPeriod(2011, 3), "SA-2011-03-25-8481362");
		assertEquals(3, list.size());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.CallHistDao#getCallHistByBillDate(sg.edu.nus.iss.billsys.vo.BillPeriod)}.
	 */
	@Test
	public void testGetCallHistByBillDate() {
		List<CallHist> list = callHxDao.getCallHistByBillDate(new BillPeriod(2011, 2));
		assertEquals(0, list.size());
	}

}
