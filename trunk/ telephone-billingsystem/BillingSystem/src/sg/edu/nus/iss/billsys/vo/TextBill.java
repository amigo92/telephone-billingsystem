package sg.edu.nus.iss.billsys.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

import sg.edu.nus.iss.billsys.tools.FinanceUtils;
import sg.edu.nus.iss.billsys.vo.Bill.Entry;
import sg.edu.nus.iss.billsys.vo.Bill.SummaryCharges;

/**
 * 
 * @author Lem Kian Hoa (Stephen)
 *
 */

public class TextBill {

	private static final long serialVersionUID = -6635386157246128910L;
	private static final char SPACE = ' ';
	private static final char DOT = '.';
	private static final char COMMA = ',';
	private static final char DOLLAR = '$';
	private static final char HYPHEN = '-';
	
	private int pageColumn;
	private int pageRow;
	private Bill bill;
	private ArrayList<String> rows = new ArrayList<String>();
	
	private class TextItem {
		StringBuffer text;
		char c;
		
		TextItem(char c) {
			this.text = new StringBuffer();
			this.c = c;
		}
		
		TextItem(String s, char c) {
			this.text = new StringBuffer(s);
			this.c = c;
		}
	}
	
	public TextBill(int pageColumn, int pageRow, Bill bill) {
		this.pageColumn = pageColumn;
		this.pageRow = pageRow;
		this.bill = bill;
	}
	
	private int fillItems(ArrayList<TextItem> items, int length, boolean center) {
		for (TextItem item : items) {
			if (item.text.length() > length) {
				length = item.text.length();
			}
		}
		for (TextItem item : items) {
			if (item.text.length() < length) {
				int count = length-item.text.length();
				char[] fill = new char[count];
				Arrays.fill(fill, item.c);
				if (!center) {
					item.text.append(fill);
				} else {
					int i = count/2;
					item.text.insert(0,fill, 0, i);
					item.text.append(fill, i, count-i);
				}
			}
		}
		return length;
	}
	
	private ArrayList<TextItem> mergeItems(ArrayList<TextItem> itemsL, ArrayList<TextItem> itemsR) {
		ArrayList<TextItem> itemsM = new ArrayList<TextItem>();
		Iterator<TextItem> iL = itemsL.iterator();
		Iterator<TextItem> iR = itemsR.iterator();
		while(iL != null && iR != null &&
				iL.hasNext() && iR.hasNext()) {
			TextItem left = iL.next();
			TextItem right = iR.next();
			TextItem item = new TextItem(left.c);
			item.text.append(left.text);
			item.text.append(right.text);
			itemsM.add(item);
		}
		return itemsM;
	}

	public ArrayList<TextItem> printCompanyInfo() {
		CompanyProfile profile = bill.getCompanyProfile();
		if (profile == null)
			return null;
		ArrayList<TextItem> companyInfoL = new ArrayList<TextItem>();
		ArrayList<TextItem> companyInfoR = new ArrayList<TextItem>();

		String alias = profile.getAlias();
		String name = profile.getCompanyName();
		if (alias != null || name != null) {
			companyInfoL.add(new TextItem(alias,SPACE));
			companyInfoR.add(new TextItem(name,SPACE));
		}
		String s = profile.getStreet();
		if (s != null && s.length() > 0) {
			companyInfoL.add(new TextItem(SPACE));
			TextItem item = new TextItem(SPACE);
			item.text.append(s);
			item.text.append(COMMA);
			companyInfoR.add(item);
		}
		s = profile.getUnit();
		if (s != null && s.length() > 0) {
			companyInfoL.add(new TextItem(SPACE));
			TextItem item = new TextItem(SPACE);
			item.text.append(s);
			item.text.append(COMMA);
			companyInfoR.add(item);
		}
		s = profile.getCountry();
		if (s != null && s.length() > 0) {
			companyInfoL.add(new TextItem(SPACE));
			TextItem item = new TextItem(SPACE);
			item.text.append(s);
			s = profile.getPostalCd();
			if (s != null && s.length() > 0) {
				item.text.append(SPACE);
				item.text.append(s);
			}
			companyInfoR.add(item);
		}
		s = profile.getHotline();
		if (s != null && s.length() > 0) {
			companyInfoL.add(new TextItem(SPACE));
			TextItem item = new TextItem(SPACE);
			item.text.append("Call ");
			item.text.append(s);
			companyInfoR.add(item);
		}
		s = profile.getEmail();
		if (s != null && s.length() > 0) {
			companyInfoL.add(new TextItem(SPACE));
			TextItem item = new TextItem(SPACE);
			item.text.append("Email: ");
			item.text.append(s);
			companyInfoR.add(item);
		}
		companyInfoL.add(new TextItem(SPACE));
		companyInfoR.add(new TextItem(SPACE));
		
		int len = fillItems(companyInfoR,0,false);
		fillItems(companyInfoL,pageColumn-len,false);
		return mergeItems(companyInfoL,companyInfoR);
	}
	
