import java.util.*;
import java.text.*;


//package sg.edu.nus.iss.billsys.vo;

/**
 * @author Wen Jing; Mar 19 2011;
 * Modified: 20 Mar 2011;
 * Methods: getters and setters;
 * paymentTerms: bill due in how many days;
 * setDueDate(): Calculate next due date based on the latest bill date
 */

public class Account {
	private int numOfPlans;
	private String accountId;
	private Date accountCreated;
	private Date lastDeregistered;
	private AccStatus accStatus;
	
    private List<SubscriptionPlan> plans;
    private Customer customer;
	
    SimpleDateFormat dateFormat = new SimpleDateFormat();
    public Account(Customer customer, int nextAccountId, Date today){
    	Calendar calendar = Calendar.getInstance();
		if(customer == null){
			System.out.println("Error! Invalid customer!");
			return;
		}
		calendar.setTime(today);
		this.accountId  = this.genAccountId(nextAccountId, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
		this.accountCreated = today;
		this.lastDeregistered = null;
		this.accStatus = AccStatus.Active;
		this.customer = customer;
		this.numOfPlans = 0;
		plans = new ArrayList<SubscriptionPlan>();
	}
	
	public String genAccountId(int nextAccountId, int year, int month){
		String newAccountId = null;
		newAccountId = "SA-" + year + "-" + month + "-" + nextAccountId; 
		return newAccountId;
	}
	
	public Date getLastDeregistered() {
		return lastDeregistered;
	}

	public void setLastDeregistered(Date lastDeregistered) {
		this.lastDeregistered = lastDeregistered;
	}

	public AccStatus getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(AccStatus accStatus, Date today) {
		this.accStatus = accStatus;
		if(accStatus == AccStatus.Deleted){
			this.lastDeregistered = today;
		}
	}

	public int getNumOfPlans() {
		return numOfPlans;
	}

	public String getAccountId() {
		return accountId;
	}

	public Date getAccountCreated() {
		return accountCreated;
	}

	public Customer getCustomer() {
		return customer;
	}

	public String showAccount(){
		String accDetails = null;
		accDetails = "Account No.: " + this.getAccountId() + "; Customer: " + customer.getCustName() + "\n"; 
		accDetails += "Created at: " + dateFormat.format(this.getAccountCreated()) + ";";
		if(this.getAccStatus() == AccStatus.Deleted){
			accDetails += " Terminated at: " + dateFormat.format(this.getLastDeregistered());
		}
		return accDetails;
	}
}
