package sg.edu.nus.iss.billsys.constant;

/**
 * 
 * @author Xu Guoneng, Lem Kian Hoa (Stephen)
 *
 */
public enum PlanType {

	DigitalVoice(PlanCode.DIGITAL_VOICE, "Digital Voice"),
	MobileVoice(PlanCode.MOBILE_VOICE, "Mobile Voice"),
	CableTv(PlanCode.CABLE_TV, "Cable TV");
	
	public final PlanCode planCode;
	public final String name;
	
	private PlanType(PlanCode code, String name){
		this.planCode = code;
		this.name = name;
	}
	
	/**
	 * 
	 * @Client BillMgr
	 */
	public int getPlanCd(){
		return planCode.ordinal();
	}
	
	public String toString(){
		return name;
	}

	public enum PlanCode {
		DIGITAL_VOICE, MOBILE_VOICE, CABLE_TV
	}
}
