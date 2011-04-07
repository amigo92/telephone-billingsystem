package sg.edu.nus.iss.billsys.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

import sg.edu.nus.iss.billsys.gui.BillingWindow;

public class BillingUtil {
	private final static DateFormat DATE_FORMAT = new SimpleDateFormat("d-MMM-yyyy");
	private final static DateFormat DATE_FORMAT_LONG = new SimpleDateFormat("d-MMM-yyyy HH:mm:ss");

	
	public static Date getDateTime(String strDate) throws ParseException {
	        Date date = DATE_FORMAT.parse(strDate);
	        return date;
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
		  Calendar ca1 = Calendar.getInstance();
		  return ca1.getTime();
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
