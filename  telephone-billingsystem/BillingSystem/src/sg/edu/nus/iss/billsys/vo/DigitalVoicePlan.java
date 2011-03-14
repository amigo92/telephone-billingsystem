package sg.edu.nus.iss.billsys.vo;

import java.util.Date;

import sg.edu.nus.iss.billsys.constant.FeatureType;

public class DigitalVoicePlan extends SubscriptionPlan {

	private String assignedTelNo;
	
	public DigitalVoicePlan(Date dateCommenced, Date dateterminated){
		super(new Feature(FeatureType.Line, dateCommenced, dateterminated));
	}
	
	public String getPlanDescription(){
		return "Digital Voice Number " + getAssignedTelNo();
	}
	
	public String getAssignedTelNo() {
		return assignedTelNo;
	}

	public void setAssignedTelNo(String assignedTelNo) {
		this.assignedTelNo = assignedTelNo;
	}


}
