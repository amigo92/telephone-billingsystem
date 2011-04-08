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
import sg.edu.nus.iss.billsys.vo.PaymentHist;

/**
 * @author Veera, Xu Guoneng
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
		assertNotNull(payHxDao);
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.PaymentHistDao#getPaymentHistByBillPeriodAcctNo(sg.edu.nus.iss.billsys.vo.BillPeriod, java.lang.String)}.
	 */
	@Test
	public void testGetPaymentHistByBillPeriodAcctNo() {
		List<PaymentHist> list = payHxDao.getPaymentHistByBillPeriodAcctNo(new BillPeriod(2011, 4), "SA-2011-03-25-8481361");
		assertEquals(2, list.size());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.PaymentHistDao#getPaymentHistByBillPeriod(sg.edu.nus.iss.billsys.vo.BillPeriod)}.
	 */
	@Test
	public void testGetPaymentHistByBillPeriod() {
		List<PaymentHist> list = payHxDao.getPaymentHistByBillPeriod(new BillPeriod(2011, 5));
		assertEquals(1, list.size());
	}

}
