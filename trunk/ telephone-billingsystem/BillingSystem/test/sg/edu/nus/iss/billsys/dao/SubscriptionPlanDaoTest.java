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
import sg.edu.nus.iss.billsys.vo.CableTvPlan;
import sg.edu.nus.iss.billsys.vo.DigitalVoicePlan;
import sg.edu.nus.iss.billsys.vo.MobileVoicePlan;
import sg.edu.nus.iss.billsys.vo.SubscriptionPlan;

/**
 * @author Veera, Lem Kian Hao (Stephen)
 *
 */
public class SubscriptionPlanDaoTest {
	private SubscriptionPlanDao subPlanDao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		try{
			subPlanDao=new SubscriptionPlanDao();
		}catch (Exception e) {
			fail("Exception while initialising SubscriptionPlanDao !!");
			BillingSystemLogger.log(Level.SEVERE, e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		subPlanDao=null;
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.SubscriptionPlanDao#objectDataMapping(java.lang.String[][])}.
	 */
	@Test
	public void testObjectDataMapping() {
		if(subPlanDao==null){
			fail("Exception while initialising SubscriptionPlanDao , objectDataMapping have errors !!");
		}
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.SubscriptionPlanDao#saveObjectData()}.
	 */
	@Test
	public void testSaveObjectData() {
		try{
			subPlanDao.saveObjectData();
		}catch (Exception e) {
			fail("Exception while Saving the Object to File SubscriptionPlanDao.saveObjectData !! , Saving Failed.");
			BillingSystemLogger.log(Level.SEVERE, e);
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.SubscriptionPlanDao#getAccountSubscriptions(java.lang.String)}.
	 */
	@Test
	public void testGetAccountSubscriptions() {
		List<SubscriptionPlan> list = subPlanDao.getAccountSubscriptions(null);
		assertNull(list);
		list = subPlanDao.getAccountSubscriptions("MyAcctNo");
		assertNull(list);
		DigitalVoicePlan digiPlan = new DigitalVoicePlan("DigiPlanId","MyAcctNo","DigiPhoneNo");
		MobileVoicePlan mobilePlan = new MobileVoicePlan("MobilePlanId","MyAcctNo","MobilePhoneNo");
		CableTvPlan cablePlan = new CableTvPlan("CablePlanId","MyAcctNo");
		
		assertTrue(subPlanDao.addAccountSubscription("MyAcctNo",digiPlan));
		list = subPlanDao.getAccountSubscriptions("MyAcctNo");
		assertNotNull(list);
		assertEquals(1,list.size());
		assertEquals(digiPlan,list.get(0));
		
		assertTrue(subPlanDao.addAccountSubscription("MyAcctNo",mobilePlan));
		list = subPlanDao.getAccountSubscriptions("MyAcctNo");
		assertNotNull(list);
		assertEquals(2,list.size());
		assertEquals(mobilePlan,list.get(1));
		
		assertTrue(subPlanDao.addAccountSubscription("MyAcctNo",cablePlan));
		list = subPlanDao.getAccountSubscriptions("MyAcctNo");
		assertNotNull(list);
		assertEquals(3,list.size());		
		assertEquals(cablePlan,list.get(2));
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.SubscriptionPlanDao#getAccountSubscription(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGetAccountSubscription() {
		DigitalVoicePlan plan = new DigitalVoicePlan("PlanId","AcctNo","PhoneNo");
		assertTrue(subPlanDao.addAccountSubscription("AcctNo",plan));
		assertNull(subPlanDao.getAccountSubscription(null, plan.getPlanId()));
		assertNull(subPlanDao.getAccountSubscription("AcctNo", null));
		SubscriptionPlan subPlan = subPlanDao.getAccountSubscription("AcctNo", plan.getPlanId());
		assertNotNull(subPlan);
		assertEquals(plan.getPlanId(),subPlan.getPlanId());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.SubscriptionPlanDao#addAccountSubscriptions(java.lang.String, sg.edu.nus.iss.billsys.vo.SubscriptionPlan)}.
	 */
	@Test
	public void testAddAccountSubscriptions() {
		DigitalVoicePlan plan = new DigitalVoicePlan("PlanId","AcctNo","PhoneNo");
		assertFalse(subPlanDao.addAccountSubscription(null,plan));
		assertFalse(subPlanDao.addAccountSubscription("AcctNo",null));
		assertTrue(subPlanDao.addAccountSubscription("AcctNo",plan));
	}

}
