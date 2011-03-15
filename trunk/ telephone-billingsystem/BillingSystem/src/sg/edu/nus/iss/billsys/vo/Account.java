package sg.edu.nus.iss.billsys.vo;

import java.util.*;

public class Account {

	private String acctNo;
	private ArrayList<SubscriptionPlan> plans;

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public ArrayList<SubscriptionPlan> getPlans() {
		return plans;
	}

	public void setPlans(ArrayList<SubscriptionPlan> plans) {
		this.plans = plans;
	}
	
	
}
