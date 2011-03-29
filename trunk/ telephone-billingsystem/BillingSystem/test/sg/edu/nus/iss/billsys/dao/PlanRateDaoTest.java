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
import sg.edu.nus.iss.billsys.vo.Rate;

/**
 * @author Veera
 *
 */
public class PlanRateDaoTest {
	private PlanRateDao planRateDao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		try{
			planRateDao=new PlanRateDao();
		}catch (Exception e) {
			fail("Exception while initialising PlanRateDao !!");
			BillingSystemLogger.log(Level.SEVERE, e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		planRateDao=null;
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.PlanRateDao#objectDataMapping(java.lang.String[][])}.
	 */
	@Test
	public void testObjectDataMapping() {
		if(planRateDao==null){
			fail("Exception while initialising PlanRateDao , objectDataMapping have errors !!");
		}
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.PlanRateDao#getPricebyPlanType(java.lang.String)}.
	 */
	@Test
	public void testGetPricebyPlanType() {
		try{
			planRateDao.getPricebyPlanType(null);
			fail("Code should not be reaching here and should have thrown a Exception for getPricebyPlanType(null) !!");
			
		}catch (Exception e) {
			
			BillingSystemLogger.log(Level.SEVERE, e);
			e.printStackTrace();
		}
		
		try{
			Rate rate=planRateDao.getPricebyPlanType("0");
			
			if(rate==null){
				fail("Failed to retrieve the user by Name getPricebyPlanType(0) !!");
			}
			
			rate=planRateDao.getPricebyPlanType("XXX");
			
			if(rate!=null){
				fail("Failed to return null for a invalid user by Name getPricebyPlanType(XXX) !!");
			}
			
		}catch (Exception e) {
			fail("Exception while testing getPricebyPlanType in PlanRateDao !!");
			BillingSystemLogger.log(Level.SEVERE, e);
			e.printStackTrace();
		}
	}

}
