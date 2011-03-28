package sg.edu.nus.iss.billsys.constant;

/**
 * 
 * @author Xu Guoneng, Lem Kian Hoa (Stephen)
 *
 */
public enum FeatureType {
	
	//basic feature
	Line(FeatureCode.LINE, "Line", false, false, false),
	Mobile(FeatureCode.MOBILE, "Mobile", false, false, false),
	StdChannels(FeatureCode.STD_CHANNEL, "Three Standard Channels", false, false, false),

	//optional feature
	DigiIDD(FeatureCode.DIGI_IDD, "IDD Calls", true, false, false),
	MobileIDD(FeatureCode.MOBILE_IDD, "IDD Calls", true, false, false),
	CallTransfer(FeatureCode.CALL_TRANSFER, "Call Transfer", true, false, false),
	DataService(FeatureCode.DATA_SERVICES, "Data Services", true, false, false),
	Roaming(FeatureCode.ROAMING, "Roaming", true, false, false),
	AddChannel(FeatureCode.ADD_CHANNEL, "Additional Channel", true, false, true),
	
	//call txn type
	DigiLocalCall(FeatureCode.DIGI_LOCAL_CALL, "Local Calls", false, true, false), 		
	MobileLocalCall(FeatureCode.MOBILE_LOCAL_CALL, "Local Calls", false, true, false), 		
	DigiIDDCall(FeatureCode.DIGI_IDD_CALL, "IDD Calls", true, true, false), 	
	MobileIDDCall(FeatureCode.MOBILE_IDD_CALL, "IDD Calls", true, true, false), 	
	RoamingCall(FeatureCode.ROAMING_CALL, "Roaming Calls", true, true, false);

	
	public enum FeatureCode {
		LINE, MOBILE, STD_CHANNEL,
		DIGI_IDD, MOBILE_IDD, CALL_TRANSFER, DATA_SERVICES, ROAMING, ADD_CHANNEL,
		DIGI_LOCAL_CALL, MOBILE_LOCAL_CALL, DIGI_IDD_CALL, MOBILE_IDD_CALL, ROAMING_CALL,
	}
	
	public final FeatureCode featureCode;
	public final String name;
	public final boolean isOptional;
	public final boolean usageCharge;
	public final boolean allowMultiple;
	
	private FeatureType(FeatureCode code, String name, boolean isOptional, boolean usageCharge, boolean allowMultiple) {
		this.featureCode = code;
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
		return featureCode.ordinal();
	}
	
	public String toString(){
		return name;
	}
}
