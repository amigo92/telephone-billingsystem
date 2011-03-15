package sg.edu.nus.iss.billsys.vo;

import java.util.Date;
import sg.edu.nus.iss.billsys.constant.*;

public class Feature {
	
	private final FeatureType aFeatureType;
	private String name;
	private Date dateCommenced;
	private Date dateTerminated;

	public Feature(FeatureType aFeatureType, Date dateCommenced, Date dateTerminated, String name) {
		this.aFeatureType = aFeatureType;
		this.dateCommenced = dateCommenced;
		this.dateTerminated = dateTerminated;
		this.name = (name == null ? aFeatureType.toString() : name); //for additional channel name
	}
	
	public FeatureType getaFeatureType() {
		return aFeatureType;
	}
	
	public String getName() {
		return name;
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
}