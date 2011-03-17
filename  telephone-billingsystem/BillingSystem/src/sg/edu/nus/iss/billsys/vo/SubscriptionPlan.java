package sg.edu.nus.iss.billsys.vo;

import java.io.Serializable;
import java.util.*;
import sg.edu.nus.iss.billsys.constant.*;

public abstract class SubscriptionPlan implements Serializable{

	private static final long serialVersionUID = 1572263436009780430L;

	private String acctNo;
	
	protected Feature basicFeature;
	protected ArrayList<Feature> optionalFeatures;
	
	protected SubscriptionPlan(Feature basicFeature){
		this.basicFeature = basicFeature;
		optionalFeatures =  new ArrayList<Feature>();
	}
	
	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public Date getDateCommenced() {
		return basicFeature.getDateCommenced();
	}

	public Date getDateTerminated() {
		return basicFeature.getDateTerminated();
	}

	public void setDateTerminated(Date dateTerminated) {
		this.basicFeature.setDateTerminated(dateTerminated);
	}
	
	public void addOptionalFeature(Feature feature){
		optionalFeatures.add(feature);
	}
	
	public Feature getOptionalFeatureByType(FeatureType type){
		for(Feature curr : optionalFeatures){
			if(curr.getaFeatureType().equals(type)){
				return curr;
			}
		}
		
		return null;
	}
	
	public Feature getBasicFeature() {
		return basicFeature;
	}

	public ArrayList<Feature> getOptionalFeatures() {
		return optionalFeatures;
	}
	
	public abstract PlanType getPlanType();
	
	public abstract String getPlanDescription();
	
	public abstract boolean isCallBased();
	
	public abstract int getUsageRate(CallTxnType type);
}
