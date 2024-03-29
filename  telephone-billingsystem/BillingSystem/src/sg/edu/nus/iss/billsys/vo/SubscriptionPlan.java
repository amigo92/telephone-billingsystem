package sg.edu.nus.iss.billsys.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sg.edu.nus.iss.billsys.constant.*;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;

/**
 * 
 * @author Lem Kian Hoa (Stephen)
 *
 */
public abstract class SubscriptionPlan implements Serializable {

	private static final long serialVersionUID = 1572263436009780430L;

	private String planId;
	private String acctNo;	
	private Feature basicFeature;
	protected final ArrayList<Feature> optionalFeatures;
	
	protected SubscriptionPlan(String planId, String acctNo, Feature basicFeature) {
		this.planId = planId;
		this.acctNo = acctNo;
		this.basicFeature = basicFeature;
		optionalFeatures =  new ArrayList<Feature>();
	}

	public String getPlanId() {
		return planId;
	}
	
	public String getAcctNo() {
		return acctNo;
	}

	public Date getDateCommenced() {
		return basicFeature.getDateCommenced();
	}

	public Date getDateTerminated() {
		return basicFeature.getDateTerminated();
	}

	public void setDateTerminated(Date dateTerminated) {
		basicFeature.setDateTerminated(dateTerminated);
	}
	
	public void setDateCommenced(Date dateCommenced) {
		basicFeature.setDateCommenced(dateCommenced);
	}
	
	public Feature getBasicFeature() {
		return basicFeature;
	}
	
	public void setBasicFeature(Feature basicFeature) {
		this.basicFeature = basicFeature;
	}
	
	/*
	 * To add an optional feature into the subscription plan.
	 * 1. The feature must be in the plan type optional features.
	 * 2. If the feature already exists, check if the feature allow multiple
	 *    registration or the feature is terminated.
	 * @throws BillingSystemException
	 */
	public void addOptionalFeature(Feature feature) throws BillingSystemException {
		for (FeatureType ft : getPlanType().optionalFeatures) {
			if (ft == feature.getFeatureType()) {
				for (Feature f : optionalFeatures) {
					if (f.getFeatureType() == feature.getFeatureType()) {
						if (!f.getFeatureType().allowMultiple && !f.isTerminated()) {
							throw new BillingSystemException("Feature ["+feature.getFeatureType().toString()+"] already exists and is active.");
						}
					}
				}
				optionalFeatures.add(feature);
				return;
			}
		}
		throw new BillingSystemException(getPlanType().toString()+" cannot accept feature type ["+feature.getFeatureType().toString()+"]");
	}

	public List<Feature> getOptionalFeatures() {
		return optionalFeatures;
	}
	
	public Feature getOptionalFeatureById(String featureId) {
		for(Feature curr : optionalFeatures){
			if(curr.getFeatureId().equals(featureId)){
				return curr;
			}
		}
		return null;
	}
	
	public abstract PlanType getPlanType();
	
	public abstract String getPlanDescription();
}
