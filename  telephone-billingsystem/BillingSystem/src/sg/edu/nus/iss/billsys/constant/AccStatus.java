package sg.edu.nus.iss.billsys.constant;

/**
 * @author Wen Jing; Mar 19 2011
 * to keep the status of accounts;
 * Active, Deleted
 */
public enum AccStatus {
	Active("Active", "Active"),
	Deleted("Deleted", "Deleted");
	
	String statName;
	String statDesc;
	
	AccStatus(String statName, String statDesc){
		this.statName = statName;
		this.statDesc = statDesc;
	}
	
	public String getStatName(String statName){
		String s = null;
		for(int i = 0; i< 3; i++){
			if(this.statName.endsWith(statName))
				s = this.statDesc;
		}
		return s;
	}

}
