package sg.edu.nus.iss.billsys.vo;

import java.util.Date;
import sg.edu.nus.iss.billsys.constant.*;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.mgr.MgrFactory;

public class Feature {
	
	private final FeatureType aFeatureType;
	private String name;
	private String featureId;
	private Date dateCommenced;
	private Date dateTerminated;

	public Feature(String id, FeatureType aFeatureType, Date dateCommenced, Date dateTerminated) {
		this(id,aFeatureType, dateCommenced, dateTerminated, null);
	}

	public Feature(String id, FeatureType aFeatureType, Date dateCommenced, Date dateTerminated, String name) {
		this.featureId = id;
		this.aFeatureType = aFeatureType;
		this.dateCommenced = dateCommenced;
		this.dateTerminated = dateTerminated;
		this.name = (name == null ? aFeatureType.toString() : name); //for additional channel name
	}
	
	public FeatureType getFeatureType() {
		return aFeatureType;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isActive(Date billStartDate){
		return (dateTerminated == null) ? true : !dateTerminated.before(billStartDate);
	}

	public boolean isTerminated() {
		return (dateTerminated == null) ? false : (dateTerminated.getTime() <= System.currentTimeMillis());
	}	

	public Date getDateCommenced() {
		return dateCommenced;
	}

	public Date getDateTerminated() {
		return dateTerminated;
	}

	public void setDateTerminated(Date dateTerminated) {
		this.dateTerminated = dateTerminated;
	}
	
	public void setDateCommenced(Date dateCommenced) {
		this.dateCommenced = dateCommenced;
	}
	
	public int getSubscriptionCharges() throws BillingSystemException {
		return MgrFactory.getSubscriptionMgr().getSubscriptionCharge(aFeatureType);
	}

	public String getFeatureId() {
		return featureId;
	}
}
