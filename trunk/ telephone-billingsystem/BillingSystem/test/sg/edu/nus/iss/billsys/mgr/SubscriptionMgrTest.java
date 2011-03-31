package sg.edu.nus.iss.billsys.mgr;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.billsys.constant.FeatureType;
import sg.edu.nus.iss.billsys.constant.PlanType;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.tools.TimeUtils;
import sg.edu.nus.iss.billsys.vo.Customer;
import sg.edu.nus.iss.billsys.vo.Feature;
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
		for (String s : subMgr.getAvailDigiVoiceNumbers()) {
			System.out.println(s);
		}
		for (String s : subMgr.getAvailMobileNumbers()) {
			System.out.println(s);
		}
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
			System.out.println(e.getMessage());
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
			System.out.println(e.getMessage());
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
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			fail();
		}
		try {
			subMgr.registerNewSubscriptionPlan(
				"AAAAAA111111",
				"61234567",
				PlanType.DigitalVoice,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			fail();
		}
		try {
			SubscriptionPlan plan1 = subMgr.registerNewSubscriptionPlan(
				cust.getAccountId(),
				"61234567",
				PlanType.DigitalVoice,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			);
			SubscriptionPlan plan2 = subMgr.getAccountSubscription(cust.getAccountId(), plan1.getPlanId());
			assertEquals(plan1,plan2);
		} catch (BillingSystemException e) {
			fail();
		} catch (ParseException e) {
			fail();
		}
		try {
			SubscriptionPlan plan1 = subMgr.registerNewSubscriptionPlan(
				cust.getAccountId(),
				"91234567",
				PlanType.MobileVoice,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			);
			SubscriptionPlan plan2 = subMgr.getAccountSubscription(cust.getAccountId(), plan1.getPlanId());
			assertEquals(plan1,plan2);
		} catch (BillingSystemException e) {
			fail();
		} catch (ParseException e) {
			fail();
		}
		try {
			SubscriptionPlan plan1 = subMgr.registerNewSubscriptionPlan(
				cust.getAccountId(),
				null,
				PlanType.CableTv,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			);
			SubscriptionPlan plan2 = subMgr.getAccountSubscription(cust.getAccountId(), plan1.getPlanId());
			assertEquals(plan1,plan2);
		} catch (BillingSystemException e) {
			fail();
		} catch (ParseException e) {
			fail();
		}
		try {
			SubscriptionPlan plan1 = subMgr.registerNewSubscriptionPlan(
				cust.getAccountId(),
				"61234567",
				PlanType.DigitalVoice,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				TimeUtils.parseDate("2013-02-01 00:00:00")
			);
			SubscriptionPlan plan2 = subMgr.getAccountSubscription(cust.getAccountId(), plan1.getPlanId());
			assertEquals(plan1,plan2);
		} catch (BillingSystemException e) {
			fail();
		} catch (ParseException e) {
			fail();
		}
		try {
			SubscriptionPlan plan1 = subMgr.registerNewSubscriptionPlan(
				cust.getAccountId(),
				"91234567",
				PlanType.MobileVoice,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				TimeUtils.parseDate("2013-02-01 00:00:00")
			);
			SubscriptionPlan plan2 = subMgr.getAccountSubscription(cust.getAccountId(), plan1.getPlanId());
			assertEquals(plan1,plan2);
		} catch (BillingSystemException e) {
			fail();
		} catch (ParseException e) {
			fail();
		}
		try {
			SubscriptionPlan plan1 = subMgr.registerNewSubscriptionPlan(
				cust.getAccountId(),
				null,
				PlanType.CableTv,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				TimeUtils.parseDate("2013-02-01 00:00:00")
			);
			SubscriptionPlan plan2 = subMgr.getAccountSubscription(cust.getAccountId(), plan1.getPlanId());
			assertEquals(plan1,plan2);
		} catch (BillingSystemException e) {
			fail();
		} catch (ParseException e) {
			fail();
		}
	}
	
	@Test
	public void testRegisterNewFeature() {
		// DigitalVoice
		SubscriptionPlan digiPlan = null;
		try {
			digiPlan = subMgr.registerNewSubscriptionPlan(
				cust.getAccountId(),
				"61234567",
				PlanType.DigitalVoice,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				TimeUtils.parseDate("2012-02-01 00:00:00")
			);
		} catch (BillingSystemException e) {
			fail();
		} catch (ParseException e) {
			fail();
		}
		try {
			subMgr.registerNewFeature(
				null,
				digiPlan.getPlanId(),
				FeatureType.DigiIDD,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			fail();
		}
		try {
			subMgr.registerNewFeature(
				cust.getAccountId(),
				null,
				FeatureType.DigiIDD,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			fail();
		}
		try {
			subMgr.registerNewFeature(
				cust.getAccountId(),
				digiPlan.getPlanId(),
				null,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			fail();
		}
		try {
			subMgr.registerNewFeature(
				cust.getAccountId(),
				digiPlan.getPlanId(),
				FeatureType.DigiIDD,
				null,
				null
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		}
		try {
			subMgr.registerNewFeature(
				cust.getAccountId(),
				digiPlan.getPlanId(),
				FeatureType.DigiIDD,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				TimeUtils.parseDate("2011-01-01 00:00:00")
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			fail();
		}
		try {
			subMgr.registerNewFeature(
				"AAAAA1111",
				digiPlan.getPlanId(),
				FeatureType.DigiIDD,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			fail();
		}
		try {
			subMgr.registerNewFeature(
				cust.getAccountId(),
				"PPPPPPFFFFF",
				FeatureType.DigiIDD,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			fail();
		}
		try {
			subMgr.registerNewFeature(
				cust.getAccountId(),
				digiPlan.getPlanId(),
				FeatureType.MobileIDD,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			fail();
		}
		try {
			subMgr.registerNewFeature(
				cust.getAccountId(),
				digiPlan.getPlanId(),
				FeatureType.DataService,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			fail();
		}
		try {
			subMgr.registerNewFeature(
				cust.getAccountId(),
				digiPlan.getPlanId(),
				FeatureType.Roaming,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			fail();
		}
		try {
			subMgr.registerNewFeature(
				cust.getAccountId(),
				digiPlan.getPlanId(),
				FeatureType.AddChannel,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			fail();
		}
		try {
			subMgr.registerNewFeature(
				cust.getAccountId(),
				digiPlan.getPlanId(),
				FeatureType.DigiIDD,
				TimeUtils.parseDate("2011-01-01 00:00:00"),
				null
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			fail();
		}
		try {
			subMgr.registerNewFeature(
				cust.getAccountId(),
				digiPlan.getPlanId(),
				FeatureType.DigiIDD,
				TimeUtils.parseDate("2013-02-01 00:00:00"),
				null
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			fail();
		}
		try {
			subMgr.registerNewFeature(
				cust.getAccountId(),
				digiPlan.getPlanId(),
				FeatureType.DigiIDD,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				TimeUtils.parseDate("2013-02-01 00:00:00")
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			fail();
		}
		try {
			String fid = subMgr.registerNewFeature(
				cust.getAccountId(),
				digiPlan.getPlanId(),
				FeatureType.DigiIDD,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			).getFeatureId();
			Feature feature = subMgr.getAccountSubscriptionFeature(cust.getAccountId(), digiPlan.getPlanId(), fid);
			assertNotNull(feature);
			assertEquals(fid,feature.getFeatureId());
		} catch (BillingSystemException e) {
			fail();
		} catch (ParseException e) {
			fail();
		}
		try {
			String fid = subMgr.registerNewFeature(
				cust.getAccountId(),
				digiPlan.getPlanId(),
				FeatureType.CallTransfer,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			).getFeatureId();
			Feature feature = subMgr.getAccountSubscriptionFeature(cust.getAccountId(), digiPlan.getPlanId(), fid);
			assertNotNull(feature);
			assertEquals(fid,feature.getFeatureId());
		} catch (BillingSystemException e) {
			fail();
		} catch (ParseException e) {
			fail();
		}
		
		//MobileVoice
		SubscriptionPlan mobilePlan = null;
		try {
			mobilePlan = subMgr.registerNewSubscriptionPlan(
				cust.getAccountId(),
				"91234567",
				PlanType.MobileVoice,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			);
		} catch (BillingSystemException e) {
			fail();
		} catch (ParseException e) {
			fail();
		}
		try {
			subMgr.registerNewFeature(
				cust.getAccountId(),
				mobilePlan.getPlanId(),
				FeatureType.DigiIDD,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			fail();
		}
		try {
			subMgr.registerNewFeature(
				cust.getAccountId(),
				mobilePlan.getPlanId(),
				FeatureType.CallTransfer,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			fail();
		}
		try {
			subMgr.registerNewFeature(
				cust.getAccountId(),
				mobilePlan.getPlanId(),
				FeatureType.AddChannel,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			fail();
		}
		try {
			String fid = subMgr.registerNewFeature(
				cust.getAccountId(),
				mobilePlan.getPlanId(),
				FeatureType.MobileIDD,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			).getFeatureId();
			Feature feature = subMgr.getAccountSubscriptionFeature(cust.getAccountId(), mobilePlan.getPlanId(), fid);
			assertNotNull(feature);
			assertEquals(fid,feature.getFeatureId());
		} catch (BillingSystemException e) {
			fail();
		} catch (ParseException e) {
			fail();
		}
		try {
			String fid = subMgr.registerNewFeature(
				cust.getAccountId(),
				mobilePlan.getPlanId(),
				FeatureType.DataService,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			).getFeatureId();
			Feature feature = subMgr.getAccountSubscriptionFeature(cust.getAccountId(), mobilePlan.getPlanId(), fid);
			assertNotNull(feature);
			assertEquals(fid,feature.getFeatureId());
		} catch (BillingSystemException e) {
			fail();
		} catch (ParseException e) {
			fail();
		}
		try {
			String fid = subMgr.registerNewFeature(
				cust.getAccountId(),
				mobilePlan.getPlanId(),
				FeatureType.Roaming,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			).getFeatureId();
			Feature feature = subMgr.getAccountSubscriptionFeature(cust.getAccountId(), mobilePlan.getPlanId(), fid);
			assertNotNull(feature);
			assertEquals(fid,feature.getFeatureId());
		} catch (BillingSystemException e) {
			fail();
		} catch (ParseException e) {
			fail();
		}

		
		//CableTV
		SubscriptionPlan cableTvPlan = null;
		try {
			cableTvPlan = subMgr.registerNewSubscriptionPlan(
				cust.getAccountId(),
				null,
				PlanType.CableTv,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			);
		} catch (BillingSystemException e) {
			fail();
		} catch (ParseException e) {
			fail();
		}
		try {
			subMgr.registerNewFeature(
				cust.getAccountId(),
				cableTvPlan.getPlanId(),
				FeatureType.DigiIDD,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			fail();
		}
		try {
			subMgr.registerNewFeature(
				cust.getAccountId(),
				cableTvPlan.getPlanId(),
				FeatureType.CallTransfer,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			fail();
		}
		try {
			subMgr.registerNewFeature(
				cust.getAccountId(),
				cableTvPlan.getPlanId(),
				FeatureType.MobileIDD,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			fail();
		}
		try {
			subMgr.registerNewFeature(
				cust.getAccountId(),
				cableTvPlan.getPlanId(),
				FeatureType.DataService,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			fail();
		}
		try {
			subMgr.registerNewFeature(
				cust.getAccountId(),
				cableTvPlan.getPlanId(),
				FeatureType.Roaming,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			fail();
		}
		try {
			String fid = subMgr.registerNewFeature(
				cust.getAccountId(),
				cableTvPlan.getPlanId(),
				FeatureType.AddChannel,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			).getFeatureId();
			Feature feature = subMgr.getAccountSubscriptionFeature(cust.getAccountId(), cableTvPlan.getPlanId(), fid);
			assertNotNull(feature);
			assertEquals(fid,feature.getFeatureId());
		} catch (BillingSystemException e) {
			fail();
		} catch (ParseException e) {
			fail();
		}
	}
	
	@Test
	public void testDeregisterFeature() {
		SubscriptionPlan digiPlan = null;
		try {
			digiPlan = subMgr.registerNewSubscriptionPlan(
				cust.getAccountId(),
				"61234567",
				PlanType.DigitalVoice,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				TimeUtils.parseDate("2012-02-01 00:00:00")
			);
		} catch (BillingSystemException e) {
			fail();
		} catch (ParseException e) {
			fail();
		}
		Feature digiIDD = null;
		Feature callTransfer = null;
		try {
			digiIDD = subMgr.registerNewFeature(
				cust.getAccountId(),
				digiPlan.getPlanId(),
				FeatureType.DigiIDD,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			);
			callTransfer = subMgr.registerNewFeature(
				cust.getAccountId(),
				digiPlan.getPlanId(),
				FeatureType.CallTransfer,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			);
		} catch (BillingSystemException e) {
			fail();
		} catch (ParseException e) {
			fail();
		}
		List<Feature> list = subMgr.getDeregisteredFeatures(cust.getAccountId(),digiPlan.getPlanId());
		assertNotNull(list);
		assertEquals(0,list.size());
		assertFalse(digiIDD.isTerminated());
		assertFalse(callTransfer.isTerminated());
		try {
			subMgr.deregisterFeature(
				null,
				digiPlan.getPlanId(),
				digiIDD.getFeatureId(),
				new Date()
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		}
		try {
			subMgr.deregisterFeature(
				cust.getAccountId(),
				null,
				digiIDD.getFeatureId(),
				new Date()
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		}
		try {
			subMgr.deregisterFeature(
				cust.getAccountId(),
				digiPlan.getPlanId(),
				null,
				new Date()
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		}
		try {
			subMgr.deregisterFeature(
				cust.getAccountId(),
				digiPlan.getPlanId(),
				digiIDD.getFeatureId(),
				null
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		}
		try {
			subMgr.deregisterFeature(
				cust.getAccountId(),
				digiPlan.getPlanId(),
				digiIDD.getFeatureId(),
				TimeUtils.parseDate("2013-02-01 00:00:00")
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			fail();
		}
		try {
			subMgr.deregisterFeature(
				cust.getAccountId(),
				digiPlan.getPlanId(),
				digiIDD.getFeatureId(),
				new Date()
			);
		} catch (BillingSystemException e) {
			fail();
		}
		list = subMgr.getDeregisteredFeatures(cust.getAccountId(),digiPlan.getPlanId());
		assertNotNull(list);
		assertEquals(1,list.size());
		assertTrue(digiIDD.isTerminated());
		assertFalse(callTransfer.isTerminated());
		try {
			subMgr.deregisterFeature(
				cust.getAccountId(),
				digiPlan.getPlanId(),
				callTransfer.getFeatureId(),
				new Date()
			);
		} catch (BillingSystemException e) {
			fail();
		}
		list = subMgr.getDeregisteredFeatures(cust.getAccountId(),digiPlan.getPlanId());
		assertNotNull(list);
		assertEquals(2,list.size());
		assertTrue(digiIDD.isTerminated());
		assertTrue(callTransfer.isTerminated());
	}
	
	@Test
	public void testDeregisterSubscriptionPlan() {
		SubscriptionPlan digiPlan = null;
		try {
			digiPlan = subMgr.registerNewSubscriptionPlan(
				cust.getAccountId(),
				"61234567",
				PlanType.DigitalVoice,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			);
		} catch (BillingSystemException e) {
			fail();
		} catch (ParseException e) {
			fail();
		}
		Feature digiIDD = null;
		Feature callTransfer = null;
		try {
			digiIDD = subMgr.registerNewFeature(
				cust.getAccountId(),
				digiPlan.getPlanId(),
				FeatureType.DigiIDD,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			);
			callTransfer = subMgr.registerNewFeature(
				cust.getAccountId(),
				digiPlan.getPlanId(),
				FeatureType.CallTransfer,
				TimeUtils.parseDate("2011-02-01 00:00:00"),
				null
			);
		} catch (BillingSystemException e) {
			fail();
		} catch (ParseException e) {
			fail();
		}
		List<Feature> list = subMgr.getDeregisteredFeatures(cust.getAccountId(),digiPlan.getPlanId());
		assertNotNull(list);
		assertEquals(0,list.size());
		assertFalse(digiPlan.getBasicFeature().isTerminated());
		assertFalse(digiIDD.isTerminated());
		assertFalse(callTransfer.isTerminated());
		try {
			subMgr.deregisterSubscriptionPlan(
				null,
				digiPlan.getPlanId(),
				new Date()
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		}
		try {
			subMgr.deregisterSubscriptionPlan(
				cust.getAccountId(),
				null,
				new Date()
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		}
		try {
			subMgr.deregisterSubscriptionPlan(
				cust.getAccountId(),
				digiPlan.getPlanId(),
				null
			);
			fail();
		} catch (BillingSystemException e) {
			System.out.println(e.getMessage());
		}
		try {
			subMgr.deregisterSubscriptionPlan(
				cust.getAccountId(),
				digiPlan.getPlanId(),
				new Date()
			);
		} catch (BillingSystemException e) {
			fail();
		}
		list = subMgr.getDeregisteredFeatures(cust.getAccountId(),digiPlan.getPlanId());
		assertNotNull(list);
		assertEquals(2,list.size());
		assertTrue(digiPlan.getBasicFeature().isTerminated());
		assertTrue(digiIDD.isTerminated());
		assertTrue(callTransfer.isTerminated());
	}
}
