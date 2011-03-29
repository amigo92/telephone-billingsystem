package sg.edu.nus.iss.billsys.util;

import sg.edu.nus.iss.billsys.constant.ComplaintStatus;

public class StringUtil {
	public static boolean isNullOrEmpty(String s) {
		return (s == null || "".equals(s)) ? true : false;
	}
	
	public static String[] getComplaintStatus() {
		String[] array = new String[ComplaintStatus.values().length];
		int i = 0;
		for (ComplaintStatus status : ComplaintStatus.values()) {
			array[i] = status.toString();
			i++;
		}
		System.out.println(">>>>>" + array.toString());
		return array;
	}

}
