//package sg.edu.nus.iss.billsys.vo;

/**
 * @author Wen Jing; Mar 19 2011;
 * Modified: 22 Mar 2011;
 * Methods: getters and setters;
 * Deregister: Set status to Deleted;
 * Reactivate: Set status to Active;
 * New Customer: Status = Fresh;
 */


public class Customer {
	private String custName;
	private String custNRIC;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String contactTel;
	private String custInterest;
	
	public Customer(String custName, String custNRIC, String addressLine1, String addressLine2, String addressLine3, String contactTel, String custInterest){
		this.custName = custName;
		this.custNRIC = custNRIC;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressLine3 = addressLine3;
		this.contactTel = contactTel;
		this.custInterest = custInterest;
	}
	
	public String getCustName() {
		return custName;
	}
	
	public String getCustAddress(){
		return this.addressLine1 + "\n" + this.addressLine2 + "\n" + this.addressLine3 + "\n";
	}

	public String showCustomer(){
		String custDetails = null;
		custDetails = "Customer Name: " + getCustName()+ ";\nNRIC: " + this.custNRIC + ";\nTel: " + this.contactTel + ";\n";
		custDetails += "Address: " + this.getCustAddress() + "Interest: " + this.custInterest+ ";\n";
		return custDetails;
	}

}
