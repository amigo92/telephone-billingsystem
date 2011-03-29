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
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.SubscriptionPlanDao#getAccountSubscription(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGetAccountSubscription() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.SubscriptionPlanDao#addAccountSubscriptions(java.lang.String, sg.edu.nus.iss.billsys.vo.SubscriptionPlan)}.
	 */
	@Test
	public void testAddAccountSubscriptions() {
		fail("Not yet implemented");
	}

}
