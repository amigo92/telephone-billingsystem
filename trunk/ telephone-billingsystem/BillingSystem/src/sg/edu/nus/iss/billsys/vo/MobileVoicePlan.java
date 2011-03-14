package sg.edu.nus.iss.billsys.vo;

import java.util.Date;

import sg.edu.nus.iss.billsys.constant.FeatureType;

public class MobileVoicePlan extends SubscriptionPlan {
	
	private static final long serialVersionUID = 7648993467677690245L;
	private String assignedTelNo;
	
	public MobileVoicePlan(String assignedTelNo, Date dateCommenced, Date dateterminated){
		super(new Feature(FeatureType.Mobile, dateCommenced, dateterminated));
		this.assignedTelNo = assignedTelNo;
	}
	
	public String getPlanDescription(){
		return "Mobile Number " + getAssignedTelNo();
	}
	
	public String getAssignedTelNo() {
		return assignedTelNo;
	}

	public void setAssignedTelNo(String assignedTelNo) {
		this.assignedTelNo = assignedTelNo;
	}
}
