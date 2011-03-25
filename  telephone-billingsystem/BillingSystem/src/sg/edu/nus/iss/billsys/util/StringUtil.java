package sg.edu.nus.iss.billsys.util;

public class StringUtil {
	public static boolean isNullOrEmpty(String s) {
		return (s == null || "".equals(s)) ? true : false;
	}
}
