package sg.edu.nus.iss.billsys.mgr;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sg.edu.nus.iss.billsys.constant.FeatureType;
import sg.edu.nus.iss.billsys.constant.PlanType;
import sg.edu.nus.iss.billsys.dao.*;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.vo.CableTvPlan;
import sg.edu.nus.iss.billsys.vo.Customer;
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

	private ISubscriptionPlanDao subPlanDao;
	private IFeatureRateDao featureRateDao;
	
	public SubscriptionMgr() throws BillingSystemException {
		subPlanDao = DaoFactory.getInstanceOfSubscriptionPlanDao();
		featureRateDao = DaoFactory.getInstanceOfFeatureRateDao();
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

    /*
     * To get all subscription plans of the account.
     * @return list of SubscriptionPlan objects
     */
    public List<SubscriptionPlan> getAccountSubscriptions(String acctNo) {
    	return subPlanDao.getAccountSubscriptions(acctNo);
    }

    /*
     * To get subscription plan of the account by plan id.
     * @return SubscriptionPlan object
     */
    public SubscriptionPlan getAccountSubscription(String acctNo, String planId) {
    	return subPlanDao.getAccountSubscription(acctNo, planId);
    }
    
    /*
     * To register new subscription plan into the account.
     * @return SubscriptionPlan object
     */
    public SubscriptionPlan registerNewSubscriptionPlan(String acctNo, String assignedTelNo, PlanType planType, Date dateCommenced, Date dateTerminated) throws BillingSystemException {
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
    		throw new BillingSystemException("Date terminated cannot be later than date terminated.");
    	}
    	if (!validateAccount(acctNo)) {
    		throw new BillingSystemException("The account is invalid/terminated.");
    	}
    	SubscriptionPlan plan = null;
    	switch (planType.planCode) {
    	case DIGITAL_VOICE:
    		plan = new DigitalVoicePlan(
    				subPlanDao.generateSequence(),
				acctNo,
				assignedTelNo,
				subPlanDao.generateSequence(),
				dateCommenced,
				dateTerminated
    		);
    		break;
    	case MOBILE_VOICE:
			plan = new MobileVoicePlan(
					subPlanDao.generateSequence(),
				acctNo,
				assignedTelNo,
				subPlanDao.generateSequence(),
				dateCommenced,
				dateTerminated
    		);
    		break;
    	case CABLE_TV:
			plan = new CableTvPlan(
					subPlanDao.generateSequence(),
				acctNo,
				subPlanDao.generateSequence(),
				dateCommenced,
				dateTerminated
    		);
    		break;
    	default:
    		throw new BillingSystemException("Unknown plan type!");
    	}
		if (!subPlanDao.addAccountSubscription(acctNo, plan)) {
			throw new BillingSystemException("Failed to add subscription plan into doa.");
		}
		subPlanDao.saveObjectData();
		return plan;
    }

    /*
     * To register new optional feature of the plan.
     * @return feature id
     */
    public Feature registerNewFeature(String acctNo, String planId, FeatureType featureType, Date dateCommenced, Date dateTerminated) throws BillingSystemException {
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
    		throw new BillingSystemException("Date terminated cannot be later than date terminated.");
    	}
    	if (!validateAccount(acctNo)) {
    		throw new BillingSystemException("The account is invalid/terminated.");
    	}
    	SubscriptionPlan plan = subPlanDao.getAccountSubscription(acctNo,planId);
    	if (plan == null) {
    		throw new BillingSystemException("Invalid plan id.");
    	}
    	if (dateCommenced.before(plan.getDateCommenced())) {
    		throw new BillingSystemException("Date commenced cannot be early than date commenced of subscription plan.");
    	}
    	if (plan.getDateTerminated() != null) {
        	if (dateCommenced.after(plan.getDateTerminated())) {
        		throw new BillingSystemException("Date commenced cannot be later than date terminated of subscription plan.");
        	}
    	}
    	if (dateTerminated != null && plan.getDateTerminated() != null) {
        	if (dateTerminated.after(plan.getDateTerminated())) {
        		throw new BillingSystemException("Date terminated cannot be later than date terminated of subscription plan.");
        	}
    	}
     	Feature feature = new Feature(
     			subPlanDao.generateSequence(),
			featureType,
			dateCommenced,
			dateTerminated
		);
    	plan.addOptionalFeature(feature);
		subPlanDao.saveObjectData();
		return feature;
    }

    /*
     * To get all registered (active) features of the plan.
     * @return list of Feature objects
     */
    public List<Feature> getRegisteredFeatures(String acctNo, String planId) {
    	ArrayList<Feature> list = new ArrayList<Feature>();
       	if (acctNo == null) {
    		return list;
    	}
    	if (planId == null) {
    		return list;
    	}
    	SubscriptionPlan plan = subPlanDao.getAccountSubscription(acctNo, planId);
    	if (plan == null) {
    		return list;
    	}
    	List<Feature> features = plan.getOptionalFeatures();
    	for (Feature f : features) {
    		if (!f.isTerminated()) {
    			list.add(f);
    		}
    	}
    	return list;
    }

    /*
     * To get feature of the plan.
     * @return list of Feature object
     */
    public Feature getAccountSubscriptionFeature(String acctNo, String planId, String featureId) {
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
    	List<Feature> features = plan.getOptionalFeatures();
    	for (Feature f : features) {
    		if (f.getFeatureId().equals(featureId)) {
    			return f;
    		}
    	}
    	return null;
    }

    /*
     * To get all deregistered (terminated) features of the plan.
     * @return list of Feature objects
     */
   public List<Feature> getDeregisteredFeatures(String acctNo, String planId) {
    	ArrayList<Feature> list = new ArrayList<Feature>();
       	if (acctNo == null) {
    		return list;
    	}
    	if (planId == null) {
    		return list;
    	}
    	SubscriptionPlan plan = subPlanDao.getAccountSubscription(acctNo, planId);
    	if (plan == null) {
    		return list;
    	}
    	List<Feature> features = plan.getOptionalFeatures();
    	for (Feature f : features) {
    		if (f.isTerminated()) {
    			list.add(f);
    		}
    	}
    	return list;
    }

   /*
    * To deregister (terminate) feature of the plan.
    */
    public void deregisterFeature(String acctNo, String planId, String featureId, Date dateTerminated) throws BillingSystemException {
    	if (acctNo == null) {
    		throw new BillingSystemException("Account number cannot be null.");
    	}
    	if (planId == null) {
    		throw new BillingSystemException("Plan id cannot be null.");
    	}
    	if (featureId == null) {
    		throw new BillingSystemException("Feature id cannot be null.");
    	}
    	if (dateTerminated == null) {
    		throw new BillingSystemException("Date commenced cannot be null.");
    	}
    	if (!validateAccount(acctNo)) {
    		throw new BillingSystemException("The account is invalid/terminated.");
    	}
    	SubscriptionPlan plan = subPlanDao.getAccountSubscription(acctNo, planId);
    	if (plan == null) {
    		throw new BillingSystemException("Invalid plan id.");
    	}
    	if (plan.getDateTerminated() != null) {
	    	if (dateTerminated.after(plan.getDateTerminated())) {
	    		throw new BillingSystemException("Date terminated cannot be later than date terminated of subscription plan.");
	    	}
    	}
    	Feature feature = plan.getOptionalFeatureById(featureId);
    	if (feature == null) {
    		throw new BillingSystemException("Invalid feature id.");
    	}
    	feature.setDateTerminated(dateTerminated);
		subPlanDao.saveObjectData();
    }

    /*
     * To deregister (terminate) the plan.
     */
    public void deregisterSubscriptionPlan(String acctNo, String planId, Date dateTerminated) throws BillingSystemException {
    	if (acctNo == null) {
    		throw new BillingSystemException("Account number cannot be null.");
    	}
    	if (planId == null) {
    		throw new BillingSystemException("Plan id cannot be null.");
    	}
    	if (dateTerminated == null) {
    		throw new BillingSystemException("Date terminated cannot be null.");
    	}
    	if (!validateAccount(acctNo)) {
    		throw new BillingSystemException("The account is invalid/terminated.");
    	}
    	SubscriptionPlan plan = subPlanDao.getAccountSubscription(acctNo, planId);
    	if (plan == null) {
    		throw new BillingSystemException("Invalid plan id.");
    	}
    	plan.setDateTerminated(dateTerminated);
    	List<Feature> regFeatures = getRegisteredFeatures(acctNo, planId);
    	for (Feature f : regFeatures) {
    		f.setDateTerminated(dateTerminated);
    	}
		subPlanDao.saveObjectData();
    }
    
    private boolean validateAccount(String acctNo) throws BillingSystemException {
    	Customer cust = MgrFactory.getAccountMgr().getCustomerDetailsByAccountId(acctNo);
    	if (cust == null) {
    		return false;
    	}
    	return !cust.isDeleted();
    }
}
