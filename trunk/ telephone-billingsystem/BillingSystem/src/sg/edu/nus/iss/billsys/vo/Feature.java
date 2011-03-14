package sg.edu.nus.iss.billsys.vo;

import java.util.Date;
import sg.edu.nus.iss.billsys.constant.*;

public class Feature {
	
	private final FeatureType aFeatureType;

	private String name;
	private Date dateCommenced;
	private Date dateterminated;

	public Feature(FeatureType aFeatureType, Date dateCommenced, Date dateterminated) {
		super();
		this.aFeatureType = aFeatureType;
		this.name = aFeatureType.toString();
		this.dateCommenced = dateCommenced;
		this.dateterminated = dateterminated;
	}
	
	public FeatureType getaFeatureType() {
		return aFeatureType;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateCommenced() {
		return dateCommenced;
	}

	public void setDateCommenced(Date dateCommenced) {
		this.dateCommenced = dateCommenced;
	}

	public Date getDateterminated() {
		return dateterminated;
	}

	public void setDateterminated(Date dateterminated) {
		this.dateterminated = dateterminated;
	}
}
