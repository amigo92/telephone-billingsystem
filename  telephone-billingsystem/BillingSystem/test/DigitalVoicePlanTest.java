import java.util.Date;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.billsys.constant.FeatureType;
import sg.edu.nus.iss.billsys.constant.PlanType;
import sg.edu.nus.iss.billsys.dao.SubscriptionPlanDao;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.tools.TimeUtils;
import sg.edu.nus.iss.billsys.vo.DigitalVoicePlan;
import sg.edu.nus.iss.billsys.vo.Feature;


public class DigitalVoicePlanTest extends TestCase {

	String planId1;
	String acctNo1;
	String assignedTelNo1;
	String basicFeatureId1;
	Date dateCommenced1;
	Date dateTerminated1;
	DigitalVoicePlan digiPlan1;
	
	@Before
	public void setUp() throws Exception {
		planId1 = SubscriptionPlanDao.generateSequence();
		acctNo1 = "SA-1234567";
		assignedTelNo1 = "66667777";
		basicFeatureId1 = SubscriptionPlanDao.generateSequence();
		dateCommenced1 = TimeUtils.parseDate("2011-03-29 00:00:00");
		dateTerminated1 = null;
		digiPlan1 = new DigitalVoicePlan(planId1, acctNo1, assignedTelNo1, basicFeatureId1, dateCommenced1, dateTerminated1);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetPlanId() {
		assertEquals(planId1,digiPlan1.getPlanId());
	}
	
	@Test
	public void testGetPlanType() {
		assertEquals(PlanType.DigitalVoice,digiPlan1.getPlanType());
	}
	
	@Test
	public void testGetAcctNo() {
		assertEquals(acctNo1,digiPlan1.getAcctNo());
	}
	
	@Test
	public void testGetAssignedTelNo() {
		assertEquals(assignedTelNo1,digiPlan1.getAssignedTelNo());
	}
	
	@Test
	public void testGetBasicFeature() {
		assertEquals(basicFeatureId1,digiPlan1.getBasicFeature().getFeatureId());
	}

	@Test
	public void testGetDateCommenced() {
		assertEquals(dateCommenced1,digiPlan1.getDateCommenced());
	}

	@Test
	public void testGetDateTerminated() {
		assertEquals(dateTerminated1,digiPlan1.getDateTerminated());
	}

	@Test
	public void testAddOptionalFeature() {
		try {
			Feature f = new Feature(SubscriptionPlanDao.generateSequence(),FeatureType.DigiIDD,new Date(),null);
			digiPlan1.addOptionalFeature(f);
		} catch (BillingSystemException bsExp) {
			fail();
		}
		try {
			Feature f = new Feature(SubscriptionPlanDao.generateSequence(),FeatureType.CallTransfer,new Date(),null);
			digiPlan1.addOptionalFeature(f);
		} catch (BillingSystemException bsExp) {
			fail();
		}
		try {
			Feature f = new Feature(SubscriptionPlanDao.generateSequence(),FeatureType.MobileIDD,new Date(),null);
			digiPlan1.addOptionalFeature(f);
			fail();
		} catch (BillingSystemException bsExp) {
		}
		try {
			Feature f = new Feature(SubscriptionPlanDao.generateSequence(),FeatureType.DataService,new Date(),null);
			digiPlan1.addOptionalFeature(f);
			fail();
		} catch (BillingSystemException bsExp) {
		}
		try {
			Feature f = new Feature(SubscriptionPlanDao.generateSequence(),FeatureType.Roaming,new Date(),null);
			digiPlan1.addOptionalFeature(f);
			fail();
		} catch (BillingSystemException bsExp) {
		}
		try {
			Feature f = new Feature(SubscriptionPlanDao.generateSequence(),FeatureType.AddChannel,new Date(),null);
			digiPlan1.addOptionalFeature(f);
			fail();
		} catch (BillingSystemException bsExp) {
		}
	}
}
