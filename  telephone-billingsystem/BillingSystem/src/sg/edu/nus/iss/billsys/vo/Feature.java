package sg.edu.nus.iss.billsys.vo;

import java.util.Date;
import sg.edu.nus.iss.billsys.constant.*;

public class Feature {
	
	private final FeatureType aFeatureType;
	private String name;
	private Date dateCommenced;
	private Date dateterminated;

	public Feature(FeatureType aFeatureType, Date dateCommenced, Date dateterminated, String name) {
		this.aFeatureType = aFeatureType;
		this.dateCommenced = dateCommenced;
		this.dateterminated = dateterminated;
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

	public Date getDateterminated() {
		return dateterminated;
	}

	public void setDateterminated(Date dateterminated) {
		this.dateterminated = dateterminated;
	}
}
