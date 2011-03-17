package sg.edu.nus.iss.billsys.vo;

import java.util.Date;

import sg.edu.nus.iss.billsys.constant.CallTxnType;
import sg.edu.nus.iss.billsys.constant.FeatureType;
import sg.edu.nus.iss.billsys.constant.PlanType;

public class CableTvPlan extends SubscriptionPlan {

	private static final long serialVersionUID = -1488023047795943161L;

	public CableTvPlan(Date dateCommenced, Date dateterminated){
		super(new Feature(FeatureType.StdChannels, dateCommenced, dateterminated, null));
	}
	
	public PlanType getPlanType(){
		return PlanType.CableTv;
	}
	
	public String getPlanDescription(){
		return "Cable TV";
	}
	
	public boolean isCallBased(){
		return false;
	}
	
	public int getUsageRate(CallTxnType type){
		return 0; //TODO
	}
}
