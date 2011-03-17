package sg.edu.nus.iss.billsys.vo;

import java.util.Date;

import sg.edu.nus.iss.billsys.constant.CallTxnType;
import sg.edu.nus.iss.billsys.constant.FeatureType;
import sg.edu.nus.iss.billsys.constant.PlanType;

public class MobileVoicePlan extends SubscriptionPlan {
	
	private static final long serialVersionUID = 7648993467677690245L;
	private String assignedTelNo;
	
	public MobileVoicePlan(String assignedTelNo, Date dateCommenced, Date dateterminated){
		super(new Feature(FeatureType.Mobile, dateCommenced, dateterminated, null));
		this.assignedTelNo = assignedTelNo;
	}
	
	public PlanType getPlanType(){
		return PlanType.MobileVoice;
	}
	
	public String getPlanDescription(){
		return "Mobile Number " + getAssignedTelNo();
	}
	
	public String getAssignedTelNo() {
		return assignedTelNo;
	}

	public boolean isCallBased(){
		return true;
	}
	
	public int getUsageRate(CallTxnType type){
		return 0; //TODO
	}
}
