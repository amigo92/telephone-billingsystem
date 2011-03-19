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
	private final long referenceNo;

	private String acctNo;
	
	private final Feature basicFeature;
	protected final ArrayList<Feature> optionalFeatures;
	
	protected SubscriptionPlan(String acctNo, Feature basicFeature) {
		this.acctNo = acctNo;
		this.basicFeature = basicFeature;
		optionalFeatures =  new ArrayList<Feature>();
		referenceNo = new Date().getTime();
	}
	
	
	public long getReferenceNo() {
		return referenceNo;
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
	
	public Feature getBasicFeature() {
		return basicFeature;
	}

	public List<Feature> getOptionalFeatures() {
		return optionalFeatures;
	}
	
	public Feature getOptionalFeatureByType(FeatureType type){
		for(Feature curr : optionalFeatures){
			if(curr.getaFeatureType().equals(type)){
				return curr;
			}
		}
		return null;
	}
	
	public abstract PlanType getPlanType();
	
	public abstract String getPlanDescription();
	
	public abstract void addOptionalFeature(Feature feature) throws BillingSystemException;
}