	public ArrayList<TextItem> printCustomerInfo() {
		Customer cust = bill.getaCustomer();
		ArrayList<TextItem> custInfo = new ArrayList<TextItem>();

		String s = cust.getName();
		if (s != null && s.length() > 0) {
			custInfo.add(new TextItem(s,SPACE));
		}
		s = cust.getAddress1();
		if (s != null && s.length() > 0) {
			custInfo.add(new TextItem(s,SPACE));
		}
		s = cust.getAddress2();
		if (s != null && s.length() > 0) {
			custInfo.add(new TextItem(s,SPACE));
		}
		s = cust.getAddress3();
		if (s != null && s.length() > 0) {
			custInfo.add(new TextItem(s,SPACE));
		}
		custInfo.add(new TextItem(SPACE));
		fillItems(custInfo,pageColumn,false);
		return custInfo;
	}
	
	public ArrayList<TextItem> printBillInfo() {
		ArrayList<TextItem> billInfo = new ArrayList<TextItem>();

		String s = bill.getAcctNo();
		if (s != null && s.length() > 0) {
			TextItem item = new TextItem(SPACE);
			item.text.append("Account No: ");
			item.text.append(s);
			billInfo.add(item);
		}
		s = bill.getBillDate();
		if (s != null && s.length() > 0) {
			TextItem item = new TextItem(SPACE);
			item.text.append("Bill date: ");
			item.text.append(s);
			billInfo.add(item);
		}
		s = bill.getDueDate();
		if (s != null && s.length() > 0) {
			TextItem item = new TextItem(SPACE);
			item.text.append("Due Date: ");
			item.text.append(s);
			billInfo.add(item);
		}
		billInfo.add(new TextItem(SPACE));
		fillItems(billInfo,pageColumn,true);
		return billInfo;
	}
	
	public ArrayList<TextItem> printBillHeader() {
		ArrayList<TextItem> billHdrL = new ArrayList<TextItem>();
		ArrayList<TextItem> billHdrR = new ArrayList<TextItem>();

		String alias = null;
		CompanyProfile profile = bill.getCompanyProfile();
		if (profile != null) {
			alias = profile.getAlias();
		}
		String s = bill.getAcctNo();
		if (s != null && s.length() > 0) {
			billHdrL.add(new TextItem(alias,SPACE));
			TextItem item = new TextItem(SPACE);
			item.text.append("Account No: ");
			item.text.append(s);
			billHdrR.add(item);
		}
		s = bill.getBillDate();
		if (s != null && s.length() > 0) {
			billHdrL.add(new TextItem(SPACE));
			TextItem item = new TextItem(SPACE);
			item.text.append("Bill date: ");
			item.text.append(s);
			billHdrR.add(item);
		}
		billHdrL.add(new TextItem(SPACE));
		billHdrR.add(new TextItem(SPACE));
		int len = fillItems(billHdrR,0,false);
		fillItems(billHdrL,pageColumn-len,false);
		return mergeItems(billHdrL,billHdrR);
	}
	
