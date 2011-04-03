package sg.edu.nus.iss.billsys.vo;

import java.util.Date;

import sg.edu.nus.iss.billsys.constant.*;

/**
 * 
 * @author Lem Kian Hoa (Stephen)
 *
 */
public class DigitalVoicePlan extends VoicePlan {

	private static final long serialVersionUID = -2343499822220564332L;
	
	public DigitalVoicePlan(String planId, String acctNo, String assignedTelNo) {
		super(planId, acctNo, assignedTelNo, null);
	}
	
	public DigitalVoicePlan(String planId, String acctNo, String assignedTelNo, String basicFeatureId, Date dateCommenced, Date dateTerminated) {
		super(
			planId,
			acctNo,
			assignedTelNo,
			new Feature(
				basicFeatureId,
				FeatureType.Line,
				dateCommenced,
				dateTerminated
			)
		);
	}

	@Override
	public PlanType getPlanType() {
		return PlanType.DigitalVoice;
	}

	@Override
	public String getPlanDescription() {
		return "Digital Voice Number " + getAssignedTelNo();
	}
}
