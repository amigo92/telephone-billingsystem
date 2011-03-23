package sg.edu.nus.iss.billsys.vo;

import java.io.Serializable;

/**
 * 
 * @author Xu Guoneng
 *
 */
public class CompanyProfile implements Serializable{

	private static final long serialVersionUID = -6711670615803325320L;

	private String alias;
	private String companyName;
	private String street;
	private String unit;
	private String country;
	private String postalCd;
	private String hotline;
	private String email;
	
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostalCd() {
		return postalCd;
	}
	public void setPostalCd(String postalCd) {
		this.postalCd = postalCd;
	}
	public String getHotline() {
		return hotline;
	}
	public void setHotline(String hotline) {
		this.hotline = hotline;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
