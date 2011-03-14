package sg.edu.nus.iss.billsys.constant;

public enum FeatureType {
	
	Line("Line", false),
	CallTransfer("Call Transfer", true),
	Mobile("Mobile", false),
	DataService("Data Services", true),
	Roaming("Roaming", true),
	StdChannels("3 Standard Channels", false);
	
	public boolean isOptional;
	public String name;
	
	FeatureType(String name, boolean isOptional){
		this.name = name;
		this.isOptional = isOptional;
	}
	
	public String toString(){
		return name;
	}
	
	public int getSubscriptionCharges(){
		return 0; //TODO
	}

}
