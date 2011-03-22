package sg.edu.nus.iss.billsys.vo;

import java.util.Date;

import sg.edu.nus.iss.billsys.constant.*;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;

/**
 * 
 * @author Lem Kian Hoa (Stephen)
 *
 */
public class DigitalVoicePlan extends VoicePlan {

	private static final long serialVersionUID = -2343499822220564332L;
	
	public DigitalVoicePlan(String acctNo, String assignedTelNo, Date dateCommenced, Date dateTerminated){
		super(acctNo, assignedTelNo, new Feature(FeatureType.Line, dateCommenced, dateTerminated));
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
