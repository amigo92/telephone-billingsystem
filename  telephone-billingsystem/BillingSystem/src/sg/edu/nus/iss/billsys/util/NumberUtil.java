package sg.edu.nus.iss.billsys.util;

public class NumberUtil {
	public static boolean isLong(String s) {
		try {
			Long.parseLong(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
