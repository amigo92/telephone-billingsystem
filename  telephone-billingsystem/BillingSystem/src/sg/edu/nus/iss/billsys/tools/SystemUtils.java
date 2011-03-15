package sg.edu.nus.iss.billsys.tools;

import sg.edu.nus.iss.billsys.vo.*;

/**
 * 
 * @author Xu Guoneng
 *
 */
public class SystemUtils {

	public static CompanyProfile getCompanyProfile(){
		CompanyProfile profile = new CompanyProfile();
		
		//TODO
		profile.setCompanyName("One# Pte Ltd");
		profile.setStreet("41 Uni Lane");
		profile.setUnit("#02-003");
		profile.setCountry("Singapore");
		profile.setPostalCd("654092");
		profile.setHotline("Call 1654");
		profile.setEmail("customer@onehash.com");
		
		return profile;
	}

}
