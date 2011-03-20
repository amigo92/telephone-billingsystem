package sg.edu.nus.iss.billsys.mgr;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sg.edu.nus.iss.billsys.constant.FeatureType;
import sg.edu.nus.iss.billsys.constant.PlanType;
import sg.edu.nus.iss.billsys.constant.PlanType.PlanCode;
import sg.edu.nus.iss.billsys.dao.*;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.vo.Account;
import sg.edu.nus.iss.billsys.vo.CableTvPlan;
import sg.edu.nus.iss.billsys.vo.DigitalVoicePlan;
import sg.edu.nus.iss.billsys.vo.Feature;
import sg.edu.nus.iss.billsys.vo.MobileVoicePlan;
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
		ArrayList<PlanType> list = new ArrayList<PlanType>();
		list.add(PlanType.DigitalVoice);
		list.add(PlanType.MobileVoice);
		list.add(PlanType.CableTv);
		return list;
	}
	
	public FeatureType getPlanBasicFeatures(PlanType planType) {
		return planType.basicFeature;
	}
	
	public List<FeatureType> getPlanOptionalFeatures(PlanType planType) {
		ArrayList<FeatureType> list = new ArrayList<FeatureType>();
		for (FeatureType featureType : planType.optionalFeatures) {
			list.add(featureType);
		}
		return list;
	}
	
	//TODO
	//Page: Register Subscription // De-Register Subscription
	//Activity:Click "Get subscription information" button
	//Input:accountNo
	//Output: List of Subscription Plan Object?  must have existing subscription detail:
	//refNo, planID, planName,startDate,endDate
	//List of features(featureID, featureName, startDate, endDate)
    public List<SubscriptionPlan> getAccountSubscriptions(String accountNo) {
    	Account acct = MgrFactory.getAccountMgr().getAccountObject(accountNo);
    	if (acct == null) {
    		return null;
    	}
    	return acct.getPlans();
    }
    
    //TODO
	//Page: Register new subscription plan -Dialog
	//Activity:Click "OK" button
	//Input:
	//Output: 
    public void registerNewSubscriptionPlan(String accountNo, String assignedTelNo, PlanType planType, Date dateCommenced, Date dateTerminated) throws BillingSystemException {
    	if (accountNo == null) {
    		throw new BillingSystemException("Account number cannot be null.");
    	}
    	if (planType == null) {
    		throw new BillingSystemException("Plan type cannot be null.");
    	}
    	if (dateCommenced == null) {
    		throw new BillingSystemException("Date commenced cannot be null.");
    	}
    	if (dateTerminated != null && dateCommenced.after(dateTerminated)) {
    		throw new BillingSystemException("Date commenced cannot be later than date terminated.");
    	}
    	Account acct = MgrFactory.getAccountMgr().getAccountObject(accountNo);
    	if (acct == null) {
    		throw new BillingSystemException("Invalid account number.");
    	}
    	switch (planType.planCode) {
    	case DIGITAL_VOICE:
    		// TODO
    		new DigitalVoicePlan(accountNo,assignedTelNo,dateCommenced,dateTerminated);
    		break;
    	case MOBILE_VOICE:
    		// TODO
    		new MobileVoicePlan(accountNo,assignedTelNo,dateCommenced,dateTerminated);
    		break;
    	case CABLE_TV:
    		//TODO
    		new CableTvPlan(accountNo,dateCommenced,dateTerminated);
    		break;
    	default:
    		throw new BillingSystemException("Unknown plan type!");
    	}
    }
    
    //TODO
	//Page: Register new feature -Dialog
	//Activity: Click "OK" button	
	//Input:
	//Output: 
    public void registerNewFeature(String accountNo, String refNo, FeatureType featureType, Date dateCommenced, Date dateTerminated) throws BillingSystemException {
    	if (accountNo == null) {
    		throw new BillingSystemException("Account number cannot be null.");
    	}
    	if (refNo == null) {
    		throw new BillingSystemException("Reference number cannot be null.");
    	}
    	if (featureType == null) {
    		throw new BillingSystemException("Feature type cannot be null.");
    	}
    	if (dateCommenced == null) {
    		throw new BillingSystemException("Date commenced cannot be null.");
    	}
    	if (dateTerminated != null && dateCommenced.after(dateTerminated)) {
    		throw new BillingSystemException("Date commenced cannot be later than date terminated.");
    	}
    	Account acct = MgrFactory.getAccountMgr().getAccountObject(accountNo);
    	if (acct == null) {
    		throw new BillingSystemException("Invalid account number.");
    	}
    	//TODO
    }
    
    //TODO
	//Page: De-register Subscription -Dialog
	//Activity:Page loading
	//Input:
	//Output:List of features object which is not registered? (feature id, name, start date, end date)
    public List<Feature> getUnregisteredFeatures(String accountNo, String refNo){
    	return null;
    }
    
    //TODO
	//Page: De-register Subscription -Dialog
	//Activity:ck "Submit" button
	//Input:
	//Output: 
    public void deregisterFeature(String accountNo, String refNo, FeatureType featureType, Date dateTerminated) throws BillingSystemException {
    	if (accountNo == null) {
    		throw new BillingSystemException("Account number cannot be null.");
    	}
    	if (refNo == null) {
    		throw new BillingSystemException("Reference number cannot be null.");
    	}
    	if (featureType == null) {
    		throw new BillingSystemException("Feature type cannot be null.");
    	}
    	if (dateTerminated == null) {
    		throw new BillingSystemException("Date commenced cannot be null.");
    	}
    }
    
    //TODO
	//Page: De-register Subscription -Dialog
	//Activity: "Submit" button
	//Input:
	//Output: 
    public void deregisterSubscriptionPlan(String accountNo, String refNo, Date dateTerminated) throws BillingSystemException {
    	if (accountNo == null) {
    		throw new BillingSystemException("Account number cannot be null.");
    	}
    	if (refNo == null) {
    		throw new BillingSystemException("Reference number cannot be null.");
    	}
    	if (dateTerminated == null) {
    		throw new BillingSystemException("Date commenced cannot be null.");
    	}
    }
}
