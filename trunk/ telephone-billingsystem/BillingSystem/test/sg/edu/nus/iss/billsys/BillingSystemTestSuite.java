package sg.edu.nus.iss.billsys;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import sg.edu.nus.iss.billsys.dao.CallHistDaoTest;
import sg.edu.nus.iss.billsys.dao.ComplaintsDaoTest;
import sg.edu.nus.iss.billsys.dao.CustomerDaoTest;
import sg.edu.nus.iss.billsys.dao.DigiVoiceNumbersDaoTest;
import sg.edu.nus.iss.billsys.dao.FeatureRateTest;
import sg.edu.nus.iss.billsys.dao.GenericDaoTest;
import sg.edu.nus.iss.billsys.dao.MobileNumbersDaoTest;
import sg.edu.nus.iss.billsys.dao.PaymentHistDaoTest;
import sg.edu.nus.iss.billsys.dao.PlanRateDaoTest;
import sg.edu.nus.iss.billsys.dao.SubscriptionPlanDaoTest;
import sg.edu.nus.iss.billsys.dao.UserDaoTest;
import sg.edu.nus.iss.billsys.mgr.AccountMgrTest;
import sg.edu.nus.iss.billsys.mgr.BillMgrTest;
import sg.edu.nus.iss.billsys.mgr.ComplaintMgrTest;
import sg.edu.nus.iss.billsys.mgr.SubscriptionMgrTest;
import sg.edu.nus.iss.billsys.mgr.UserMgrTest;
import sg.edu.nus.iss.billsys.vo.CableTvPlanTest;
import sg.edu.nus.iss.billsys.vo.DigitalVoicePlanTest;
import sg.edu.nus.iss.billsys.vo.MobileVoicePlanTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	CallHistDaoTest.class,
	ComplaintsDaoTest.class,
	CustomerDaoTest.class,
	DigiVoiceNumbersDaoTest.class,
	FeatureRateTest.class,
	GenericDaoTest.class,
	MobileNumbersDaoTest.class,
	PaymentHistDaoTest.class,
	PlanRateDaoTest.class,
	SubscriptionPlanDaoTest.class,
	UserDaoTest.class,
	DigitalVoicePlanTest.class,
	MobileVoicePlanTest.class,
	CableTvPlanTest.class,
	AccountMgrTest.class,
	ComplaintMgrTest.class,
	UserMgrTest.class,
	SubscriptionMgrTest.class,
	BillMgrTest.class
})
public class BillingSystemTestSuite {
}	