	public ArrayList<TextItem> printBalanceInfo() {
		ArrayList<TextItem> balanceInfoL = new ArrayList<TextItem>();
		ArrayList<TextItem> balanceInfoR = new ArrayList<TextItem>();

		balanceInfoL.add(new TextItem("At a Glance",SPACE));
		balanceInfoR.add(new TextItem(SPACE));

		balanceInfoL.add(new TextItem(SPACE));
		balanceInfoR.add(new TextItem(SPACE));

		String s = FinanceUtils.formatCentToDollar(bill.getPreviousBalance());		
		balanceInfoL.add(new TextItem("Previous Balance",DOT));
		TextItem item = new TextItem(SPACE);
		item.text.append(DOT);
		item.text.append(DOLLAR);
		item.text.append(s);
		balanceInfoR.add(item);

		s = FinanceUtils.formatCentToDollar(bill.getTotalPaymentMade());
		balanceInfoL.add(new TextItem("Payments",DOT));
		item = new TextItem(SPACE);
		item.text.append(HYPHEN);
		item.text.append(DOLLAR);
		item.text.append(s);
		balanceInfoR.add(item);

		balanceInfoL.add(new TextItem(SPACE));
		balanceInfoR.add(new TextItem(SPACE));

		s = FinanceUtils.formatCentToDollar(bill.getCurrChargesDue());		
		balanceInfoL.add(new TextItem("Current Charges Due on "+bill.getDueDate(),DOT));
		item = new TextItem(SPACE);
		item.text.append(DOT);
		item.text.append(DOLLAR);
		item.text.append(s);
		balanceInfoR.add(item);

		balanceInfoL.add(new TextItem(SPACE));
		balanceInfoR.add(new TextItem(SPACE));

		s = FinanceUtils.formatCentToDollar(bill.getTotalCurrCharges());
		balanceInfoL.add(new TextItem("Please pay",DOT));
		item = new TextItem(SPACE);
		item.text.append(DOT);
		item.text.append(DOLLAR);
		item.text.append(s);
		balanceInfoR.add(item);

		balanceInfoL.add(new TextItem(SPACE));
		balanceInfoR.add(new TextItem(SPACE));
		
		int len = fillItems(balanceInfoL,pageColumn/2,false);
		fillItems(balanceInfoR,pageColumn-len,false);
		return mergeItems(balanceInfoL,balanceInfoR);
	}
	
