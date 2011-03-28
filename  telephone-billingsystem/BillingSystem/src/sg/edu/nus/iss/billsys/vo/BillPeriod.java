package sg.edu.nus.iss.billsys.vo;

import java.io.Serializable;
import java.text.DecimalFormat;
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
	
	public Date getStartTime(){
		return startCal.getTime();
	}
	
	public Date getEndTime(){
		return endCal.getTime();
	}
	
	public boolean isInRange(Date date){
		return (!date.before(getStartTime())) && (!date.after(getEndTime()));
	}
	
	public boolean isOverlapped(Date sDate, Date eDate){
		if(isInRange(sDate) || isInRange(eDate)){
			return true;
		}
		else if(sDate.before(getStartTime()) && eDate.after(getEndTime())){
			return true;
		}
		else{
			return false;
		}
	}
	
	public String getDueDate(){
		Calendar cal = (Calendar)endCal.clone();
		cal.add(Calendar.DATE, 15);
		return TimeUtils.formatDate(cal.getTime());
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
			return this.getStartTime().equals(bp.getStartTime());
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
		return getStartTime().compareTo(bp.getStartTime());
	}
	
	public String toString(){
		return startCal.get(Calendar.YEAR) + new DecimalFormat("00").format(startCal.get(Calendar.MONTH) + 1);
	}

}
