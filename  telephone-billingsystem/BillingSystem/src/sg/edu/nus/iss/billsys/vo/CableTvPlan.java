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
		super(acctNo, new Feature(FeatureType.StdChannels, dateCommenced, dateTerminated));
	}

	@Override
	public PlanType getPlanType() {
		return PlanType.CableTv;
	}

	@Override
	public String getPlanDescription() {
		return "Cable TV";
	}
}