	public ArrayList<TextItem> printAccountSummary() {
		ArrayList<TextItem> L1 = new ArrayList<TextItem>();
		ArrayList<TextItem> L2 = new ArrayList<TextItem>();
		ArrayList<TextItem> R1 = new ArrayList<TextItem>();
		ArrayList<TextItem> R2 = new ArrayList<TextItem>();

		L1.add(new TextItem(SPACE));
		L2.add(new TextItem(SPACE));
		R1.add(new TextItem("Amount (S$)",SPACE));
		R2.add(new TextItem("Total (S$)",SPACE));
		
		L1.add(new TextItem(HYPHEN));
		L2.add(new TextItem(HYPHEN));
		R1.add(new TextItem(HYPHEN));
		R2.add(new TextItem(HYPHEN));

		L1.add(new TextItem("Summary - Payment Details",SPACE));
		L2.add(new TextItem(SPACE));
		R1.add(new TextItem(SPACE));
		R2.add(new TextItem(SPACE));
		
		L1.add(new TextItem(HYPHEN));
		L2.add(new TextItem(HYPHEN));
		R1.add(new TextItem(HYPHEN));
		R2.add(new TextItem(HYPHEN));

		int count = 0;
		TextItem totalPayment = null;
		ArrayList<Entry> list = bill.getPaymentReceived();
		for (Entry e : list) {
			count++;
			L1.add(new TextItem("Payment received",SPACE));
			L2.add(new TextItem(e.getDesc(),SPACE));
			R1.add(new TextItem(FinanceUtils.formatCentToDollar(e.getAmt()),SPACE));
			R2.add(totalPayment = new TextItem(SPACE));
		}
		if (count == 1 && totalPayment != null) {
			totalPayment.text.append(FinanceUtils.formatCentToDollar(bill.getTotalPaymentMade()));
		} else {
			L1.add(new TextItem(SPACE));
			L2.add(new TextItem(SPACE));
			R1.add(new TextItem(SPACE));
			R2.add(new TextItem(FinanceUtils.formatCentToDollar(bill.getTotalPaymentMade()),SPACE));
		}
		
		L1.add(new TextItem(SPACE));
		L2.add(new TextItem(SPACE));
		R1.add(new TextItem(SPACE));
		R2.add(new TextItem(SPACE));
		
		L1.add(new TextItem(HYPHEN));
		L2.add(new TextItem(HYPHEN));
		R1.add(new TextItem(HYPHEN));
		R2.add(new TextItem(HYPHEN));

		L1.add(new TextItem("Summary Current Charges",SPACE));
		L2.add(new TextItem(SPACE));
		R1.add(new TextItem(SPACE));
		R2.add(new TextItem(SPACE));
		
		L1.add(new TextItem(HYPHEN));
		L2.add(new TextItem(HYPHEN));
		R1.add(new TextItem(HYPHEN));
		R2.add(new TextItem(HYPHEN));
		
		ArrayList<SummaryCharges> charges = bill.getSummaryChargesList();
		for (SummaryCharges sc : charges) {			
			L1.add(new TextItem(SPACE));
			L2.add(new TextItem(SPACE));
			R1.add(new TextItem(SPACE));
			R2.add(new TextItem(SPACE));
					
			L1.add(new TextItem(sc.getDesc(),SPACE));
			L2.add(new TextItem(SPACE));
			R1.add(new TextItem(SPACE));
			R2.add(new TextItem(SPACE));
					
			ArrayList<Entry> entries = sc.getEntries();
			for (Entry e : entries) {			
				L1.add(new TextItem("   "+e.getDesc(),SPACE));
				L2.add(new TextItem(SPACE));
				R1.add(new TextItem(FinanceUtils.formatCentToDollar(e.getAmt()),SPACE));
				R2.add(new TextItem(SPACE));
			}
			
			L1.add(new TextItem(SPACE));
			L2.add(new TextItem(SPACE));
			R1.add(new TextItem(SPACE));
			R2.add(new TextItem(FinanceUtils.formatCentToDollar(sc.getTotalAmt()),SPACE));
		}

		L1.add(new TextItem(SPACE));
		L2.add(new TextItem(SPACE));
		R1.add(new TextItem(SPACE));
		R2.add(new TextItem(SPACE));
		
		L1.add(new TextItem("Total GST",SPACE));
		L2.add(new TextItem(SPACE));
		R1.add(new TextItem(SPACE));
		R2.add(new TextItem(FinanceUtils.formatCentToDollar(bill.getTotalGST()),SPACE));
		
		L1.add(new TextItem("Total Current Charges",SPACE));
		L2.add(new TextItem(SPACE));
		R1.add(new TextItem(SPACE));
		R2.add(new TextItem(FinanceUtils.formatCentToDollar(bill.getTotalCurrCharges()),SPACE));

		L1.add(new TextItem(SPACE));
		L2.add(new TextItem(SPACE));
		R1.add(new TextItem(SPACE));
		R2.add(new TextItem(SPACE));

		int lenL = pageColumn*2/3;
		int lenL1 = lenL/2;
		int lenR = pageColumn-lenL;
		int lenR1 = lenR/2;
		
		int len = fillItems(L1,lenL1,false);
		fillItems(L2,lenL-len,false);
		ArrayList<TextItem> L = mergeItems(L1,L2);
		
		len = fillItems(R1,lenR1,false);
		fillItems(R2,lenR-len,false);
		ArrayList<TextItem> R = mergeItems(R1,R2);
		
		return mergeItems(L,R);
	}
	
