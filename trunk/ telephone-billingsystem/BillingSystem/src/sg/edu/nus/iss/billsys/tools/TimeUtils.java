package sg.edu.nus.iss.billsys.tools;

import java.util.*;
import java.text.*;

/**
 * To manage time-related methods e.g. formatting
 * @author Xu Guoneng, Veera
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
	
	public static String formatDate(Date date){
		return new SimpleDateFormat("dd-MMM-yyyy").format(date);
	}
	
	public static String dateToString(Date date){
		return date!=null?new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date):null;
	}
}
