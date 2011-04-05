package sg.edu.nus.iss.billsys.mgr;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sg.edu.nus.iss.billsys.constant.FeatureType;
import sg.edu.nus.iss.billsys.constant.PlanType;
import sg.edu.nus.iss.billsys.dao.*;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.logger.BillingSystemLogger;
import sg.edu.nus.iss.billsys.tools.SystemUtils;
import sg.edu.nus.iss.billsys.vo.CableTvPlan;
import sg.edu.nus.iss.billsys.vo.Customer;
import sg.edu.nus.iss.billsys.vo.DigitalVoicePlan;
import sg.edu.nus.iss.billsys.vo.Feature;
import sg.edu.nus.iss.billsys.vo.MobileVoicePlan;
import sg.edu.nus.iss.billsys.vo.SubscriptionPlan;

/**
 * @author Lem Kian Hoa (Stephen)
 * 
 * To retrieve system plan types and feature types.
 * To retrieve available digital voice and mobile phone numbers.
 * To register and de-register subscription plans.
 * To register and de-register plan features.
 * To retrieve subscription plans of an account.
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

	/*
	 * Call ISubscriptionPlanDao.saveObjData if the value is set to true. (default)
	 * For junit usage.
	 */
	public void setSaveObjData(boolean value) {
		BillingSystemLogger.logInfo("Set save object data to "+value);
		this.saveObjData = value;
	}
	
	/*
	 * To retrieve subscription charge of a feature type.
	 * @return int of subscription charge in cents.
	 */
	public int getSubscriptionCharge(FeatureType featureType){
		BillingSystemLogger.logInfo("Retrieve subscription charge of FeatureType ["+featureType+"]");
		return featureRateDao.getPricebyFeatureCode(featureType.getFeatureCd()).getPrice();
	}

	/*
	 * To retrieve all plan types.
	 * @return an array of PlanTypes.
	 */
	public PlanType[] getAllPlanType() {
		BillingSystemLogger.logInfo("Retrieve all plan types");
		return PlanType.values();
	}
	
	/*
	 * To retrieve basic features of a plan type.
	 * @return a FeatureType object.
	 */
	public FeatureType getPlanBasicFeature(PlanType planType) {
		BillingSystemLogger.logInfo("Retrieve basic feature of PlanType ["+planType+"]");
		return planType.basicFeature;
	}	

	/*
	 * To retrieve optional features of a plan type.
	 * @return a list of FeatureType objects.
	 */
	public List<FeatureType> getPlanOptionalFeatures(PlanType planType) {
		BillingSystemLogger.logInfo("Retrieve optional features of PlanType ["+planType+"]");
		List<FeatureType> list = new ArrayList<FeatureType>();
		for (FeatureType f : planType.optionalFeatures) {
			list.add(f);
		}
		return list;
	}


	/*
	 * To retrieve usage charge features of a plan type.
	 * @return a list of FeatureType objects.
	 */
	public List<FeatureType> getPlanUsageChargeFeatures(PlanType planType) {
		BillingSystemLogger.logInfo("Retrieve usage charge features of PlanType ["+planType+"]");
		List<FeatureType> list = new ArrayList<FeatureType>();
		for (FeatureType f : planType.usageChargeFeatures) {
			list.add(f);
		}
		return list;
	}

    /*
     * To retrieve a list of available mobile numbers.
     * @return a list of String objects.
     */
	public List<String> getAvailMobileNumbers() {
		BillingSystemLogger.logInfo("Retrieve available mobile numbers");
		return mobileNumbersDao.getPhoneNumbers();
	}

    /*
     * To retrieve a list of available digital voice numbers.
     * @return a list of String objects.
     */
	public List<String> getAvailDigiVoiceNumbers() {
		BillingSystemLogger.logInfo("Retrieve available digital voice numbers");
		return digiVoiceNumbersDao.getPhoneNumbers();
	}

    /*
     * To retrieve a list of available phone numbers of a plan type.
     * @return a list of String objects
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
     * To retrieve all subscription plans of an account.
     * @return a list of SubscriptionPlan objects
     */
    public List<SubscriptionPlan> getAccountSubscriptions(String acctNo) {
		BillingSystemLogger.logInfo("Retrieve all subscription plans of account ["+acctNo+"]");
    	return subPlanDao.getAccountSubscriptions(acctNo);
    }

    /*
     * To retrieve subscription plan of an account by plan id.
     * @return a SubscriptionPlan object
     */
    public SubscriptionPlan getAccountSubscription(String acctNo, String planId) {
		BillingSystemLogger.logInfo("Retrieve subscription plan of account ["+acctNo+"] by plan id ["+planId+"]");
    	return subPlanDao.getAccountSubscription(acctNo, planId);
    }
    
    /*
     * To register a new subscription plan into an account.
     * @return a registered SubscriptionPlan object.
     * @throws BillingSystemException
     */
    public SubscriptionPlan registerNewSubscriptionPlan(String acctNo, String assignedTelNo, PlanType planType, Date dateCommenced, Date dateTerminated) throws BillingSystemException {
		BillingSystemLogger.logInfo("Register new subscription plan of account ["+acctNo+"], assignedTelNo ["+assignedTelNo+"] planType ["+planType+"] dateCommenced ["+dateCommenced+"] dateTerminated ["+dateTerminated+"]");
    	if (acctNo == null) {
    		throw new BillingSystemException("Account number cannot be null.");
    	}
    	if (planType == null) {
    		throw new BillingSystemException("Plan type cannot be null.");
    	}
    	if (dateCommenced == null) {
    		throw new BillingSystemException("Date commenced cannot be null.");
    	}
    	if (dateTerminated != null && dateTerminated.before(dateCommenced)) {
    		throw new BillingSystemException("Date terminated cannot be earlier than date commenced.");
    	}
    	if (!validateAccount(acctNo)) {
    		throw new BillingSystemException("The account is invalid or terminated.");
    	}
    	SubscriptionPlan plan = null;
    	IPhoneNumbersDao phoneNosDao = null;
    	switch (planType.planCode) {
    	case DIGITAL_VOICE:
        	if (assignedTelNo == null) {
        		throw new BillingSystemException("Assigned phone number cannot be null.");
        	}
        	phoneNosDao = digiVoiceNumbersDao;
        	if (!phoneNosDao.removePhoneNumber(assignedTelNo)) {
        		throw new BillingSystemException("Invalid assigned phone number. The number is not in the list.");
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
        		throw new BillingSystemException("Assigned phone number cannot be null.");
        	}
        	phoneNosDao = mobileNumbersDao;
        	if (!phoneNosDao.removePhoneNumber(assignedTelNo)) {
        		throw new BillingSystemException("Invalid assigned phone number. The number is not in the list.");
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
    		throw new BillingSystemException("Unknown plan type!");
    	}
		if (!subPlanDao.addAccountSubscription(acctNo, plan)) {
			throw new BillingSystemException("Failed to add subscription plan into doa.");
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
     * To register a new optional feature of the subscription plan.
     * @return a registered Feature object.
     * @throws BillingSystemException
     */
    public Feature registerNewFeature(String acctNo, String planId, FeatureType featureType, Date dateCommenced, Date dateTerminated) throws BillingSystemException {
		BillingSystemLogger.logInfo("Register new feature of account ["+acctNo+"], planId ["+planId+"] featureType ["+featureType+"] dateCommenced ["+dateCommenced+"] dateTerminated ["+dateTerminated+"]");
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
    	if (dateTerminated != null && dateTerminated.before(dateCommenced)) {
    		throw new BillingSystemException("Date terminated cannot be earlier than date commenced.");
    	}
    	if (!validateAccount(acctNo)) {
    		throw new BillingSystemException("The account is invalid or terminated.");
    	}
    	SubscriptionPlan plan = subPlanDao.getAccountSubscription(acctNo,planId);
    	if (plan == null) {
    		throw new BillingSystemException("Invalid plan id.");
    	}
    	if (dateCommenced.before(plan.getDateCommenced())) {
    		throw new BillingSystemException("Date commenced cannot be earlier than date commenced of subscription plan.");
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
     * To retrieve all registered (active) features of the subscription plan.
     * @return a list of Feature objects.
     */
    public List<Feature> getRegisteredFeatures(String acctNo, String planId) {
		BillingSystemLogger.logInfo("Retrieve registered features of account ["+acctNo+"], planId ["+planId+"]");
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
     * To retrieve feature of the subscription plan by feature id.
     * @return a Feature object.
     */
    public Feature getAccountSubscriptionFeature(String acctNo, String planId, String featureId) {
		BillingSystemLogger.logInfo("Retrieve a feature of subscription plan of account ["+acctNo+"], planId ["+planId+"] featureId ["+featureId+"]");
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
     * To retrieve all de-registered (terminated) features of the subscription plan.
     * @return a list of Feature objects.
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
    * To de-register (terminate) a feature of the subscription plan.
    * @throws BillingSystemException
    */
    public void deregisterFeature(String acctNo, String planId, String featureId, Date dateTerminated) throws BillingSystemException {
		BillingSystemLogger.logInfo("De-register feature of account ["+acctNo+"], planId ["+planId+"] featureId ["+featureId+"] dateTerminated ["+dateTerminated+"]");
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
    		throw new BillingSystemException("Date terminated cannot be null.");
    	}
    	if (!validateAccount(acctNo)) {
    		throw new BillingSystemException("The account is invalid or terminated.");
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
    	if (saveObjData) {
    		subPlanDao.saveObjectData();
    	}
    }

    /*
     * To de-register (terminate) a subscription plan.
     * @throws BillingSystemException
     */
    public void deregisterSubscriptionPlan(String acctNo, String planId, Date dateTerminated) throws BillingSystemException {
		BillingSystemLogger.logInfo("De-register subscription plan of account ["+acctNo+"], planId ["+planId+"] dateTerminated ["+dateTerminated+"]");
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
    		throw new BillingSystemException("The account is invalid or terminated.");
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
    	if (saveObjData) {
    		subPlanDao.saveObjectData();
    	}
    }
    
    /*
     * To validate customer account by checking the customer object is active.
     * @return true if the customer is active, otherwise false.
     */
    private boolean validateAccount(String acctNo) throws BillingSystemException {
		BillingSystemLogger.logInfo("Validate customer account ["+acctNo+"]");
    	Customer cust = MgrFactory.getAccountMgr().getCustomerDetailsByAccountId(acctNo);
    	if (cust == null) {
    		return false;
    	}
    	return !cust.isDeleted();
    }
}
