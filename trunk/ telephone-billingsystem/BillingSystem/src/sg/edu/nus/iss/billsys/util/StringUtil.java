package sg.edu.nus.iss.billsys.util;

import sg.edu.nus.iss.billsys.constant.ComplaintStatus;
/**
 * 
 * @author L Sriragavan
 *
 *This is the utility class contains String related methods.
 */
public class StringUtil {
	
	/**
	 * Checks whether the given string is empty/ null.
	 * @param s String
	 * @return boolean
	 */
	public static boolean isNullOrEmpty(String s) {
		return (s == null || s.trim().length()==0) ? true : false;
	}
	
	/**
	 * Returns the complaint status option values.
	 * @return String array
	 */
	public static String[] getComplaintStatus() {
		String[] array = new String[ComplaintStatus.values().length];
		int i = 0;
		for (ComplaintStatus status : ComplaintStatus.values()) {
			array[i] = status.toString();
			i++;
		}
		return array;
	}

}
