package sg.edu.nus.iss.billsys.vo;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.billsys.constant.FeatureType;
import sg.edu.nus.iss.billsys.constant.PlanType;
import sg.edu.nus.iss.billsys.dao.DaoFactory;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.tools.TimeUtils;
import sg.edu.nus.iss.billsys.vo.Feature;

/*
 * @author Lem Kian Hoa (Stephen)
 */

public class DigitalVoicePlanTest extends TestCase {

	String planId1;
	String acctNo1;
	String assignedTelNo1;
	String basicFeatureId1;
	Date dateCommenced1;
	Date dateTerminated1;
	DigitalVoicePlan plan1;
	
	@Before
	public void setUp() throws Exception {
		planId1 = DaoFactory.getInstanceOfSubscriptionPlanDao().generateSequence();
		acctNo1 = "SA-1234567";
		assignedTelNo1 = "66667777";
		basicFeatureId1 = DaoFactory.getInstanceOfSubscriptionPlanDao().generateSequence();
		dateCommenced1 = TimeUtils.parseDate("2011-01-01 00:00:00");
		dateTerminated1 = null;
		plan1 = new DigitalVoicePlan(planId1, acctNo1, assignedTelNo1, basicFeatureId1, dateCommenced1, dateTerminated1);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetPlanId() {
		assertEquals(planId1,plan1.getPlanId());
	}
	
	@Test
	public void testGetPlanType() {
		assertEquals(PlanType.DigitalVoice,plan1.getPlanType());
	}
	
	@Test
	public void testGetAcctNo() {
		assertEquals(acctNo1,plan1.getAcctNo());
	}
	
	@Test
	public void testGetAssignedTelNo() {
		assertEquals(assignedTelNo1,plan1.getAssignedTelNo());
	}
	
	@Test
	public void testGetBasicFeature() {
		assertEquals(basicFeatureId1,plan1.getBasicFeature().getFeatureId());
	}

	@Test
	public void testGetDateCommenced() {
		assertEquals(dateCommenced1,plan1.getDateCommenced());
	}

	@Test
	public void testGetDateTerminated() {
		assertEquals(dateTerminated1,plan1.getDateTerminated());
		try {
			dateTerminated1 = TimeUtils.parseDate("2012-01-01 00:00:00");
		} catch (ParseException e) {
		}
		plan1.setDateTerminated(dateTerminated1);
		assertEquals(dateTerminated1,plan1.getDateTerminated());
	}

	@Test
	public void testAddOptionalFeature() {
		String fidIDD = null;
		try {
			fidIDD = DaoFactory.getInstanceOfSubscriptionPlanDao().generateSequence();
			Feature f = new Feature(
				fidIDD,
				FeatureType.DigiIDD,
				TimeUtils.parseDate("2011-01-01 00:00:00"),
				null
			);
			plan1.addOptionalFeature(f);
		} catch (BillingSystemException bsExp) {
			fail();
		} catch (ParseException e) {
			fail();
		}
		String fidCallTransfer = null;
		try {
			fidCallTransfer = DaoFactory.getInstanceOfSubscriptionPlanDao().generateSequence();
			Feature f = new Feature(
				fidCallTransfer,
				FeatureType.CallTransfer,
				TimeUtils.parseDate("2011-01-01 00:00:00"),
				null
			);
			plan1.addOptionalFeature(f);
		} catch (BillingSystemException bsExp) {
			fail();
		} catch (ParseException e) {
			fail();
		}
		try {
			Feature f = new Feature(
				DaoFactory.getInstanceOfSubscriptionPlanDao().generateSequence(),
				FeatureType.MobileIDD,
				TimeUtils.parseDate("2011-01-01 00:00:00"),
				null
			);
			plan1.addOptionalFeature(f);
			fail();
		} catch (BillingSystemException bsExp) {
			System.out.println(bsExp.getMessage());
		} catch (ParseException e) {
			fail();
		}
		try {
			Feature f = new Feature(
				DaoFactory.getInstanceOfSubscriptionPlanDao().generateSequence(),
				FeatureType.DataService,
				TimeUtils.parseDate("2011-01-01 00:00:00"),
				null
			);
			plan1.addOptionalFeature(f);
			fail();
		} catch (BillingSystemException bsExp) {
			System.out.println(bsExp.getMessage());
		} catch (ParseException e) {
			fail();
		}
		try {
			Feature f = new Feature(
				DaoFactory.getInstanceOfSubscriptionPlanDao().generateSequence(),
				FeatureType.Roaming,
				TimeUtils.parseDate("2011-01-01 00:00:00"),
				null
			);
			plan1.addOptionalFeature(f);
			fail();
		} catch (BillingSystemException bsExp) {
			System.out.println(bsExp.getMessage());
		} catch (ParseException e) {
			fail();
		}
		try {
			Feature f = new Feature(
				DaoFactory.getInstanceOfSubscriptionPlanDao().generateSequence(),
				FeatureType.AddChannel,
				TimeUtils.parseDate("2011-01-01 00:00:00"),
				null
			);
			plan1.addOptionalFeature(f);
			fail();
		} catch (BillingSystemException bsExp) {
			System.out.println(bsExp.getMessage());
		} catch (ParseException e) {
			fail();
		}
		try {
			Feature f = new Feature(
				DaoFactory.getInstanceOfSubscriptionPlanDao().generateSequence(),
				FeatureType.DigiIDD,
				TimeUtils.parseDate("2011-01-01 00:00:00"),
				null
			);
			plan1.addOptionalFeature(f);
			fail();
		} catch (BillingSystemException bsExp) {
			System.out.println(bsExp.getMessage());
		} catch (ParseException e) {
			fail();
		}
		try {
			Feature f = new Feature(
				DaoFactory.getInstanceOfSubscriptionPlanDao().generateSequence(),
				FeatureType.CallTransfer,
				TimeUtils.parseDate("2011-01-01 00:00:00"),
				null
			);
			plan1.addOptionalFeature(f);
			fail();
		} catch (BillingSystemException bsExp) {
			System.out.println(bsExp.getMessage());
		} catch (ParseException e) {
			fail();
		}
		try {
			Feature f = plan1.getOptionalFeatureById(fidIDD);
			f.setDateTerminated(TimeUtils.parseDate("2011-02-01 00:00:00"));
			f = new Feature(
				DaoFactory.getInstanceOfSubscriptionPlanDao().generateSequence(),
				FeatureType.DigiIDD,
				TimeUtils.parseDate("2011-03-01 00:00:00"),
				null
			);
			plan1.addOptionalFeature(f);
		} catch (BillingSystemException bsExp) {
			fail();
		} catch (ParseException e) {
			fail();
		}
		try {
			Feature f = plan1.getOptionalFeatureById(fidCallTransfer);
			f.setDateTerminated(TimeUtils.parseDate("2011-02-01 00:00:00"));
			f = new Feature(
				DaoFactory.getInstanceOfSubscriptionPlanDao().generateSequence(),
				FeatureType.CallTransfer,
				TimeUtils.parseDate("2011-03-01 00:00:00"),
				null
			);
			plan1.addOptionalFeature(f);
		} catch (BillingSystemException bsExp) {
			fail();
		} catch (ParseException e) {
			fail();
		}
	}

	@Test
	public void testGetOptionalFeatures() {
		List<Feature> list = plan1.getOptionalFeatures();
		assertSame(0,list.size());
		Feature f = null;
		try {
			f = new Feature(
				DaoFactory.getInstanceOfSubscriptionPlanDao().generateSequence(),
				FeatureType.DigiIDD,
				TimeUtils.parseDate("2011-01-01 00:00:00"),
				null
			);
			plan1.addOptionalFeature(f);
		} catch (BillingSystemException bsExp) {
			fail();
		} catch (ParseException e) {
			fail();
		}
		
		list = plan1.getOptionalFeatures();
		assertSame(1,list.size());
		assertEquals(f,list.get(0));
		try {
			f = new Feature(
				DaoFactory.getInstanceOfSubscriptionPlanDao().generateSequence(),
				FeatureType.CallTransfer,
				TimeUtils.parseDate("2011-01-01 00:00:00"),
				null
			);
			plan1.addOptionalFeature(f);
		} catch (BillingSystemException bsExp) {
			fail();
		} catch (ParseException e) {
			fail();
		}
		list = plan1.getOptionalFeatures();
		assertSame(2,list.size());
		assertEquals(f,list.get(1));
	}

	@Test
	public void testGetOptionalFeatureById() {
		Feature f2 = null;
		try {
			f2 = new Feature(
				DaoFactory.getInstanceOfSubscriptionPlanDao().generateSequence(),
				FeatureType.DigiIDD,
				TimeUtils.parseDate("2011-01-01 00:00:00"),
				null
			);
			plan1.addOptionalFeature(f2);
		} catch (BillingSystemException bsExp) {
			fail();
		} catch (ParseException e) {
			fail();
		}
		Feature f1 = plan1.getOptionalFeatureById(f2.getFeatureId());
		assertNotNull(f1);
		assertEquals(f1,f2);

		f2 = null;
		try {
			f2 = new Feature(
				DaoFactory.getInstanceOfSubscriptionPlanDao().generateSequence(),
				FeatureType.CallTransfer,
				TimeUtils.parseDate("2011-01-01 00:00:00"),
				null
			);
			plan1.addOptionalFeature(f2);
		} catch (BillingSystemException bsExp) {
			fail();
		} catch (ParseException e) {
			fail();
		}
		f1 = plan1.getOptionalFeatureById(f2.getFeatureId());
		assertNotNull(f1);
		assertEquals(f1,f2);
	}
}
