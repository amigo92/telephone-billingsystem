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
		super(acctNo, assignedTelNo, new Feature(FeatureType.Line, dateCommenced, dateTerminated, null));
	}

	@Override
	public PlanType getPlanType() {
		return PlanType.DigitalVoice;
	}

	@Override
	public String getPlanDescription() {
		return "Digital Voice Number " + getAssignedTelNo();
	}

	@Override
	public void addOptionalFeature(Feature feature)
			throws BillingSystemException {
		FeatureType type = feature.getaFeatureType();
		switch (type.featureCode) {
		case LOCAL_CALL:
		case IDD_CALL:
		case CALL_TRANSFER:
			optionalFeatures.add(feature);
			break;
		default:
			throw new BillingSystemException(getPlanType().toString()+" cannot accept feature type "+type.toString());
		}
	}
}
