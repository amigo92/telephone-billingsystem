package sg.edu.nus.iss.billsys.vo;

import java.util.Date;

import sg.edu.nus.iss.billsys.constant.*;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;

/**
 * 
 * @author Lem Kian Hoa (Stephen)
 *
 */
public class CableTvPlan extends SubscriptionPlan {

	private static final long serialVersionUID = -1488023047795943161L;

	public CableTvPlan(String acctNo, Date dateCommenced, Date dateTerminated) {
		super(acctNo, new Feature(FeatureType.StdChannels, dateCommenced, dateTerminated, null));
	}

	@Override
	public PlanType getPlanType() {
		return PlanType.CableTv;
	}

	@Override
	public String getPlanDescription() {
		return "Cable TV";
	}

	@Override
	public void addOptionalFeature(Feature feature)
			throws BillingSystemException {
		FeatureType type = feature.getaFeatureType();
		switch (type.featureCode) {
		case ADD_CHANNEL:
			optionalFeatures.add(feature);
			break;
		default:
			throw new BillingSystemException(getPlanType().toString()+" cannot accept feature type "+type.toString());
		}
	}
}
