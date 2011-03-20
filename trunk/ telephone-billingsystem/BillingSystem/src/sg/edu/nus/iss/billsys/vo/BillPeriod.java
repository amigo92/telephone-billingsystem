package sg.edu.nus.iss.billsys.vo;

import java.util.*;

import sg.edu.nus.iss.billsys.tools.TimeUtils;

/**
 * 
 * @author Xu Guoneng
 *
 */
public class BillPeriod {

	int year;
	int month;
	
	Calendar startCal;
	Calendar endCal;
	
	/**
	 * 
	 * @param year e.g. 2011
	 * @param month e.g. Jan 1, Feb 2....Dec 12
	 */
	public BillPeriod(int year, int month){
		this.year = year;
		this.month = month - 1; //Calendar month starts with 0
		
		startCal = Calendar.getInstance();
		startCal.set(year, this.month, 1, 0, 0, 0);
		
		endCal = Calendar.getInstance();
		endCal.set(year, month, 1, 0, 0, 0);
		endCal.add(Calendar.SECOND, -1);
	}
	
	public Date getSatrtTime(){
		return startCal.getTime();
	}
	
	public Date getEndTime(){
		return endCal.getTime();
	}
	
	public String getDueDate(){
		return TimeUtils.formatDate(TimeUtils.addDays(getEndTime(), 15));
	}
	
	public String getBillDate(){
		return TimeUtils.formatDate(getEndTime());
	}
	
//	public static void main(String[] args){
//		BillPeriod p = new BillPeriod(2011, 3);
//		System.out.println(p.getSatrtTime());
//		System.out.println(p.getEndTime());
//		System.out.println(p.getBillDate());
//	}
}
