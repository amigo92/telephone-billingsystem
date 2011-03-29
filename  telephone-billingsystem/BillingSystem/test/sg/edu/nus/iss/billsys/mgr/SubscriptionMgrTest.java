package sg.edu.nus.iss.billsys.mgr;

import java.text.ParseException;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.billsys.constant.PlanType;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.tools.TimeUtils;
import sg.edu.nus.iss.billsys.vo.Customer;
import sg.edu.nus.iss.billsys.vo.SubscriptionPlan;

import junit.framework.TestCase;

/*
 * @author Lem Kian Hoa (Stephen)
 */

public class SubscriptionMgrTest extends TestCase {
	private SubscriptionMgr subMgr;
	private Customer cust;
	
	@Before
	public void setUp() throws Exception {
		subMgr = MgrFactory.getSubscriptionMgr();
		cust = MgrFactory.getAccountMgr().createCustomer(
			"John",
			"S1234567",
			"66667777",
			"700 Hill Street",
			"#11-11",
			"Singapore 100000",
			""
		);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testRegisterNewSubscriptionPlan() {
		try {
			subMgr.registerNewSubscriptionPlan(
				null,
				"61234567",
				PlanType.DigitalVoice,
				TimeUtils.parseDate("2011-01-01 00:00:00"),
				null
			);
			fail();
		} catch (BillingSystemException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			fail();
		}
		try {
			subMgr.registerNewSubscriptionPlan(
				cust.getAccountId(),
				"61234567",
				PlanType.DigitalVoice,
				null,
				null
			);
			fail();
		} catch (BillingSystemException e) {
			e.printStackTrace();
		}
		try {
			subMgr.registerNewSubscriptionPlan(
				cust.getAccountId(),
				"61234567",
				PlanType.DigitalVoice,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				TimeUtils.parseDate("2011-01-01 00:00:00")
			);
			fail();
		} catch (BillingSystemException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			fail();
		}
	}

}
