package sg.edu.nus.iss.billsys.constant;

/**
 * 
 * @author Xu Guoneng
 *
 */
public enum CallTxnType {

	LocalCall(0, "Local Calls"),
	IDD(1, "IDD Calls"),
	RoamingCall(2, "Roaming Calls");
	
	public final int typeCd;
	String name;
	
	CallTxnType(int typeCd, String name){
		this.typeCd = typeCd;
		this.name = name;
	}
	
	public String toString(){
		return name;
	}

}
