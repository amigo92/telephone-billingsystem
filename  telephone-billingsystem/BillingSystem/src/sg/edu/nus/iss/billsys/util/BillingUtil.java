package sg.edu.nus.iss.billsys.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BillingUtil {
	private final static DateFormat dateFormat = new SimpleDateFormat("d-MMM-yyyy");
	
	public static Date getDateTime(String strDate) throws ParseException {
	        Date date = dateFormat.parse(strDate);
	        return date;
	    } 
	public static String getDateTimeStr(Date date) {
		 return dateFormat.format(date);
	    }
	
	public static Date getCurrentDate() {
		  Calendar ca1 = Calendar.getInstance();
		  return ca1.getTime();
	    }
	
	
	public static String getCurrentDateStr() {
		 Date date = getCurrentDate();
		 return getDateTimeStr(date);
	    }
	
	public static Date getNextYear() {
		  Calendar ca1 = Calendar.getInstance();
		  ca1.add(ca1.YEAR, 1);
		  return ca1.getTime();
	    }
	public static String getNextYearStr() {	
		  return getDateTimeStr(getNextYear());
	    }
	
}
