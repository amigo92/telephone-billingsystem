package sg.edu.nus.iss.billsys.mgr;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sg.edu.nus.iss.billsys.constant.FeatureType;
import sg.edu.nus.iss.billsys.constant.PlanType;
import sg.edu.nus.iss.billsys.dao.*;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.logger.BillingSystemLogger;
import sg.edu.nus.iss.billsys.resource.ResourceHandler;
import sg.edu.nus.iss.billsys.tools.SystemUtils;
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
	private IPhoneNumbersDao mobileNumbersDao;
	private IPhoneNumbersDao digiVoiceNumbersDao;
	private boolean saveObjData = true;
	
	protected SubscriptionMgr() throws BillingSystemException {
		subPlanDao = DaoFactory.getInstanceOfSubscriptionPlanDao();
		featureRateDao = DaoFactory.getInstanceOfFeatureRateDao();
		mobileNumbersDao = DaoFactory.getInstanceOfMobileNumbersDao();
		digiVoiceNumbersDao = DaoFactory.getInstanceOfDigiVoiceNumbersDao();
	}
	
	public void setSaveObjData(boolean value) {
		BillingSystemLogger.logInfo("Set save object data to "+value);
		this.saveObjData = value;
	}
		
	public int getSubscriptionCharge(FeatureType featureType){
		BillingSystemLogger.logInfo("Retrieve subscription charge for FeatureType ["+featureType+"]");
		return featureRateDao.getPricebyFeatureCode(featureType.getFeatureCd()).getPrice();
	}
	
	public PlanType[] getAllPlanType() {
		BillingSystemLogger.logInfo("Retrieve all plan types");
		return PlanType.values();
	}
	
	public FeatureType getPlanBasicFeatures(PlanType planType) {
		BillingSystemLogger.logInfo("Retrieve basic feature for PlanType ["+planType+"]");
		return planType.basicFeature;
	}	

	public List<FeatureType> getPlanOptionalFeatures(PlanType planType) {
		BillingSystemLogger.logInfo("Retrieve optional features for PlanType ["+planType+"]");
		List<FeatureType> list = new ArrayList<FeatureType>();
		for (FeatureType f : planType.optionalFeatures) {
			list.add(f);
		}
		return list;
	}

	public List<FeatureType> getPlanUsageChargeFeatures(PlanType planType) {
		BillingSystemLogger.logInfo("Retrieve usage charge features for PlanType ["+planType+"]");
		List<FeatureType> list = new ArrayList<FeatureType>();
		for (FeatureType f : planType.usageChargeFeatures) {
			list.add(f);
		}
		return list;
	}

    /*
     * To get a list of available mobile numbers.
     * @return list of String objects
     */
	public List<String> getAvailMobileNumbers() {
		BillingSystemLogger.logInfo("Retrieve available mobile numbers");
		return mobileNumbersDao.getPhoneNumbers();
	}

    /*
     * To get a list of available digital voice numbers.
     * @return list of String objects
     */
	public List<String> getAvailDigiVoiceNumbers() {
		BillingSystemLogger.logInfo("Retrieve available digital voice numbers");
		return digiVoiceNumbersDao.getPhoneNumbers();
	}

    /*
     * To get a list of available phone numbers by plan type.
     * @return list of String objects
     */
	public List<String> getAvailPhoneNumbers(PlanType planType) {
		switch(planType) {
		case DigitalVoice:
			return digiVoiceNumbersDao.getPhoneNumbers();
		case MobileVoice:
			return mobileNumbersDao.getPhoneNumbers();
		default:
			return null;	
		}
	}

    /*
     * To get all subscription plans of the account.
     * @return list of SubscriptionPlan objects
     */
    public List<SubscriptionPlan> getAccountSubscriptions(String acctNo) {
		BillingSystemLogger.logInfo("Retrieve all subscription plans for account ["+acctNo+"]");
    	return subPlanDao.getAccountSubscriptions(acctNo);
    }

    /*
     * To get subscription plan of the account by plan id.
     * @return SubscriptionPlan object
     */
    public SubscriptionPlan getAccountSubscription(String acctNo, String planId) {
		BillingSystemLogger.logInfo("Retrieve subscription plan for account ["+acctNo+"] by plan id ["+planId+"]");
    	return subPlanDao.getAccountSubscription(acctNo, planId);
    }
    
    /*
     * To register new subscription plan into the account.
     * @return SubscriptionPlan object
     */
    public SubscriptionPlan registerNewSubscriptionPlan(String acctNo, String assignedTelNo, PlanType planType, Date dateCommenced, Date dateTerminated) throws BillingSystemException {
		BillingSystemLogger.logInfo("Register new subscription plan for account ["+acctNo+"], assignedTelNo ["+assignedTelNo+"] planType ["+planType+"] dateCommenced ["+dateCommenced+"] dateTerminated ["+dateTerminated+"]");
    	if (acctNo == null) {
    		throw new BillingSystemException(ResourceHandler.getError("Account number cannot be null."));
    	}
    	if (planType == null) {
    		throw new BillingSystemException(ResourceHandler.getError("Plan type cannot be null."));
    	}
    	if (dateCommenced == null) {
    		throw new BillingSystemException(ResourceHandler.getError("Date commenced cannot be null."));
    	}
    	if (dateTerminated != null && dateTerminated.before(dateCommenced)) {
    		throw new BillingSystemException(ResourceHandler.getError("Date terminated cannot be earlier than date commenced."));
    	}
    	if (!validateAccount(acctNo)) {
    		throw new BillingSystemException(ResourceHandler.getError("The account is invalid or terminated."));
    	}
    	SubscriptionPlan plan = null;
    	IPhoneNumbersDao phoneNosDao = null;
    	switch (planType.planCode) {
    	case DIGITAL_VOICE:
        	if (assignedTelNo == null) {
        		throw new BillingSystemException(ResourceHandler.getError("Assigned phone number cannot be null."));
        	}
        	phoneNosDao = digiVoiceNumbersDao;
        	if (!phoneNosDao.removePhoneNumber(assignedTelNo)) {
        		throw new BillingSystemException(ResourceHandler.getError("Invalid assigned phone number. The number is not in the list."));
        	}
    		plan = new DigitalVoicePlan(
    			SystemUtils.generateSequence(),
				acctNo,
				assignedTelNo,
				SystemUtils.generateSequence(),
				dateCommenced,
				dateTerminated
    		);
    		break;
    	case MOBILE_VOICE:
        	if (assignedTelNo == null) {
        		throw new BillingSystemException(ResourceHandler.getError("Assigned phone number cannot be null."));
        	}
        	phoneNosDao = mobileNumbersDao;
        	if (!phoneNosDao.removePhoneNumber(assignedTelNo)) {
        		throw new BillingSystemException(ResourceHandler.getError("Invalid assigned phone number. The number is not in the list."));
        	}
			plan = new MobileVoicePlan(
				SystemUtils.generateSequence(),
				acctNo,
				assignedTelNo,
				SystemUtils.generateSequence(),
				dateCommenced,
				dateTerminated
    		);
    		break;
    	case CABLE_TV:
			plan = new CableTvPlan(
				SystemUtils.generateSequence(),
				acctNo,
				SystemUtils.generateSequence(),
				dateCommenced,
				dateTerminated
    		);
    		break;
    	default:
    		throw new BillingSystemException(ResourceHandler.getError("Unknown plan type!"));
    	}
		if (!subPlanDao.addAccountSubscription(acctNo, plan)) {
			throw new BillingSystemException(ResourceHandler.getError("Failed to add subscription plan into doa."));
		}
		if (saveObjData) {
			subPlanDao.saveObjectData();
		}
		if (phoneNosDao != null && saveObjData) {
			phoneNosDao.saveObjectData();
		}
		return plan;
    }

    /*
     * To register new optional feature of the plan.
     * @return feature id
     */
    public Feature registerNewFeature(String acctNo, String planId, FeatureType featureType, Date dateCommenced, Date dateTerminated) throws BillingSystemException {
		BillingSystemLogger.logInfo("Register new feature for account ["+acctNo+"], planId ["+planId+"] featureType ["+featureType+"] dateCommenced ["+dateCommenced+"] dateTerminated ["+dateTerminated+"]");
    	if (acctNo == null) {
    		throw new BillingSystemException(ResourceHandler.getError("Account number cannot be null."));
    	}
    	if (planId == null) {
    		throw new BillingSystemException(ResourceHandler.getError("Plan id cannot be null."));
    	}
    	if (featureType == null) {
    		throw new BillingSystemException(ResourceHandler.getError("Feature type cannot be null."));
    	}
    	if (dateCommenced == null) {
    		throw new BillingSystemException(ResourceHandler.getError("Date commenced cannot be null."));
    	}
    	if (dateTerminated != null && dateTerminated.before(dateCommenced)) {
    		throw new BillingSystemException(ResourceHandler.getError("Date terminated cannot be earlier than date commenced."));
    	}
    	if (!validateAccount(acctNo)) {
    		throw new BillingSystemException(ResourceHandler.getError("The account is invalid or terminated."));
    	}
    	SubscriptionPlan plan = subPlanDao.getAccountSubscription(acctNo,planId);
    	if (plan == null) {
    		throw new BillingSystemException(ResourceHandler.getError("Invalid plan id."));
    	}
    	if (dateCommenced.before(plan.getDateCommenced())) {
    		throw new BillingSystemException(ResourceHandler.getError("Date commenced cannot be earlier than date commenced of subscription plan."));
    	}
    	if (plan.getDateTerminated() != null) {
        	if (dateCommenced.after(plan.getDateTerminated())) {
        		throw new BillingSystemException(ResourceHandler.getError("Date commenced cannot be later than date terminated of subscription plan."));
        	}
    	}
    	if (dateTerminated != null && plan.getDateTerminated() != null) {
        	if (dateTerminated.after(plan.getDateTerminated())) {
        		throw new BillingSystemException(ResourceHandler.getError("Date terminated cannot be later than date terminated of subscription plan."));
        	}
    	}
     	Feature feature = new Feature(
     		SystemUtils.generateSequence(),
			featureType,
			dateCommenced,
			dateTerminated
		);
    	plan.addOptionalFeature(feature);
    	if (saveObjData) {
    		subPlanDao.saveObjectData();
    	}
		return feature;
    }

    /*
     * To get all registered (active) features of the plan.
     * @return list of Feature objects
     */
    public List<Feature> getRegisteredFeatures(String acctNo, String planId) {
		BillingSystemLogger.logInfo("Retrieve registered features for account ["+acctNo+"], planId ["+planId+"]");
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
		BillingSystemLogger.logInfo("Retrieve a feature of subscription plan for account ["+acctNo+"], planId ["+planId+"] featureId ["+featureId+"]");
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
		BillingSystemLogger.logInfo("Retrieve all de-registered features of subscription plan for account ["+acctNo+"], planId ["+planId+"]");
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
		BillingSystemLogger.logInfo("De-register feature for account ["+acctNo+"], planId ["+planId+"] featureId ["+featureId+"] dateTerminated ["+dateTerminated+"]");
    	if (acctNo == null) {
    		throw new BillingSystemException(ResourceHandler.getError("Account number cannot be null."));
    	}
    	if (planId == null) {
    		throw new BillingSystemException(ResourceHandler.getError("Plan id cannot be null."));
    	}
    	if (featureId == null) {
    		throw new BillingSystemException(ResourceHandler.getError("Feature id cannot be null."));
    	}
    	if (dateTerminated == null) {
    		throw new BillingSystemException(ResourceHandler.getError("Date terminated cannot be null."));
    	}
    	if (!validateAccount(acctNo)) {
    		throw new BillingSystemException(ResourceHandler.getError("The account is invalid or terminated."));
    	}
    	SubscriptionPlan plan = subPlanDao.getAccountSubscription(acctNo, planId);
    	if (plan == null) {
    		throw new BillingSystemException(ResourceHandler.getError("Invalid plan id."));
    	}
    	if (plan.getDateTerminated() != null) {
	    	if (dateTerminated.after(plan.getDateTerminated())) {
	    		throw new BillingSystemException(ResourceHandler.getError("Date terminated cannot be later than date terminated of subscription plan."));
	    	}
    	}
    	Feature feature = plan.getOptionalFeatureById(featureId);
    	if (feature == null) {
    		throw new BillingSystemException(ResourceHandler.getError("Invalid feature id."));
    	}
    	feature.setDateTerminated(dateTerminated);
    	if (saveObjData) {
    		subPlanDao.saveObjectData();
    	}
    }

    /*
     * To deregister (terminate) the plan.
     */
    public void deregisterSubscriptionPlan(String acctNo, String planId, Date dateTerminated) throws BillingSystemException {
		BillingSystemLogger.logInfo("De-register subscription plan for account ["+acctNo+"], planId ["+planId+"] dateTerminated ["+dateTerminated+"]");
    	if (acctNo == null) {
    		throw new BillingSystemException(ResourceHandler.getError("Account number cannot be null."));
    	}
    	if (planId == null) {
    		throw new BillingSystemException(ResourceHandler.getError("Plan id cannot be null."));
    	}
    	if (dateTerminated == null) {
    		throw new BillingSystemException(ResourceHandler.getError("Date terminated cannot be null."));
    	}
    	if (!validateAccount(acctNo)) {
    		throw new BillingSystemException(ResourceHandler.getError("The account is invalid or terminated."));
    	}
    	SubscriptionPlan plan = subPlanDao.getAccountSubscription(acctNo, planId);
    	if (plan == null) {
    		throw new BillingSystemException(ResourceHandler.getError("Invalid plan id."));
    	}
    	plan.setDateTerminated(dateTerminated);
    	List<Feature> regFeatures = getRegisteredFeatures(acctNo, planId);
    	for (Feature f : regFeatures) {
    		f.setDateTerminated(dateTerminated);
    	}
    	if (saveObjData) {
    		subPlanDao.saveObjectData();
    	}
    }
    
    private boolean validateAccount(String acctNo) throws BillingSystemException {
		BillingSystemLogger.logInfo("Validate customer for account ["+acctNo+"]");
    	Customer cust = MgrFactory.getAccountMgr().getCustomerDetailsByAccountId(acctNo);
    	if (cust == null) {
    		return false;
    	}
    	return !cust.isDeleted();
    }
}
