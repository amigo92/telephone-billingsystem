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
public class PaymentHistDaoTest {
	private PaymentHistDao payHxDao; 

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		try{
			payHxDao=new PaymentHistDao();
		}catch (Exception e) {
			fail("Exception while initialising PaymentHistDao !!");
			BillingSystemLogger.log(Level.SEVERE, e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		payHxDao=null;
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.PaymentHistDao#objectDataMapping(java.lang.String[][])}.
	 */
	@Test
	public void testObjectDataMapping() {
		if(payHxDao==null){
			fail("Exception while initialising PaymentHistDao , objectDataMapping have errors !!");
		}
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.PaymentHistDao#getPaymentHistByBillPeriodAcctNo(sg.edu.nus.iss.billsys.vo.BillPeriod, java.lang.String)}.
	 */
	@Test
	public void testGetPaymentHistByBillPeriodAcctNo() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.PaymentHistDao#getPaymentHistByBillPeriod(sg.edu.nus.iss.billsys.vo.BillPeriod)}.
	 */
	@Test
	public void testGetPaymentHistByBillPeriod() {
		fail("Not yet implemented");
	}

}
