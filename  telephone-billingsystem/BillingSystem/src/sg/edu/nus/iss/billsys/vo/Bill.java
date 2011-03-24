package sg.edu.nus.iss.billsys.vo;

import java.io.Serializable;
import java.util.*;
import sg.edu.nus.iss.billsys.tools.TimeUtils;

/**
 * 
 * @author Xu Guoneng
 *
 */
public class Bill implements Serializable{

	private static final long serialVersionUID = -985027259591734266L;
	
	private String billDate;
	private String dueDate;
	
	private CompanyProfile aCompanyProfile;
	private Customer aCustomer;
	private String acctNo;
	
	private int previousBalance;
	private int totalPaymentMade;
	private int totalCurrCharges;
	private int totalGST;
	private int currChargesDue;
	
	private ArrayList<Entry> aPaymentReceivedList;
	private ArrayList<SummaryCharges> aSummaryChargesList;
	private ArrayList<DetailCharges> aDetailChargesList;
	
	public Bill(){
		aPaymentReceivedList = new ArrayList<Entry>();
		aSummaryChargesList = new ArrayList<SummaryCharges>();
		aDetailChargesList = new ArrayList<DetailCharges>();
	}
	
	
	
	public int getTotalCurrCharges() {
		return totalCurrCharges;
	}



	public void setTotalCurrCharges(int totalCurrCharges) {
		this.totalCurrCharges = totalCurrCharges;
	}



	public String getBillDate() {
		return billDate;
	}



	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}



	public String getDueDate() {
		return dueDate;
	}



	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}



	public int getPreviousBalance() {
		return previousBalance;
	}



	public int getTotalPaymentMade() {
		return totalPaymentMade;
	}



	public int getCurrChargesDue() {
		return currChargesDue;
	}



	public int getTotalGST() {
		return totalGST;
	}



	public void addPaymentReceived(Date date, int amt){
		aPaymentReceivedList.add(new Entry(TimeUtils.formatDate(date), amt));
	}

	public ArrayList<Entry> getPaymentReceived(){
		return aPaymentReceivedList;
	}
	
	public void addSummaryCharges(SummaryCharges charges){
		aSummaryChargesList.add(charges);
	}
	
	public ArrayList<SummaryCharges> getSummaryChargesList() {
		return aSummaryChargesList;
	}

	public void addDetailChargesList(DetailCharges charges){
		aDetailChargesList.add(charges);
	}
	
	public ArrayList<DetailCharges> getDetailChargesList() {
		return aDetailChargesList;
	}

	public void setBillDate(Date date) {
		this.billDate = TimeUtils.formatDate(date);
	}

	public void setDueDate(Date date) {
		this.dueDate = TimeUtils.formatDate(date);
	}

	public CompanyProfile getCompanyProfile() {
		return aCompanyProfile;
	}

	public void setaCompanyProfile(CompanyProfile aCompanyProfile) {
		this.aCompanyProfile = aCompanyProfile;
	}

	public void setaCustomer(Customer aCustomer) {
		try{
			this.aCustomer = (Customer)aCustomer.clone();
		}
		catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}

	public Customer getaCustomer() {
		return aCustomer;
	}

	public String getAcctNo() {
		return acctNo;
	}


	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
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


	public class SummaryCharges implements Serializable{

		private static final long serialVersionUID = 7027261213227363033L;
		
		String desc;
		ArrayList<Entry> entries;
		int totalAmt;

		public SummaryCharges(){
			entries = new ArrayList<Entry>();
		}
		
		public void addEntry(Entry en){
			entries.add(en);
		}
		
		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public ArrayList<Entry> getEntries() {
			return entries;
		}

		public void setEntries(ArrayList<Entry> entries) {
			this.entries = entries;
		}

		public int getTotalAmt() {
			return totalAmt;
		}

		public void setTotalAmt(int totalAmt) {
			this.totalAmt = totalAmt;
		}
	}
	
	public class DetailCharges implements Serializable{

		private static final long serialVersionUID = -247095117835378059L;
		
		String desc;
		ArrayList<Entry> entries;
		int totalAmt;
		
		public DetailCharges() {
			this.entries = new ArrayList<Entry>();
		}
		
		public void addEntry(Entry en){
			entries.add(en);
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public ArrayList<Entry> getEntries() {
			return entries;
		}

		public void setEntries(ArrayList<Entry> entries) {
			this.entries = entries;
		}

		public int getTotalAmt() {
			return totalAmt;
		}

		public void setTotalAmt(int totalAmt) {
			this.totalAmt = totalAmt;
		}
		
	}
	
	public class Entry implements Serializable{

		private static final long serialVersionUID = 6300446223978632204L;
		
		String desc;
		Integer amt;
		
		public Entry(String desc, Integer amt){ //it is a title entry if amt is NULL
			this.desc = desc;
			this.amt = amt;
		}

		public String getDesc() {
			return desc;
		}

		public Integer getAmt() {
			return amt;
		}
	}

	public String toString(){
		return new TextBill(80,70,this).toString();
	}
}
