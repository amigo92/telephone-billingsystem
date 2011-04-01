package sg.edu.nus.iss.billsys.vo;

import java.util.Date;

import sg.edu.nus.iss.billsys.constant.*;
import sg.edu.nus.iss.billsys.dao.SubscriptionPlanDao;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.tools.TimeUtils;

/**
 * 
 * @author Lem Kian Hoa (Stephen)
 *
 */
public class CableTvPlan extends SubscriptionPlan {

	private static final long serialVersionUID = -1488023047795943161L;

	public CableTvPlan(String planId, String acctNo) {
		super(planId, acctNo, null);
	}
	
	public CableTvPlan(String planId, String acctNo, String basicFeatureId, Date dateCommenced, Date dateTerminated) {
		super(
			planId,
			acctNo,
			new Feature(
				basicFeatureId,
				FeatureType.StdChannels,
				dateCommenced,
				dateTerminated
			)
		);
	}

	@Override
	public PlanType getPlanType() {
		return PlanType.CableTv;
	}

	@Override
	public String getPlanDescription() {
		String id = getPlanId();
		if (id.length() > 12) {
			id = id.substring(id.length()-12);
		}
		return "Cable TV MAC ID "+id.toUpperCase();
	}
}
