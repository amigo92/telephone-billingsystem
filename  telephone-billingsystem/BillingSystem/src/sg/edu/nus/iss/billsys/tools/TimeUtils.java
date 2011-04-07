package sg.edu.nus.iss.billsys.tools;

import java.util.*;
import java.text.*;

import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

import sg.edu.nus.iss.billsys.gui.BillingWindow;

/**
 * To manage time-related methods e.g. formatting
 * @author Xu Guoneng, Veera, Ma Huazhen
 *
 */
public class TimeUtils {
	private final static DateFormat DATE_FORMAT = new SimpleDateFormat("d-MMM-yyyy");
	private final static DateFormat DATE_FORMAT_Display = new SimpleDateFormat("dd-MMM-yyyy");
	private final static DateFormat DATE_FORMAT_LONG = new SimpleDateFormat("d-MMM-yyyy HH:mm:ss");

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
	
	public static String formatDate(Date date){
		return new SimpleDateFormat("dd-MMM-yyyy").format(date);
	}
	
	public static String dateToString(Date date){
		return date!=null?new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date):null;
	}
	
		
	public static Date getDateTime(String strDate) throws ParseException {
	        Date date = DATE_FORMAT.parse(strDate);
	        return date;
	    } 
	public static String getDisplayDateTimeStr(Date date) {
		 if (date == null)
	    	 return "";
	     else
	    	 return DATE_FORMAT_Display.format(date);
    } 
	public static Date getLongDateTime(String strDate) throws ParseException {
        Date date = DATE_FORMAT_LONG.parse(strDate);
        return date;
    	} 
	public static String getDateTimeStr(Date date) {
	     if (date == null)
	    	 return "";
	     else
	    	 return DATE_FORMAT.format(date);
	    }
	public static Date getCurrentDate() {
		  return new Date();
	    }
	public static Date getYesterdayDate() {
		  Calendar ca1 = Calendar.getInstance();
		  ca1.add(Calendar.DATE, -1);
		  return ca1.getTime();
	    }
	public static String getCurrentDateStr() {
		 Date date = getCurrentDate();
		 return getDateTimeStr(date);
	    }
	public static Date getNextYear() {
		  Calendar ca1 = Calendar.getInstance();
		  ca1.add(Calendar.YEAR, 1);
		  return ca1.getTime();
	    }
	public static String getNextYearStr() {	
		  return getDateTimeStr(getNextYear());
	    }
	
	public static MaskFormatter createFormatter(BillingWindow window, String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (java.text.ParseException ex) {
    		JOptionPane.showMessageDialog(window, ex.getMessage());
        }
        return formatter;
    }
}
