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
		super(acctNo, assignedTelNo, new Feature(FeatureType.Mobile, dateCommenced, dateTerminated, null));
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

	@Override
	public void addOptionalFeature(Feature feature)
			throws BillingSystemException {
		FeatureType type = feature.getaFeatureType();
		switch (type.featureCode) {
		case LOCAL_CALL:
		case IDD_CALL:
		case ROAMING_CALL:
		case DATA_SERVICES:
			optionalFeatures.add(feature);
			break;
		default:
			throw new BillingSystemException(getPlanType().toString()+" cannot accept feature type "+type.toString());
		}
	}
}
