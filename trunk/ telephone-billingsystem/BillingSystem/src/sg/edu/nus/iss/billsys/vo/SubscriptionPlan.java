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

	public Date getDateterminated() {
		return basicFeature.getDateterminated();
	}

	public void setDateterminated(Date dateterminated) {
		this.basicFeature.setDateterminated(dateterminated);
	}
	
	public void addOptionalFeature(Feature feature){
		optionalFeatures.add(feature);
	}
	
	public void removeOptionalFeature(FeatureType type){
		Feature curr = null;
		for(Feature f : optionalFeatures){
			if(f.getaFeatureType() == type){
				curr = f;
				break;
			}
		}
		
		optionalFeatures.remove(curr);
	}
	
	public Feature getBasicFeature() {
		return basicFeature;
	}

	public ArrayList<Feature> getOptionalFeatures() {
		return optionalFeatures;
	}
	
	public abstract String getPlanDescription();
}
