package sg.edu.nus.iss.billsys.constant;

/**
 * 
 * @author Xu Guoneng, Lem Kian Hoa (Stephen)
 *
 */
public enum FeatureType {
	
	//basic feature
	Line("Line", false, false, false),									//0
	Mobile("Mobile", false, false, false),								//1
	StdChannels("Three Standard Channels", false, false, false),		//2

	//optional feature
	DigiIDD("IDD Calls", true, false, false),							//3
	MobileIDD("IDD Calls", true, false, false),							//4
	CallTransfer("Call Transfer", true, false, false),					//5
	DataService("Data Services", true, false, false),					//6
	Roaming("Roaming", true, false, false),								//7
	AddChannel("Additional Channel", true, false, true),				//8
	
	//call txn type
	DigiLocalCall("Local Calls", false, true, false), 					//9
	MobileLocalCall("Local Calls", false, true, false),  				//10		
	DigiIDDCall("IDD Calls", true, true, false),  						//11	
	MobileIDDCall("IDD Calls", true, true, false), 	 					//12
	RoamingCall("Roaming Calls", true, true, false); 					//13
	
	public final String name;
	public final boolean isOptional;
	public final boolean usageCharge;
	public final boolean allowMultiple;
	
	private FeatureType(String name, boolean isOptional, boolean usageCharge, boolean allowMultiple) {
		this.name = name;
		this.isOptional = isOptional;
		this.usageCharge = usageCharge;
		this.allowMultiple = allowMultiple;
	}
	
	/**
	 * 
	 * @return int representation of Feature Code, as it is an Integer in the .txt file
	 */
	public int getFeatureCd(){
		return ordinal();
	}
	
	public String toString(){
		return name;
	}

}
