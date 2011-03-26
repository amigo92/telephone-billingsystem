package sg.edu.nus.iss.billsys.vo;
/**
 * 
 * @author: Wen Jing
 * Mar 24 2011
 *
 */
import java.text.*;
import java.util.*;

public class Account {
	
	private String acctNo;
	private int balance;
	private Date balanceUpdateDate;
	private int paymentTerms;
	private Date dateCreated;
	private Date dateDeleted;
	
//	private HashMap<String,SubscriptionPlan> plans;

	public Account(Date today, int nextAcct){
		this.paymentTerms = 21;
		this.balance = 0;
		this.balanceUpdateDate = null;
		this.acctNo = this.genAcctNo(today, nextAcct);
	}
	public Account() {
		// TODO Auto-generated constructor stub
	}
	
	private String genAcctNo(Date today, int nextAcct){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		NumberFormat nf = new DecimalFormat("#00");
		return  "SA-" + Integer.toString(year) + "-" + nf.format(month) + nf.format(nextAcct);
	}
	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateDeleted() {
		return dateDeleted;
	}
	public void setDateDeleted(Date dateDeleted) {
		this.dateDeleted = dateDeleted;
	}
	/*	public void addPlan(SubscriptionPlan plan) {
		plans.put(plan.getPlanId(), plan);
	}
	
	public SubscriptionPlan getPlan(String planId) {
		return plans.get(planId);
	}

	public Collection<SubscriptionPlan> getPlans() {
		return plans.values();
	}

	public void setPlans(List<SubscriptionPlan> plans) {
		this.plans=new HashMap<String,SubscriptionPlan>();
		for (SubscriptionPlan plan : plans) {
			this.plans.put(plan.getPlanId(), plan);
		}
	}
*/
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public void updatePayment(int amountPaid){
		this.balance -= amountPaid;
	}

	public Date getBalanceUpdateDate() {
		return balanceUpdateDate;
	}

	public void setBalanceUpdateDate(Date balanceUpdateDate) {
		this.balanceUpdateDate = balanceUpdateDate;
	}

	public String showAccount(){
		String accDetails = null;
		accDetails = "Account No: " + this.acctNo + "\n";
		return accDetails;
	}
	
}