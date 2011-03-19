package sg.edu.nus.iss.billsys.mgr;

import java.util.Date;

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
	
	//TODO
	//Page:Introduction of subscription plans   
	//Activity:Page loading 
	//Input:null
	//Output:planID, planName, available optional features , rate(monthly charge & usage charge)
	public void getSubscriptionPlanDetail(){	
		
	    
	}
	
	//TODO
	//Page: Register Subscription // De-Register Subscription
	//Activity:Click "Get subscription information" button
	//Input:accountNo
	//Output: List of Subscription Plan Object?  must have existing subscription detail:
	//subscriptionID, planID, planName,startDate,endDate
	//List of features(featureID, featureName, startDate, endDate)
    public void getSubscriptionInfo(String accountNo){
    	
    }
    
    //TODO
	//Page: Register new feature -Dialog
	//Activity: Click "OK" button	
	//Input:
	//Output: 
    public boolean registerNewFeature( String accountNo, int subscriptionID, int featureID, Date startDate, Date endDate){
        return true;
    }
    
    //TODO
	//Page: Register new subscription plan -Dialog
	//Activity:Click "OK" button
	//Input:
	//Output: 
    public boolean registerNewSubscriptionPlan(String accountNo,int planID, Date startDate, Date endDate){
    	 return true;
    }
    
    //TODO
	//Page: De-register Subscription -Dialog
	//Activity:Page loading
	//Input:
	//Output:List of features object which is not registered? (feature id, name, start date, end date)
    public void getUnregisteredFeatures(String accountNo, int subscriptionID){
    
    }
    
    //TODO
	//Page: De-register Subscription -Dialog
	//Activity:ck "Submit" button
	//Input:
	//Output: 
    public boolean deregiseterFeature(String accountNo, int subscriptionID, int featureID){
    	return true;
    }
    
    //TODO
	//Page: De-register Subscription -Dialog
	//Activity: "Submit" button
	//Input:
	//Output: 
    public boolean deregiseterSubscriptionPlan(String accountNo, int subscriptionID){
    	return true;
    }

	
}
