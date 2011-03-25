package sg.edu.nus.iss.billsys.vo;
/**
 * 
 * @author: Wen Jing
 * Mar 24 2011
 *
 */
public class Customer implements Cloneable {

	
	private String name;
	private String address1;
	private String address2;
	private String address3;
	private String contact_tel;
	private String interest;
	private String isDeleted;
	private String nric;
	
	
	private Account acct;

	public Account getAcct() {
		return acct;
	}

	public void setAcct(Account acct) {
		this.acct = acct;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getContact_tel() {
		return contact_tel;
	}

	public void setContact_tel(String contact_tel) {
		this.contact_tel = contact_tel;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNric() {
		return nric;
	}

	public void setNric(String nric) {
		this.nric = nric;
	}
	
	public Object clone() throws CloneNotSupportedException {
        return super.clone();
	}
	
	public String getAddress(){
		return this.address1 + "\n" + this.address2 + "\n" + this.address3 + "\n";
	}
	
	public String showCustomer(){
		String custDetails = null;
		custDetails = "Name: " + this.name + "; NRIC: " + this.nric + ";\n";
		custDetails += "Address: " + this.getAddress();
		custDetails += "Contact: " + this.contact_tel + "\n";
		custDetails += "Interest: " + this.interest + "\n";
		if(this.isDeleted == "Y"){
			custDetails += "This customer has been deleted.";
		}
		return custDetails;
	}

}
