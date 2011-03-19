package sg.edu.nus.iss.billsys.mgr;

import java.util.Date;
import java.util.List;

import sg.edu.nus.iss.billsys.constant.FeatureType;
import sg.edu.nus.iss.billsys.constant.PlanType;
import sg.edu.nus.iss.billsys.dao.*;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.vo.Feature;
import sg.edu.nus.iss.billsys.vo.SubscriptionPlan;

public class SubscriptionMgr {

	private static CallRateDao aCallRateDao = new CallRateDao();
	private static SubscriptionChargesDao aSubscriptionChargesDao = new SubscriptionChargesDao();
		
	public int getSubscriptionCharge(FeatureType featureType){
		return aSubscriptionChargesDao.getSubscriptionCharge(featureType.featureCode);
	}
	
	//TODO
	//Page:Introduction of subscription plans   
	//Activity:Page loading 
	//Input:null
	//Output: planName, available optional features , rate(monthly charge & usage charge)
	
	public List<PlanType> getAllPlanType() {
		return null;
	}
	
	public FeatureType getPlanBasicFeature(PlanType planType) {
		return null;
	}
	
	public List<FeatureType> getPlanOptionalFeatures(PlanType planType) {
		return null;
	}
	
	//TODO
	//Page: Register Subscription // De-Register Subscription
	//Activity:Click "Get subscription information" button
	//Input:accountNo
	//Output: List of Subscription Plan Object?  must have existing subscription detail:
	//refNo, planID, planName,startDate,endDate
	//List of features(featureID, featureName, startDate, endDate)
    public List<SubscriptionPlan> getAccountSubscriptions(String accountNo) {
    	return null;
    }
    
    //TODO
	//Page: Register new feature -Dialog
	//Activity: Click "OK" button	
	//Input:
	//Output: 
    public void registerNewFeature( String accountNo, int refNo, FeatureType featureType, Date startDate, Date endDate) throws BillingSystemException {
    }
    
    //TODO
	//Page: Register new subscription plan -Dialog
	//Activity:Click "OK" button
	//Input:
	//Output: 
    public void registerNewSubscriptionPlan(String accountNo,PlanType planType, Date startDate, Date endDate) throws BillingSystemException {
    }
    
    //TODO
	//Page: De-register Subscription -Dialog
	//Activity:Page loading
	//Input:
	//Output:List of features object which is not registered? (feature id, name, start date, end date)
    public List<Feature> getUnregisteredFeatures(String accountNo, long refNo){
    	return null;
    }
    
    //TODO
	//Page: De-register Subscription -Dialog
	//Activity:ck "Submit" button
	//Input:
	//Output: 
    public void deregisterFeature(String accountNo, long refNo, FeatureType featureType, Date endDate) throws BillingSystemException {
    }
    
    //TODO
	//Page: De-register Subscription -Dialog
	//Activity: "Submit" button
	//Input:
	//Output: 
    public void deregisterSubscriptionPlan(String accountNo, long refNo) throws BillingSystemException {
    }

	
}
