package sg.edu.nus.iss.billsys.vo;

import java.io.Serializable;
import java.util.*;

import sg.edu.nus.iss.billsys.tools.TimeUtils;

/**
 * 
 * @author Xu Guoneng
 *
 */
public class BillPeriod implements Serializable, Comparable<BillPeriod>{

	private static final long serialVersionUID = -7707485868822025708L;
	
	private Calendar startCal;
	private Calendar endCal;
	
	/**
	 * 
	 * @param year e.g. 2011
	 * @param month e.g. Jan 1, Feb 2....Dec 12
	 */
	public BillPeriod(int year, int month){
		startCal = Calendar.getInstance();
		startCal.clear();
		startCal.set(year, month - 1, 1, 0, 0, 0); //Calendar month starts with 0
		
		endCal = Calendar.getInstance();
		endCal.clear();
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
		Calendar cal = (Calendar)endCal.clone();
		cal.add(Calendar.MONTH, 1);
		return new BillPeriod(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1);
	}
	
	public BillPeriod getPrevBillPeriod(){
		Calendar cal = (Calendar)startCal.clone();
		cal.add(Calendar.MONTH, -1);
		return new BillPeriod(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1);
	}

	/**
	 * @override
	 */
	public boolean equals(Object obj){
		if(obj instanceof BillPeriod){
			BillPeriod bp = (BillPeriod)obj;
			return this.getSatrtTime().equals(bp.getSatrtTime());
		}
		
		return false;
	}
	
	/**
	 * @override
	 */
	public int hashCode(){
		return startCal.hashCode();
	}

	@Override
	public int compareTo(BillPeriod bp) {
		return getSatrtTime().compareTo(bp.getSatrtTime());
	}
	
//	public static void main(String[] args){
//		BillPeriod p = new BillPeriod(2011, 3);
//		System.out.println(p.getSatrtTime());
//		System.out.println(p.getEndTime());
//		System.out.println(p.getBillDate());
	
//	System.out.println(new BillPeriod(2011, 12).getBillDate());
//	System.out.println(new BillPeriod(2011, 12).getSatrtTime());
//	System.out.println(new BillPeriod(2011, 12).getEndTime());
//	System.out.println("..............");
//	
//	System.out.println(new BillPeriod(2011, 12).getNextBillPeriod().getBillDate());
//	System.out.println(new BillPeriod(2011, 12).getNextBillPeriod().getSatrtTime());
//	System.out.println(new BillPeriod(2011, 12).getNextBillPeriod().getEndTime());
//	System.out.println("..............");
//	
//	System.out.println(new BillPeriod(2011, 12).getPrevBillPeriod().getBillDate());
//	System.out.println(new BillPeriod(2011, 12).getPrevBillPeriod().getSatrtTime());
//	System.out.println(new BillPeriod(2011, 12).getPrevBillPeriod().getEndTime());
//	}
}
