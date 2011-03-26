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

	private SubscriptionPlanDao subPlanDao;
	private FeatureRateDao featureRateDao;
	
	public SubscriptionMgr() throws BillingSystemException {
		subPlanDao = new SubscriptionPlanDao();
		featureRateDao = new FeatureRateDao();
	}
		
	public int getSubscriptionCharge(FeatureType featureType){
		return featureRateDao.getPricebyFeatureCode(featureType.getFeatureCd()).getPrice();
	}
	
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
	
    public List<SubscriptionPlan> getAccountSubscriptions(String acctNo) {
    	return subPlanDao.getAccountSubscriptions(acctNo);
    }
    
    public void registerNewSubscriptionPlan(String acctNo, String assignedTelNo, PlanType planType, Date dateCommenced, Date dateTerminated) throws BillingSystemException {
    	if (acctNo == null) {
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
    	SubscriptionPlan plan = null;
    	switch (planType.planCode) {
    	case DIGITAL_VOICE:
    		plan = new DigitalVoicePlan(
				SubscriptionPlanDao.generateSequence(),
				acctNo,
				assignedTelNo,
				SubscriptionPlanDao.generateSequence(),
				dateCommenced,
				dateTerminated
    		);
    		break;
    	case MOBILE_VOICE:
			plan = new MobileVoicePlan(
    			SubscriptionPlanDao.generateSequence(),
				acctNo,
				assignedTelNo,
				SubscriptionPlanDao.generateSequence(),
				dateCommenced,
				dateTerminated
    		);
    		break;
    	case CABLE_TV:
			plan = new CableTvPlan(
    			SubscriptionPlanDao.generateSequence(),
				acctNo,
				SubscriptionPlanDao.generateSequence(),
				dateCommenced,
				dateTerminated
    		);
    		break;
    	default:
    		throw new BillingSystemException("Unknown plan type!");
    	}
		subPlanDao.addAccountSubscriptions(acctNo, plan);
		subPlanDao.save();
    }
    
    public void registerNewFeature(String acctNo, String planId, FeatureType featureType, Date dateCommenced, Date dateTerminated) throws BillingSystemException {
    	if (acctNo == null) {
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
    	SubscriptionPlan plan = subPlanDao.getAccountSubscription(acctNo,planId);
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
		subPlanDao.save();
    }
    
    public List<Feature> getDeregisteredFeatures(String acctNo, String planId) {
       	if (acctNo == null) {
    		return null;
    	}
    	if (planId == null) {
    		return null;
    	}
    	SubscriptionPlan plan = subPlanDao.getAccountSubscription(acctNo, planId);
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
    
    public void deregisterFeature(String acctNo, String planId, FeatureType featureType, Date dateTerminated) throws BillingSystemException {
    	if (acctNo == null) {
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
    	SubscriptionPlan plan = subPlanDao.getAccountSubscription(acctNo, planId);
    	if (plan == null) {
    		throw new BillingSystemException("Invalid plan id.");
    	}
    	Feature feature = plan.getOptionalFeatureByType(featureType);
    	if (feature == null) {
    		throw new BillingSystemException("Invalid feature type or feature is not registered.");
    	}
    	feature.setDateTerminated(dateTerminated);
		subPlanDao.save();
    }
    
    public void deregisterSubscriptionPlan(String acctNo, String planId, Date dateTerminated) throws BillingSystemException {
    	if (acctNo == null) {
    		throw new BillingSystemException("Account number cannot be null.");
    	}
    	if (planId == null) {
    		throw new BillingSystemException("Plan id cannot be null.");
    	}
    	if (dateTerminated == null) {
    		throw new BillingSystemException("Date commenced cannot be null.");
    	}
    	SubscriptionPlan plan = subPlanDao.getAccountSubscription(acctNo, planId);
    	if (plan == null) {
    		throw new BillingSystemException("Invalid plan id.");
    	}
    	plan.setDateTerminated(dateTerminated);
		subPlanDao.save();
    }
}
