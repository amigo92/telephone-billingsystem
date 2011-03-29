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
public class FeatureRateTest {
	
	private FeatureRateDao featureRateDao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		try{
			featureRateDao=new FeatureRateDao();
		}catch (Exception e) {
			fail("Exception while initialising FeatureRateDao !!");
			BillingSystemLogger.log(Level.SEVERE, e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		featureRateDao=null;
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.FeatureRateDao#objectDataMapping(java.lang.String[][])}.
	 */
	@Test
	public void testObjectDataMapping() {
		if(featureRateDao==null){
			fail("Exception while initialising FeatureRateDao , objectDataMapping have errors !!");
		}
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.FeatureRateDao#getPricebyFeatureCode(int)}.
	 */
	@Test
	public void testGetPricebyFeatureCode() {
		
		try{
			Rate rate=featureRateDao.getPricebyFeatureCode(0);
			
			if(rate==null){
				fail("Failed to retrieve the user by Name getPricebyFeatureCode(0) !!");
			}
			
			rate=featureRateDao.getPricebyFeatureCode(-1);
			
			if(rate!=null){
				fail("Failed to return null for a invalid user by Name getPricebyFeatureCode(-1) !!");
			}
			
		}catch (Exception e) {
			fail("Exception while testing getPricebyFeatureCode in FeatureRateDao !!");
			BillingSystemLogger.log(Level.SEVERE, e);
			e.printStackTrace();
		}
	}

}
