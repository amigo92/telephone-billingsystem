package sg.edu.nus.iss.billsys.vo;

/**
 * 
 * @author Lem Kian Hoa (Stephen)
 *
 */
public abstract class VoicePlan extends SubscriptionPlan {
	
	private static final long serialVersionUID = 7359975007504783302L;

	protected final String assignedTelNo;
	
	public VoicePlan(String acctNo, String assignedTelNo, Feature feature) {
		super(acctNo, feature);
		this.assignedTelNo = assignedTelNo;
	}
	
	public String getAssignedTelNo() {
		return assignedTelNo;
	}
}
