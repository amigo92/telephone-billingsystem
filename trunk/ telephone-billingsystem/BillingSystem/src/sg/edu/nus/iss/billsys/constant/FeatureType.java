package sg.edu.nus.iss.billsys.constant;

/**
 * 
 * @author Xu Guoneng, Lem Kian Hoa (Stephen)
 *
 */
public enum FeatureType {
	
	LocalCall(FeatureCode.LOCAL_CALL, "Local Calls", true, true),
	IDD(FeatureCode.IDD_CALL, "IDD Calls", true, true),
	RoamingCall(FeatureCode.ROAMING_CALL, "Roaming Calls", true, true),
	Line(FeatureCode.LINE, "Line", false,false),
	CallTransfer(FeatureCode.CALL_TRANSFER, "Call Transfer", true,false),
	Mobile(FeatureCode.MOBILE, "Mobile", false, false),
	DataService(FeatureCode.DATA_SERVICES, "Data Services", true, false),
	Roaming(FeatureCode.ROAMING, "Roaming", true, false),
	StdChannels(FeatureCode.STD_CHANNEL, "Three Standard Channels", false, false),
	AddChannel(FeatureCode.ADD_CHANNEL, "Additional Channel", true, false);
	
	public final FeatureCode featureCode;
	public final boolean isOptional;
	public final boolean usageCharge;
	public final String name;
	
	private FeatureType(FeatureCode code, String name, boolean isOptional, boolean usageCharge){
		this.featureCode = code;
		this.name = name;
		this.isOptional = isOptional;
		this.usageCharge = usageCharge;
	}
	
	public String toString(){
		return name;
	}
	
	public enum FeatureCode {
		LOCAL_CALL, IDD_CALL, ROAMING_CALL, LINE, CALL_TRANSFER, MOBILE, DATA_SERVICES,ROAMING, STD_CHANNEL, ADD_CHANNEL
	}
}
