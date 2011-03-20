package sg.edu.nus.iss.billsys.vo;

public class Customer implements Cloneable {

	private String customerId;
	private Account acct;

	public Account getAcct() {
		return acct;
	}

	public void setAcct(Account acct) {
		this.acct = acct;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public Object clone() throws CloneNotSupportedException {
        return super.clone();
	}

}
