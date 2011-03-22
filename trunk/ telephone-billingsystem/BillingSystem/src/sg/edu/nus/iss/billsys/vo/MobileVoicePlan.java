package sg.edu.nus.iss.billsys.vo;

import java.util.Date;

import sg.edu.nus.iss.billsys.constant.*;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;

/**
 * 
 * @author Lem Kian Hoa (Stephen)
 *
 */
public class MobileVoicePlan extends VoicePlan {
	
	private static final long serialVersionUID = 7648993467677690245L;
	
	public MobileVoicePlan(String acctNo, String assignedTelNo, Date dateCommenced, Date dateTerminated) {
		super(acctNo, assignedTelNo, new Feature(FeatureType.Mobile, dateCommenced, dateTerminated));
	}
	
	public String getAssignedTelNo() {
		return assignedTelNo;
	}

	@Override
	public PlanType getPlanType() {
		return PlanType.MobileVoice;
	}

	@Override
	public String getPlanDescription() {
		return "Mobile Number " + getAssignedTelNo();
	}
}
