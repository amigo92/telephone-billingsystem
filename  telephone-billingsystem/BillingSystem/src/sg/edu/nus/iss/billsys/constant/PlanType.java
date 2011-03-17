package sg.edu.nus.iss.billsys.constant;

/**
 * 
 * @author Xu Guoneng
 *
 */
public enum PlanType {

	DigitalVoice(0),
	MobileVoice(1),
	CableTv(2);
	
	int planTypeCd;
	
	PlanType(int code){
		planTypeCd = code;
	}
}
