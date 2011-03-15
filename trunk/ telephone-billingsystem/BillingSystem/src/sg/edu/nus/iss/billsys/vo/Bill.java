package sg.edu.nus.iss.billsys.vo;

import java.util.*;
import sg.edu.nus.iss.billsys.tools.TimeUtils;

/**
 * 
 * @author Xu Guoneng
 *
 */
public class Bill {

	private String billDate;
	private String dueDate;
	
	private CompanyProfile aCompanyProfile;
	private Customer aCustomer;
	private Account aAccount;
	
	private int previousBalance;
	private int totalPaymentMade;
	private int currChargesDue;
	private int totalGST;
	
	private ArrayList<Entry> aPaymentReceivedList;
	private ArrayList<SummaryCharges> aSummaryChargesList;
	private ArrayList<DetailCharges> aDetailChargesList;
	
	public Bill(){
		aPaymentReceivedList = new ArrayList<Entry>();
		aSummaryChargesList = new ArrayList<SummaryCharges>();
		aDetailChargesList = new ArrayList<DetailCharges>();
	}
	
	public void addPaymentReceived(Date date, int amt){
		aPaymentReceivedList.add(new Entry(TimeUtils.formatDate(date), amt));
	}
	
	public void addSummaryCharges(SummaryCharges charges){
		aSummaryChargesList.add(charges);
	}

	public void setBillDate(Date date) {
		this.billDate = TimeUtils.formatDate(date);
	}

	public void setDueDate(Date date) {
		this.dueDate = TimeUtils.formatDate(date);
	}

	public void setaCompanyProfile(CompanyProfile aCompanyProfile) {
		this.aCompanyProfile = aCompanyProfile;
	}

	public void setaCustomer(Customer aCustomer) {
		this.aCustomer = aCustomer;
	}

	public void setaAccount(Account aAccount) {
		this.aAccount = aAccount;
	}

	public void setPreviousBalance(int previousBalance) {
		this.previousBalance = previousBalance;
	}

	public void setTotalPaymentMade(int totalPaymentMade) {
		this.totalPaymentMade = totalPaymentMade;
	}

	public void setCurrChargesDue(int currChargesDue) {
		this.currChargesDue = currChargesDue;
	}

	public void setTotalGST(int totalGST) {
		this.totalGST = totalGST;
	}


	public class SummaryCharges{
		String desc;
		ArrayList<Entry> entries;
		int totalAmt;
		
		public SummaryCharges(String desc, ArrayList<Entry> entries, int totalAmt) {
			this.desc = desc;
			this.entries = entries;
			this.totalAmt = totalAmt;
		}
	}
	
	public class DetailCharges{
		String desc;
		ArrayList<Entry> entries;
		int totalAmt;
		
		public DetailCharges(String desc, ArrayList<Entry> entries, int totalAmt) {
			super();
			this.desc = desc;
			this.entries = entries;
			this.totalAmt = totalAmt;
		}
	}
	
	public class Entry{
		String desc;
		Integer amt;
		
		public Entry(String desc, Integer amt){ //it is a title entry if amt is NULL
			this.desc = desc;
			this.amt = amt;
		}
	}

	public String toString(){
		return printOverview() + printSummarySection() + printDetailsSection();
	}
	
	private String printOverview(){
		return "page 1 info"; //TODO
	}
	
	private String printSummarySection(){
		return "Summary Section"; //TODO
	}
	
	private String printDetailsSection(){
		return "Details Section"; //TODO
	}
}