	public ArrayList<TextItem> printAccountDetails() {
		ArrayList<TextItem> L1 = new ArrayList<TextItem>();
		ArrayList<TextItem> L2 = new ArrayList<TextItem>();
		ArrayList<TextItem> R1 = new ArrayList<TextItem>();
		ArrayList<TextItem> R2 = new ArrayList<TextItem>();

		L1.add(new TextItem(SPACE));
		L2.add(new TextItem(SPACE));
		R1.add(new TextItem("Amount (S$)",SPACE));
		R2.add(new TextItem("Total (S$)",SPACE));
		
		L1.add(new TextItem(HYPHEN));
		L2.add(new TextItem(HYPHEN));
		R1.add(new TextItem(HYPHEN));
		R2.add(new TextItem(HYPHEN));

		L1.add(new TextItem("Summary - Payment Details",SPACE));
		L2.add(new TextItem(SPACE));
		R1.add(new TextItem(SPACE));
		R2.add(new TextItem(SPACE));
		
		L1.add(new TextItem(HYPHEN));
		L2.add(new TextItem(HYPHEN));
		R1.add(new TextItem(HYPHEN));
		R2.add(new TextItem(HYPHEN));

		int count = 0;
		TextItem totalPayment = null;
		ArrayList<Entry> list = bill.getPaymentReceived();
		for (Entry e : list) {
			count++;
			L1.add(new TextItem("Payment received",SPACE));
			L2.add(new TextItem(e.getDesc(),SPACE));
			R1.add(new TextItem(FinanceUtils.formatCentToDollar(e.getAmt()),SPACE));
			R2.add(totalPayment = new TextItem(SPACE));
		}
		if (count == 1 && totalPayment != null) {
			totalPayment.text.append(FinanceUtils.formatCentToDollar(bill.getTotalPaymentMade()));
		} else {
			L1.add(new TextItem(SPACE));
			L2.add(new TextItem(SPACE));
			R1.add(new TextItem(SPACE));
			R2.add(new TextItem(FinanceUtils.formatCentToDollar(bill.getTotalPaymentMade()),SPACE));
		}
		
		L1.add(new TextItem(SPACE));
		L2.add(new TextItem(SPACE));
		R1.add(new TextItem(SPACE));
		R2.add(new TextItem(SPACE));
		
		L1.add(new TextItem(HYPHEN));
		L2.add(new TextItem(HYPHEN));
		R1.add(new TextItem(HYPHEN));
		R2.add(new TextItem(HYPHEN));

		L1.add(new TextItem("Summary Current Charges",SPACE));
		L2.add(new TextItem(SPACE));
		R1.add(new TextItem(SPACE));
		R2.add(new TextItem(SPACE));
		
		L1.add(new TextItem(HYPHEN));
		L2.add(new TextItem(HYPHEN));
		R1.add(new TextItem(HYPHEN));
		R2.add(new TextItem(HYPHEN));
		
		ArrayList<SummaryCharges> charges = bill.getSummaryChargesList();
		for (SummaryCharges sc : charges) {			
			L1.add(new TextItem(SPACE));
			L2.add(new TextItem(SPACE));
			R1.add(new TextItem(SPACE));
			R2.add(new TextItem(SPACE));
					
			L1.add(new TextItem(sc.getDesc(),SPACE));
			L2.add(new TextItem(SPACE));
			R1.add(new TextItem(SPACE));
			R2.add(new TextItem(SPACE));
					
			ArrayList<Entry> entries = sc.getEntries();
			for (Entry e : entries) {			
				L1.add(new TextItem(e.getDesc(),SPACE));
				L2.add(new TextItem(SPACE));
				R1.add(new TextItem(FinanceUtils.formatCentToDollar(e.getAmt()),SPACE));
				R2.add(new TextItem(SPACE));
			}
			
			L1.add(new TextItem(SPACE));
			L2.add(new TextItem(SPACE));
			R1.add(new TextItem(SPACE));
			R2.add(new TextItem(FinanceUtils.formatCentToDollar(sc.getTotalAmt()),SPACE));
		}

		L1.add(new TextItem(SPACE));
		L2.add(new TextItem(SPACE));
		R1.add(new TextItem(SPACE));
		R2.add(new TextItem(SPACE));
		
		L1.add(new TextItem("Total GST",SPACE));
		L2.add(new TextItem(SPACE));
		R1.add(new TextItem(SPACE));
		R2.add(new TextItem(FinanceUtils.formatCentToDollar(bill.getTotalGST()),SPACE));
		
		L1.add(new TextItem("Total Current Charges",SPACE));
		L2.add(new TextItem(SPACE));
		R1.add(new TextItem(SPACE));
		R2.add(new TextItem(FinanceUtils.formatCentToDollar(bill.getTotalCurrCharges()),SPACE));

		L1.add(new TextItem(SPACE));
		L2.add(new TextItem(SPACE));
		R1.add(new TextItem(SPACE));
		R2.add(new TextItem(SPACE));

		int lenL = pageColumn*2/3;
		int lenL1 = lenL/2;
		int lenR = pageColumn-lenL;
		int lenR1 = lenR/2;
		
		int len = fillItems(L1,lenL1,false);
		fillItems(L2,lenL-len,false);
		ArrayList<TextItem> L = mergeItems(L1,L2);
		
		len = fillItems(R1,lenR1,false);
		fillItems(R2,lenR-len,false);
		ArrayList<TextItem> R = mergeItems(R1,R2);
		
		return mergeItems(L,R);
	}
	
