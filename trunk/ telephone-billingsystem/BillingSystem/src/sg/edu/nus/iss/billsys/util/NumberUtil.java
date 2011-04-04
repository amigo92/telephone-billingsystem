package sg.edu.nus.iss.billsys.util;

/**
 * 
 * @author L Sriragavan
 *
 *This is the utility class contains number related methods.
 */
public class NumberUtil {
	/**
	 * Checks whether string value is a long value.
	 * @param s String
	 * @return Long
	 */
	public static boolean isLong(String s) {
		try {
			Long.parseLong(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
