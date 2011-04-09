package sg.edu.nus.iss.billsys.vo;

import java.util.Date;

import sg.edu.nus.iss.billsys.constant.*;


/**
 * 
 * @author Lem Kian Hoa (Stephen)
 *
 */
public class MobileVoicePlan extends VoicePlan {
	
	private static final long serialVersionUID = 7648993467677690245L;
	
	public MobileVoicePlan(String planId, String acctNo, String assignedTelNo) {
		super(planId, acctNo, assignedTelNo, null);
	}
	
	public MobileVoicePlan(String planId, String acctNo, String assignedTelNo, String basicFeatureId, Date dateCommenced, Date dateTerminated) {
		super(
			planId,
			acctNo,
			assignedTelNo,
			new Feature(
				basicFeatureId,
				FeatureType.Mobile,
				dateCommenced,
				dateTerminated
			)
		);
	}

	@Override
	public PlanType getPlanType() {
		return PlanType.MobileVoice;
	}

	@Override
	public String getPlanDescription() {
		return "Mobile Number (" + getAssignedTelNo() + ")";
	}
}