	public ArrayList<TextItem> printSummaryCharges() {
		ArrayList<SummaryCharges> list = bill.getSummaryChargesList();
		for (SummaryCharges sc : list) {
			sc.getDesc();
			sc.getTotalAmt();
			ArrayList<Entry> entries = sc.getEntries();
			for (Entry e : entries) {
				e.getDesc();
				e.getAmt();
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		CompanyProfile profile = new CompanyProfile();
		profile.setAlias("One#");
		profile.setCompanyName("One# Pte Ltd");
		profile.setStreet("41 Uni Lane");
		profile.setUnit("#02-003");
		profile.setCountry("Singapore");
		profile.setPostalCd("654092");
		profile.setHotline("1654");
		profile.setEmail("customer@onehash.com");

		Customer cust = new Customer();
		cust.setName("Mohamed Ali Khan");
		cust.setAddress1("35, Heng Mui Keng Towers");
		cust.setAddress2("Clemence Avenue 1");
		cust.setAddress3("Singapore 356908");
		
		Bill bill = new Bill();
		bill.setaCompanyProfile(profile);
		bill.setaCustomer(cust);
		bill.setAcctNo("SA-2010-02-10");
		bill.setBillDate(new Date());
		bill.setDueDate(new Date());
		bill.setPreviousBalance(20080);
		bill.setTotalPaymentMade(20080);
		bill.setCurrChargesDue(32346);
		bill.setTotalCurrCharges(32346);
		bill.setTotalGST(2116);
		bill.addPaymentReceived(new Date(), 10080);
		bill.addPaymentReceived(new Date(), 10000);

		SummaryCharges charges = bill.new SummaryCharges();
		charges.setDesc("Digital Voice");
		charges.setTotalAmt(2730);
		charges.addEntry(bill.new Entry("Subscription charges",1500));
		charges.addEntry(bill.new Entry("Usage charges",1230));
		bill.addSummaryCharges(charges);

		charges = bill.new SummaryCharges();
		charges.setDesc("Mobile Voice");
		charges.setTotalAmt(20450);
		charges.addEntry(bill.new Entry("Subscription charges",13420));
		charges.addEntry(bill.new Entry("Usage charges",7030));
		bill.addSummaryCharges(charges);

		charges = bill.new SummaryCharges();
		charges.setDesc("Cable TV");
		charges.setTotalAmt(5050);
		charges.addEntry(bill.new Entry("Subscription charges",2020));
		charges.addEntry(bill.new Entry("Usage charges",7070));
		bill.addSummaryCharges(charges);
		
		TextBill printer = new TextBill(80,70,bill);
		ArrayList<TextItem> list = printer.printCompanyInfo();
		for (TextItem item : list) {
			System.out.println(item.text);
		}
		list = printer.printCustomerInfo();
		for (TextItem item : list) {
			System.out.println(item.text);
		}
		list = printer.printBillInfo();
		for (TextItem item : list) {
			System.out.println(item.text);
		}
		list = printer.printBalanceInfo();
		for (TextItem item : list) {
			System.out.println(item.text);
		}
		list = printer.printBillHeader();
		for (TextItem item : list) {
			System.out.println(item.text);
		}
		list = printer.printAccountSummary();
		for (TextItem item : list) {
			System.out.println(item.text);
		}
	}
}
