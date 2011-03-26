package sg.edu.nus.iss.billsys.mgr;

import java.util.ArrayList;
import java.util.Collection;
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

/**
 * 
 * @author Lem Kian Hoa (Stephen)
 *
 */

public class SubscriptionMgr {

	private static SubscriptionPlanDao subPlanDao = new SubscriptionPlanDao();
	private static FeatureRateDao featureRateDao = new FeatureRateDao();
		
	public int getSubscriptionCharge(FeatureType featureType){
		return featureRateDao.getPricebyFeatureCode(featureType.getFeatureCd()).getPrice();
	}
	
	//TODO
	//Page:Introduction of subscription plans   
	//Activity:Page loading 
	//Input:null
	//Output: planName, available optional features , rate(monthly charge & usage charge)
	
	public PlanType[] getAllPlanType() {
		return PlanType.values();
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
    public Collection<SubscriptionPlan> getAccountSubscriptions(String accountNo) {
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
    		acct.addPlan(
    			new DigitalVoicePlan(
    				SubscriptionPlanDao.generateSequence(),
    				accountNo,
    				assignedTelNo,
    				SubscriptionPlanDao.generateSequence(),
    				dateCommenced,
    				dateTerminated
    			)
    		);
    		break;
    	case MOBILE_VOICE:
    		acct.addPlan(
    			new MobileVoicePlan(
        			SubscriptionPlanDao.generateSequence(),
    				accountNo,
    				assignedTelNo,
    				SubscriptionPlanDao.generateSequence(),
    				dateCommenced,
    				dateTerminated
    			)
    		);
    		break;
    	case CABLE_TV:
    		acct.addPlan(
    			new CableTvPlan(
        			SubscriptionPlanDao.generateSequence(),
    				accountNo,
    				SubscriptionPlanDao.generateSequence(),
    				dateCommenced,
    				dateTerminated
    			)
    		);
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
    public void registerNewFeature(String accountNo, String planId, FeatureType featureType, Date dateCommenced, Date dateTerminated) throws BillingSystemException {
    	if (accountNo == null) {
    		throw new BillingSystemException("Account number cannot be null.");
    	}
    	if (planId == null) {
    		throw new BillingSystemException("Plan id cannot be null.");
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
    	SubscriptionPlan plan = acct.getPlan(planId);
    	if (plan == null) {
    		throw new BillingSystemException("Invalid plan id.");
    	}
    	plan.addOptionalFeature(
    		new Feature(
    			SubscriptionPlanDao.generateSequence(),
    			featureType,
    			dateCommenced,
    			dateTerminated
    		)
    	);
    }
    
    //TODO
	//Page: De-register Subscription -Dialog
	//Activity:Page loading
	//Input:
	//Output:List of features object which is not registered? (feature id, name, start date, end date)
    public List<Feature> getUnregisteredFeatures(String accountNo, String planId) {
       	if (accountNo == null) {
    		return null;
    	}
    	if (planId == null) {
    		return null;
    	}
    	Account acct = MgrFactory.getAccountMgr().getAccountObject(accountNo);
    	if (acct == null) {
    		return null;
    	}
    	SubscriptionPlan plan = acct.getPlan(planId);
    	if (plan == null) {
    		return null;
    	}
    	ArrayList<Feature> list = new ArrayList<Feature>();
    	List<Feature> features = plan.getOptionalFeatures();
    	for (Feature f : features) {
    		if (f.isTerminated()) {
    			list.add(f);
    		}
    	}
    	return list;
    }
    
    //TODO
	//Page: De-register Subscription -Dialog
	//Activity:ck "Submit" button
	//Input:
	//Output: 
    public void deregisterFeature(String accountNo, String planId, FeatureType featureType, Date dateTerminated) throws BillingSystemException {
    	if (accountNo == null) {
    		throw new BillingSystemException("Account number cannot be null.");
    	}
    	if (planId == null) {
    		throw new BillingSystemException("Plan id cannot be null.");
    	}
    	if (featureType == null) {
    		throw new BillingSystemException("Feature type cannot be null.");
    	}
    	if (dateTerminated == null) {
    		throw new BillingSystemException("Date commenced cannot be null.");
    	}
    	Account acct = MgrFactory.getAccountMgr().getAccountObject(accountNo);
    	if (acct == null) {
    		throw new BillingSystemException("Invalid account number.");
    	}
    	SubscriptionPlan plan = acct.getPlan(planId);
    	if (plan == null) {
    		throw new BillingSystemException("Invalid plan id.");
    	}
    	Feature feature = plan.getOptionalFeatureByType(featureType);
    	if (feature == null) {
    		throw new BillingSystemException("Invalid feature type or feature is not registered.");
    	}
    	feature.setDateTerminated(dateTerminated);
    }
    
    //TODO
	//Page: De-register Subscription -Dialog
	//Activity: "Submit" button
	//Input:
	//Output: 
    public void deregisterSubscriptionPlan(String accountNo, String planId, Date dateTerminated) throws BillingSystemException {
    	if (accountNo == null) {
    		throw new BillingSystemException("Account number cannot be null.");
    	}
    	if (planId == null) {
    		throw new BillingSystemException("Plan id cannot be null.");
    	}
    	if (dateTerminated == null) {
    		throw new BillingSystemException("Date commenced cannot be null.");
    	}
    	Account acct = MgrFactory.getAccountMgr().getAccountObject(accountNo);
    	if (acct == null) {
    		throw new BillingSystemException("Invalid account number.");
    	}
    	SubscriptionPlan plan = acct.getPlan(planId);
    	if (plan == null) {
    		throw new BillingSystemException("Invalid plan id.");
    	}
    	plan.setDateTerminated(dateTerminated);
    }
}
