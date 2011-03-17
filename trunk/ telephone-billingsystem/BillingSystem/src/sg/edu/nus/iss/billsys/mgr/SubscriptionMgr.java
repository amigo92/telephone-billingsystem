package sg.edu.nus.iss.billsys.mgr;

import sg.edu.nus.iss.billsys.dao.*;

public class SubscriptionMgr {

	private static CallRateDao aCallRateDao = new CallRateDao();
	private static SubscriptionChargesDao aSubscriptionChargesDao = new SubscriptionChargesDao();
	
	public int getCallUsageRate(int planTypeCd, int callTxnTypeCd){
		return aCallRateDao.getRate(planTypeCd, callTxnTypeCd);
	}
	
	public int getSubscriptionCharge(int featureTypeCd){
		return aSubscriptionChargesDao.getSubscriptionCharge(featureTypeCd);
	}
}
