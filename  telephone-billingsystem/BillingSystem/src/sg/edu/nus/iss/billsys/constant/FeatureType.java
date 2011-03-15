package sg.edu.nus.iss.billsys.constant;

/**
 * 
 * @author Xu Guoneng
 *
 */
public enum FeatureType {
	
	Line(0, "Line", false),
	CallTransfer(1, "Call Transfer", true),
	Mobile(2, "Mobile", false),
	DataService(3, "Data Services", true),
	Roaming(4, "Roaming", true),
	StdChannels(5, "Three Standard Channels", false),
	AddChannel(6, "Additional Channel", true);
	
	public int typeCd;
	public boolean isOptional;
	public String name;
	
	FeatureType(int typeCd, String name, boolean isOptional){
		this.typeCd = typeCd;
		this.name = name;
		this.isOptional = isOptional;
	}
	
	public String toString(){
		return name;
	}
}
