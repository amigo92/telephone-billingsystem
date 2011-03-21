package sg.edu.nus.iss.billsys.constant;

/**
 * 
 * @author Xu Guoneng, Lem Kian Hoa (Stephen)
 *
 */
public enum FeatureType {
	
	//basic feature
	Line(FeatureCode.LINE, "Line", false,false),
	Mobile(FeatureCode.MOBILE, "Mobile", false, false),
	StdChannels(FeatureCode.STD_CHANNEL, "Three Standard Channels", false, false),

	//optional feature
	DigiIDD(FeatureCode.DIGI_IDD, "IDD Calls", true,false),
	MobileIDD(FeatureCode.MOBILE_IDD, "IDD Calls", true,false),
	CallTransfer(FeatureCode.CALL_TRANSFER, "Call Transfer", true,false),
	DataService(FeatureCode.DATA_SERVICES, "Data Services", true, false),
	Roaming(FeatureCode.ROAMING, "Roaming", true, false),
	AddChannel(FeatureCode.ADD_CHANNEL, "Additional Channel", true, false),
	
	//call txn type
	LocalCall(FeatureCode.LOCAL_CALL, "Local Calls", false, true), 		
	IDDCall(FeatureCode.IDD_CALL, "IDD Calls", true, true), 	
	RoamingCall(FeatureCode.ROAMING_CALL, "Roaming Calls", true, true);

	
	public enum FeatureCode {
		LINE, MOBILE, STD_CHANNEL,
		LOCAL_CALL, IDD_CALL, ROAMING_CALL,
		DIGI_IDD, MOBILE_IDD, CALL_TRANSFER, DATA_SERVICES, ROAMING, ADD_CHANNEL
	}
	
	public final FeatureCode featureCode;
	public final boolean isOptional;
	public final boolean usageCharge;
	public final String name;
	
	private FeatureType(FeatureCode code, String name, boolean isOptional, boolean usageCharge) {
		this.featureCode = code;
		this.name = name;
		this.isOptional = isOptional;
		this.usageCharge = usageCharge;
	}
	
	/**
	 * 
	 * @return int representation of Feature Code, as it is an Integer in the .txt file
	 */
	public int getFeatureCd(){
		return featureCode.ordinal();
	}
	
	public String toString(){
		return name;
	}
}
