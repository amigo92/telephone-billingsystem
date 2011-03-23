package sg.edu.nus.iss.billsys.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

/**
 * 
 * @author Lem Kian Hoa (Stephen)
 *
 */

public class TextBill {

	private static final long serialVersionUID = -6635386157246128910L;
	private static final char SPACE = ' ';
	private int pageColumn;
	private int pageRow;
	private Bill bill;
	private ArrayList<String> rows = new ArrayList<String>();
	
	public TextBill(int pageColumn, int pageRow, Bill bill) {
		this.pageColumn = pageColumn;
		this.pageRow = pageRow;
		this.bill = bill;
	}
	
	private int fillSpace(ArrayList<StringBuffer> block, int length, boolean center) {
		for (StringBuffer sb : block) {
			if (sb.length() > length) {
				length = sb.length();
			}
		}
		for (StringBuffer sb : block) {
			if (sb.length() < length) {
				int count = length-sb.length();
				char[] fill = new char[count];
				Arrays.fill(fill, SPACE);
				if (!center) {
					sb.append(fill);
				} else {
					int i = count/2;
					sb.insert(0,fill, 0, i);
					sb.append(fill, i, count-i);
				}
			}
		}
		return length;
	}
	
	private ArrayList<StringBuffer> mergeBlock(ArrayList<StringBuffer> blockL, ArrayList<StringBuffer> blockR) {
		ArrayList<StringBuffer> blockM = new ArrayList<StringBuffer>();
		Iterator<StringBuffer> iL = blockL.iterator();
		Iterator<StringBuffer> iR = blockR.iterator();
		while(iL != null && iR != null &&
				iL.hasNext() && iR.hasNext()) {
			StringBuffer sb = new StringBuffer();
			sb.append(iL.next());
			sb.append(iR.next());
			blockM.add(sb);
		}
		return blockM;
	}

	public ArrayList<StringBuffer> printCompanyInfo() {
		CompanyProfile profile = bill.getCompanyProfile();
		if (profile == null)
			return null;
		ArrayList<StringBuffer> companyInfoR = new ArrayList<StringBuffer>();
		ArrayList<StringBuffer> companyInfoL = new ArrayList<StringBuffer>();

		String s = profile.getAlias();
		if (s != null && s.length() > 0) {
			companyInfoL.add(new StringBuffer(s));
		}
		s = profile.getCompanyName();
		if (s != null && s.length() > 0) {
			companyInfoR.add(new StringBuffer(s));
		}
		s = profile.getStreet();
		if (s != null && s.length() > 0) {
			companyInfoL.add(new StringBuffer());
			StringBuffer sb = new StringBuffer();
			sb.append(s);
			sb.append(',');
			companyInfoR.add(sb);
		}
		s = profile.getUnit();
		if (s != null && s.length() > 0) {
			companyInfoL.add(new StringBuffer());
			StringBuffer sb = new StringBuffer();
			sb.append(s);
			sb.append(',');
			companyInfoR.add(sb);
		}
		s = profile.getCountry();
		if (s != null && s.length() > 0) {
			companyInfoL.add(new StringBuffer());
			StringBuffer sb = new StringBuffer();
			sb.append(s);
			s = profile.getPostalCd();
			if (s != null && s.length() > 0) {
				sb.append(' ');
				sb.append(s);
			}
			companyInfoR.add(sb);
		}
		s = profile.getHotline();
		if (s != null && s.length() > 0) {
			companyInfoL.add(new StringBuffer());
			StringBuffer sb = new StringBuffer();
			sb.append("Call ");
			sb.append(s);
			companyInfoR.add(sb);
		}
		s = profile.getEmail();
		if (s != null && s.length() > 0) {
			companyInfoL.add(new StringBuffer());
			StringBuffer sb = new StringBuffer();
			sb.append("Email: ");
			sb.append(s);
			companyInfoR.add(sb);
		}
		companyInfoL.add(new StringBuffer());
		companyInfoR.add(new StringBuffer());
		
		int len = fillSpace(companyInfoR,0,false);
		fillSpace(companyInfoL,pageColumn-len,false);
		return mergeBlock(companyInfoL,companyInfoR);
	}
	
	public ArrayList<StringBuffer> printCustomerInfo() {
		Customer cust = bill.getaCustomer();
		ArrayList<StringBuffer> custInfo = new ArrayList<StringBuffer>();

		String s = cust.getName();
		if (s != null && s.length() > 0) {
			custInfo.add(new StringBuffer(s));
		}
		s = cust.getAddress1();
		if (s != null && s.length() > 0) {
			custInfo.add(new StringBuffer(s));
		}
		s = cust.getAddress2();
		if (s != null && s.length() > 0) {
			custInfo.add(new StringBuffer(s));
		}
		s = cust.getAddress3();
		if (s != null && s.length() > 0) {
			custInfo.add(new StringBuffer(s));
		}
		custInfo.add(new StringBuffer());
		fillSpace(custInfo,pageColumn,false);
		return custInfo;
	}
	
	public ArrayList<StringBuffer> printBillInfo() {
		ArrayList<StringBuffer> billInfo = new ArrayList<StringBuffer>();

		String s = bill.getAcctNo();
		if (s != null && s.length() > 0) {
			StringBuffer sb = new StringBuffer();
			sb.append("Account No: ");
			sb.append(s);
			billInfo.add(sb);
		}
		s = bill.getBillDate();
		if (s != null && s.length() > 0) {
			StringBuffer sb = new StringBuffer();
			sb.append("Bill date: ");
			sb.append(s);
			billInfo.add(sb);
		}
		s = bill.getDueDate();
		if (s != null && s.length() > 0) {
			StringBuffer sb = new StringBuffer();
			sb.append("Due Date: ");
			sb.append(s);
			billInfo.add(sb);
		}
		billInfo.add(new StringBuffer());
		fillSpace(billInfo,pageColumn,true);
		return billInfo;
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
		
		TextBill printer = new TextBill(80,70,bill);
		ArrayList<StringBuffer> list = printer.printCompanyInfo();
		for (StringBuffer sb : list) {
			System.out.println(sb);
		}
		list = printer.printCustomerInfo();
		for (StringBuffer sb : list) {
			System.out.println(sb);
		}
		list = printer.printBillInfo();
		for (StringBuffer sb : list) {
			System.out.println(sb);
		}
	}
}
