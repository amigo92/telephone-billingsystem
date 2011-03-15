package sg.edu.nus.iss.billsys.tools;

import java.text.*;

/**
 * 
 * @author Xu Guoneng
 *
 */
public class FinanceUtils {

	public static double getGSTRate(){
		return 0.07;
	}
	
	public static String formatCentToDollar(int cents){
		return new DecimalFormat("0.00").format(cents/100.00);
	}
}
