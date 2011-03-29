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
		if(callHxDao==null){
			fail("Exception while initialising CallHistDao , objectDataMapping have errors !!");
		}
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.CallHistDao#getCallHistByBillDateAcctNo(sg.edu.nus.iss.billsys.vo.BillPeriod, java.lang.String)}.
	 */
	@Test
	public void testGetCallHistByBillDateAcctNo() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.CallHistDao#getCallHistByBillDate(sg.edu.nus.iss.billsys.vo.BillPeriod)}.
	 */
	@Test
	public void testGetCallHistByBillDate() {
		fail("Not yet implemented");
	}

}
