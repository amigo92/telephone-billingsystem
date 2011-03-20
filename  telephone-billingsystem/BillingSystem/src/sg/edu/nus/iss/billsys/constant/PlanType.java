package sg.edu.nus.iss.billsys.constant;

/**
 * 
 * @author Xu Guoneng, Lem Kian Hoa (Stephen)
 *
 */
public enum PlanType {

	DigitalVoice(
		PlanCode.DIGITAL_VOICE,
		"Digital Voice",
		FeatureType.Line,
		new FeatureType[] {
			FeatureType.LocalCall,
			FeatureType.IDDCall,
			FeatureType.CallTransfer
		}),
	MobileVoice(
		PlanCode.MOBILE_VOICE,
		"Mobile Voice",
		FeatureType.Mobile,
		new FeatureType[] {
			FeatureType.LocalCall,
			FeatureType.IDDCall,
			FeatureType.DataService,
			FeatureType.Roaming,
			FeatureType.RoamingCall
		}),
	CableTv(
		PlanCode.CABLE_TV,
		"Cable TV",
		FeatureType.StdChannels,
		new FeatureType[] {
			FeatureType.AddChannel
		});
	
	public final PlanCode planCode;
	public final String name;
	public final FeatureType basicFeature;
	public final FeatureType[] optionalFeatures;
	
	private PlanType(PlanCode code, String name, FeatureType basicFeature, FeatureType[] optionalFeatures) {
		this.planCode = code;
		this.name = name;
		this.basicFeature = basicFeature;
		this.optionalFeatures = optionalFeatures;
	}
	
	/**
	 * 
	 * @return int representation of Plan Code, as it is an Integer in the .txt file
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
