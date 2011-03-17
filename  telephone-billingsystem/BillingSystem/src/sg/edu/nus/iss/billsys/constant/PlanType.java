package sg.edu.nus.iss.billsys.constant;

/**
 * 
 * @author Xu Guoneng
 *
 */
public enum PlanType {

	DigitalVoice(0, "Digital Voice"),
	MobileVoice(1, "Mobile Voice"),
	CableTv(2, "Cable TV");
	
	int planTypeCd;
	String planName;
	
	PlanType(int code, String name){
		planTypeCd = code;
		planName = name;
	}
	
	public String toString(){
		return planName;
	}
}
