package sg.edu.nus.iss.billsys.vo;

import java.io.Serializable;
import java.util.*;

import sg.edu.nus.iss.billsys.tools.TimeUtils;

/**
 * 
 * @author Xu Guoneng
 *
 */
public class BillPeriod implements Serializable{

	private static final long serialVersionUID = -7707485868822025708L;
	
	private int year;
	private int month;
	
	private Calendar startCal;
	private Calendar endCal;
	
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
	
	public boolean isInRange(Date date){
		return (!date.before(getSatrtTime())) && (!date.after(getEndTime()));
	}
	
	public boolean isOverlapped(Date sDate, Date eDate){
		if(isInRange(sDate) || isInRange(eDate)){
			return true;
		}
		else if(sDate.before(getSatrtTime()) && eDate.after(getEndTime())){
			return true;
		}
		else{
			return false;
		}
	}
	
	public String getDueDate(){
		return TimeUtils.formatDate(TimeUtils.addDays(getEndTime(), 15));
	}
	
	public String getBillDate(){
		return TimeUtils.formatDate(getEndTime());
	}
	
	public BillPeriod getNextBillPeriod(){
		return new BillPeriod(year, month+2);
	}
	
	public BillPeriod getPrevBillPeriod(){
		return new BillPeriod(year, month);
	}
	
	/**
	 * @override
	 */
	public boolean equals(Object obj){
		if(obj instanceof BillPeriod){
			BillPeriod bp = (BillPeriod)obj;
			return year == bp.year && month == bp.month;
		}
		
		return false;
	}
	
	/**
	 * @override
	 */
	public int hashCode(){
		return Integer.parseInt("" + month + year);
	}
	
//	public static void main(String[] args){
//		BillPeriod p = new BillPeriod(2011, 3);
//		System.out.println(p.getSatrtTime());
//		System.out.println(p.getEndTime());
//		System.out.println(p.getBillDate());
//	}
}
