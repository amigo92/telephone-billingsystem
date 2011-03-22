package sg.edu.nus.iss.billsys.vo;

import java.util.*;

public class Account {
	
	private String acctNo;
	private int balance;
	private Date balanceUpdateDate;
	
	private HashMap<String,SubscriptionPlan> plans;

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	
	public void addPlan(SubscriptionPlan plan) {
		plans.put(plan.getPlanId(), plan);
	}
	
	public SubscriptionPlan getPlan(String planId) {
		return plans.get(planId);
	}

	public Collection<SubscriptionPlan> getPlans() {
		return plans.values();
	}

	public void setPlans(List<SubscriptionPlan> plans) {
		this.plans.clear();
		for (SubscriptionPlan plan : plans) {
			this.plans.put(plan.getPlanId(), plan);
		}
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Date getBalanceUpdateDate() {
		return balanceUpdateDate;
	}

	public void setBalanceUpdateDate(Date balanceUpdateDate) {
		this.balanceUpdateDate = balanceUpdateDate;
	}
	
}
