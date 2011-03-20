package sg.edu.nus.iss.billsys.tools;

import java.util.*;
import java.text.*;

/**
 * 
 * @author Xu Guoneng
 *
 */
public class TimeUtils {

	/**
	 * 
	 * @param dateTime e.g. 2010-02-18 18:10:01
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String dateTime) throws ParseException{
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(dateTime);
	}
	
	public static Date addDays(Date oDate, int days){
		Calendar cal = Calendar.getInstance(); 
		cal.add(Calendar.DATE, days); 
		return cal.getTime();
	}
	
	public static int getYear(Date date){
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		return now.get(Calendar.YEAR);
	}
	
	public static int getMonth(Date date){
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		return now.get(Calendar.MONTH);
	}
	
	public static int getDay(Date date){
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		return now.get(Calendar.DATE);
	}
	
	public static boolean equalsYearMonth(Date d1, Date d2){
		if(getYear(d1) == getYear(d2) && getMonth(d1) == getMonth(d2)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static String formatDate(Date date){
		return new SimpleDateFormat("dd-MMM-yyyy").format(date);
	}
}
